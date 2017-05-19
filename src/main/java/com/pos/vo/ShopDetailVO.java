/*    */ package com.pos.vo;
/*    */ 
/*    */ import java.math.BigDecimal;
/*    */ 
/*    */ public class ShopDetailVO {
/*    */   private String name;
/*    */   private BigDecimal unitPrice;
/*  8 */   private int count = 1;
/*    */   private BigDecimal unitPriceSrc;
/*    */   private BigDecimal uintPirceSum;
/*    */   private String detailsUnit;
/*    */   
/*    */   public String getDetailsUnit() {
/* 14 */     return this.detailsUnit;
/*    */   }
/*    */   
/* 17 */   public void setDetailsUnit(String detailsUnit) { this.detailsUnit = detailsUnit; }
/*    */   
/*    */   public BigDecimal getUintPirceSum() {
/* 20 */     return this.uintPirceSum;
/*    */   }
/*    */   
/* 23 */   public void setUintPirceSum(BigDecimal uintPirceSum) { this.uintPirceSum = uintPirceSum; }
/*    */   
/*    */   public BigDecimal getUnitPriceSrc() {
/* 26 */     return this.unitPriceSrc;
/*    */   }
/*    */   
/* 29 */   public void setUnitPriceSrc(BigDecimal unitPriceSrc) { this.unitPriceSrc = unitPriceSrc; }
/*    */   
/*    */   public int getCount() {
/* 32 */     return this.count;
/*    */   }
/*    */   
/* 35 */   public void setCount(int count) { this.count = count; }
/*    */   
/*    */   public String getName() {
/* 38 */     return this.name;
/*    */   }
/*    */   
/* 41 */   public void setName(String name) { this.name = name; }
/*    */   
/*    */   public BigDecimal getUnitPrice() {
/* 44 */     return this.unitPrice;
/*    */   }
/*    */   
/* 47 */   public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
/*    */ }


/* Location:              F:\otec\pos软件\20170517\1.jar!\com\pos\vo\ShopDetailVO.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */