package com.pos.iframe;

import com.pos.db.BaseDao;
import com.pos.util.CalcProdList;
import com.pos.util.DateUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class RegionIFrame extends JInternalFrame
{
    private JTextField username;
    private JTextField cardJf;
    private JTextField cardValidJf;
    private JTextField cardSelJf;
    private String region;
    private String sql;
    private JButton button;
    private JCheckBox checkBox;
    private JTextField tuoguan;
    private JScrollPane scrollPane_1;
    private Map checkMap = new HashMap();
    private List<JCheckBox> checksMap = new ArrayList();
    private Map selectCheckMap = new HashMap();
    private Map typeMap = new HashMap();
    private Map shopMap = new HashMap();
    private Map dateMap = new HashMap();
    private Map amountMap = new HashMap();
    private Map foreignMap = new HashMap();
    private JLabel foreignLabel = new JLabel("外汇汇率:");

    private JLabel foreignTip = new JLabel("提示:输入外汇金额时，会根据汇率自动转换成人民币金额！");
    private JTextField foreignJf = new JTextField();

    public RegionIFrame(String region) {
        super(region);
        this.checkMap.put("region", region);
        this.region = region;
        setIconifiable(true);
        setClosable(true);
        setBounds(30, 30, 720, 600);

        JPanel panel_1 = new JPanel();
        panel_1.setLayout(new BorderLayout());

        this.scrollPane_1 = new JScrollPane();

        this.scrollPane_1.setPreferredSize(new Dimension(800, 900));
        panel_1.add(this.scrollPane_1);
        JPanel panel = new JPanel();
        this.scrollPane_1.setViewportView(panel);

        addJpanelObject(panel);
        add(panel_1);
        setVisible(true);
    }

    public void addJpanelObject(JPanel panel)
    {
        panel.setLayout(null);

        this.foreignLabel.setBounds(20, 5, 60, 20);
        this.foreignLabel.setForeground(Color.red);

        this.foreignJf.setBounds(75, 5, 80, 20);
        this.foreignJf.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent e) {
                RegionIFrame.this.checkkeyTyped(e, "外汇汇率", RegionIFrame.this.foreignJf);
            }

            public void keyReleased(KeyEvent e)
            {
                RegionIFrame.this.checkkeyReleased(e, "外汇汇率", RegionIFrame.this.foreignJf, "foreignJf");
                String foreignJfStr = RegionIFrame.this.foreignJf.getText();
                if ((foreignJfStr != null) && (!"".equals(foreignJfStr))) {
                    JCheckBox j = null;
                    String keyStr = null;
                    String foreignStr = null;
                    BigDecimal forT = new BigDecimal(foreignJfStr);
                    BigDecimal temp = null;
                    for (int i = 0; i < RegionIFrame.this.checksMap.size(); ++i) {
                        keyStr = ((JCheckBox)RegionIFrame.this.checksMap.get(i)).getName();
                        foreignStr = ((JTextField)RegionIFrame.this.foreignMap.get(keyStr))
                                .getText();
                        try {
                            if ((foreignStr != null) && (!"".equals(foreignStr))) {
                                temp = new BigDecimal(foreignStr);
                                JTextField jf =
                                        (JTextField)RegionIFrame.this.amountMap
                                                .get(keyStr);
                                jf.setText(forT.multiply(temp)
                                        .setScale(2, 4)
                                        .toPlainString());
                            }
                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }

            public void keyPressed(KeyEvent e)
            {
            }
        });
        this.foreignTip.setForeground(Color.blue);
        this.foreignTip.setBounds(20, 25, 700, 20);

        panel.add(this.foreignLabel);
        panel.add(this.foreignJf);
        panel.add(this.foreignTip);

        JLabel labelStr = new JLabel(
                "--------------------------------------------------------------------------------------------------------");

        labelStr.setBounds(20, 40, 720, 20);

        JLabel userName = new JLabel("姓  名:");
        userName.setBounds(20, 60, 50, 20);
        labelStr.setAlignmentX(20.0F);
        labelStr.setAlignmentY(60.0F);

        this.username = new JTextField();
        this.username.setSize(new Dimension(30, 10));
        this.username.setBounds(70, 60, 150, 20);

        JLabel tuoLabel = new JLabel("托管号:");
        tuoLabel.setBounds(260, 60, 70, 20);

        this.tuoguan = new JTextField();
        this.tuoguan.setSize(30, 10);
        this.tuoguan.setBounds(310, 60, 150, 20);

        JLabel cardLabel = new JLabel("卡  号:");
        cardLabel.setBounds(20, 90, 50, 20);
        this.cardJf = new JTextField();
        this.cardJf.setSize(30, 10);
        this.cardJf.setBounds(70, 90, 150, 20);

        JLabel cardValid = new JLabel("有效期:");
        cardValid.setBounds(260, 90, 50, 20);
        this.cardValidJf = new JTextField();
        this.cardValidJf.setSize(30, 10);
        this.cardValidJf.setBounds(310, 90, 150, 20);

        JLabel cardSel = new JLabel("发行卡:");
        cardSel.setBounds(480, 90, 60, 20);
        this.cardSelJf = new JTextField();
        this.cardSelJf.setSize(30, 10);
        this.cardSelJf.setBounds(530, 90, 150, 20);

        JButton queryBtn = new JButton("查询");
        queryBtn.setBounds(530, 60, 80, 20);

        queryBtn.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e) {
                if ((RegionIFrame.this.username.getText() == null) || ("".equals(RegionIFrame.this.username.getText()))) {
                    JOptionPane.showMessageDialog(null, "用户信息不能为空！");
                    return;
                }
                List list = null;
                try {
                    RegionIFrame.this.sql =
                            ("select * from user where user_name like '%" +
                                    RegionIFrame.this.username.getText() + "%'");
                    list = new BaseDao().queryDbBySql(RegionIFrame.this.sql);
                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "用户信息不存在,请手动输入相关信息！");
                }
                if ((list != null) && (list.size() != 0)) {
                    if (list.size() == 1) {
                        Map map = (Map)list.get(0);
                        RegionIFrame.this.cardJf.setText((map.get("CARD_NO") == null) ? null : map
                                .get("CARD_NO").toString());
                        RegionIFrame.this.cardValidJf.setText((map.get("VALIDE_DATE") == null) ? null :
                                map.get("VALIDE_DATE").toString());
                        RegionIFrame.this.cardSelJf.setText((map.get("CARD_SELF") == null) ? null :
                                map.get("CARD_SELF").toString());
                    } else {
                        Map tempMap = null;
                        String[] str = new String[list.size()];
                        for (int i = 0; i < list.size(); ++i) {
                            StringBuffer sb = new StringBuffer();
                            tempMap = (Map)list.get(i);
                            sb.append("顺序:").append(i).append(" 用户名：")
                                    .append(tempMap.get("USER_NAME"))
                                    .append(", 卡号：")
                                    .append(tempMap.get("CARD_NO"))
                                    .append(", 有效期:")
                                    .append(tempMap.get("VALIDE_DATE"))
                                    .append(", 发卡行:")
                                    .append(tempMap.get("CARD_SELF"));
                            str[i] = sb.toString();
                        }
                        String s = (String)JOptionPane.showInputDialog(null,
                                "请确认用户信息:\n", "用户选择列表",
                                -1, null, str, str[0]);
                        int index = 0;
                        if (s != null) {
                            String[] tempStr = s.split(" ");
                            for (int t = 0; t < tempStr.length; ++t) {
                                if ("顺序:".equals(tempStr[t].substring(0, 3))) {
                                    index = Integer.parseInt(tempStr[t]
                                            .substring(3, tempStr[t].length()));
                                }
                            }
                        }
                        Map map = (Map)list.get(index);
                        RegionIFrame.this.cardJf.setText((map.get("CARD_NO") == null) ? null : map
                                .get("CARD_NO").toString());
                        RegionIFrame.this.cardValidJf.setText((map.get("VALIDE_DATE") == null) ? null :
                                map.get("VALIDE_DATE").toString());
                        RegionIFrame.this.cardSelJf.setText((map.get("CARD_SELF") == null) ? null :
                                map.get("CARD_SELF").toString());
                        RegionIFrame.this.username.setText((map.get("USER_NAME") == null) ? null :
                                map.get("USER_NAME").toString());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "用户信息不存在,请手动输入相关信息！");
                    return;
                }
            }
        });
        panel.add(labelStr);
        panel.add(userName);
        panel.add(this.username);
        panel.add(tuoLabel);
        panel.add(this.tuoguan);
        panel.add(queryBtn);
        panel.add(cardLabel);
        panel.add(this.cardJf);
        panel.add(cardLabel);
        panel.add(this.cardJf);
        panel.add(cardValid);
        panel.add(this.cardValidJf);
        panel.add(cardSel);
        panel.add(this.cardSelJf);
        try
        {
            getDetails(this.region, panel);
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, "商品列表获取失败，请联系管理员！");
        }
    }

    public void getDetails(String region, JPanel panel) throws Exception
    {
        this.sql =
                ("select n.Navigation_ID, st.type_id, st.type_name, s.shop_id, s.shop_name from navigation n, shop_type st, shop s where n.Navigation_ID = st.Navigation_ID and st.type_id = s.shop_type_id and n.Navigation_name = '" +
                        region + "' order by st.type_name, s.shop_name");

        List list = new BaseDao().queryDbBySql(this.sql);
        int h = 110;

        JLabel label = new JLabel("---------------商铺列表---------------------------商铺列表-------------------------商铺列表-----------------");

        label.setBounds(10, 120, 700, 20);
        panel.add(label);

        if ((list == null) || (list.size() == 0)) {
            JOptionPane.showMessageDialog(null, "商铺列表信息获取失败，请联系管理员！");
            h = 110;
        } else {
            Map shoppingMap = null;
            int t1 = 0;
            int t2 = 0;
            int ts1 = 0;
            int ts2 = 0;

            Map tempMap = null;
            for (int i = 0; i < list.size(); ++i) {
                tempMap = (Map)list.get(i);
                String ss1 = (String)tempMap.get("TYPE_NAME");
                String ss2 = (String)tempMap.get("SHOP_NAME");
                t1 = ss1.length();
                t2 = ss2.length();
                if (t1 > ts1) {
                    ts1 = t1;
                }
                if (t2 > ts2) {
                    ts2 = t2;
                }
            }

            if (ts1 < 4) {
                ts1 = 4;
            }

            if (ts2 < 4) {
                ts2 = 4;
            }

            for (int i = 0; i < list.size(); ++i) {
                shoppingMap = (Map)list.get(i);
                String shopId = shoppingMap.get("SHOP_ID").toString();
                String shopTypeId = shoppingMap.get("TYPE_ID").toString();
                String str = "TYPEID:" + shopTypeId + "-id-SHOPID:" + shopId;

                this.checkBox =
                        new JCheckBox(shoppingMap.get("SHOP_NAME")
                                .toString());
                this.checkBox.setName(str);
                this.checksMap.add(this.checkBox);

                JLabel typeName = new JLabel(shoppingMap.get("TYPE_NAME")
                        .toString());
                typeName.setName(str);

                JLabel shopName = new JLabel(shoppingMap.get("SHOP_NAME")
                        .toString());
                shopName.setName(str);

                JLabel jla = new JLabel("打印日期：");
                DateUtil ser = DateUtil.getInstance();
                JTextField text = new JTextField();
                ser.register(text);
                text.setName(str);
                text.setEditable(false);
                text.addFocusListener(new FocusListener(text)
                {
                    public void focusLost(FocusEvent e) {
                        String textStr = this.val$text.getName();
                        String textDate = this.val$text.getText();
                        Map tempMap = (Map)RegionIFrame.this.checkMap.get(textStr);
                        if (tempMap != null) {
                            tempMap.put("printDate", textDate);
                            RegionIFrame.this.checkMap.put(textStr, tempMap);
                        }
                    }

                    public void focusGained(FocusEvent e)
                    {
                    }
                });
                JLabel amountLabel = new JLabel("金  额：");
                JTextField amountJf = new JTextField();
                amountJf.setName(str);
                amountJf.addKeyListener(new KeyListener(amountJf)
                {
                    public void keyTyped(KeyEvent e) {
                        RegionIFrame.this.checkkeyTyped(e, "金额", this.val$amountJf);
                    }

                    public void keyReleased(KeyEvent e)
                    {
                        RegionIFrame.this.checkkeyReleased(e, "金额", this.val$amountJf, "amount");
                    }

                    public void keyPressed(KeyEvent e)
                    {
                    }
                });
                this.checkBox.setBounds(10, h + (i + 1) * 30, 20, 20);
                this.checkBox.addItemListener(new ItemListener(typeName, shopName, text, amountJf)
                {
                    public void itemStateChanged(ItemEvent e) {
                        JCheckBox cb = (JCheckBox)e.getItem();
                        String cbStr = cb.getName();
                        String typeNameStr = this.val$typeName.getText();
                        String shopNameStr = this.val$shopName.getText();
                        String printDate = this.val$text.getText();
                        String amount = this.val$amountJf.getText();

                        if ((((printDate == null) || ("".equals(printDate)))) &&
                                (cb.isSelected()))
                            try
                            {
                                JOptionPane.showMessageDialog(null, "打印日期不能为空！");
                                throw new Exception("打印日期不能为空！");
                            } catch (Exception e1) {
                                e1.printStackTrace();

                                return;
                            }
                        if ((((amount == null) || ("".equals(amount)))) &&
                                (cb.isSelected()))
                            try {
                                JOptionPane.showMessageDialog(null, "金额不能为空！");
                                throw new Exception("金额不能为空！");
                            } catch (Exception e1) {
                                e1.printStackTrace();

                                return;
                            }
                        if (cb.isSelected()) {
                            Map a = new HashMap();
                            a.put("amount", amount);
                            a.put("printDate", printDate);
                            a.put("typeName", typeNameStr);
                            a.put("shopName", shopNameStr);
                            RegionIFrame.this.checkMap.put(cbStr, a);
                            RegionIFrame.this.selectCheckMap.put(cbStr, cb);
                        } else {
                            RegionIFrame.this.checkMap.remove(cbStr);
                            RegionIFrame.this.selectCheckMap.remove(cbStr);
                        }
                    }
                });
                JLabel foreignL = new JLabel("外汇金额:");
                JTextField foreginAmount = new JTextField();
                foreginAmount.addKeyListener(new KeyListener(foreginAmount, amountJf)
                {
                    public void keyTyped(KeyEvent e) {
                        RegionIFrame.this.checkkeyTyped(e, "外汇金额", this.val$foreginAmount);
                    }

                    public void keyReleased(KeyEvent e)
                    {
                        RegionIFrame.this.checkkeyReleased(e, "外汇金额", this.val$foreginAmount,
                                "foreginAmount");
                        String keyStr = null;
                        Map m = null;
                        if ((RegionIFrame.this.foreignJf.getText() == null) ||
                                ("".equals(RegionIFrame.this.foreignJf.getText()))) return;
                        try {
                            BigDecimal foreignJfStr = new BigDecimal(
                                    RegionIFrame.this.foreignJf.getText());
                            keyStr = this.val$amountJf.getName();
                            BigDecimal foreginAmountStr = new BigDecimal(
                                    this.val$foreginAmount.getText());
                            BigDecimal b = foreignJfStr.multiply(
                                    foreginAmountStr).setScale(2,
                                    4);
                            this.val$amountJf.setText(b.toPlainString());
                            m = (Map)RegionIFrame.this.checkMap.get(keyStr);
                            if ((m != null) && (m.size() > 0)) {
                                m.put("amount", b.toPlainString());
                                RegionIFrame.this.checkMap.put(keyStr, m);
                            }
                            RegionIFrame.this.amountMap.put(keyStr, this.val$amountJf);
                        }
                        catch (Exception e1) {
                            JOptionPane.showMessageDialog(null, "外汇汇率输入有误！");
                            e1.printStackTrace();
                        }
                    }

                    public void keyPressed(KeyEvent e)
                    {
                    }
                });
                typeName.setBounds(40, h + (i + 1) * 30, ts1 * 12, 20);
                shopName.setBounds(50 + ts1 * 12, h + (i + 1) * 30, ts2 * 12,
                        20);
                jla.setBounds(60 + (ts2 + ts1) * 12, h + (i + 1) * 30, 80, 20);
                text.setBounds(120 + (ts2 + ts1) * 12, h + (i + 1) * 30, 80, 20);
                amountLabel.setBounds(210 + (ts2 + ts1) * 12, h + (i + 1) * 30,
                        80, 20);
                amountJf.setBounds(260 + (ts2 + ts1) * 12, h + (i + 1) * 30,
                        80, 20);
                foreignL.setBounds(350 + (ts2 + ts1) * 12, h + (i + 1) * 30,
                        70, 20);
                foreginAmount.setBounds(410 + (ts2 + ts1) * 12, h + (i + 1) *
                        30, 80, 20);

                panel.add(this.checkBox);
                panel.add(typeName);
                panel.add(shopName);
                panel.add(text);
                panel.add(jla);
                panel.add(amountLabel);
                panel.add(amountJf);
                panel.add(foreignL);
                panel.add(foreginAmount);
                this.typeMap.put(str, typeName);
                this.shopMap.put(str, shopName);
                this.dateMap.put(str, text);
                this.amountMap.put(str, amountJf);
                this.foreignMap.put(str, foreginAmount);
            }

            JButton jb = new JButton("全选");
            jb.setBounds(20, h + (list.size() + 1) * 30, 60, 20);
            jb.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    RegionIFrame.this.setCheckBoxStatus(RegionIFrame.this.checksMap, true, "2");
                }
            });
            panel.add(jb);

            JButton canelBtn = new JButton("取消选中");
            canelBtn.setBounds(90, h + (list.size() + 1) * 30, 80, 20);
            panel.add(canelBtn);
            canelBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    RegionIFrame.this.setCheckBoxStatus(RegionIFrame.this.checksMap, false, "");
                }
            });
            JButton resetBtn = new JButton("重置");
            resetBtn.setBounds(180, h + (list.size() + 1) * 30, 80, 20);
            panel.add(resetBtn);
            resetBtn.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e) {
                    RegionIFrame.this.setCheckBoxStatus(RegionIFrame.this.checksMap, true, "1");
                }
            });
            this.button = new JButton();
            this.button.setText("生成文件");
            this.button.setBounds(320, h + (list.size() + 1) * 30, 100, 20);
            this.button.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    if (RegionIFrame.this.checkMap.size() == 0) {
                        JOptionPane.showMessageDialog(null, "请选择一行记录！");
                        return;
                    }

                    String region = (String)RegionIFrame.this.checkMap.get("region");
                    Set st = RegionIFrame.this.checkMap.keySet();
                    Map m = new HashMap();
                    String amountStr = "";
                    for (String s : st) {
                        if (("userName".equals(s)) || ("jf2Str".equals(s)) ||
                                ("region".equals(s)) ||
                                ("cardJfStr".equals(s)) ||
                                ("cardValidJfStr".equals(s)) ||
                                ("cardSelJfStr".equals(s)) ||
                                ("foreignJf".equals(s)) ||
                                ("foreginAmount".equals(s))) continue;
                        m = (Map)RegionIFrame.this.checkMap.get(s);
                        amountStr = (String)m.get("amount");
                        String printDate = (String)m.get("printDate");
                        StringBuffer sb = new StringBuffer();

                        if ((printDate == null) || ("".equals(printDate))) {
                            sb.append("商店类型:").append(m.get("typeName"))
                                    .append(",商店名称:")
                                    .append(m.get("shopName"))
                                    .append(",打印日期不能为空！");
                            JOptionPane.showMessageDialog(null,
                                    sb.toString());
                            return;
                        }
                        if (amountStr.contains(".")) {
                            int i = 0;
                            int count = 0;
                            while ((i = amountStr.indexOf(".")) != -1) {
                                amountStr = amountStr.substring(i + 1,
                                        amountStr.length());
                                ++count;
                                if (count == 2) {
                                    break;
                                }
                            }
                            if (count != 2) continue;
                            sb.append("商店类型:")
                                    .append(m.get("typeName"))
                                    .append(",商店名称:")
                                    .append(m.get("shopName"))
                                    .append(",金额输入有误！");
                            JOptionPane.showMessageDialog(null,
                                    sb.toString());
                            return;
                        }
                        if ((amountStr != null) &&
                                (!"".equals(amountStr))) continue;
                        sb.append("商店类型:").append(m.get("typeName"))
                                .append(",商店名称:")
                                .append(m.get("shopName"))
                                .append(",金额不能为空！");
                        JOptionPane.showMessageDialog(null,
                                sb.toString());
                        return;
                    }

                    String userName = RegionIFrame.this.username.getText();
                    String tuoguanStr = RegionIFrame.this.tuoguan.getText();
                    String cardJfStr = RegionIFrame.this.cardJf.getText();
                    String cardValidJfStr = RegionIFrame.this.cardValidJf.getText();
                    String cardSelJfStr = RegionIFrame.this.cardSelJf.getText();
                    if ((cardJfStr == null) || ("".equals(cardJfStr))) {
                        JOptionPane.showMessageDialog(null, "卡号不能为空！");
                        return;
                    }
                    if ((cardValidJfStr == null) || ("".equals(cardValidJfStr))) {
                        JOptionPane.showMessageDialog(null, "有效期不能为空！");
                        return;
                    }
                    if ((cardSelJfStr == null) || ("".equals(cardSelJfStr))) {
                        JOptionPane.showMessageDialog(null, "发卡行不能为空！");
                        return;
                    }
                    Map dateMap = new HashMap();
                    dateMap.put("userName", userName);
                    dateMap.put("tuoguan", tuoguanStr);
                    dateMap.put("cardJfStr", cardJfStr);
                    dateMap.put("cardValidJfStr", cardValidJfStr);
                    dateMap.put("cardSelJfStr", cardSelJfStr);
                    dateMap.put("data", RegionIFrame.this.checkMap);
                    RegionIFrame.this.checkMap.remove("region");

                    if (RegionIFrame.this.checkMap.size() <= 0) return;
                    try {
                        CalcProdList.createWordByMap(dateMap, region);
                        JOptionPane.showMessageDialog(null, "按钮操作结束.");
                    } catch (Exception e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(null, "部分文件生成异常！");
                        return;
                    }
                }
            });
            JButton button_1 = new JButton();
            button_1.setText("关闭");
            button_1.addActionListener(new CloseActionListener());
            button_1.setBounds(450, h + (list.size() + 1) * 30, 70, 20);

            panel.add(this.button);
            panel.add(button_1);

            panel.setPreferredSize(
                    new Dimension(720, h + (list.size() + 1) *
                            30 + 40));
        }
    }

    public String checkkeyTyped(KeyEvent e, String message, JTextField jf)
    {
        String str = "";
        int keyChar = e.getKeyChar();
        String s = jf.getText();
        if (s.contains(".")) {
            String[] ss = s.split(".");
            if (ss.length > 2) {
                JOptionPane.showMessageDialog(null, message + "数字输入有误！");
                str = message + "数字输入有误！";
            }
        }
        if ((((keyChar < 48) || (keyChar > 57))) &&
                (keyChar != 46) && (keyChar != 8) &&
                (keyChar != 127))
        {
            e.consume();
            JOptionPane.showMessageDialog(null, message + "只能输入有效数字！");
            str = message + "只能输入有效数字！";
        }
        return str;
    }

    public void checkkeyReleased(KeyEvent e, String message, JTextField jf, String flag)
    {
        String number = jf.getText();
        String temp = jf.getText();
        String numberName = jf.getName();
        if (number.contains(".")) {
            int i = 0;
            int count = 0;
            while ((i = temp.indexOf(".")) != -1) {
                temp = temp.substring(i + 1);
                ++count;
                if (count == 2) {
                    break;
                }
            }
            if (count == 2) {
                e.consume();
                Map tempMap = (Map)this.checkMap.get(numberName);
                if (tempMap != null) {
                    tempMap.put(flag, number);
                    this.checkMap.put(numberName, tempMap);
                    this.checkMap.put(numberName, tempMap);
                }

                JOptionPane.showMessageDialog(null, message + "输入有误！");
                return;
            }
        } else if ((number == null) || ("".equals(number))) {
            Map tempMap = (Map)this.checkMap.get(numberName);
            if (tempMap != null) {
                tempMap.put(flag, number);
                this.checkMap.put(numberName, tempMap);
            }
            JOptionPane.showMessageDialog(null, message + "不能为空！");
            return;
        }
        if (this.checkMap.containsKey(numberName)) {
            Map tempMap = (Map)this.checkMap.get(numberName);
            tempMap.put(flag, number);
            this.checkMap.put(numberName, tempMap);
        }
    }

    public void setCheckBoxStatus(List<JCheckBox> list, boolean flag, String flagStr)
    {
        if ((list != null) && (list.size() > 0)) {
            JCheckBox j = null;
            JLabel typeLabel = null;
            JLabel shopLabel = null;
            JTextField dateJf = null;
            JTextField amountJf1 = null;
            JTextField foreignJf1 = null;
            for (int i = 0; i < list.size(); ++i) {
                j = (JCheckBox)list.get(i);
                if (!flag) {
                    j.setSelected(false);
                } else {
                    String s = j.getName();
                    typeLabel = (JLabel)this.typeMap.get(s);
                    shopLabel = (JLabel)this.shopMap.get(s);
                    dateJf = (JTextField)this.dateMap.get(s);
                    amountJf1 = (JTextField)this.amountMap.get(s);
                    foreignJf1 = (JTextField)this.foreignMap.get(s);
                    if ("1".equals(flagStr)) {
                        dateJf.setText("");
                        amountJf1.setText("");
                        foreignJf1.setText("");
                        j.setSelected(false);
                    } else if ("2".equals(flagStr)) {
                        if ((dateJf.getText() == null) ||
                                ("".equals(dateJf.getText()))) {
                            JOptionPane.showMessageDialog(null, "商铺类型：" +
                                    typeLabel.getText() + " , 商品名称：" +
                                    shopLabel.getText() + " ,打印不能为空！");
                            return;
                        }
                        if ((dateJf.getText() == null) ||
                                ("".equals(dateJf.getText()))) {
                            JOptionPane.showMessageDialog(null, "商铺类型：" +
                                    typeLabel.getText() + " , 商品名称：" +
                                    shopLabel.getText() + " ,金额不能为空！");
                            return;
                        }
                        j.setSelected(true);
                    }
                }
            }
        }
    }

    class CloseActionListener implements ActionListener {
        CloseActionListener() {
        }

        public void actionPerformed(ActionEvent e) {
            RegionIFrame.this.doDefaultCloseAction();
        }
    }
}
