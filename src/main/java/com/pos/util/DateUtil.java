package com.pos.util;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.Stroke;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

public class DateUtil
  extends JPanel
{
  private static final long serialVersionUID = -5384012731547358720L;
  private static Random r = new Random();
  private Calendar calendar;
  private Calendar now = Calendar.getInstance();
  private JPanel calendarPanel;
  private Font font = new Font("Times",
    0, 12);
  private SimpleDateFormat sdf;
  private final LabelManager lm = new LabelManager();

  private Popup pop;
  private TitlePanel titlePanel;
  private BodyPanel bodyPanel;
  private FooterPanel footerPanel;
  private JComponent showDate;
  private boolean isShow = false;
  private static final String DEFAULTFORMAT = "yyyy-MM-dd";
  private static final String[] showTEXT = { "Sun", "Mon", "Tue", "Wed",
    "Thu", "Fri", "Sat" };
  private static WeekLabel[] weekLabels = new WeekLabel[7];
  private static int defaultStartDAY = 0;

  private static Color hoverColor = Color.BLUE;

  private DateUtil(Date date, String format, int startDAY) {
    if ((startDAY > -1) && (startDAY < 7))
      defaultStartDAY = startDAY;
    int dayIndex = defaultStartDAY;
    for (int i = 0; i < 7; i++) {
      if (dayIndex > 6)
        dayIndex = 0;
      weekLabels[i] = new WeekLabel(dayIndex, showTEXT[dayIndex]);
      dayIndex++;
    }
    this.sdf = new SimpleDateFormat(format);
    this.calendar = Calendar.getInstance();
    this.calendar.setTime(date);
    initCalendarPanel();
  }

  public static DateUtil getInstance(Date date, String format) {
    return new DateUtil(date, format, defaultStartDAY);
  }

  public static DateUtil getInstance(Date date) {
    return getInstance(date, "yyyy-MM-dd");
  }

  public static DateUtil getInstance(String format) {
    return getInstance(new Date(), format);
  }

  public static DateUtil getInstance() {
    return getInstance(new Date(), "yyyy-MM-dd");
  }

  private void initCalendarPanel() {
    this.calendarPanel = new JPanel(new BorderLayout());
    this.calendarPanel.setBorder(
      BorderFactory.createLineBorder(new Color(170, 170, 170)));
    this.calendarPanel.add(this.titlePanel = new TitlePanel(),
      "North");
    this.calendarPanel.add(this.bodyPanel = new BodyPanel(),
      "Center");
    this.calendarPanel.add(this.footerPanel = new FooterPanel(this.showDate),
      "South");
    addAncestorListener(new AncestorListener()
    {
      public void ancestorAdded(AncestorEvent event) {}

      public void ancestorRemoved(AncestorEvent event) {
        DateUtil.this.hidePanel();
      }

      public void ancestorMoved(AncestorEvent event)
      {
        DateUtil.this.hidePanel();
      }
    });
  }

  public void register(final JComponent showComponent) {
    this.showDate = showComponent;
    showComponent.setRequestFocusEnabled(true);
    showComponent.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent me) {
        showComponent.requestFocusInWindow();
      }
    });
    add(showComponent, "Center");
    setPreferredSize(new Dimension(90, 25));
    setBorder(BorderFactory.createLineBorder(Color.GRAY));
    showComponent.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent me) {
        if (showComponent.isEnabled()) {
          showComponent.setCursor(new Cursor(12));
        }
      }

      public void mouseExited(MouseEvent me) {
        if (showComponent.isEnabled()) {
          showComponent.setCursor(new Cursor(0));
          showComponent.setForeground(Color.BLACK);
        }
      }

      public void mousePressed(MouseEvent me) {
        if (showComponent.isEnabled()) {
          showComponent.setForeground(DateUtil.hoverColor);
          if (DateUtil.this.isShow) {
            DateUtil.this.hidePanel();
          } else {
            DateUtil.this.showPanel(showComponent);
          }
        }
      }

      public void mouseReleased(MouseEvent me) {
        if (showComponent.isEnabled()) {
          showComponent.setForeground(Color.BLACK);
        }
      }
    });
    showComponent.addFocusListener(new FocusListener() {
      public void focusLost(FocusEvent e) {
        DateUtil.this.hidePanel();
      }


      public void focusGained(FocusEvent e) {}
    });
  }

  private void hidePanel()
  {
    if (this.pop != null) {
      this.isShow = false;
      this.pop.hide();
      this.pop = null;
    }
  }

  private void showPanel(Component owner)
  {
    if (this.pop != null)
      this.pop.hide();
    Point show = new Point(0, this.showDate.getHeight());
    SwingUtilities.convertPointToScreen(show, this.showDate);
    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    int x = show.x;
    int y = show.y;
    if (x < 0)
      x = 0;
    if (x > size.width - 212)
      x = size.width - 212;
    if (y > size.height - 167)
      y -= 165;
    this.pop = PopupFactory.getSharedInstance().getPopup(owner, this.calendarPanel,
      x, y);
    this.pop.show();
    this.isShow = true;
  }

  private void commit()
  {
    if ((this.showDate instanceof JTextField)) {
      ((JTextField)this.showDate).setText(this.sdf.format(this.calendar.getTime()));
    } else if ((this.showDate instanceof JLabel)) {
      ((JLabel)this.showDate).setText(this.sdf.format(this.calendar.getTime()));
    }
    hidePanel();
  }

  private class TitlePanel extends JPanel { private static final long serialVersionUID = -2865282186037420798L;
    private JLabel preYear;
    private JLabel preMonth;
    private JLabel center;
    private JLabel nextMonth;
    private JLabel nextYear;
    private JLabel centercontainer;

    public TitlePanel() { super();
      setBackground(new Color(190, 200, 200));
      initTitlePanel();
    }

    private void initTitlePanel() {
      this.preYear = new JLabel("<<", 0);
      this.preMonth = new JLabel("<", 0);
      this.center = new JLabel("", 0);
      this.centercontainer = new JLabel("", 0);
      this.nextMonth = new JLabel(">", 0);
      this.nextYear = new JLabel(">>", 0);

      this.preYear.setToolTipText("Last Year");
      this.preMonth.setToolTipText("Last Month");
      this.nextMonth.setToolTipText("Next Month");
      this.nextYear.setToolTipText("Next Year");

      this.preYear.setBorder(BorderFactory.createEmptyBorder(2,
        10, 0, 0));
      this.preMonth.setBorder(BorderFactory.createEmptyBorder(2,
        15, 0, 0));
      this.nextMonth.setBorder(BorderFactory.createEmptyBorder(2,
        0, 0, 15));
      this.nextYear.setBorder(BorderFactory.createEmptyBorder(2,
        0, 0, 10));

      this.centercontainer.setLayout(new BorderLayout());
      this.centercontainer.add(this.preMonth, "West");
      this.centercontainer.add(this.center, "Center");
      this.centercontainer.add(this.nextMonth, "East");

      add(this.preYear, "West");
      add(this.centercontainer, "Center");
      add(this.nextYear, "East");
      setPreferredSize(new Dimension(210, 25));

      updateDate();

      this.preYear.addMouseListener(new MyMouseAdapter(this.preYear, 1,
        -1));
      this.preMonth.addMouseListener(new MyMouseAdapter(this.preMonth,
        2, -1));
      this.nextMonth.addMouseListener(new MyMouseAdapter(this.nextMonth,
        2, 1));
      this.nextYear.addMouseListener(new MyMouseAdapter(this.nextYear,
        1, 1));
    }

    private void updateDate() {
      this.center.setText(DateUtil.this.calendar.get(1) + "-" + (
        DateUtil.this.calendar.get(2) + 1));
    }

    class MyMouseAdapter extends MouseAdapter
    {
      JLabel label;
      private int type;
      private int value;

      public MyMouseAdapter(JLabel label, int type, int value)
      {
        this.label = label;
        this.type = type;
        this.value = value;
      }

      public void mouseEntered(MouseEvent me) {
        this.label.setCursor(new Cursor(12));
        this.label.setForeground(DateUtil.hoverColor);
      }

      public void mouseExited(MouseEvent me) {
        this.label.setCursor(new Cursor(
          0));
        this.label.setForeground(Color.BLACK);
      }

      public void mousePressed(MouseEvent me) {
        DateUtil.this.calendar.add(this.type, this.value);
        this.label.setForeground(Color.WHITE);
        DateUtil.this.refresh();
      }

      public void mouseReleased(MouseEvent me) {
        this.label.setForeground(Color.BLACK);
      }
    }
  }

  private class BodyPanel extends JPanel
  {
    private static final long serialVersionUID = 5677718768457235447L;

    public BodyPanel()
    {
      super();
      setPreferredSize(new Dimension(210, 140));
      initMonthPanel();
    }

    private void initMonthPanel() {
      updateDate();
    }

    public void updateDate() {
      removeAll();
      DateUtil.this.lm.clear();
      Date temp = DateUtil.this.calendar.getTime();
      Calendar cal = Calendar.getInstance();
      cal.setTime(temp);
      cal.set(5, 1);

      int index = cal.get(7);


      if (index > DateUtil.defaultStartDAY) {
        cal.add(5, -index + DateUtil.defaultStartDAY);
      } else
        cal.add(5, -index + DateUtil.defaultStartDAY - 7);
      DateUtil.WeekLabel[] arrayOfWeekLabel;
      int j = (arrayOfWeekLabel = DateUtil.weekLabels).length; for (int i = 0; i < j; i++) { DateUtil.WeekLabel weekLabel = arrayOfWeekLabel[i];
        add(weekLabel);
      }
      for (int i = 0; i < 42; i++) {
        cal.add(5, 1);
        DateUtil.this.lm.addLabel(new DateUtil.DayLabel(cal));
      }
      for (DateUtil.DayLabel my : DateUtil.this.lm.getLabels()) {
        add(my);
      }
    }
  }

  private class FooterPanel extends JPanel
  {
    private static final long serialVersionUID = 8135037333899746736L;
    private JLabel eliminate;

    public FooterPanel(JComponent showComponent) {
      super();
      initFooterPanel(showComponent);
    }

    private void initFooterPanel(JComponent showComponent) {
      this.eliminate = new JLabel("清除");
      this.eliminate.addMouseListener(new MouseListener()
      {
        public void mouseReleased(MouseEvent e) {}


        public void mousePressed(MouseEvent e)
        {
          if ((DateUtil.this.showDate instanceof JTextField)) {
            ((JTextField)DateUtil.this.showDate).setText("");
          } else if ((DateUtil.this.showDate instanceof JLabel)) {
            ((JLabel)DateUtil.this.showDate).setText("");
          }
          DateUtil.this.hidePanel();
        }



        public void mouseExited(MouseEvent e) {}


        public void mouseEntered(MouseEvent e) {}


        public void mouseClicked(MouseEvent e)
        {
          if ((DateUtil.this.showDate instanceof JTextField)) {
            ((JTextField)DateUtil.this.showDate).setText("");
          } else if ((DateUtil.this.showDate instanceof JLabel)) {
            ((JLabel)DateUtil.this.showDate).setText("");
          }
          DateUtil.this.hidePanel();
        }
      });
      add(this.eliminate, "Center");
    }


    public void updateDate() {}
  }

  private void refresh()
  {
    this.titlePanel.updateDate();
    this.bodyPanel.updateDate();
    this.footerPanel.updateDate();
    SwingUtilities.updateComponentTreeUI(this);
  }

  private class WeekLabel extends JLabel
  {
    private static final long serialVersionUID = -8053965084432740110L;
    private String name;

    public WeekLabel(int index, String name) {
      super();
//      super(name,0);
      this.name = name;
    }

    public String toString() {
      return this.name;
    }
  }

  private class DayLabel extends JLabel implements Comparator<DayLabel>, MouseListener, MouseMotionListener
  {
    private static final long serialVersionUID = -6002103678554799020L;
    private boolean isSelected;
    private int year;
    private int month;
    private int day;

    public DayLabel(Calendar cal) {
      super();
      this.year = cal.get(1);
      this.month = cal.get(2);
      this.day = cal.get(5);

      setFont(DateUtil.this.font);
      addMouseListener(this);
      addMouseMotionListener(this);
      if (this.month == DateUtil.this.calendar.get(2)) {
        setForeground(Color.BLACK);
      } else {
        setForeground(Color.LIGHT_GRAY);
      }
    }

    public boolean getIsSelected() {
      return this.isSelected;
    }

    public void setSelected(boolean b, boolean isDrag) {
      this.isSelected = b;
      if ((b) && (!isDrag)) {
        int temp = DateUtil.this.calendar.get(2);
        DateUtil.this.calendar.set(this.year, this.month, this.day);
        if (temp == this.month) {
          SwingUtilities.updateComponentTreeUI(DateUtil.this.bodyPanel);
        } else {
          DateUtil.this.refresh();
        }
        repaint();
      }
    }


    protected void paintComponent(Graphics g)
    {
      if ((this.day == DateUtil.this.calendar.get(5)) &&
        (this.month == DateUtil.this.calendar.get(2))) {
        g.setColor(new Color(187, 191, 218));
        g.fillRect(0, 0, getWidth(), getHeight());
      }

      if ((this.year == DateUtil.this.now.get(1)) &&
        (this.month == DateUtil.this.now.get(2)) &&
        (this.day == DateUtil.this.now.get(5))) {
        Graphics2D gd = (Graphics2D)g;
        gd.setColor(new Color(85, 85, 136));
        Polygon p = new Polygon();
        p.addPoint(0, 0);
        p.addPoint(getWidth() - 1, 0);
        p.addPoint(getWidth() - 1, getHeight() - 1);
        p.addPoint(0, getHeight() - 1);
        gd.drawPolygon(p);
      }
      if (this.isSelected) {
        Stroke s = new BasicStroke(1.0F, 2,
          2, 1.0F,
          new float[] { 2.0F, 2.0F }, 1.0F);
        Graphics2D gd = (Graphics2D)g;
        gd.setStroke(s);
        gd.setColor(Color.BLACK);
        Polygon p = new Polygon();
        p.addPoint(0, 0);
        p.addPoint(getWidth() - 1, 0);
        p.addPoint(getWidth() - 1, getHeight() - 1);
        p.addPoint(0, getHeight() - 1);
        gd.drawPolygon(p);
      }
      super.paintComponent(g);
    }

    public boolean contains(Point p) {
      return getBounds().contains(p);
    }

    private void update() {
      repaint();
    }



    public void mouseDragged(MouseEvent e) {}



    public void mouseMoved(MouseEvent e) {}


    public void mouseClicked(MouseEvent e) {}


    public void mousePressed(MouseEvent e)
    {
      this.isSelected = true;
      update();
    }

    public void mouseReleased(MouseEvent e)
    {
      Point p =
        SwingUtilities.convertPoint(this, e.getPoint(), DateUtil.this.bodyPanel);
      setForeground(Color.BLACK);
      DateUtil.this.lm.setSelect(p, false);
      DateUtil.this.commit();
    }


    public void mouseEntered(MouseEvent e)
    {
      setForeground(DateUtil.hoverColor);
      repaint();
    }


    public void mouseExited(MouseEvent e)
    {
      if (this.month == DateUtil.this.calendar.get(2)) {
        setForeground(Color.BLACK);
      } else
        setForeground(Color.LIGHT_GRAY);
      repaint();
    }

    public int compare(DayLabel o1, DayLabel o2)
    {
      Calendar c1 = Calendar.getInstance();
      c1.set(o1.year, o1.month, o1.day);
      Calendar c2 = Calendar.getInstance();
      c2.set(o2.year, o2.month, o2.day);
      return c1.compareTo(c2);
    }
  }

  private class LabelManager
  {
    private List<DateUtil.DayLabel> list;

    public LabelManager() {
      this.list = new ArrayList();
    }

    public List<DateUtil.DayLabel> getLabels() {
      return this.list;
    }

    public void addLabel(DateUtil.DayLabel label) {
      this.list.add(label);
    }

    public void clear() {
      this.list.clear();
    }

    public void setSelect(Point p, boolean b) {
      DateUtil.DayLabel lab;
      if (b)
      {

        boolean findPrevious = false;boolean findNext = false;
        for (Iterator localIterator = this.list.iterator(); localIterator.hasNext();) { lab = (DateUtil.DayLabel)localIterator.next();
          if (lab.contains(p)) {
            findNext = true;
            if (lab.getIsSelected()) {
              findPrevious = true;
            } else
              lab.setSelected(true, b);
          } else if (lab.getIsSelected()) {
            findPrevious = true;
            lab.setSelected(false, b);
          }
          if ((findPrevious) && (findNext))
            return;
        }
      } else {
        DateUtil.DayLabel temp = null;
        for (DateUtil.DayLabel m : this.list) {
          if (m.contains(p)) {
            temp = m;
          } else if (m.getIsSelected()) {
            m.setSelected(false, b);
          }
        }
        if (temp != null) {
          temp.setSelected(true, b);
        }
      }
    }
  }

  public static String getDate1(String paramDate) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");


    int i = 0;
    do {
      i = r.nextInt(120);
    } while ((i < 90) || (i > 120));





    Calendar calendar = Calendar.getInstance();
    try {
      calendar.setTime(sdf.parse(paramDate));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    calendar.add(13, i);
    sdf.applyPattern("yyyy/MM/dd HH:mm:ss");
    return sdf.format(calendar.getTime());
  }












  public static String getDateStr(String paramDateStr, String flag, String shopName)
  {
    SimpleDateFormat sdf = new SimpleDateFormat();
    if ("1".equals(flag)) {
      if (("常州明都酒店".equals(shopName)) || ("常州新城希尔顿酒店".equals(shopName)) ||
        ("海澜之家（关河东路店）".equals(shopName)) || ("环洲绿岛咖啡延陵店".equals(shopName)) || ("常州泰富百货集团有限公司".equals(shopName))) {
        sdf.applyPattern("yyyy/MM/dd HH:mm:ss");
        try {
          Date d = sdf.parse(paramDateStr);
          sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
          return sdf.format(d);
        }
        catch (ParseException e) {
          e.printStackTrace();
        }
      } else if (("传奇人生音乐酒吧".equals(shopName)) || ("家乐福(乐家邹区店)".equals(shopName))) {
        sdf.applyPattern("yyyy/MM/dd HH:mm:ss");
        try {
          Date d = sdf.parse(paramDateStr);
          sdf.applyPattern("yyyy-MM-dd   HH:mm:ss");
          return sdf.format(d);
        }
        catch (ParseException e) {
          e.printStackTrace();
        }
      } else if ("大润发（常州店）".equals(shopName)) {
        sdf.applyPattern("yyyy/MM/dd HH:mm:ss");
        try {
          Date d = sdf.parse(paramDateStr);
          sdf.applyPattern("yyyy年MM月dd日 HH:mm");
          return sdf.format(d);
        }
        catch (ParseException e) {
          e.printStackTrace();
        }
      }
    } else if (("2".equals(flag)) &&
      ("传奇人生音乐酒吧".equals(shopName))) {
      sdf.applyPattern("yyyy/MM/dd HH:mm:ss");
      try {
        Date d = sdf.parse(paramDateStr);
        sdf.applyPattern("yyyy-MM-dd   HH:mm:ss");
        return sdf.format(d);
      }
      catch (ParseException e) {
        e.printStackTrace();
      }
    }

    return "";
  }

  public static String getDate(String dateStr, String typeName)
  {
    StringBuffer sb = new StringBuffer(dateStr + " ");

    if ("饭店".equals(typeName)) {
      String[] hh = { "12", "13", "14", "15", "16", "17", "18", "19",
        "20", "21", "22", "23" };
      String hour = hh[r.nextInt(hh.length)];
      sb.append(hour + ":");
      int i = 0;

      if (("12".equals(hour)) || ("23".equals(hour)))
      {
        do {
          if (("12".equals(hour)) && ((i = r.nextInt(60)) > 30)) {
            sb.append(i);
            break;
          } } while ((!"23".equals(hour)) || ((i = r.nextInt(30)) >= 30));
        sb.append(i < 10 ? "0" + i : Integer.valueOf(i));

      }
      else
      {

        i = r.nextInt(60);
        sb.append(i < 10 ? "0" + i : Integer.valueOf(i));
      }

      i = r.nextInt(60);
      sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
    } else if ("油站".equals(typeName)) {
      String[] hh = { "00", "01", "02", "03", "04", "05", "06", "07",
        "08", "09", "20", "11", "12", "13", "14", "15", "16", "17",
        "18", "19", "20", "21", "22", "23" };
      String hour = hh[r.nextInt(hh.length)];
      sb.append(hour);
      int i = 0;
      i = r.nextInt(60);
      sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
      i = r.nextInt(60);
      sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
    } else if ("酒吧".equals(typeName)) {
      String[] hh = { "00", "01", "02", "03", "04", "05", "19", "20",
        "21", "22", "23" };
      String hour = hh[r.nextInt(hh.length)];
      sb.append(hour);
      int i = 0;
      if ("05".equals(hour)) {
        sb.append(":00:00");
      } else {
        i = r.nextInt(60);
        sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
        i = r.nextInt(60);
        sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
      }
    } else if (("超市".equals(typeName)) || ("金店".equals(typeName)) || ("服装".equals(typeName))) {
      String[] hh = { "08", "09", "20", "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20" };
      String hour = hh[r.nextInt(hh.length)];
      sb.append(hour);
      int i = 0;
      if ("20".equals(hour)) {
        i = r.nextInt(30);
        sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
        i = r.nextInt(60);
        sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
      } else {
        i = r.nextInt(60);
        sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
        i = r.nextInt(60);
        sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
      }
    } else if ("咖啡".equals(typeName)) {
      String[] hh = { "08", "09", "20", "11", "12", "13", "14", "15",
        "16", "17", "18", "19", "20", "21", "22" };
      String hour = hh[r.nextInt(hh.length)];
      sb.append(hour);
      int i = 0;
      if ("22".equals(hour)) {
        i = r.nextInt(30);
        sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
        i = r.nextInt(60);
        sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
      } else {
        i = r.nextInt(60);
        sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
        i = r.nextInt(60);
        sb.append(":").append(i < 10 ? "0" + i : Integer.valueOf(i));
      }
    }

    return sb.toString();
  }
}
