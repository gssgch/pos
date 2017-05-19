/*     */ package com.pos.vo;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ public class GetString
/*     */ {
/*   7 */   private static Random r = new Random();
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getBatchNo(String shopName)
/*     */   {
/*  17 */     String batchNo = commonStr(6, 1000000);
/*  18 */     return batchNo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getTXNo(String shopName)
/*     */   {
/*  29 */     String txNo = commonStr(7, 10000000);
/*  30 */     return txNo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getVoucherNo(String shopName)
/*     */   {
/*  41 */     String voucher = commonStr(6, 1000000);
/*  42 */     return voucher;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getAuthNo(String shpName)
/*     */   {
/*  52 */     String authNo = commonStr(6, 1000000);
/*  53 */     return authNo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getRefNo(String shopName, int length, String printDate)
/*     */   {
/*  63 */     String refNo = printDate.trim().replaceAll("/", "").replaceAll("-", "")
/*  64 */       .replaceAll(":", "").replaceAll(" ", "").substring(0, length);
/*  65 */     return refNo;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String commonStr(int i, int maxInt)
/*     */   {
/*  75 */     StringBuffer sb = new StringBuffer();
/*  76 */     long l = r.nextInt(maxInt);
/*  77 */     String temp = String.valueOf(l);
/*  78 */     if (temp.length() < i) {
/*  79 */       for (int t = 0; t < i - temp.length(); t++) {
/*  80 */         sb.append("0");
/*     */       }
/*     */     }
/*  83 */     sb.append(temp);
/*  84 */     return sb.toString();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getDetailsNum(String shopName, String paramDateString)
/*     */   {
/*  97 */     String detailsNum = "";
/*  98 */     int i = 0;
/*  99 */     if (("益新办公百货".equals(shopName)) || ("龙泉苑大酒店".equals(shopName))) {
/* 100 */       detailsNum = 
/*     */       
/* 102 */         paramDateString.trim().replaceAll("/", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").substring(0, 12);
/* 103 */       return detailsNum;
/*     */     }
/* 105 */     detailsNum = 
/*     */     
/* 107 */       paramDateString.trim().replaceAll("/", "").replaceAll("-", "").replaceAll(":", "").replaceAll(" ", "").substring(0, 12);
/* 108 */     i = r.nextInt(10);
/* 109 */     return detailsNum + i;
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public static String getMemberShipCard(String shopName)
/*     */   {
/* 118 */     String memberShipCard = "";
/* 119 */     if ("银滕珠宝".equals(shopName)) {
/* 120 */       memberShipCard = commonStr(6, 1999999999);
/*     */     }
/* 122 */     return memberShipCard;
/*     */   }
/*     */ }


/* Location:              F:\otec\pos软件\20170517\1.jar!\com\pos\vo\GetString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */