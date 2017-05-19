/*    */ package com.pos.vo;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ public class UserDB
/*    */ {
/*  7 */   private static Random r = new Random();
/*    */   
/*  9 */   private static String[][] shoppingUser = {
/* 10 */     { "A01", "A02", "A03", "A04", "A05", "A06", "A07", "A08", "A09", 
/* 11 */     "A10" }, 
/* 12 */     { "B01", "B02", "B03", "B04", "B05", "B06", "B07", "B08", "B09", 
/* 13 */     "B10" }, 
/* 14 */     { "C01", "C02", "C03", "C04", "C05", "C06", "C07", "C08", "C09", 
/* 15 */     "C10" } };
/*    */   
/* 17 */   private static String[] chaoshi = { "0001", "0002", "0003", "0004", "0005", 
/* 18 */     "0006", "0007", "0008", "0009", "0010" };
/*    */   
/*    */   public static String getShoppingUser(String typeName)
/*    */   {
/* 22 */     if (("饭店".equals(typeName)) || ("酒吧".equals(typeName)) || ("服装".equals(typeName))) {
/* 23 */       int t = r.nextInt(shoppingUser.length);
/* 24 */       int f = r.nextInt(shoppingUser[t].length);
/* 25 */       return shoppingUser[t][f]; }
/* 26 */     if ("超市".equals(typeName)) {
/* 27 */       return chaoshi[r.nextInt(chaoshi.length)];
/*    */     }
/* 29 */     return "";
/*    */   }
/*    */ }


/* Location:              F:\otec\pos软件\20170517\1.jar!\com\pos\vo\UserDB.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */