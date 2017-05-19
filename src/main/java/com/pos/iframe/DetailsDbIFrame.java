/*     */ package com.pos.iframe;
/*     */ 
/*     */ import com.pos.db.BaseDao;
/*     */ import com.pos.dboperation.DetailsDbOpertionAction;
/*     */ import com.pos.table.CheckHeaderCellRender;
/*     */ import com.pos.table.TableModleProxy;
/*     */ import com.pos.util.ComboBoxUtil;
/*     */ import com.pos.vo.Item;
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
/*     */ import javax.swing.JComboBox;
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
/*     */ public class DetailsDbIFrame extends JInternalFrame
/*     */ {
/*     */   private String region;
/*     */   private String sql;
/*     */   private JScrollPane scrollPane_1;
/*     */   private JScrollPane js;
/*     */   private JTable table;
/*     */   private Object[][] data;
/*     */   private JLabel pageLabel;
/*     */   private TableModleProxy tableModel;
/*     */   private JButton colseBtn;
/*     */   private JTextField currentPageJf;
/*     */   private JButton previousPage;
/*     */   private JButton nextPage;
/*     */   private JComboBox navigationComp;
/*     */   private JComboBox shopTypeComp;
/*     */   private JTextField detailsName;
/*     */   private JTextField navigationJF2;
/*     */   private JTextField typeJF2;
/*     */ 
/*     */   public DetailsDbIFrame(String region)
/*     */   {
/*  51 */     super(region);
/*  52 */     this.region = region;
/*  53 */     setIconifiable(true);
/*  54 */     setClosable(true);
/*  55 */     setBounds(30, 30, 720, 600);
/*     */ 
/*  57 */     JPanel panel_1 = new JPanel();
/*  58 */     panel_1.setLayout(new BorderLayout());
/*     */ 
/*  60 */     JPanel panel = new JPanel();
/*  61 */     this.scrollPane_1 = new JScrollPane();
/*  62 */     this.scrollPane_1.setPreferredSize(new Dimension(450, 200));
/*  63 */     panel_1.add(this.scrollPane_1);
/*  64 */     this.scrollPane_1.setViewportView(panel);
/*     */ 
/*  66 */     addJpanelObject(panel);
/*  67 */     add(panel_1);
/*  68 */     setVisible(true);
/*     */   }
/*     */ 
/*     */   public void addJpanelObject(JPanel panel)
/*     */   {
/*  73 */     panel.setLayout(null);
/*     */ 
/*  75 */     JLabel label1 = new JLabel(
/*  76 */       "--查询条件----------------------------------------------------------------------------------");
/*  77 */     label1.setBounds(20, 10, 600, 20);
/*  78 */     panel.add(label1);
/*     */ 
/*  81 */     JLabel navigationName = new JLabel("地区:");
/*  82 */     navigationName.setBounds(20, 30, 60, 20);
/*     */ 
/*  85 */     this.navigationComp = new JComboBox();
/*  86 */     new ComboBoxUtil()
/*  87 */       .setComboBox(this.navigationComp, "navigationComp", "", "");
/*  88 */     this.navigationComp.setBounds(50, 30, 80, 20);
/*  89 */     panel.add(navigationName);
/*  90 */     panel.add(this.navigationComp);
/*  91 */     this.navigationJF2 = 
/*  92 */       new JTextField(((Item)this.navigationComp.getSelectedItem()).getKey());
/*  93 */     this.navigationComp.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/*  96 */         String navigation1 = ((Item)DetailsDbIFrame.this.navigationComp.getSelectedItem())
/*  97 */           .getKey();
/*  98 */         String navigation2 = DetailsDbIFrame.this.navigationJF2.getText();
/*  99 */         if (!navigation2.equals(navigation1)) {
/* 100 */           DetailsDbIFrame.this.initLabel("init");
/* 101 */           DetailsDbIFrame.this.navigationJF2.setText(navigation1);
/*     */         }
/*     */       }
/*     */     });
/* 107 */     JLabel shopTypeL = new JLabel("商品类型：");
/* 108 */     shopTypeL.setBounds(150, 30, 80, 20);
/*     */ 
/* 110 */     this.shopTypeComp = new JComboBox();
/* 111 */     new ComboBoxUtil().setComboBox(this.shopTypeComp, "shopTypeComp", 
/* 112 */       ((Item)this.navigationComp.getSelectedItem()).getKey(), "");
/* 113 */     this.shopTypeComp.setBounds(205, 30, 60, 20);
/* 114 */     this.typeJF2 = 
/* 115 */       new JTextField(((Item)this.shopTypeComp.getSelectedItem()).getKey());
/*     */ 
/* 117 */     this.shopTypeComp.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 120 */         String shopType1 = ((Item)DetailsDbIFrame.this.shopTypeComp.getSelectedItem())
/* 121 */           .getKey();
/* 122 */         String shopType2 = DetailsDbIFrame.this.typeJF2.getText();
/* 123 */         if (!shopType2.equals(shopType1)) {
/* 124 */           DetailsDbIFrame.this.initLabel("init");
/* 125 */           DetailsDbIFrame.this.typeJF2.setText(shopType1);
/*     */         }
/*     */       }
/*     */     });
/* 130 */     panel.add(this.navigationJF2);
/* 131 */     panel.add(this.typeJF2);
/*     */ 
/* 133 */     panel.add(shopTypeL);
/* 134 */     panel.add(this.shopTypeComp);
/*     */ 
/* 137 */     this.detailsName = new JTextField();
/* 138 */     JLabel detailsL = new JLabel("商品名称:");
/* 139 */     this.detailsName.setBounds(340, 30, 130, 20);
/* 140 */     detailsL.setBounds(285, 30, 80, 20);
/*     */ 
/* 142 */     panel.add(this.detailsName);
/* 143 */     panel.add(detailsL);
/*     */ 
/* 146 */     JButton queryBtn = new JButton("查询");
/* 147 */     queryBtn.setBounds(480, 30, 60, 20);
/* 148 */     panel.add(queryBtn);
/* 149 */     queryBtn.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 152 */         DetailsDbIFrame.this.initLabel("query");
/* 153 */         DetailsDbIFrame.this.resetTableMap();
/*     */       }
/*     */     });
/* 156 */     JLabel label2 = new JLabel(
/* 157 */       "----查询结果集----------------------------------------------------------------------------------");
/* 158 */     label2.setBounds(20, 55, 600, 20);
/* 159 */     panel.add(label2);
/*     */ 
/* 161 */     JPanel jp = new JPanel();
/* 162 */     jp.setBounds(20, 100, 680, 325);
/* 163 */     jp.setLayout(new BorderLayout());
/*     */ 
/* 165 */     JScrollPane jsp = new JScrollPane();
/* 166 */     jsp.setPreferredSize(new Dimension(500, 0));
/* 167 */     jp.add(jsp);
/*     */ 
/* 169 */     this.table = new JTable();
/* 170 */     this.table.setSize(800, 800);
/*     */ 
/* 172 */     jsp.setViewportView(this.table);
/* 173 */     this.table.setAutoscrolls(true);
/* 174 */     this.table.setRowHeight(15);
/*     */ 
/* 176 */     this.table.setAutoResizeMode(0);
/* 177 */     this.table.updateUI();
/* 178 */     panel.add(jp);
/*     */ 
/* 181 */     this.previousPage = new JButton("上一页");
/* 182 */     this.previousPage.setBounds(20, 80, 70, 20);
/* 183 */     panel.add(this.previousPage);
/* 184 */     this.previousPage.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 187 */         DetailsDbIFrame.this.initLabel("previousPage");
/* 188 */         DetailsDbIFrame.this.resetTableMap();
/*     */       }
/*     */     });
/* 193 */     this.nextPage = new JButton("下一页");
/* 194 */     this.nextPage.setBounds(100, 80, 70, 20);
/* 195 */     panel.add(this.nextPage);
/* 196 */     this.nextPage.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 199 */         DetailsDbIFrame.this.initLabel("nextPage");
/* 200 */         DetailsDbIFrame.this.resetTableMap();
/*     */       }
/*     */     });
/* 206 */     JButton insertBtn = new JButton("添加");
/* 207 */     insertBtn.setBounds(180, 80, 70, 20);
/* 208 */     insertBtn.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 211 */         Map data = new HashMap();
/* 212 */         data.put("navigationId", 
/* 213 */           ((Item)DetailsDbIFrame.this.navigationComp.getSelectedItem()).getKey());
/* 214 */         data.put("shopId", 
/* 215 */           ((Item)DetailsDbIFrame.this.shopTypeComp.getSelectedItem()).getKey());
/* 216 */         DetailsDbOpertionAction.showDialog(DetailsDbIFrame.this, data, 
/* 217 */           "insert");
/* 218 */         DetailsDbIFrame.this.initLabel("insert");
/*     */       }
/*     */     });
/* 221 */     panel.add(insertBtn);
/*     */ 
/* 224 */     JButton updateBtn = new JButton("修改");
/* 225 */     updateBtn.setBounds(260, 80, 70, 20);
/* 226 */     updateBtn.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 229 */         Map m = DetailsDbIFrame.this.tableModel.getDataMap();
/* 230 */         Map data = new HashMap();
/* 231 */         data.put("update", m);
/* 232 */         data.put("navigationId", 
/* 233 */           ((Item)DetailsDbIFrame.this.navigationComp.getSelectedItem()).getKey());
/* 234 */         data.put("shopId", 
/* 235 */           ((Item)DetailsDbIFrame.this.shopTypeComp.getSelectedItem()).getKey());
/* 236 */         if ((m == null) || (m.size() == 0)) {
/* 237 */           JOptionPane.showMessageDialog(null, "请选择一条记录！");
/* 238 */           return;
/* 239 */         }if (m.size() > 1) {
/* 240 */           JOptionPane.showMessageDialog(null, "修改时，只能选择一条记录！");
/* 241 */           return;
/*     */         }
/*     */         try {
/* 244 */           DetailsDbOpertionAction.showDialog(DetailsDbIFrame.this, data, 
/* 245 */             "update");
/*     */         } catch (Exception e1) {
/* 247 */           e1.printStackTrace();
/* 248 */           JOptionPane.showMessageDialog(null, "数据异常！");
/*     */         }
/* 250 */         DetailsDbIFrame.this.initLabel("update");
/*     */       }
/*     */     });
/* 253 */     panel.add(updateBtn);
/*     */ 
/* 256 */     JButton deleteBtn = new JButton("删除");
/* 257 */     deleteBtn.setBounds(340, 80, 70, 20);
/* 258 */     deleteBtn.addActionListener(new ActionListener()
/*     */     {
/*     */       public void actionPerformed(ActionEvent e) {
/* 261 */         String str = DetailsDbIFrame.this.dbOperation("delete");
/* 262 */         if (!"".equals(str))
/* 263 */           JOptionPane.showMessageDialog(null, str);
/*     */         else {
/* 265 */           JOptionPane.showMessageDialog(null, "数据删除成功！");
/*     */         }
/* 267 */         DetailsDbIFrame.this.initLabel("delete");
/*     */       }
/*     */     });
/* 270 */     panel.add(deleteBtn);
/*     */ 
/* 273 */     this.currentPageJf = new JTextField();
/* 274 */     this.currentPageJf.setBounds(420, 80, 70, 20);
/* 275 */     this.currentPageJf.setVisible(false);
/* 276 */     panel.add(this.currentPageJf);
/*     */ 
/* 279 */     this.pageLabel = new JLabel();
/* 280 */     panel.add(this.pageLabel);
/*     */ 
/* 283 */     this.colseBtn = new JButton();
/* 284 */     this.colseBtn.setText("关闭");
/* 285 */     this.colseBtn.addActionListener(new CloseActionListener());
/* 286 */     panel.add(this.colseBtn);
/*     */ 
/* 288 */     panel.setPreferredSize(new Dimension(720, 600));
/* 289 */     initLabel("init");
/*     */   }
/*     */ 
/*     */   public void initLabel(String flag) {
/* 293 */     int page = ((this.currentPageJf.getText() == null) || 
/* 294 */       ("".equals(this.currentPageJf.getText()))) ? 
/* 294 */       1 : 
/* 295 */       Integer.parseInt(this.currentPageJf.getText());
/*     */ 
/* 297 */     int pageCount = 0;
/* 298 */     int dbCount = getDbCount(this.detailsName.getText(), 
/* 299 */       ((Item)this.shopTypeComp.getSelectedItem()).getKey()).intValue();
/*     */ 
/* 301 */     if (dbCount % 20 == 0)
/* 302 */       pageCount = dbCount / 20;
/*     */     else {
/* 304 */       pageCount = dbCount / 20 + 1;
/*     */     }
/*     */ 
/* 307 */     if (("query".equals(flag)) || ("insert".equals(flag)) || 
/* 308 */       ("update".equals(flag)) || ("delete".equals(flag)) || 
/* 309 */       ("init".equals(flag)))
/* 310 */       page = 1;
/* 311 */     else if ("previousPage".equals(flag)) {
/* 312 */       if (page > 1)
/* 313 */         --page;
/*     */     }
/* 315 */     else if (("nextPage".equals(flag)) && 
/* 316 */       (page < pageCount)) {
/* 317 */       ++page;
/*     */     }
/*     */ 
/* 322 */     initTable(page);
/*     */ 
/* 324 */     if (pageCount == 1) {
/* 325 */       this.nextPage.setEnabled(false);
/* 326 */       this.previousPage.setEnabled(false);
/* 327 */     } else if (page == 1) {
/* 328 */       this.nextPage.setEnabled(true);
/* 329 */       this.previousPage.setEnabled(false);
/* 330 */     } else if ((page > 1) && (page < pageCount)) {
/* 331 */       this.nextPage.setEnabled(true);
/* 332 */       this.previousPage.setEnabled(true);
/*     */     } else {
/* 334 */       this.nextPage.setEnabled(false);
/* 335 */       this.previousPage.setEnabled(true);
/*     */     }
/* 337 */     String str = "总条数：" + dbCount + "，每页显示20条，当前第" + page + "页，总共：" + 
/* 338 */       pageCount + "页";
/* 339 */     this.pageLabel.setText(str);
/* 340 */     this.pageLabel.setBounds(20, 425, str.getBytes().length * 6, 20);
/* 341 */     this.colseBtn.setBounds(30 + str.getBytes().length * 6, 425, 70, 20);
/* 342 */     this.currentPageJf.setText(page);
/*     */   }
/*     */ 
/*     */   public void initTable(int currentPage)
/*     */   {
/* 352 */     String[] title = { "复选框", "商品名称", "商品价格", "主键", "商品单位" };
/* 353 */     this.data = getData(title, currentPage);
/* 354 */     int[] length = new int[title.length];
/*     */ 
/* 356 */     if (this.data == null) {
/* 357 */       this.data = new Object[0][0];
/*     */     } else {
/* 359 */       length[0] = 50;
/* 360 */       for (int i = 1; i < this.data.length; ++i) {
/* 361 */         long temp = 0L;
/* 362 */         for (int j = 1; j < this.data[i].length; ++j) {
/* 363 */           int oldCol = 0;
/* 364 */           int newCol = 0;
/* 365 */           if (i == 0) {
/* 366 */             if ((this.data[i][j] != null) && 
/* 367 */               (!"".equals(this.data[i][j].toString())))
/* 368 */               length[j] = (this.data[i][j].toString().getBytes().length * 8);
/*     */             else
/* 370 */               length[j] = 50;
/*     */           }
/*     */           else {
/* 373 */             if ((this.data[i][j] == null) || 
/* 374 */               ("".equals(this.data[i][j].toString()))) continue;
/* 375 */             oldCol = length[j];
/* 376 */             newCol = this.data[i][j].toString().getBytes().length * 8;
/* 377 */             if (oldCol < newCol) {
/* 378 */               length[j] = newCol;
/*     */             }
/*     */           }
/*     */         }
/*     */       }
/*     */ 
/*     */     }
/*     */ 
/* 386 */     this.tableModel = new TableModleProxy(title, this.data);
/* 387 */     this.table.setModel(this.tableModel);
/* 388 */     TableColumnModel columnModel = this.table.getColumnModel();
/* 389 */     for (int t = 1; t < length.length; ++t) {
/* 390 */       if (length[t] < 100)
/* 391 */         columnModel.getColumn(t).setPreferredWidth(100);
/*     */       else {
/* 393 */         columnModel.getColumn(t).setPreferredWidth(length[t]);
/*     */       }
/*     */     }
/*     */ 
/* 397 */     columnModel.getColumn(3).setMinWidth(0);
/* 398 */     columnModel.getColumn(3).setMaxWidth(0);
/* 399 */     columnModel.getColumn(3).setWidth(0);
/* 400 */     columnModel.getColumn(3).setPreferredWidth(0);
/*     */ 
/* 402 */     this.table.getTableHeader().setDefaultRenderer(
/* 403 */       new CheckHeaderCellRender(this.table));
/* 404 */     this.table.updateUI();
/*     */   }
/*     */ 
/*     */   public Object[][] getData(String[] title, int currentPage) {
/* 408 */     Object[][] obj = null;
/* 409 */     this.sql = "select DETAILS_ID,details_name,details_price,details_until from details where 1=1 ";
/*     */ 
/* 411 */     if ((this.detailsName.getText() != null) && (!"".equals(this.detailsName.getText()))) {
/* 412 */       this.sql = 
/* 413 */         (this.sql + " and details_name like '%" + this.detailsName.getText() + 
/* 413 */         "%' ");
/*     */     }
/*     */ 
/* 417 */     if ((((Item)this.shopTypeComp.getSelectedItem()).getKey() != null) && 
/* 418 */       (!"".equals(((Item)this.shopTypeComp.getSelectedItem()).getKey()))) {
/* 419 */       this.sql = 
/* 420 */         (this.sql + " and shop_type_id ='" + 
/* 420 */         ((Item)this.shopTypeComp.getSelectedItem()).getKey() + "' ");
/*     */     }
/* 422 */     this.sql = 
/* 423 */       (this.sql + " order by DETAILS_ID asc  limit " + 
/* 423 */       (currentPage - 1) * 20 + ",20");
/*     */     try
/*     */     {
/* 426 */       List list = new BaseDao().queryDbBySql(this.sql);
/* 427 */       if ((list != null) && (list.size() > 0)) {
/* 428 */         obj = new Object[list.size()][5];
/* 429 */         Map m = null;
/* 430 */         for (int j = 0; j < list.size(); ++j) {
/* 431 */           m = (Map)list.get(j);
/* 432 */           obj[j][0] = new Boolean(false);
/* 433 */           obj[j][1] = m.get("DETAILS_NAME");
/* 434 */           obj[j][2] = m.get("DETAILS_PRICE");
/* 435 */           obj[j][3] = m.get("DETAILS_ID");
/* 436 */           obj[j][4] = m.get("DETAILS_UNTIL");
/*     */         }
/*     */       }
/*     */     }
/*     */     catch (SQLException e) {
/* 441 */       e.printStackTrace();
/*     */     }
/* 443 */     return obj;
/*     */   }
/*     */ 
/*     */   public String dbOperation(String flag)
/*     */   {
/* 452 */     String sql = "";
/* 453 */     Map m = this.tableModel.getDataMap();
/* 454 */     StringBuffer sb = new StringBuffer();
/* 455 */     String str = "";
/* 456 */     if ("delete".equals(flag)) {
/* 457 */       if (m.size() == 0) {
/* 458 */         str = "请选择一条记录";
/*     */       } else {
/* 460 */         Set ss = m.keySet();
/* 461 */         int i = 0;
/* 462 */         for (String s : ss) {
/* 463 */           Object[] o = (Object[])m.get(s);
/* 464 */           if (i == 0)
/* 465 */             sb.append("'" + o[3] + "'");
/*     */           else {
/* 467 */             sb.append(",'" + o[3] + "'");
/*     */           }
/* 469 */           ++i;
/*     */         }
/* 471 */         sql = "delete from DETAILS where DETAILS_ID in(" + 
/* 472 */           sb.toString() + ")";
/* 473 */         new BaseDao().deleteDbBySql(sql);
/*     */       }
/*     */     }
/* 476 */     return str;
/*     */   }
/*     */ 
/*     */   public Integer getDbCount(String detailsName, String shopType) {
/* 480 */     Integer count = Integer.valueOf(0);
/*     */     try {
/* 482 */       this.sql = "select count(*) count from DETAILS where 1=1 ";
/* 483 */       if ((detailsName != null) && (!"".equals(detailsName))) {
/* 484 */         this.sql = (this.sql + " and DETAILS_NAME like '%" + detailsName + "%'");
/*     */       }
/* 486 */       if ((shopType != null) && (!"".equals(shopType))) {
/* 487 */         this.sql = (this.sql + " and SHOP_TYPE_ID = '" + shopType + "'");
/*     */       }
/* 489 */       List list = new BaseDao().queryDbBySql(this.sql);
/* 490 */       count = Integer.valueOf(Integer.parseInt((String)((Map)list.get(0)).get("COUNT")));
/*     */     } catch (SQLException e) {
/* 492 */       e.printStackTrace();
/*     */     }
/* 494 */     return count;
/*     */   }
/*     */ 
/*     */   public void resetTableMap()
/*     */   {
/* 538 */     this.tableModel.setDataMap(new HashMap());
/*     */   }
/*     */ 
/*     */   class CloseActionListener
/*     */     implements ActionListener
/*     */   {
/*     */     CloseActionListener()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/* 499 */       DetailsDbIFrame.this.doDefaultCloseAction();
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.iframe.DetailsDbIFrame
 * JD-Core Version:    0.5.4
 */