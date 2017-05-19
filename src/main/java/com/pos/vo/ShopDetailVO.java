/*    */ package com.pos.vo;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class ShopDetailVO
/*    */ {
/*    */   private String name;
/*    */   private BigDecimal unitPrice;
/*  8 */   private int count = 1;
/*    */   private BigDecimal unitPriceSrc;
/*    */   private BigDecimal uintPirceSum;
/*    */   private String detailsUnit;
/*    */ 
/*    */   public String getDetailsUnit()
/*    */   {
/* 14 */     return this.detailsUnit;
/*    */   }
/*    */   public void setDetailsUnit(String detailsUnit) {
/* 17 */     this.detailsUnit = detailsUnit;
/*    */   }
/*    */   public BigDecimal getUintPirceSum() {
/* 20 */     return this.uintPirceSum;
/*    */   }
/*    */   public void setUintPirceSum(BigDecimal uintPirceSum) {
/* 23 */     this.uintPirceSum = uintPirceSum;
/*    */   }
/*    */   public BigDecimal getUnitPriceSrc() {
/* 26 */     return this.unitPriceSrc;
/*    */   }
/*    */   public void setUnitPriceSrc(BigDecimal unitPriceSrc) {
/* 29 */     this.unitPriceSrc = unitPriceSrc;
/*    */   }
/*    */   public int getCount() {
/* 32 */     return this.count;
/*    */   }
/*    */   public void setCount(int count) {
/* 35 */     this.count = count;
/*    */   }
/*    */   public String getName() {
/* 38 */     return this.name;
/*    */   }
/*    */   public void setName(String name) {
/* 41 */     this.name = name;
/*    */   }
/*    */   public BigDecimal getUnitPrice() {
/* 44 */     return this.unitPrice;
/*    */   }
/*    */   public void setUnitPrice(BigDecimal unitPrice) {
/* 47 */     this.unitPrice = unitPrice;
/*    */   }
/*    */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.vo.ShopDetailVO
 * JD-Core Version:    0.5.4
 */