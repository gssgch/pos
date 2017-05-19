/*     */ package com.pos.action;
/*     */ 
/*     */ import com.pos.LoginAction;
/*     */ import com.pos.iframe.DetailsDbIFrame;
/*     */ import com.pos.iframe.RegionIFrame;
/*     */ import com.pos.iframe.UserDbIFrame;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import javax.swing.AbstractAction;
/*     */ import javax.swing.JInternalFrame;
/*     */ 
/*     */ public class MenuAction
/*     */ {
/*     */   private static Map<String, JInternalFrame> frames;
/*     */   public ExitAction EXIT;
/*     */   public RegionAction REGION;
/*     */   public DBAction db;
/*     */ 
/*     */   public MenuAction(String regionStr)
/*     */   {
/*  27 */     frames = new HashMap();
/*  28 */     this.REGION = new RegionAction(regionStr);
/*  29 */     this.db = new DBAction(regionStr);
/*     */   }
/*     */ 
/*     */   public MenuAction() {
/*  33 */     frames = new HashMap();
/*  34 */     this.EXIT = new ExitAction();
/*     */   }
/*     */ 
/*     */   public static class DBAction extends AbstractAction {
/*     */     private String region;
/*     */ 
/*     */     public String getRegion() {
/*  41 */       return this.region;
/*     */     }
/*     */ 
/*     */     public void setRegion(String region) {
/*  45 */       this.region = region;
/*     */     }
/*     */ 
/*     */     public DBAction(String region) {
/*  49 */       super(region);
/*  50 */       this.region = region;
/*  51 */       putValue("LongDescription", region + "操作");
/*  52 */       putValue("ShortDescription", region);
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/*  57 */       if ((!MenuAction.frames.containsKey(this.region)) || (((JInternalFrame)MenuAction.frames.get(this.region)).isClosed())) {
/*  58 */         if ("用户信息维护".equals(this.region)) {
/*  59 */           UserDbIFrame ri = new UserDbIFrame(this.region);
/*  60 */           ri.setMaximizable(true);
/*  61 */           ri.setResizable(true);
/*  62 */           MenuAction.frames.put(this.region, ri);
/*  63 */         } else if ("商品信息维护".equals(this.region)) {
/*  64 */           DetailsDbIFrame ri = new DetailsDbIFrame(this.region);
/*  65 */           ri.setMaximizable(true);
/*  66 */           ri.setResizable(true);
/*  67 */           MenuAction.frames.put(this.region, ri);
/*     */         }
/*     */ 
/*  70 */         LoginAction.addIFame((JInternalFrame)MenuAction.frames.get(this.region));
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   private static class ExitAction extends AbstractAction
/*     */   {
/*     */     public ExitAction()
/*     */     {
/* 114 */       super("退出系统", null);
/* 115 */       putValue("LongDescription", "退出POS打印系统");
/* 116 */       putValue("ShortDescription", "退出系统");
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/* 121 */       System.exit(0);
/*     */     }
/*     */   }
/*     */ 
/*     */   public static class RegionAction extends AbstractAction
/*     */   {
/*     */     private String region;
/*     */ 
/*     */     public String getRegion()
/*     */     {
/*  81 */       return this.region;
/*     */     }
/*     */ 
/*     */     public void setRegion(String region) {
/*  85 */       this.region = region;
/*     */     }
/*     */ 
/*     */     public RegionAction(String region) {
/*  89 */       super(region);
/*  90 */       this.region = region;
/*  91 */       putValue("LongDescription", region + "POS打印");
/*  92 */       putValue("ShortDescription", region);
/*     */     }
/*     */ 
/*     */     public void actionPerformed(ActionEvent e)
/*     */     {
/*  97 */       if ((!MenuAction.frames.containsKey(this.region)) || (((JInternalFrame)MenuAction.frames.get(this.region)).isClosed())) {
/*  98 */         RegionIFrame ri = new RegionIFrame(this.region);
/*  99 */         ri.setMaximizable(true);
/* 100 */         ri.setResizable(true);
/* 101 */         MenuAction.frames.put(this.region, ri);
/* 102 */         LoginAction.addIFame((JInternalFrame)MenuAction.frames.get(this.region));
/*     */       }
/*     */     }
/*     */   }
/*     */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.action.MenuAction
 * JD-Core Version:    0.5.4
 */