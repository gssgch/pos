/*    */ package com.pos.table;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import javax.swing.table.AbstractTableModel;
/*    */ 
/*    */ public class TableModleProxy extends AbstractTableModel
/*    */ {
/*    */   private String[] title;
/*    */   private Object[][] data;
/*    */   private Map dataMap;
/*    */ 
/*    */   public Map getDataMap()
/*    */   {
/* 15 */     return this.dataMap;
/*    */   }
/*    */ 
/*    */   public void setDataMap(Map dataMap) {
/* 19 */     this.dataMap = dataMap;
/*    */   }
/*    */ 
/*    */   public String[] getTitle() {
/* 23 */     return this.title;
/*    */   }
/*    */ 
/*    */   public void setTitle(String[] title) {
/* 27 */     this.title = title;
/*    */   }
/*    */ 
/*    */   public Object[][] getData() {
/* 31 */     return this.data;
/*    */   }
/*    */ 
/*    */   public void setData(Object[][] data) {
/* 35 */     this.data = data;
/*    */   }
/*    */ 
/*    */   public TableModleProxy(String[] title, Object[][] data) {
/* 39 */     this.data = data;
/* 40 */     this.title = title;
/* 41 */     this.dataMap = new HashMap();
/*    */   }
/*    */ 
/*    */   public String getColumnName(int column)
/*    */   {
/* 46 */     return this.title[column];
/*    */   }
/*    */ 
/*    */   public Class<?> getColumnClass(int columnIndex)
/*    */   {
/* 51 */     return (getValueAt(0, columnIndex) == null) ? new String("").getClass() : getValueAt(0, columnIndex).getClass();
/*    */   }
/*    */ 
/*    */   public boolean isCellEditable(int rowIndex, int columnIndex)
/*    */   {
/* 57 */     return columnIndex == 0;
/*    */   }
/*    */ 
/*    */   public void setValueAt(Object aValue, int rowIndex, int columnIndex)
/*    */   {
/* 65 */     this.data[rowIndex][columnIndex] = aValue;
/* 66 */     if (((Boolean)aValue).booleanValue()) {
/* 67 */       this.dataMap.put(rowIndex, this.data[rowIndex]);
/*    */     } else {
/* 69 */       Object o = this.dataMap.get(rowIndex);
/* 70 */       if (o != null) {
/* 71 */         this.dataMap.remove(rowIndex);
/*    */       }
/*    */     }
/* 74 */     fireTableCellUpdated(rowIndex, columnIndex);
/*    */   }
/*    */ 
/*    */   public int getRowCount()
/*    */   {
/* 79 */     return this.data.length;
/*    */   }
/*    */ 
/*    */   public int getColumnCount()
/*    */   {
/* 84 */     return this.title.length;
/*    */   }
/*    */ 
/*    */   public Object getValueAt(int rowIndex, int columnIndex)
/*    */   {
/* 89 */     return this.data[rowIndex][columnIndex];
/*    */   }
/*    */ 
/*    */   public void selectAllOrNull(boolean value) {
/* 93 */     for (int index = 0; index < getRowCount(); ++index)
/* 94 */       setValueAt(Boolean.valueOf(value), index, 0);
/*    */   }
/*    */ }

/* Location:           F:\otec\pos软件\原始文件\20170517-1.jar
 * Qualified Name:     com.pos.table.TableModleProxy
 * JD-Core Version:    0.5.4
 */