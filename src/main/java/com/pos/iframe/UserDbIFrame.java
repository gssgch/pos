/*     */ package com.pos.iframe;
/*     */ 
/*     */ import com.pos.db.BaseDao;
/*     */ import com.pos.dboperation.UserDbOperationActtion;
/*     */ import com.pos.table.CheckHeaderCellRender;
/*     */ import com.pos.table.TableModleProxy;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.sql.SQLException;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JScrollPane;
/*     */ import javax.swing.JTable;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.table.JTableHeader;
/*     */ import javax.swing.table.TableColumn;
/*     */ import javax.swing.table.TableColumnModel;
/*     */ 
/*     */ public class UserDbIFrame extends JInternalFrame
/*     */ {
/*     */   private String region;
/*     */   private String sql;
/*     */   private JScrollPane scrollPane_1;
/*     */   private JScrollPane js;
/*     */   private JTable table;
/*     */   private Object[][] data;
/*     */   private JLabel pageLabel;
/*     */   private TableModleProxy tableModel;
/*     */   private JTextField userNameJf;
/*     */   private JTextField tuoguanJf;
/*     */   private JButton colseBtn;
/*     */   private JTextField currentPageJf;
/*     */   private JButton previousPage;
/*     */   private JButton nextPage;
/*     */   
/*     */   public UserDbIFrame(String region)
/*     */   {
/*  47 */     super(region);
/*  48 */     this.region = region;
/*  49 */     setIconifiable(true);
/*  50 */     setClosable(true);
/*  51 */     setBounds(30, 30, 720, 600);
/*     */     
/*  53 */     JPanel panel_1 = new JPanel();
/*  54 */     panel_1.setLayout(new BorderLayout());
/*     */     
/*  56 */     JPanel panel = new JPanel();
/*  57 */     this.scrollPane_1 = new JScrollPane();
/*  58 */     this.scrollPane_1.setPreferredSize(new Dimension(450, 200));
/*  59 */     panel_1.add(this.scrollPane_1);
/*  60 */     this.scrollPane_1.setViewportView(panel);
/*     */     
/*  62 */     addJpanelObject(panel);
/*  63 */     add(panel_1);
/*  64 */     setVisible(true);
/*     */   }
/*     */   
/*     */   public void addJpanelObject(JPanel panel)
/*     */   {
/*  69 */     panel.setLayout(null);
/*     */     
/*  71 */     JLabel label1 = new JLabel(
/*  72 */       "--查询条件----------------------------------------------------------------------------------");
/*  73 */     label1.setBounds(20, 10, 600, 20);
/*  74 */     panel.add(label1);
/*     */     
/*     */ 
/*  77 */     JLabel userNameStr = new JLabel("用户名称:");
/*  78 */     userNameStr.setBounds(20, 30, 60, 20);
/*     */     
/*  80 */     this.userNameJf = new JTextField();
/*  81 */     this.userNameJf.setBounds(75, 30, 120, 20);
/*  82 */     panel.add(userNameStr);
/*  83 */     panel.add(this.userNameJf);
/*     */     
/*     */ 
/*  86 */     JLabel tuoguanStr = new JLabel("托管号:");
/*  87 */     tuoguanStr.setBounds(220, 30, 60, 20);
/*     */     
/*  89 */     this.tuoguanJf = new JTextField();
/*  90 */     this.tuoguanJf.setBounds(265, 30, 120, 20);
/*  91 */     panel.add(tuoguanStr);
/*  92 */     panel.add(this.tuoguanJf);
/*     */     
/*     */ 
/*  95 */     JButton queryBtn = new JButton("查询");
/*  96 */     queryBtn.setBounds(420, 30, 60, 20);
/*  97 */     panel.add(queryBtn);
/*  98 */     queryBtn.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 101 */         UserDbIFrame.this.initLabel("query");
/* 102 */         UserDbIFrame.this.resetTableMap();
/*     */       }
/* 104 */     });
/* 105 */     JLabel label2 = new JLabel(
/* 106 */       "----查询结果集----------------------------------------------------------------------------------");
/* 107 */     label2.setBounds(20, 60, 600, 20);
/* 108 */     panel.add(label2);
/*     */     
/* 110 */     JPanel jp = new JPanel();
/* 111 */     jp.setBounds(20, 100, 680, 325);
/* 112 */     jp.setLayout(new BorderLayout());
/*     */     
/* 114 */     JScrollPane jsp = new JScrollPane();
/* 115 */     jsp.setPreferredSize(new Dimension(500, 0));
/* 116 */     jp.add(jsp);
/*     */     
/* 118 */     this.table = new JTable();
/* 119 */     this.table.setSize(800, 800);
/*     */     
/* 121 */     jsp.setViewportView(this.table);
/* 122 */     this.table.setAutoscrolls(true);
/* 123 */     this.table.setRowHeight(15);
/*     */     
/* 125 */     this.table.setAutoResizeMode(0);
/* 126 */     this.table.updateUI();
/* 127 */     panel.add(jp);
/*     */     
/*     */ 
/* 130 */     this.previousPage = new JButton("上一页");
/* 131 */     this.previousPage.setBounds(20, 80, 70, 20);
/* 132 */     panel.add(this.previousPage);
/* 133 */     this.previousPage.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 136 */         UserDbIFrame.this.initLabel("previousPage");
/* 137 */         UserDbIFrame.this.resetTableMap();
/*     */       }
/*     */       
/*     */ 
/* 141 */     });
/* 142 */     this.nextPage = new JButton("下一页");
/* 143 */     this.nextPage.setBounds(100, 80, 70, 20);
/* 144 */     panel.add(this.nextPage);
/* 145 */     this.nextPage.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 148 */         UserDbIFrame.this.initLabel("nextPage");
/* 149 */         UserDbIFrame.this.resetTableMap();
/*     */ 
/*     */       }
/*     */       
/*     */ 
/* 154 */     });
/* 155 */     JButton insertBtn = new JButton("添加");
/* 156 */     insertBtn.setBounds(180, 80, 70, 20);
/* 157 */     insertBtn.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e)
/*     */       {
/* 161 */         UserDbOperationActtion.showDialog(UserDbIFrame.this, null, "insert");
/* 162 */         UserDbIFrame.this.initLabel("insert");
/*     */       }
/* 164 */     });
/* 165 */     panel.add(insertBtn);
/*     */     
/*     */ 
/* 168 */     JButton updateBtn = new JButton("修改");
/* 169 */     updateBtn.setBounds(260, 80, 70, 20);
/* 170 */     updateBtn.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 173 */         Map m = UserDbIFrame.this.tableModel.getDataMap();
/* 174 */         if ((m == null) || (m.size() == 0)) {
/* 175 */           JOptionPane.showMessageDialog(null, "请选择一条记录！");
/* 176 */           return; }
/* 177 */         if (m.size() > 1) {
/* 178 */           JOptionPane.showMessageDialog(null, "修改时，只能选择一条记录！");
/* 179 */           return;
/*     */         }
/*     */         try {
/* 182 */           UserDbOperationActtion.showDialog(UserDbIFrame.this, m, "update");
/*     */         } catch (Exception e1) {
/* 184 */           e1.printStackTrace();
/* 185 */           JOptionPane.showMessageDialog(null, "数据异常！");
/*     */         }
/* 187 */         UserDbIFrame.this.initLabel("update");
/*     */       }
/* 189 */     });
/* 190 */     panel.add(updateBtn);
/*     */     
/*     */ 
/* 193 */     JButton deleteBtn = new JButton("删除");
/* 194 */     deleteBtn.setBounds(340, 80, 70, 20);
/* 195 */     deleteBtn.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 198 */         String str = UserDbIFrame.this.dbOperation("delete");
/* 199 */         if (!"".equals(str)) {
/* 200 */           JOptionPane.showMessageDialog(null, str);
/*     */         } else {
/* 202 */           JOptionPane.showMessageDialog(null, "数据删除成功！");
/*     */         }
/* 204 */         UserDbIFrame.this.initLabel("delete");
/*     */       }
/* 206 */     });
/* 207 */     panel.add(deleteBtn);
/*     */     
/*     */ 
/* 210 */     this.currentPageJf = new JTextField();
/* 211 */     this.currentPageJf.setBounds(420, 80, 70, 20);
/* 212 */     this.currentPageJf.setVisible(false);
/* 213 */     panel.add(this.currentPageJf);
/*     */     
/*     */ 
/* 216 */     this.pageLabel = new JLabel();
/* 217 */     panel.add(this.pageLabel);
/*     */     
/*     */ 
/* 220 */     this.colseBtn = new JButton();
/* 221 */     this.colseBtn.setText("关闭");
/* 222 */     this.colseBtn.addActionListener(new CloseActionListener());
/* 223 */     panel.add(this.colseBtn);
/*     */     
/* 225 */     panel.setPreferredSize(new Dimension(720, 600));
/* 226 */     initLabel("init");
/*     */   }
/*     */   
/*     */   public void initLabel(String flag) {
/* 230 */     int page = (this.currentPageJf.getText() == null) || 
/* 231 */       ("".equals(this.currentPageJf.getText())) ? 1 : 
/* 232 */       Integer.parseInt(this.currentPageJf.getText());
/*     */     
/* 234 */     int pageCount = 0;
/* 235 */     int dbCount = getDbCount(this.userNameJf.getText(), this.tuoguanJf.getText()).intValue();
/*     */     
/* 237 */     if (dbCount % 20 == 0) {
/* 238 */       pageCount = dbCount / 20;
/*     */     } else {
/* 240 */       pageCount = dbCount / 20 + 1;
/*     */     }
/*     */     
/* 243 */     if (("query".equals(flag)) || ("insert".equals(flag)) || 
/* 244 */       ("update".equals(flag)) || ("delete".equals(flag)) || 
/* 245 */       ("init".equals(flag))) {
/* 246 */       page = 1;
/* 247 */     } else if ("previousPage".equals(flag)) {
/* 248 */       if (page > 1) {
/* 249 */         page--;
/*     */       }
/* 251 */     } else if (("nextPage".equals(flag)) && 
/* 252 */       (page < pageCount)) {
/* 253 */       page++;
/*     */     }
/*     */     
/* 256 */     initTable(this.userNameJf.getText(), this.tuoguanJf.getText(), page);
/*     */     
/* 258 */     if (pageCount == 1) {
/* 259 */       this.nextPage.setEnabled(false);
/* 260 */       this.previousPage.setEnabled(false);
/* 261 */     } else if (page == 1) {
/* 262 */       this.nextPage.setEnabled(true);
/* 263 */       this.previousPage.setEnabled(false);
/* 264 */     } else if ((page > 1) && (page < pageCount)) {
/* 265 */       this.nextPage.setEnabled(true);
/* 266 */       this.previousPage.setEnabled(true);
/*     */     } else {
/* 268 */       this.nextPage.setEnabled(false);
/* 269 */       this.previousPage.setEnabled(true);
/*     */     }
/* 271 */     String str = "总条数：" + dbCount + "，每页显示20条，当前第" + page + "页，总共：" + 
/* 272 */       pageCount + "页";
/* 273 */     this.pageLabel.setText(str);
/* 274 */     this.pageLabel.setBounds(20, 425, str.getBytes().length * 6, 20);
/* 275 */     this.colseBtn.setBounds(30 + str.getBytes().length * 6, 425, 70, 20);
/* 276 */     this.currentPageJf.setText(page+"");
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public void initTable(String userName, String tuoguan, int currentPage)
/*     */   {
/* 286 */     String[] title = { "复选框", "用户名", "托管号", "卡号", "有效期", "发行卡", "主键" };
/* 287 */     this.data = getData(userName, tuoguan, title, currentPage);
/* 288 */     int[] length = new int[title.length];
/*     */     
/* 290 */     if (this.data == null) {
/* 291 */       this.data = new Object[0][0];
/*     */     } else {
/* 293 */       length[0] = 50;
/* 294 */       for (int i = 0; i < this.data.length; i++) {
/* 295 */         long temp = 0L;
/* 296 */         for (int j = 1; j < this.data[i].length; j++) {
/* 297 */           int oldCol = 0;
/* 298 */           int newCol = 0;
/* 299 */           if (i == 0) {
/* 300 */             if ((this.data[i][j] != null) && 
/* 301 */               (!"".equals(this.data[i][j].toString()))) {
/* 302 */               length[j] = (this.data[i][j].toString().getBytes().length * 8);
/*     */             } else {
/* 304 */               length[j] = 50;
/*     */             }
/*     */           }
/* 307 */           else if ((this.data[i][j] != null) && 
/* 308 */             (!"".equals(this.data[i][j].toString()))) {
/* 309 */             oldCol = length[j];
/* 310 */             newCol = this.data[i][j].toString().getBytes().length * 8;
/* 311 */             if (oldCol < newCol) {
/* 312 */               length[j] = newCol;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */     }
/*     */     
/*     */ 
/* 320 */     this.tableModel = new TableModleProxy(title, this.data);
/* 321 */     this.table.setModel(this.tableModel);
/* 322 */     TableColumnModel columnModel = this.table.getColumnModel();
/* 323 */     for (int t = 0; t < length.length; t++) {
/* 324 */       if (length[t] < 50) {
/* 325 */         columnModel.getColumn(t).setPreferredWidth(50);
/*     */       } else {
/* 327 */         columnModel.getColumn(t).setPreferredWidth(length[t]);
/*     */       }
/*     */     }
/*     */     
/* 331 */     columnModel.getColumn(6).setMinWidth(0);
/* 332 */     columnModel.getColumn(6).setMaxWidth(0);
/* 333 */     columnModel.getColumn(6).setWidth(0);
/* 334 */     columnModel.getColumn(6).setPreferredWidth(0);
/*     */     
/* 336 */     this.table.getTableHeader().setDefaultRenderer(
/* 337 */       new CheckHeaderCellRender(this.table));
/* 338 */     this.table.updateUI();
/*     */   }
/*     */   
/*     */   public Object[][] getData(String userName, String tuoguan, String[] title, int currentPage)
/*     */   {
/* 343 */     Object[][] obj = null;
/* 344 */     this.sql = "select user_id,user_name,card_no,valide_date,card_self,tuo_guan from user where 1=1 ";
/* 345 */     if ((userName != null) && (!"".equals(userName))) {
/* 346 */       this.sql = (this.sql + " and user_name like '%" + userName + "%' ");
/*     */     }
/* 348 */     if ((tuoguan != null) && (!"".equals(tuoguan))) {
/* 349 */       this.sql = (this.sql + " and tuo_guan like '%" + tuoguan + "%' ");
/*     */     }
/* 351 */     this.sql = 
/* 352 */       (this.sql + " order by user_id asc  limit " + (currentPage - 1) * 20 + ",20");
/*     */     try
/*     */     {
/* 355 */       List<Map> list = new BaseDao().queryDbBySql(this.sql);
/* 356 */       if ((list != null) && (list.size() > 0)) {
/* 357 */         obj = new Object[list.size()][7];
/* 358 */         Map m = null;
/* 359 */         for (int j = 0; j < list.size(); j++) {
/* 360 */           m = (Map)list.get(j);
/* 361 */           obj[j][0] = new Boolean(false);
/* 362 */           obj[j][1] = m.get("USER_NAME");
/* 363 */           obj[j][2] = m.get("TUO_GUAN");
/* 364 */           obj[j][3] = m.get("CARD_NO");
/* 365 */           obj[j][4] = m.get("VALIDE_DATE");
/* 366 */           obj[j][5] = m.get("CARD_SELF");
/* 367 */           obj[j][6] = m.get("USER_ID");
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (SQLException e) {
/* 372 */       e.printStackTrace();
/*     */     }
/* 374 */     return obj;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public String dbOperation(String flag)
/*     */   {
/* 383 */     String sql = "";
/* 384 */     Map m = this.tableModel.getDataMap();
/* 385 */     StringBuffer sb = new StringBuffer();
/* 386 */     String str = "";
/* 387 */     if ("delete".equals(flag)) {
/* 388 */       if (m.size() == 0) {
/* 389 */         str = "请选择一条记录";
/*     */       } else {
/* 391 */         Set<String> ss = m.keySet();
/* 392 */         int i = 0;
/* 393 */         for (String s : ss) {
/* 394 */           Object[] o = (Object[])m.get(s);
/* 395 */           if (i == 0) {
/* 396 */             sb.append("'" + o[6] + "'");
/*     */           } else {
/* 398 */             sb.append(",'" + o[6] + "'");
/*     */           }
/* 400 */           i++;
/*     */         }
/* 402 */         sql = 
/* 403 */           "delete from user where user_id in(" + sb.toString() + ")";
/* 404 */         new BaseDao().deleteDbBySql(sql);
/*     */       }
/*     */     }
/* 407 */     return str;
/*     */   }
/*     */   
/*     */   public Integer getDbCount(String userName, String tuoGuan) {
/* 411 */     Integer count = Integer.valueOf(0);
/*     */     try {
/* 413 */       this.sql = "select count(*) count from user where 1=1 ";
/* 414 */       if ((userName != null) && (!"".equals(userName))) {
/* 415 */         this.sql = (this.sql + " and USER_NAME like '%" + userName + "%'");
/*     */       }
/* 417 */       if ((tuoGuan != null) && (!"".equals(tuoGuan))) {
/* 418 */         this.sql = (this.sql + " and TUO_GUAN like '%" + tuoGuan + "%'");
/*     */       }
/* 420 */       List<Map> list = new BaseDao().queryDbBySql(this.sql);
/* 421 */       count = Integer.valueOf(Integer.parseInt((String)((Map)list.get(0)).get("COUNT")));
/*     */     } catch (SQLException e) {
/* 423 */       e.printStackTrace();
/*     */     }
/* 425 */     return count;
/*     */   }
/*     */   
/*     */   class CloseActionListener implements ActionListener { CloseActionListener() {}
/*     */     
/* 430 */     public void actionPerformed(ActionEvent e) { UserDbIFrame.this.doDefaultCloseAction(); }
/*     */   }
/*     */   
/*     */   public void resetTableMap()
/*     */   {
/* 435 */     this.tableModel.setDataMap(new HashMap());
/*     */   }
/*     */ }


/* Location:              F:\otec\pos软件\20170517\1.jar!\com\pos\iframe\UserDbIFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */