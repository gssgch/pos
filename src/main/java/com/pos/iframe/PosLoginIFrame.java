/*     */ package com.pos.iframe;
/*     */ 
/*     */ import com.pos.LoginAction;
/*     */ import com.pos.util.CheckUtil;
/*     */ import com.pos.util.DimensionUtil;
/*     */ import java.awt.BorderLayout;
/*     */ import java.awt.Color;
/*     */ import java.awt.Container;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import javax.swing.JButton;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JOptionPane;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JPasswordField;
/*     */ import javax.swing.JTextField;
/*     */ import javax.swing.border.EmptyBorder;
/*     */ 
/*     */ public class PosLoginIFrame extends JFrame
/*     */ {
/*     */   private JPasswordField password;
/*     */   private JTextField username;
/*     */   private JButton login;
/*     */   private JButton reset;
/*  28 */   private Dimension ds = DimensionUtil.getDimension();
/*     */ 
/*     */   public PosLoginIFrame() {
/*  31 */     super("POS打印系统登录窗口");
/*     */ 
/*  33 */     setDefaultCloseOperation(3);
/*  34 */     BorderLayout borderLayout = new BorderLayout();
/*  35 */     borderLayout.setVgap(20);
/*  36 */     getContentPane().setLayout(borderLayout);
/*  37 */     setBounds(this.ds.width / 3, this.ds.height / 3, 320, 230);
/*     */ 
/*  40 */     JPanel panel = new JPanel();
/*  41 */     panel.setLayout(new BorderLayout());
/*  42 */     panel.setBorder(new EmptyBorder(0, 0, 0, 0));
/*  43 */     getContentPane().add(panel);
/*     */ 
/*  46 */     JLabel tupianLabel = new JLabel();
/*     */ 
/*  49 */     tupianLabel.setOpaque(true);
/*  50 */     tupianLabel.setBackground(Color.GREEN);
/*  51 */     tupianLabel.setPreferredSize(new Dimension(260, 60));
/*  52 */     panel.add(tupianLabel, "North");
/*     */ 
/*  55 */     JPanel panel_2 = new JPanel();
/*  56 */     panel_2.setLayout(null);
/*  57 */     JLabel userLabel = new JLabel("用  户  名：");
/*  58 */     JLabel passWdLabel = new JLabel("密      码：");
/*     */ 
/*  60 */     this.username = new JTextField(20);
/*  61 */     this.password = new JPasswordField(20);
/*     */ 
/*  63 */     userLabel.setBounds(70, 20, 80, 20);
/*  64 */     this.username.setBounds(150, 20, 140, 20);
/*  65 */     passWdLabel.setBounds(70, 50, 80, 20);
/*  66 */     this.password.setBounds(150, 50, 140, 20);
/*     */ 
/*  68 */     panel_2.add(this.username);
/*  69 */     panel_2.add(userLabel);
/*     */ 
/*  71 */     panel_2.add(passWdLabel);
/*  72 */     panel_2.add(this.password);
/*     */ 
/*  74 */     this.login = new JButton("登录");
/*  75 */     this.reset = new JButton("重置");
/*  76 */     this.login.setBounds(70, 80, 60, 30);
/*  77 */     this.reset.setBounds(160, 80, 60, 30);
/*     */ 
/*  79 */     this.login.addActionListener(new PosLoginAction());
/*  80 */     this.reset.addActionListener(new PosResetAction(null));
/*  81 */     panel_2.add(this.login);
/*  82 */     panel_2.add(this.reset);
/*     */ 
/*  84 */     panel.add(panel_2);
/*     */ 
/*  86 */     setVisible(true);
/*  87 */     setResizable(false);
/*     */   }
/*     */ 
/*     */   class PosLoginAction
/*     */     implements ActionListener
/*     */   {
/*     */     PosLoginAction()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/* 100 */       String userName = PosLoginIFrame.this.username.getText();
/* 101 */       String passWord = PosLoginIFrame.this.password.getText();
/*     */       try
/*     */       {
/* 104 */         if (CheckUtil.checkValide(userName, passWord)) {
/* 105 */           JOptionPane.showMessageDialog(null, "用户不合法，请联系管理员！");
/* 106 */           PosLoginIFrame.this.username.setText("");
/* 107 */           PosLoginIFrame.this.password.setText("");
/* 108 */           return;
/*     */         }
/*     */       } catch (Exception e1) {
/* 111 */         e1.printStackTrace();
/* 112 */         JOptionPane.showMessageDialog(null, "用户验证异常，请联系管理员！");
/* 113 */         return;
/*     */       }
/* 115 */       PosLoginAction t = this;
/* 116 */       if (userName != null) {
/*     */         try {
/* 118 */           LoginAction frame = new LoginAction(userName, 
/* 119 */             PosLoginIFrame.this);
/* 120 */           frame.setVisible(true);
/* 121 */           PosLoginIFrame.this.setVisible(false);
/* 122 */           PosLoginIFrame.this.dispose();
/*     */         } catch (Exception ex) {
/* 124 */           ex.printStackTrace();
/*     */         }
/*     */       } else {
/* 127 */         JOptionPane.showMessageDialog(null, "用户验证失败，请联系管理员！");
/* 128 */         PosLoginIFrame.this.username.setText("");
/* 129 */         PosLoginIFrame.this.password.setText("");
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private class PosResetAction
/*     */     implements ActionListener
/*     */   {
/*     */     private PosResetAction()
/*     */     {
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/*  92 */       PosLoginIFrame.this.username.setText("");
/*  93 */       PosLoginIFrame.this.password.setText("");
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.iframe.PosLoginIFrame
 * JD-Core Version:    0.5.4
 */