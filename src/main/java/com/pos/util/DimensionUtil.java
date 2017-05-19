/*    */ package com.pos.util;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Toolkit;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DimensionUtil
/*    */ {
/*    */   public static Dimension getDimension()
/*    */   {
/* 12 */     Dimension ds = Toolkit.getDefaultToolkit().getScreenSize();
/* 13 */     return ds;
/*    */   }
/*    */ }


/* Location:              F:\otec\pos软件\20170517\1.jar!\com\pos\util\DimensionUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */