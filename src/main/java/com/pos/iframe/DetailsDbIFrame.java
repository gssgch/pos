package com.pos.iframe;

import com.pos.db.BaseDao;
import com.pos.dboperation.DetailsDbOpertionAction;
import com.pos.table.CheckHeaderCellRender;
import com.pos.table.TableModleProxy;
import com.pos.util.ComboBoxUtil;
import com.pos.vo.Item;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class DetailsDbIFrame extends JInternalFrame
{
  private String region;
  private String sql;
  private JScrollPane scrollPane_1;
  private JScrollPane js;
  private JTable table;
  private Object[][] data;
  private JLabel pageLabel;
  private TableModleProxy tableModel;
  private JButton colseBtn;
  private JTextField currentPageJf;
  private JButton previousPage;
  private JButton nextPage;
  private JComboBox navigationComp;
  private JComboBox shopTypeComp;
  private JTextField detailsName;
  private JTextField navigationJF2;
  private JTextField typeJF2;
  
  public DetailsDbIFrame(String region)
  {
    super(region);
    this.region = region;
    setIconifiable(true);
    setClosable(true);
    setBounds(30, 30, 720, 600);
    
    JPanel panel_1 = new JPanel();
    panel_1.setLayout(new BorderLayout());
    
    JPanel panel = new JPanel();
    this.scrollPane_1 = new JScrollPane();
    this.scrollPane_1.setPreferredSize(new Dimension(450, 200));
    panel_1.add(this.scrollPane_1);
    this.scrollPane_1.setViewportView(panel);
    
    addJpanelObject(panel);
    add(panel_1);
    setVisible(true);
  }
  
  public void addJpanelObject(JPanel panel)
  {
    panel.setLayout(null);
    
    JLabel label1 = new JLabel(
      "--查询条件----------------------------------------------------------------------------------");
    label1.setBounds(20, 10, 600, 20);
    panel.add(label1);
    

    JLabel navigationName = new JLabel("地区:");
    navigationName.setBounds(20, 30, 60, 20);
    

    this.navigationComp = new JComboBox();
    new ComboBoxUtil()
      .setComboBox(this.navigationComp, "navigationComp", "", "");
    this.navigationComp.setBounds(50, 30, 80, 20);
    panel.add(navigationName);
    panel.add(this.navigationComp);
    this.navigationJF2 = new JTextField(
      ((Item)this.navigationComp.getSelectedItem()).getKey());
    this.navigationComp.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        String navigation1 = ((Item)DetailsDbIFrame.this.navigationComp.getSelectedItem())
          .getKey();
        String navigation2 = DetailsDbIFrame.this.navigationJF2.getText();
        if (!navigation2.equals(navigation1)) {
          DetailsDbIFrame.this.initLabel("init");
          DetailsDbIFrame.this.navigationJF2.setText(navigation1);
        }
        
      }
      
    });
    JLabel shopTypeL = new JLabel("商品类型：");
    shopTypeL.setBounds(150, 30, 80, 20);
    
    this.shopTypeComp = new JComboBox();
    new ComboBoxUtil().setComboBox(this.shopTypeComp, "shopTypeComp", 
      ((Item)this.navigationComp.getSelectedItem()).getKey(), "");
    this.shopTypeComp.setBounds(205, 30, 60, 20);
    this.typeJF2 = new JTextField(
      ((Item)this.shopTypeComp.getSelectedItem()).getKey());
    
    this.shopTypeComp.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        String shopType1 = ((Item)DetailsDbIFrame.this.shopTypeComp.getSelectedItem())
          .getKey();
        String shopType2 = DetailsDbIFrame.this.typeJF2.getText();
        if (!shopType2.equals(shopType1)) {
          DetailsDbIFrame.this.initLabel("init");
          DetailsDbIFrame.this.typeJF2.setText(shopType1);
        }
        
      }
    });
    panel.add(this.navigationJF2);
    panel.add(this.typeJF2);
    
    panel.add(shopTypeL);
    panel.add(this.shopTypeComp);
    

    this.detailsName = new JTextField();
    JLabel detailsL = new JLabel("商品名称:");
    this.detailsName.setBounds(340, 30, 130, 20);
    detailsL.setBounds(285, 30, 80, 20);
    
    panel.add(this.detailsName);
    panel.add(detailsL);
    

    JButton queryBtn = new JButton("查询");
    queryBtn.setBounds(480, 30, 60, 20);
    panel.add(queryBtn);
    queryBtn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        DetailsDbIFrame.this.initLabel("query");
        DetailsDbIFrame.this.resetTableMap();
      }
    });
    JLabel label2 = new JLabel(
      "----查询结果集----------------------------------------------------------------------------------");
    label2.setBounds(20, 55, 600, 20);
    panel.add(label2);
    
    JPanel jp = new JPanel();
    jp.setBounds(20, 100, 680, 325);
    jp.setLayout(new BorderLayout());
    
    JScrollPane jsp = new JScrollPane();
    jsp.setPreferredSize(new Dimension(500, 0));
    jp.add(jsp);
    
    this.table = new JTable();
    this.table.setSize(800, 800);
    
    jsp.setViewportView(this.table);
    this.table.setAutoscrolls(true);
    this.table.setRowHeight(15);
    
    this.table.setAutoResizeMode(0);
    this.table.updateUI();
    panel.add(jp);
    

    this.previousPage = new JButton("上一页");
    this.previousPage.setBounds(20, 80, 70, 20);
    panel.add(this.previousPage);
    this.previousPage.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        DetailsDbIFrame.this.initLabel("previousPage");
        DetailsDbIFrame.this.resetTableMap();
      }
      

    });
    this.nextPage = new JButton("下一页");
    this.nextPage.setBounds(100, 80, 70, 20);
    panel.add(this.nextPage);
    this.nextPage.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        DetailsDbIFrame.this.initLabel("nextPage");
        DetailsDbIFrame.this.resetTableMap();

      }
      

    });
    JButton insertBtn = new JButton("添加");
    insertBtn.setBounds(180, 80, 70, 20);
    insertBtn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        Map data = new HashMap();
        data.put("navigationId", 
          ((Item)DetailsDbIFrame.this.navigationComp.getSelectedItem()).getKey());
        data.put("shopId", 
          ((Item)DetailsDbIFrame.this.shopTypeComp.getSelectedItem()).getKey());
        DetailsDbOpertionAction.showDialog(DetailsDbIFrame.this, data, 
          "insert");
        DetailsDbIFrame.this.initLabel("insert");
      }
    });
    panel.add(insertBtn);
    

    JButton updateBtn = new JButton("修改");
    updateBtn.setBounds(260, 80, 70, 20);
    updateBtn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        Map m = DetailsDbIFrame.this.tableModel.getDataMap();
        Map data = new HashMap();
        data.put("update", m);
        data.put("navigationId", 
          ((Item)DetailsDbIFrame.this.navigationComp.getSelectedItem()).getKey());
        data.put("shopId", 
          ((Item)DetailsDbIFrame.this.shopTypeComp.getSelectedItem()).getKey());
        if ((m == null) || (m.size() == 0)) {
          JOptionPane.showMessageDialog(null, "请选择一条记录！");
          return; }
        if (m.size() > 1) {
          JOptionPane.showMessageDialog(null, "修改时，只能选择一条记录！");
          return;
        }
        try {
          DetailsDbOpertionAction.showDialog(DetailsDbIFrame.this, data, 
            "update");
        } catch (Exception e1) {
          e1.printStackTrace();
          JOptionPane.showMessageDialog(null, "数据异常！");
        }
        DetailsDbIFrame.this.initLabel("update");
      }
    });
    panel.add(updateBtn);
    

    JButton deleteBtn = new JButton("删除");
    deleteBtn.setBounds(340, 80, 70, 20);
    deleteBtn.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        String str = DetailsDbIFrame.this.dbOperation("delete");
        if (!"".equals(str)) {
          JOptionPane.showMessageDialog(null, str);
        } else {
          JOptionPane.showMessageDialog(null, "数据删除成功！");
        }
        DetailsDbIFrame.this.initLabel("delete");
      }
    });
    panel.add(deleteBtn);
    

    this.currentPageJf = new JTextField();
    this.currentPageJf.setBounds(420, 80, 70, 20);
    this.currentPageJf.setVisible(false);
    panel.add(this.currentPageJf);
    

    this.pageLabel = new JLabel();
    panel.add(this.pageLabel);
    

    this.colseBtn = new JButton();
    this.colseBtn.setText("关闭");
    this.colseBtn.addActionListener(new CloseActionListener());
    panel.add(this.colseBtn);
    
    panel.setPreferredSize(new Dimension(720, 600));
    initLabel("init");
  }
  
  public void initLabel(String flag) {
    int page = (this.currentPageJf.getText() == null) || 
      ("".equals(this.currentPageJf.getText())) ? 1 : 
      Integer.parseInt(this.currentPageJf.getText());
    
    int pageCount = 0;
    int dbCount = getDbCount(this.detailsName.getText(), 
      ((Item)this.shopTypeComp.getSelectedItem()).getKey()).intValue();
    

    if (dbCount % 20 == 0) {
      pageCount = dbCount / 20;
    } else {
      pageCount = dbCount / 20 + 1;
    }
    
    if (("query".equals(flag)) || ("insert".equals(flag)) || 
      ("update".equals(flag)) || ("delete".equals(flag)) || 
      ("init".equals(flag))) {
      page = 1;
    } else if ("previousPage".equals(flag)) {
      if (page > 1) {
        page--;
      }
    } else if (("nextPage".equals(flag)) && 
      (page < pageCount)) {
      page++;
    }
    


    initTable(page);
    
    if (pageCount == 1) {
      this.nextPage.setEnabled(false);
      this.previousPage.setEnabled(false);
    } else if (page == 1) {
      this.nextPage.setEnabled(true);
      this.previousPage.setEnabled(false);
    } else if ((page > 1) && (page < pageCount)) {
      this.nextPage.setEnabled(true);
      this.previousPage.setEnabled(true);
    } else {
      this.nextPage.setEnabled(false);
      this.previousPage.setEnabled(true);
    }
    String str = "总条数：" + dbCount + "，每页显示20条，当前第" + page + "页，总共：" + 
      pageCount + "页";
    this.pageLabel.setText(str);
    this.pageLabel.setBounds(20, 425, str.getBytes().length * 6, 20);
    this.colseBtn.setBounds(30 + str.getBytes().length * 6, 425, 70, 20);
    this.currentPageJf.setText(page+"");
  }
  





  public void initTable(int currentPage)
  {
    String[] title = { "复选框", "商品名称", "商品价格", "主键", "商品单位" };
    this.data = getData(title, currentPage);
    int[] length = new int[title.length];
    
    if (this.data == null) {
      this.data = new Object[0][0];
    } else {
      length[0] = 50;
      for (int i = 1; i < this.data.length; i++) {
        long temp = 0L;
        for (int j = 1; j < this.data[i].length; j++) {
          int oldCol = 0;
          int newCol = 0;
          if (i == 0) {
            if ((this.data[i][j] != null) && 
              (!"".equals(this.data[i][j].toString()))) {
              length[j] = (this.data[i][j].toString().getBytes().length * 8);
            } else {
              length[j] = 50;
            }
          }
          else if ((this.data[i][j] != null) && 
            (!"".equals(this.data[i][j].toString()))) {
            oldCol = length[j];
            newCol = this.data[i][j].toString().getBytes().length * 8;
            if (oldCol < newCol) {
              length[j] = newCol;
            }
          }
        }
      }
    }
    

    this.tableModel = new TableModleProxy(title, this.data);
    this.table.setModel(this.tableModel);
    TableColumnModel columnModel = this.table.getColumnModel();
    for (int t = 1; t < length.length; t++) {
      if (length[t] < 100) {
        columnModel.getColumn(t).setPreferredWidth(100);
      } else {
        columnModel.getColumn(t).setPreferredWidth(length[t]);
      }
    }
    
    columnModel.getColumn(3).setMinWidth(0);
    columnModel.getColumn(3).setMaxWidth(0);
    columnModel.getColumn(3).setWidth(0);
    columnModel.getColumn(3).setPreferredWidth(0);
    
    this.table.getTableHeader().setDefaultRenderer(
      new CheckHeaderCellRender(this.table));
    this.table.updateUI();
  }
  
  public Object[][] getData(String[] title, int currentPage) {
    Object[][] obj = null;
    this.sql = "select DETAILS_ID,details_name,details_price,details_until from details where 1=1 ";
    
    if ((this.detailsName.getText() != null) && (!"".equals(this.detailsName.getText()))) {
      this.sql = 
        (this.sql + " and details_name like '%" + this.detailsName.getText() + "%' ");
    }
    

    if ((((Item)this.shopTypeComp.getSelectedItem()).getKey() != null) && 
      (!"".equals(((Item)this.shopTypeComp.getSelectedItem()).getKey()))) {
      this.sql = 
        (this.sql + " and shop_type_id ='" + ((Item)this.shopTypeComp.getSelectedItem()).getKey() + "' ");
    }
    this.sql = 
      (this.sql + " order by DETAILS_ID asc  limit " + (currentPage - 1) * 20 + ",20");
    try
    {
      List<Map> list = new BaseDao().queryDbBySql(this.sql);
      if ((list != null) && (list.size() > 0)) {
        obj = new Object[list.size()][5];
        Map m = null;
        for (int j = 0; j < list.size(); j++) {
          m = (Map)list.get(j);
          obj[j][0] = new Boolean(false);
          obj[j][1] = m.get("DETAILS_NAME");
          obj[j][2] = m.get("DETAILS_PRICE");
          obj[j][3] = m.get("DETAILS_ID");
          obj[j][4] = m.get("DETAILS_UNTIL");
        }
      }
    }
    catch (SQLException e) {
      e.printStackTrace();
    }
    return obj;
  }
  




  public String dbOperation(String flag)
  {
    String sql = "";
    Map m = this.tableModel.getDataMap();
    StringBuffer sb = new StringBuffer();
    String str = "";
    if ("delete".equals(flag)) {
      if (m.size() == 0) {
        str = "请选择一条记录";
      } else {
        java.util.Set<String> ss = m.keySet();
        int i = 0;
        for (String s : ss) {
          Object[] o = (Object[])m.get(s);
          if (i == 0) {
            sb.append("'" + o[3] + "'");
          } else {
            sb.append(",'" + o[3] + "'");
          }
          i++;
        }
        sql = 
          "delete from DETAILS where DETAILS_ID in(" + sb.toString() + ")";
        new BaseDao().deleteDbBySql(sql);
      }
    }
    return str;
  }
  
  public Integer getDbCount(String detailsName, String shopType) {
    Integer count = Integer.valueOf(0);
    try {
      this.sql = "select count(*) count from DETAILS where 1=1 ";
      if ((detailsName != null) && (!"".equals(detailsName))) {
        this.sql = (this.sql + " and DETAILS_NAME like '%" + detailsName + "%'");
      }
      if ((shopType != null) && (!"".equals(shopType))) {
        this.sql = (this.sql + " and SHOP_TYPE_ID = '" + shopType + "'");
      }
      List<Map> list = new BaseDao().queryDbBySql(this.sql);
      count = Integer.valueOf(Integer.parseInt((String)((Map)list.get(0)).get("COUNT")));
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return count;
  }
  
  class CloseActionListener implements ActionListener { CloseActionListener() {}
    
    public void actionPerformed(ActionEvent e) { DetailsDbIFrame.this.doDefaultCloseAction(); }
  }
  


































  public void resetTableMap()
  {
    this.tableModel.setDataMap(new HashMap());
  }
}
