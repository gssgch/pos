/*     */ package com.pos;
/*     */ 
/*     */ import com.pos.action.MenuAction;
/*     */ import com.pos.db.BaseDao;
/*     */ import com.pos.iframe.PosLoginIFrame;
/*     */ import com.pos.util.DimensionUtil;
/*     */ import java.awt.Component;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ComponentAdapter;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.sql.SQLException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.swing.JDesktopPane;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JInternalFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JMenu;
/*     */ import javax.swing.JMenuBar;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JToolBar;
/*     */ import javax.swing.UIManager;
/*     */ import javax.swing.border.BevelBorder;
/*     */ 
/*     */ public class LoginAction extends JFrame
/*     */ {
/*  29 */   private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();
/*     */ 
/*     */   public static void addIFame(JInternalFrame iframe) {
/*  32 */     DESKTOP_PANE.add(iframe);
/*     */   }
/*     */ 
/*     */   public static void main(String[] args) {
/*     */     try {
/*  37 */       UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
/*  38 */       new PosLoginIFrame();
/*     */     } catch (Exception ex) {
/*  40 */       ex.printStackTrace();
/*     */     }
/*     */   }
/*     */ 
/*     */   public LoginAction(String userName, PosLoginIFrame posLogin) {
/*  45 */     if ((userName == null) || ("".equals(userName))) {
/*  46 */       JOptionPane.showMessageDialog(null, "非合法用户登陆，请联系管理员！");
/*  47 */       posLogin.setVisible(false);
/*  48 */       posLogin.dispose();
/*  49 */       System.exit(0);
/*     */     }
/*  51 */     Dimension ds = DimensionUtil.getDimension();
/*  52 */     setDefaultCloseOperation(3);
/*  53 */     setLocationByPlatform(true);
/*     */ 
/*  55 */     setBounds(ds.width / 12, ds.height / 12, 800, 800);
/*  56 */     setTitle("POS打印系统");
/*     */ 
/*  58 */     JMenuBar menuBar = null;
/*     */     try {
/*  60 */       menuBar = createMenu();
/*     */     } catch (Exception e1) {
/*  62 */       e1.printStackTrace();
/*  63 */       JOptionPane.showMessageDialog(null, e1.getMessage());
/*     */     }
/*  65 */     setJMenuBar(menuBar);
/*  66 */     JToolBar toolBar = createToolBar();
/*  67 */     getContentPane().add(toolBar, "North");
/*  68 */     JLabel label = new JLabel();
/*  69 */     label.setBounds(0, 0, 0, 0);
/*  70 */     label.setIcon(null);
/*     */ 
/*  72 */     DESKTOP_PANE.addComponentListener(new ComponentAdapter(label) {
/*     */       public void componentResized(ComponentEvent e) {
/*  74 */         this.val$label.setSize(e.getComponent().getSize());
/*     */       }
/*     */     });
/*  77 */     DESKTOP_PANE.add(label, new Integer(-2147483648));
/*  78 */     getContentPane().add(DESKTOP_PANE);
/*     */   }
/*     */ 
/*     */   private JToolBar createToolBar()
/*     */   {
/*  85 */     JToolBar toolBar = new JToolBar();
/*  86 */     toolBar.setFloatable(false);
/*  87 */     toolBar.setBorder(new BevelBorder(0));
/*  88 */     return toolBar;
/*     */   }
/*     */ 
/*     */   private JMenuBar createMenu()
/*     */     throws Exception
/*     */   {
/*  95 */     JMenuBar menuBar = new JMenuBar();
/*     */ 
/*  97 */     JMenu regionMenu = new JMenu();
/*  98 */     regionMenu.setText("地区列表");
/*     */ 
/* 100 */     List list = null;
/*     */     try {
/* 102 */       list = new BaseDao()
/* 103 */         .queryDbBySql("select * from Navigation order by Navigation_ID asc");
/*     */     } catch (SQLException e) {
/* 105 */       throw new Exception("地区列表获取失败！");
/*     */     }
/* 107 */     if ((list == null) || (list.size() == 0)) {
/* 108 */       throw new Exception("地区列表为空，请联系管理员!");
/*     */     }
/*     */ 
/* 111 */     for (int i = 0; i < list.size(); ++i) {
/* 112 */       Map map = (Map)list.get(i);
/* 113 */       MenuAction ma = new MenuAction((String)map.get("NAVIGATION_NAME"));
/* 114 */       regionMenu.add(ma.REGION);
/*     */     }
/*     */ 
/* 117 */     JMenu db = new JMenu("数据维护");
/* 118 */     MenuAction userUpdate = new MenuAction("用户信息维护");
/* 119 */     MenuAction details = new MenuAction("商品信息维护");
/* 120 */     db.add(userUpdate.db);
/* 121 */     db.add(details.db);
/* 122 */     JMenu sysMenu = new JMenu("系统");
/* 123 */     sysMenu.add(new MenuAction().EXIT);
/* 124 */     menuBar.add(regionMenu);
/* 125 */     menuBar.add(db);
/* 126 */     menuBar.add(sysMenu);
/*     */ 
/* 128 */     return menuBar;
/*     */   }
/*     */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.LoginAction
 * JD-Core Version:    0.5.4
 */