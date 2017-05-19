/*     */ package com.pos.dboperation;
/*     */ 
/*     */ import com.pos.db.BaseDao;
/*     */ import com.pos.util.ComboBoxUtil;
/*     */ import com.pos.vo.Item;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JDialog;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTextField;
/*     */ 
/*     */ public class DetailsDbOpertionAction extends JDialog
/*     */ {
/*     */   private JScrollPane scrollPane_1;
/*     */   private JComboBox navigationComp;
/*     */   private JComboBox shopTypeComp;
/*     */   private JTextField detailsNameJf;
/*     */   private JTextField detailsPriceJf;
/*     */   private JTextField primaryJf;
/*     */   private JButton okButton;
/*     */   private JButton cancelButton;
/*     */   private JTextField detailsUntilJf;
/*  41 */   private String sql = "";
/*  42 */   private Map map = new HashMap();
/*  43 */   private String res = "";
/*     */ 
/*     */   public DetailsDbOpertionAction(Map map, String flag)
/*     */   {
/*  47 */     setTitle("商品信息维护窗口");
/*  48 */     init(map, flag);
/*  49 */     setModal(true);
/*  50 */     setSize(400, 300);
/*     */   }
/*     */ 
/*     */   private void init(Map map, String flag) {
/*  54 */     Container c = getContentPane();
/*  55 */     String navigationId = (String)map.get("navigationId");
/*  56 */     String shopId = (String)map.get("shopId");
/*  57 */     map.remove("navigationId");
/*  58 */     map.remove("shopId");
/*     */ 
/*  61 */     JPanel panel_1 = new JPanel();
/*  62 */     panel_1.setLayout(new BorderLayout());
/*     */ 
/*  64 */     JPanel panel = new JPanel();
/*  65 */     panel.setLayout(null);
/*  66 */     this.scrollPane_1 = new JScrollPane();
/*  67 */     this.scrollPane_1.setPreferredSize(new Dimension(450, 200));
/*  68 */     panel_1.add(this.scrollPane_1);
/*  69 */     this.scrollPane_1.setViewportView(panel);
/*     */ 
/*  72 */     JLabel navigationStr = new JLabel("地    区:");
/*  73 */     navigationStr.setBounds(10, 10, 90, 25);
/*  74 */     navigationStr.setFont(new Font("宋体", 0, 18));
/*     */ 
/*  76 */     this.navigationComp = new JComboBox();
/*  77 */     new ComboBoxUtil().setComboBox(this.navigationComp, "navigationComp", 
/*  78 */       navigationId, "");
/*  79 */     this.navigationComp.setEnabled(false);
/*  80 */     this.navigationComp.setBounds(90, 10, 130, 25);
/*  81 */     this.navigationComp.setFont(new Font("宋体", 0, 18));
/*     */ 
/*  83 */     panel.add(this.navigationComp);
/*  84 */     panel.add(navigationStr);
/*     */ 
/*  87 */     JLabel shopTypeStr = new JLabel("商品类型:");
/*  88 */     shopTypeStr.setBounds(10, 40, 90, 30);
/*  89 */     shopTypeStr.setFont(new Font("宋体", 0, 18));
/*     */ 
/*  91 */     this.shopTypeComp = new JComboBox();
/*  92 */     this.shopTypeComp.setBounds(90, 40, 80, 30);
/*  93 */     this.shopTypeComp.setFont(new Font("宋体", 0, 18));
/*  94 */     this.shopTypeComp.setEnabled(false);
/*  95 */     new ComboBoxUtil().setComboBox(this.shopTypeComp, "shopTypeComp", 
/*  96 */       navigationId, shopId);
/*  97 */     this.primaryJf = new JTextField();
/*  98 */     this.primaryJf.setBounds(160, 40, 80, 30);
/*  99 */     this.primaryJf.setVisible(false);
/*     */ 
/* 101 */     panel.add(this.primaryJf);
/* 102 */     panel.add(shopTypeStr);
/* 103 */     panel.add(this.shopTypeComp);
/*     */ 
/* 106 */     JLabel detailsNameStr = new JLabel("商品名称:");
/* 107 */     detailsNameStr.setBounds(10, 75, 90, 30);
/* 108 */     detailsNameStr.setFont(new Font("宋体", 0, 18));
/*     */ 
/* 110 */     this.detailsNameJf = new JTextField();
/* 111 */     this.detailsNameJf.setBounds(90, 75, 180, 30);
/* 112 */     this.detailsNameJf.setFont(new Font("宋体", 0, 18));
/*     */ 
/* 114 */     panel.add(detailsNameStr);
/* 115 */     panel.add(this.detailsNameJf);
/*     */ 
/* 118 */     JLabel detailsPriceStr = new JLabel("商品价格:");
/* 119 */     detailsPriceStr.setBounds(10, 110, 90, 30);
/* 120 */     detailsPriceStr.setFont(new Font("宋体", 0, 18));
/* 121 */     this.detailsPriceJf = new JTextField();
/* 122 */     this.detailsPriceJf.setBounds(90, 110, 120, 30);
/* 123 */     this.detailsPriceJf.setFont(new Font("宋体", 0, 18));
/* 124 */     JLabel rmb = new JLabel("RMB");
/* 125 */     rmb.setBounds(210, 110, 40, 30);
/* 126 */     rmb.setFont(new Font("宋体", 0, 18));
/*     */ 
/* 128 */     panel.add(rmb);
/* 129 */     panel.add(detailsPriceStr);
/* 130 */     panel.add(this.detailsPriceJf);
/*     */ 
/* 133 */     this.detailsUntilJf = new JTextField();
/* 134 */     this.detailsUntilJf.setFont(new Font("宋体", 0, 18));
/* 135 */     JLabel detailsUntilStr = new JLabel("商品单位:");
/* 136 */     detailsUntilStr.setFont(new Font("宋体", 0, 18));
/* 137 */     detailsUntilStr.setBounds(10, 145, 90, 30);
/* 138 */     this.detailsUntilJf.setBounds(90, 145, 60, 30);
/*     */ 
/* 140 */     JLabel tip = new JLabel("提示:输入后，打印单显示单位！！！");
/* 141 */     tip.setForeground(Color.red);
/* 142 */     tip.setBounds(155, 145, 220, 30);
/*     */ 
/* 144 */     panel.add(this.detailsUntilJf);
/* 145 */     panel.add(detailsUntilStr);
/* 146 */     panel.add(tip);
/*     */ 
/* 148 */     c.add(panel_1);
/*     */ 
/* 151 */     this.okButton = new JButton("添加");
/* 152 */     this.okButton.setBounds(50, 180, 60, 30);
/* 153 */     this.okButton.addActionListener(new ActionListener(flag)
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 156 */         String detailsName = DetailsDbOpertionAction.this.detailsNameJf.getText();
/* 157 */         String detailsPrice = DetailsDbOpertionAction.this.detailsPriceJf.getText();
/* 158 */         String navigationId = ((Item)DetailsDbOpertionAction.this.navigationComp.getSelectedItem())
/* 159 */           .getKey();
/* 160 */         String shopId = ((Item)DetailsDbOpertionAction.this.shopTypeComp.getSelectedItem())
/* 161 */           .getKey();
/*     */ 
/* 163 */         if ((detailsName == null) || ("".equals(detailsName))) {
/* 164 */           JOptionPane.showMessageDialog(null, "商品名称不能为空！");
/* 165 */           return;
/*     */         }
/*     */ 
/* 168 */         if ((detailsPrice == null) || ("".equals(detailsPrice))) {
/* 169 */           JOptionPane.showMessageDialog(null, "商品价格不能为空！");
/* 170 */           return;
/*     */         }
/*     */ 
/* 173 */         if ((shopId == null) || ("".equals(shopId))) {
/* 174 */           JOptionPane.showMessageDialog(null, "商品类型信息获取失败,请联系管理员！");
/* 175 */           return;
/*     */         }
/*     */ 
/* 179 */         DetailsDbOpertionAction.this.sql = 
/* 180 */           ("select count(*) count from details where details_name='" + 
/* 180 */           detailsName + "' and shop_type_id='" + shopId + "'");
/* 181 */         if ("update".equals(this.val$flag))
/* 182 */           DetailsDbOpertionAction.this.sql = 
/* 183 */             (DetailsDbOpertionAction.this.sql + " and details_id!='" + DetailsDbOpertionAction.this.primaryJf.getText() + 
/* 183 */             "'");
/*     */         try
/*     */         {
/* 186 */           List countList = new BaseDao().queryDbBySql(DetailsDbOpertionAction.this.sql);
/* 187 */           Integer count = Integer.valueOf(Integer.parseInt(
/* 188 */             (String)((Map)countList.get(0))
/* 188 */             .get("COUNT")));
/* 189 */           if (count.intValue() > 0) {
/* 190 */             JOptionPane.showMessageDialog(null, "数据已存在！");
/* 191 */             return;
/*     */           }
/*     */         } catch (SQLException e1) {
/* 194 */           e1.printStackTrace();
/* 195 */           JOptionPane.showMessageDialog(null, "数据插入校验异常，请联系管理员！");
/* 196 */           return;
/*     */         }
/*     */ 
/* 199 */         if ("update".equals(this.val$flag)) {
/* 200 */           DetailsDbOpertionAction.this.sql = 
/* 203 */             ("update details set details_name='" + detailsName + 
/* 201 */             "',details_price='" + detailsPrice + 
/* 202 */             "' where details_id='" + DetailsDbOpertionAction.this.primaryJf.getText() + 
/* 203 */             "'");
/* 204 */           new BaseDao().updateDbBySql(DetailsDbOpertionAction.this.sql);
/* 205 */           JOptionPane.showMessageDialog(null, "数据修改成功！");
/*     */         } else {
/* 207 */           DetailsDbOpertionAction.this.sql = 
/* 212 */             ("insert into details(details_name,details_price,shop_type_id,details_until) values('" + 
/* 208 */             detailsName + 
/* 209 */             "','" + 
/* 210 */             detailsPrice + 
/* 211 */             "','" + 
/* 212 */             shopId + "','" + DetailsDbOpertionAction.this.detailsUntilJf.getText() + "')");
/* 213 */           new BaseDao().insertDbBySql(DetailsDbOpertionAction.this.sql);
/* 214 */           JOptionPane.showMessageDialog(null, "数据添加成功！");
/*     */         }
/*     */       }
/*     */     });
/* 219 */     this.cancelButton = new JButton("取消");
/* 220 */     this.cancelButton.setBounds(120, 180, 60, 30);
/* 221 */     this.cancelButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 224 */         DetailsDbOpertionAction.this.dispose();
/*     */       }
/*     */     });
/* 228 */     panel.add(this.okButton);
/* 229 */     panel.add(this.cancelButton);
/* 230 */     if ("update".equals(flag)) {
/* 231 */       Map temp = (Map)map.get("update");
/* 232 */       Set s = temp.keySet();
/* 233 */       Object[] o = null;
/* 234 */       for (String ss : s) {
/* 235 */         o = (Object[])temp.get(ss);
/*     */       }
/*     */ 
/* 238 */       this.detailsNameJf.setText((String)o[1]);
/* 239 */       this.detailsPriceJf.setText((String)o[2]);
/* 240 */       this.primaryJf.setText((String)o[3]);
/* 241 */       this.detailsUntilJf.setText((String)o[4]);
/*     */     }
/*     */ 
/* 244 */     panel.setPreferredSize(new Dimension(400, 300));
/*     */   }
/*     */ 
/*     */   public static String showDialog(Component relativeTo, Map map, String flag) {
/* 248 */     DetailsDbOpertionAction d = new DetailsDbOpertionAction(map, flag);
/* 249 */     d.setLocationRelativeTo(relativeTo);
/* 250 */     d.setVisible(true);
/* 251 */     return d.res;
/*     */   }
/*     */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.dboperation.DetailsDbOpertionAction
 * JD-Core Version:    0.5.4
 */