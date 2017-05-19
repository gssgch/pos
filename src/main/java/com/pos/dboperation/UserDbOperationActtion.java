/*     */ package com.pos.dboperation;
/*     */ 
/*     */ import com.pos.db.BaseDao;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Font;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
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
/*     */ public class UserDbOperationActtion extends JDialog
/*     */ {
/*     */   private JScrollPane scrollPane_1;
/*     */   private JTextField userNameJf;
/*     */   private JTextField tuoGaunJf;
/*     */   private JTextField cardNoJf;
/*     */   private JTextField cardShopJf;
/*     */   private JComboBox yearComb;
/*     */   private JComboBox monthComb;
/*     */   private JButton okButton;
/*     */   private JButton cancelButton;
/*     */   private JTextField primaryJf;
/*  40 */   private String sql = "";
/*  41 */   private Map map = new HashMap();
/*  42 */   private String rsString = "";
/*     */ 
/*     */   public UserDbOperationActtion(Map map, String flag)
/*     */   {
/*  46 */     setTitle("用户信息维护窗口");
/*  47 */     init(map, flag);
/*  48 */     setModal(true);
/*  49 */     setSize(400, 300);
/*     */   }
/*     */ 
/*     */   private void init(Map map, String flag) {
/*  53 */     Container c = getContentPane();
/*     */ 
/*  56 */     JPanel panel_1 = new JPanel();
/*  57 */     panel_1.setLayout(new BorderLayout());
/*     */ 
/*  59 */     JPanel panel = new JPanel();
/*  60 */     panel.setLayout(null);
/*  61 */     this.scrollPane_1 = new JScrollPane();
/*  62 */     this.scrollPane_1.setPreferredSize(new Dimension(450, 200));
/*  63 */     panel_1.add(this.scrollPane_1);
/*  64 */     this.scrollPane_1.setViewportView(panel);
/*     */ 
/*  67 */     JLabel userNameStr = new JLabel("姓  名:");
/*  68 */     userNameStr.setBounds(10, 10, 70, 25);
/*  69 */     userNameStr.setFont(new Font("宋体", 0, 18));
/*     */ 
/*  71 */     this.userNameJf = new JTextField();
/*  72 */     this.userNameJf.setBounds(75, 10, 130, 25);
/*  73 */     this.userNameJf.setFont(new Font("宋体", 0, 18));
/*     */ 
/*  75 */     panel.add(userNameStr);
/*  76 */     panel.add(this.userNameJf);
/*     */ 
/*  79 */     JLabel tuoGaunStr = new JLabel("托管号:");
/*  80 */     tuoGaunStr.setBounds(10, 40, 70, 30);
/*  81 */     tuoGaunStr.setFont(new Font("宋体", 0, 18));
/*  82 */     this.tuoGaunJf = new JTextField();
/*  83 */     this.tuoGaunJf.setBounds(75, 40, 80, 30);
/*  84 */     this.tuoGaunJf.setFont(new Font("宋体", 0, 18));
/*  85 */     this.primaryJf = new JTextField();
/*  86 */     this.primaryJf.setBounds(160, 40, 80, 30);
/*  87 */     this.primaryJf.setVisible(false);
/*     */ 
/*  89 */     panel.add(this.primaryJf);
/*  90 */     panel.add(tuoGaunStr);
/*  91 */     panel.add(this.tuoGaunJf);
/*     */ 
/*  94 */     JLabel cardNoStr = new JLabel("卡  号:");
/*  95 */     cardNoStr.setBounds(10, 75, 70, 30);
/*  96 */     cardNoStr.setFont(new Font("宋体", 0, 18));
/*  97 */     this.cardNoJf = new JTextField();
/*  98 */     this.cardNoJf.setBounds(75, 75, 180, 30);
/*  99 */     this.cardNoJf.setFont(new Font("宋体", 0, 18));
/*     */ 
/* 101 */     panel.add(cardNoStr);
/* 102 */     panel.add(this.cardNoJf);
/*     */ 
/* 105 */     JLabel cardShopStr = new JLabel("发卡行:");
/* 106 */     cardShopStr.setBounds(10, 110, 70, 30);
/* 107 */     cardShopStr.setFont(new Font("宋体", 0, 18));
/* 108 */     this.cardShopJf = new JTextField();
/* 109 */     this.cardShopJf.setBounds(75, 110, 200, 30);
/* 110 */     this.cardShopJf.setFont(new Font("宋体", 0, 18));
/*     */ 
/* 112 */     panel.add(cardShopStr);
/* 113 */     panel.add(this.cardShopJf);
/*     */ 
/* 116 */     JLabel valideStr = new JLabel("有效期:");
/* 117 */     valideStr.setBounds(10, 145, 70, 30);
/* 118 */     valideStr.setFont(new Font("宋体", 0, 18));
/*     */ 
/* 121 */     Calendar c1 = Calendar.getInstance();
/* 122 */     c1.setTime(new Date());
/* 123 */     int startYear = c1.get(1) - 20;
/* 124 */     int endYear = c1.get(1) + 20;
/*     */ 
/* 126 */     this.yearComb = new JComboBox();
/* 127 */     this.yearComb.setBounds(75, 145, 65, 30);
/* 128 */     this.yearComb.setFont(new Font("宋体", 0, 18));
/* 129 */     for (int i = endYear; i >= startYear; --i) {
/* 130 */       this.yearComb.addItem(i);
/*     */     }
/*     */ 
/* 133 */     this.yearComb.setSelectedItem(Integer.valueOf(endYear));
/*     */ 
/* 135 */     JLabel valideYearJfStr = new JLabel("年/");
/* 136 */     valideYearJfStr.setBounds(140, 145, 70, 30);
/* 137 */     valideYearJfStr.setFont(new Font("宋体", 0, 18));
/*     */ 
/* 140 */     int month = c1.get(2);
/* 141 */     this.monthComb = new JComboBox();
/* 142 */     this.monthComb.setBounds(167, 145, 45, 30);
/* 143 */     this.monthComb.setFont(new Font("宋体", 0, 18));
/* 144 */     for (int i = 12; i >= 1; --i) {
/* 145 */       if (i < 10)
/* 146 */         this.monthComb.addItem("0" + i);
/*     */       else {
/* 148 */         this.monthComb.addItem(i);
/*     */       }
/*     */     }
/* 151 */     this.monthComb.setSelectedItem((month < 10) ? "0" + month : Integer.valueOf(month));
/*     */ 
/* 153 */     JLabel valideMonthStr = new JLabel("月");
/* 154 */     valideMonthStr.setBounds(213, 145, 20, 30);
/* 155 */     valideMonthStr.setFont(new Font("宋体", 0, 18));
/*     */ 
/* 157 */     panel.add(valideStr);
/* 158 */     panel.add(this.yearComb);
/* 159 */     panel.add(valideYearJfStr);
/* 160 */     panel.add(this.monthComb);
/* 161 */     panel.add(valideMonthStr);
/*     */ 
/* 163 */     c.add(panel_1);
/*     */ 
/* 166 */     this.okButton = new JButton("添加");
/* 167 */     this.okButton.setBounds(50, 200, 60, 30);
/* 168 */     this.okButton.addActionListener(new ActionListener(flag)
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 171 */         String userName = UserDbOperationActtion.this.userNameJf.getText();
/* 172 */         String tuoGaun = UserDbOperationActtion.this.tuoGaunJf.getText();
/* 173 */         String cardNo = UserDbOperationActtion.this.cardNoJf.getText();
/* 174 */         String cardShop = UserDbOperationActtion.this.cardShopJf.getText();
/* 175 */         String yearStr = (String)UserDbOperationActtion.this.yearComb.getSelectedItem();
/* 176 */         String monthStr = (String)UserDbOperationActtion.this.monthComb.getSelectedItem();
/* 177 */         if ((userName == null) || ("".equals(userName))) {
/* 178 */           JOptionPane.showMessageDialog(null, "姓名不能为空！");
/* 179 */           return;
/*     */         }
/*     */ 
/* 182 */         if ((cardNo == null) || ("".equals(cardNo))) {
/* 183 */           JOptionPane.showMessageDialog(null, "卡号不能为空！");
/* 184 */           return;
/*     */         }
/* 186 */         if ((cardShop == null) || ("".equals(cardShop))) {
/* 187 */           JOptionPane.showMessageDialog(null, "发卡行不能为空！");
/* 188 */           return;
/*     */         }
/*     */ 
/* 192 */         UserDbOperationActtion.this.sql = 
/* 196 */           ("select count(*) count from user where user_name='" + 
/* 193 */           userName + "' and card_no='" + cardNo + 
/* 194 */           "' and valide_date='" + yearStr + "/" + monthStr + 
/* 195 */           "' and card_self='" + cardShop + "' and tuo_guan='" + 
/* 196 */           tuoGaun + "'");
/* 197 */         if ("update".equals(this.val$flag))
/* 198 */           UserDbOperationActtion.this.sql = (UserDbOperationActtion.this.sql + " and user_id!='" + UserDbOperationActtion.this.primaryJf.getText() + "'");
/*     */         try
/*     */         {
/* 201 */           List countList = new BaseDao().queryDbBySql(UserDbOperationActtion.this.sql);
/* 202 */           Integer count = Integer.valueOf(Integer.parseInt(
/* 203 */             (String)((Map)countList.get(0))
/* 203 */             .get("COUNT")));
/* 204 */           if (count.intValue() > 0) {
/* 205 */             JOptionPane.showMessageDialog(null, "数据已存在！");
/* 206 */             return;
/*     */           }
/*     */         } catch (SQLException e1) {
/* 209 */           e1.printStackTrace();
/* 210 */           JOptionPane.showMessageDialog(null, "数据插入校验异常，请联系管理员！");
/* 211 */           return;
/*     */         }
/*     */ 
/* 214 */         if ("update".equals(this.val$flag)) {
/* 215 */           UserDbOperationActtion.this.sql = 
/* 219 */             ("update user set user_name='" + userName + 
/* 216 */             "',card_no='" + cardNo + "',valide_date='" + 
/* 217 */             yearStr + "/" + monthStr + "',card_self='" + 
/* 218 */             cardShop + "',tuo_guan='" + tuoGaun + 
/* 219 */             "' where user_id='" + UserDbOperationActtion.this.primaryJf.getText() + "'");
/* 220 */           new BaseDao().updateDbBySql(UserDbOperationActtion.this.sql);
/* 221 */           JOptionPane.showMessageDialog(null, "数据修改成功！");
/*     */         } else {
/* 223 */           UserDbOperationActtion.this.sql = 
/* 235 */             ("insert into user(user_name,card_no,valide_date,card_self,tuo_guan) values('" + 
/* 224 */             userName + 
/* 225 */             "','" + 
/* 226 */             cardNo + 
/* 227 */             "','" + 
/* 228 */             yearStr + 
/* 229 */             "/" + 
/* 230 */             monthStr + 
/* 231 */             "','" + 
/* 232 */             cardShop + 
/* 233 */             "','" + 
/* 234 */             tuoGaun + 
/* 235 */             "')");
/* 236 */           new BaseDao().insertDbBySql(UserDbOperationActtion.this.sql);
/* 237 */           JOptionPane.showMessageDialog(null, "数据添加成功！");
/*     */         }
/*     */       }
/*     */     });
/* 242 */     this.cancelButton = new JButton("取消");
/* 243 */     this.cancelButton.setBounds(120, 200, 60, 30);
/* 244 */     this.cancelButton.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 247 */         UserDbOperationActtion.this.dispose();
/*     */       }
/*     */     });
/* 251 */     panel.add(this.okButton);
/* 252 */     panel.add(this.cancelButton);
/* 253 */     if ("update".equals(flag)) {
/* 254 */       Set s = map.keySet();
/* 255 */       Object[] o = null;
/* 256 */       for (String ss : s) {
/* 257 */         o = (Object[])map.get(ss);
/*     */       }
/* 259 */       this.userNameJf.setText((String)o[1]);
/* 260 */       this.tuoGaunJf.setText((String)o[2]);
/* 261 */       this.cardNoJf.setText((String)o[3]);
/* 262 */       String[] validStr = ((String)o[4]).split("/");
/* 263 */       if (validStr.length == 1) {
/* 264 */         this.yearComb.setSelectedIndex(0);
/* 265 */         this.monthComb.setSelectedIndex(0);
/*     */       } else {
/* 267 */         this.yearComb.setSelectedItem(validStr[0]);
/* 268 */         this.monthComb.setSelectedItem(validStr[1]);
/*     */       }
/* 270 */       this.cardShopJf.setText((String)o[5]);
/* 271 */       this.primaryJf.setText((String)o[6]);
/*     */     }
/*     */ 
/* 274 */     panel.setPreferredSize(new Dimension(400, 300));
/*     */   }
/*     */ 
/*     */   public static String showDialog(Component relativeTo, Map map, String flag) {
/* 278 */     UserDbOperationActtion d = new UserDbOperationActtion(map, flag);
/* 279 */     d.setLocationRelativeTo(relativeTo);
/* 280 */     d.setVisible(true);
/* 281 */     return d.rsString;
/*     */   }
/*     */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.dboperation.UserDbOperationActtion
 * JD-Core Version:    0.5.4
 */