package com.pos.dboperation;

import com.pos.db.BaseDao;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class UserDbOperationActtion
  extends JDialog
{
  private JScrollPane scrollPane_1;
  private JTextField userNameJf;
  private JTextField tuoGaunJf;
  private JTextField cardNoJf;
  private JTextField cardShopJf;
  private JComboBox yearComb;
  private JComboBox monthComb;
  private JButton okButton;
  private JButton cancelButton;
  private JTextField primaryJf;
  private String sql = "";
  private Map map = new HashMap();
  private String rsString = "";
  
  public UserDbOperationActtion(Map map, String flag)
  {
    setTitle("用户信息维护窗口");
    init(map, flag);
    setModal(true);
    setSize(400, 300);
  }
  
  private void init(Map map, final String flag) {
    Container c = getContentPane();
    

    JPanel panel_1 = new JPanel();
    panel_1.setLayout(new BorderLayout());
    
    JPanel panel = new JPanel();
    panel.setLayout(null);
    this.scrollPane_1 = new JScrollPane();
    this.scrollPane_1.setPreferredSize(new Dimension(450, 200));
    panel_1.add(this.scrollPane_1);
    this.scrollPane_1.setViewportView(panel);
    

    JLabel userNameStr = new JLabel("姓  名:");
    userNameStr.setBounds(10, 10, 70, 25);
    userNameStr.setFont(new Font("宋体", 0, 18));
    
    this.userNameJf = new JTextField();
    this.userNameJf.setBounds(75, 10, 130, 25);
    this.userNameJf.setFont(new Font("宋体", 0, 18));
    
    panel.add(userNameStr);
    panel.add(this.userNameJf);
    

    JLabel tuoGaunStr = new JLabel("托管号:");
    tuoGaunStr.setBounds(10, 40, 70, 30);
    tuoGaunStr.setFont(new Font("宋体", 0, 18));
    this.tuoGaunJf = new JTextField();
    this.tuoGaunJf.setBounds(75, 40, 80, 30);
    this.tuoGaunJf.setFont(new Font("宋体", 0, 18));
    this.primaryJf = new JTextField();
    this.primaryJf.setBounds(160, 40, 80, 30);
    this.primaryJf.setVisible(false);
    
    panel.add(this.primaryJf);
    panel.add(tuoGaunStr);
    panel.add(this.tuoGaunJf);
    

    JLabel cardNoStr = new JLabel("卡  号:");
    cardNoStr.setBounds(10, 75, 70, 30);
    cardNoStr.setFont(new Font("宋体", 0, 18));
    this.cardNoJf = new JTextField();
    this.cardNoJf.setBounds(75, 75, 180, 30);
    this.cardNoJf.setFont(new Font("宋体", 0, 18));
    
    panel.add(cardNoStr);
    panel.add(this.cardNoJf);
    

    JLabel cardShopStr = new JLabel("发卡行:");
    cardShopStr.setBounds(10, 110, 70, 30);
    cardShopStr.setFont(new Font("宋体", 0, 18));
    this.cardShopJf = new JTextField();
    this.cardShopJf.setBounds(75, 110, 200, 30);
    this.cardShopJf.setFont(new Font("宋体", 0, 18));
    
    panel.add(cardShopStr);
    panel.add(this.cardShopJf);
    

    JLabel valideStr = new JLabel("有效期:");
    valideStr.setBounds(10, 145, 70, 30);
    valideStr.setFont(new Font("宋体", 0, 18));
    

    Calendar c1 = Calendar.getInstance();
    c1.setTime(new Date());
    int startYear = c1.get(1) - 20;
    int endYear = c1.get(1) + 20;
    
    this.yearComb = new JComboBox();
    this.yearComb.setBounds(75, 145, 65, 30);
    this.yearComb.setFont(new Font("宋体", 0, 18));
    for (int i = endYear; i >= startYear; i--) {
      this.yearComb.addItem(i);
    }
    
    this.yearComb.setSelectedItem(Integer.valueOf(endYear));
    
    JLabel valideYearJfStr = new JLabel("年/");
    valideYearJfStr.setBounds(140, 145, 70, 30);
    valideYearJfStr.setFont(new Font("宋体", 0, 18));
    

    int month = c1.get(2);
    this.monthComb = new JComboBox();
    this.monthComb.setBounds(167, 145, 45, 30);
    this.monthComb.setFont(new Font("宋体", 0, 18));
    for (int i = 12; i >= 1; i--) {
      if (i < 10) {
        this.monthComb.addItem("0" + i);
      } else {
        this.monthComb.addItem(i);
      }
    }
    this.monthComb.setSelectedItem(month < 10 ? "0" + month : Integer.valueOf(month));
    
    JLabel valideMonthStr = new JLabel("月");
    valideMonthStr.setBounds(213, 145, 20, 30);
    valideMonthStr.setFont(new Font("宋体", 0, 18));
    
    panel.add(valideStr);
    panel.add(this.yearComb);
    panel.add(valideYearJfStr);
    panel.add(this.monthComb);
    panel.add(valideMonthStr);
    
    c.add(panel_1);
    

    this.okButton = new JButton("添加");
    this.okButton.setBounds(50, 200, 60, 30);
    this.okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        String userName = UserDbOperationActtion.this.userNameJf.getText();
        String tuoGaun = UserDbOperationActtion.this.tuoGaunJf.getText();
        String cardNo = UserDbOperationActtion.this.cardNoJf.getText();
        String cardShop = UserDbOperationActtion.this.cardShopJf.getText();
        String yearStr = (String)UserDbOperationActtion.this.yearComb.getSelectedItem();
        String monthStr = (String)UserDbOperationActtion.this.monthComb.getSelectedItem();
        if ((userName == null) || ("".equals(userName))) {
          JOptionPane.showMessageDialog(null, "姓名不能为空！");
          return;
        }
        
        if ((cardNo == null) || ("".equals(cardNo))) {
          JOptionPane.showMessageDialog(null, "卡号不能为空！");
          return;
        }
        if ((cardShop == null) || ("".equals(cardShop))) {
          JOptionPane.showMessageDialog(null, "发卡行不能为空！");
          return;
        }
        

        UserDbOperationActtion.this.sql = 
        


          ("select count(*) count from user where user_name='" + userName + "' and card_no='" + cardNo + "' and valide_date='" + yearStr + "/" + monthStr + "' and card_self='" + cardShop + "' and tuo_guan='" + tuoGaun + "'");
        if ("update".equals(flag)) {
          UserDbOperationActtion.this.sql = (UserDbOperationActtion.this.sql + " and user_id!='" + UserDbOperationActtion.this.primaryJf.getText() + "'");
        }
        try {
          List<Map> countList = new BaseDao().queryDbBySql(UserDbOperationActtion.this.sql);
          Integer count = Integer.valueOf(Integer.parseInt(
            (String)((Map)countList.get(0)).get("COUNT")));
          if (count.intValue() > 0) {
            JOptionPane.showMessageDialog(null, "数据已存在！");
            return;
          }
        } catch (SQLException e1) {
          e1.printStackTrace();
          JOptionPane.showMessageDialog(null, "数据插入校验异常，请联系管理员！");
          return;
        }
        
        if ("update".equals(flag)) {
          UserDbOperationActtion.this.sql = 
          


            ("update user set user_name='" + userName + "',card_no='" + cardNo + "',valide_date='" + yearStr + "/" + monthStr + "',card_self='" + cardShop + "',tuo_guan='" + tuoGaun + "' where user_id='" + UserDbOperationActtion.this.primaryJf.getText() + "'");
          new BaseDao().updateDbBySql(UserDbOperationActtion.this.sql);
          JOptionPane.showMessageDialog(null, "数据修改成功！");
        } else {
          UserDbOperationActtion.this.sql = 
          










            ("insert into user(user_name,card_no,valide_date,card_self,tuo_guan) values('" + userName + "','" + cardNo + "','" + yearStr + "/" + monthStr + "','" + cardShop + "','" + tuoGaun + "')");
          new BaseDao().insertDbBySql(UserDbOperationActtion.this.sql);
          JOptionPane.showMessageDialog(null, "数据添加成功！");
        }
        
      }
    });
    this.cancelButton = new JButton("取消");
    this.cancelButton.setBounds(120, 200, 60, 30);
    this.cancelButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        UserDbOperationActtion.this.dispose();
      }
      
    });
    panel.add(this.okButton);
    panel.add(this.cancelButton);
    if ("update".equals(flag)) {
      Set<String> s = map.keySet();
      Object[] o = null;
      for (String ss : s) {
        o = (Object[])map.get(ss);
      }
      this.userNameJf.setText((String)o[1]);
      this.tuoGaunJf.setText((String)o[2]);
      this.cardNoJf.setText((String)o[3]);
      String[] validStr = ((String)o[4]).split("/");
      if (validStr.length == 1) {
        this.yearComb.setSelectedIndex(0);
        this.monthComb.setSelectedIndex(0);
      } else {
        this.yearComb.setSelectedItem(validStr[0]);
        this.monthComb.setSelectedItem(validStr[1]);
      }
      this.cardShopJf.setText((String)o[5]);
      this.primaryJf.setText((String)o[6]);
    }
    
    panel.setPreferredSize(new Dimension(400, 300));
  }
  
  public static String showDialog(Component relativeTo, Map map, String flag) {
    UserDbOperationActtion d = new UserDbOperationActtion(map, flag);
    d.setLocationRelativeTo(relativeTo);
    d.setVisible(true);
    return d.rsString;
  }
}
