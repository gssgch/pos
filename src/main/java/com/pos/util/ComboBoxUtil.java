/*    */ package com.pos.util;
/*    */ 
/*    */ import com.pos.db.BaseDao;
/*    */ import com.pos.vo.Item;
/*    */ import java.sql.SQLException;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import javax.swing.DefaultComboBoxModel;
/*    */ import javax.swing.JComboBox;
/*    */ 
/*    */ public class ComboBoxUtil
/*    */ {
/*    */   private String sql;
/*    */ 
/*    */   public void setComboBox(JComboBox comb, String flag, String navigationId, String shopId)
/*    */   {
/* 16 */     List list = null;
/* 17 */     if ("navigationComp".equals(flag)) {
/* 18 */       this.sql = "select navigation_id ,navigation_name  from navigation where 1=1 ";
/* 19 */       if ((navigationId != null) && (!"".equals(navigationId)))
/* 20 */         this.sql = (this.sql + " and navigation_id='" + navigationId + "' ");
/*    */     }
/* 22 */     else if ("shopTypeComp".equals(flag)) {
/* 23 */       this.sql = 
/* 24 */         ("select type_id,type_name from shop_type where navigation_id='" + 
/* 24 */         navigationId + "'");
/* 25 */       if ((shopId != null) && (!"".equals(shopId)))
/* 26 */         this.sql = (this.sql + " and type_id='" + shopId + "' ");
/*    */     }
/*    */     try
/*    */     {
/* 30 */       list = new BaseDao().queryDbBySql(this.sql);
/* 31 */       if ((list != null) && (list.size() > 0)) {
/* 32 */         DefaultComboBoxModel model = new DefaultComboBoxModel();
/* 33 */         Map m = null;
/* 34 */         Item it = null;
/* 35 */         comb.setModel(model);
/* 36 */         for (int i = 0; i < list.size(); ++i) {
/* 37 */           it = new Item();
/* 38 */           m = (Map)list.get(i);
/* 39 */           it = new Item();
/* 40 */           if ("navigationComp".equals(flag)) {
/* 41 */             it.setKey((String)m.get("NAVIGATION_ID"));
/* 42 */             it.setValue((String)m.get("NAVIGATION_NAME"));
/* 43 */             if ((navigationId != null) && (!"".equals(navigationId)) && (it.getKey().equals(navigationId)))
/* 44 */               comb.setSelectedItem(it);
/*    */           }
/* 46 */           else if ("shopTypeComp".equals(flag)) {
/* 47 */             it.setKey((String)m.get("TYPE_ID"));
/* 48 */             it.setValue((String)m.get("TYPE_NAME"));
/* 49 */             if ((shopId != null) && (!"".equals(shopId)) && (it.getKey().equals(shopId))) {
/* 50 */               comb.setSelectedItem(it);
/*    */             }
/*    */           }
/* 53 */           model.addElement(it);
/*    */         }
/*    */       }
/*    */     } catch (SQLException e) {
/* 57 */       e.printStackTrace();
/*    */     }
/*    */   }
/*    */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.util.ComboBoxUtil
 * JD-Core Version:    0.5.4
 */