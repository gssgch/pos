package com.pos.dboperation;

import com.pos.db.BaseDao;
import com.pos.util.ComboBoxUtil;
import com.pos.vo.Item;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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


public class DetailsDbOpertionAction
  extends JDialog
{
  private JScrollPane scrollPane_1;
  private JComboBox navigationComp;
  private JComboBox shopTypeComp;
  private JTextField detailsNameJf;
  private JTextField detailsPriceJf;
  private JTextField primaryJf;
  private JButton okButton;
  private JButton cancelButton;
  private JTextField detailsUntilJf;
  private String sql = "";
  private Map map = new HashMap();
  private String res = "";
  
  public DetailsDbOpertionAction(Map map, String flag)
  {
    setTitle("商品信息维护窗口");
    init(map, flag);
    setModal(true);
    setSize(400, 300);
  }
  
  private void init(Map map, final String flag) {
    Container c = getContentPane();
    String navigationId = (String)map.get("navigationId");
    String shopId = (String)map.get("shopId");
    map.remove("navigationId");
    map.remove("shopId");
    

    JPanel panel_1 = new JPanel();
    panel_1.setLayout(new BorderLayout());
    
    JPanel panel = new JPanel();
    panel.setLayout(null);
    this.scrollPane_1 = new JScrollPane();
    this.scrollPane_1.setPreferredSize(new Dimension(450, 200));
    panel_1.add(this.scrollPane_1);
    this.scrollPane_1.setViewportView(panel);
    

    JLabel navigationStr = new JLabel("地    区:");
    navigationStr.setBounds(10, 10, 90, 25);
    navigationStr.setFont(new Font("宋体", 0, 18));
    
    this.navigationComp = new JComboBox();
    new ComboBoxUtil().setComboBox(this.navigationComp, "navigationComp", 
      navigationId, "");
    this.navigationComp.setEnabled(false);
    this.navigationComp.setBounds(90, 10, 130, 25);
    this.navigationComp.setFont(new Font("宋体", 0, 18));
    
    panel.add(this.navigationComp);
    panel.add(navigationStr);
    

    JLabel shopTypeStr = new JLabel("商品类型:");
    shopTypeStr.setBounds(10, 40, 90, 30);
    shopTypeStr.setFont(new Font("宋体", 0, 18));
    
    this.shopTypeComp = new JComboBox();
    this.shopTypeComp.setBounds(90, 40, 80, 30);
    this.shopTypeComp.setFont(new Font("宋体", 0, 18));
    this.shopTypeComp.setEnabled(false);
    new ComboBoxUtil().setComboBox(this.shopTypeComp, "shopTypeComp", 
      navigationId, shopId);
    this.primaryJf = new JTextField();
    this.primaryJf.setBounds(160, 40, 80, 30);
    this.primaryJf.setVisible(false);
    
    panel.add(this.primaryJf);
    panel.add(shopTypeStr);
    panel.add(this.shopTypeComp);
    

    JLabel detailsNameStr = new JLabel("商品名称:");
    detailsNameStr.setBounds(10, 75, 90, 30);
    detailsNameStr.setFont(new Font("宋体", 0, 18));
    
    this.detailsNameJf = new JTextField();
    this.detailsNameJf.setBounds(90, 75, 180, 30);
    this.detailsNameJf.setFont(new Font("宋体", 0, 18));
    
    panel.add(detailsNameStr);
    panel.add(this.detailsNameJf);
    

    JLabel detailsPriceStr = new JLabel("商品价格:");
    detailsPriceStr.setBounds(10, 110, 90, 30);
    detailsPriceStr.setFont(new Font("宋体", 0, 18));
    this.detailsPriceJf = new JTextField();
    this.detailsPriceJf.setBounds(90, 110, 120, 30);
    this.detailsPriceJf.setFont(new Font("宋体", 0, 18));
    JLabel rmb = new JLabel("RMB");
    rmb.setBounds(210, 110, 40, 30);
    rmb.setFont(new Font("宋体", 0, 18));
    
    panel.add(rmb);
    panel.add(detailsPriceStr);
    panel.add(this.detailsPriceJf);
    

    this.detailsUntilJf = new JTextField();
    this.detailsUntilJf.setFont(new Font("宋体", 0, 18));
    JLabel detailsUntilStr = new JLabel("商品单位:");
    detailsUntilStr.setFont(new Font("宋体", 0, 18));
    detailsUntilStr.setBounds(10, 145, 90, 30);
    this.detailsUntilJf.setBounds(90, 145, 60, 30);
    
    JLabel tip = new JLabel("提示:输入后，打印单显示单位！！！");
    tip.setForeground(Color.red);
    tip.setBounds(155, 145, 220, 30);
    
    panel.add(this.detailsUntilJf);
    panel.add(detailsUntilStr);
    panel.add(tip);
    
    c.add(panel_1);
    

    this.okButton = new JButton("添加");
    this.okButton.setBounds(50, 180, 60, 30);
    this.okButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        String detailsName = DetailsDbOpertionAction.this.detailsNameJf.getText();
        String detailsPrice = DetailsDbOpertionAction.this.detailsPriceJf.getText();
        String navigationId = ((Item)DetailsDbOpertionAction.this.navigationComp.getSelectedItem())
          .getKey();
        String shopId = ((Item)DetailsDbOpertionAction.this.shopTypeComp.getSelectedItem())
          .getKey();
        
        if ((detailsName == null) || ("".equals(detailsName))) {
          JOptionPane.showMessageDialog(null, "商品名称不能为空！");
          return;
        }
        
        if ((detailsPrice == null) || ("".equals(detailsPrice))) {
          JOptionPane.showMessageDialog(null, "商品价格不能为空！");
          return;
        }
        
        if ((shopId == null) || ("".equals(shopId))) {
          JOptionPane.showMessageDialog(null, "商品类型信息获取失败,请联系管理员！");
          return;
        }
        

        DetailsDbOpertionAction.this.sql = 
          ("select count(*) count from details where details_name='" + detailsName + "' and shop_type_id='" + shopId + "'");
        if ("update".equals(flag)) {
          DetailsDbOpertionAction.this.sql = 
            (DetailsDbOpertionAction.this.sql + " and details_id!='" + DetailsDbOpertionAction.this.primaryJf.getText() + "'");
        }
        try {
          List<Map> countList = new BaseDao().queryDbBySql(DetailsDbOpertionAction.this.sql);
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
          DetailsDbOpertionAction.this.sql = 
          

            ("update details set details_name='" + detailsName + "',details_price='" + detailsPrice + "' where details_id='" + DetailsDbOpertionAction.this.primaryJf.getText() + "'");
          new BaseDao().updateDbBySql(DetailsDbOpertionAction.this.sql);
          JOptionPane.showMessageDialog(null, "数据修改成功！");
        } else {
          DetailsDbOpertionAction.this.sql = 
          



            ("insert into details(details_name,details_price,shop_type_id,details_until) values('" + detailsName + "','" + detailsPrice + "','" + shopId + "','" + DetailsDbOpertionAction.this.detailsUntilJf.getText() + "')");
          new BaseDao().insertDbBySql(DetailsDbOpertionAction.this.sql);
          JOptionPane.showMessageDialog(null, "数据添加成功！");
        }
        
      }
    });
    this.cancelButton = new JButton("取消");
    this.cancelButton.setBounds(120, 180, 60, 30);
    this.cancelButton.addActionListener(new ActionListener()
    {
      public void actionPerformed(ActionEvent e) {
        DetailsDbOpertionAction.this.dispose();
      }
      
    });
    panel.add(this.okButton);
    panel.add(this.cancelButton);
    if ("update".equals(flag)) {
      Map temp = (Map)map.get("update");
      Set<String> s = temp.keySet();
      Object[] o = null;
      for (String ss : s) {
        o = (Object[])temp.get(ss);
      }
      
      this.detailsNameJf.setText((String)o[1]);
      this.detailsPriceJf.setText((String)o[2]);
      this.primaryJf.setText((String)o[3]);
      this.detailsUntilJf.setText((String)o[4]);
    }
    
    panel.setPreferredSize(new Dimension(400, 300));
  }
  
  public static String showDialog(Component relativeTo, Map map, String flag) {
    DetailsDbOpertionAction d = new DetailsDbOpertionAction(map, flag);
    d.setLocationRelativeTo(relativeTo);
    d.setVisible(true);
    return d.res;
  }
}
