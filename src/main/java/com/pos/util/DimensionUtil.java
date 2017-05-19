/*    */ package com.pos.util;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Toolkit;
/*    */ 
/*    */ public class DimensionUtil
/*    */ {
/*    */   public static Dimension getDimension()
/*    */   {
/* 12 */     Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
/* 13 */     return ds;
/*    */   }
/*    */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.util.DimensionUtil
 * JD-Core Version:    0.5.4
 */