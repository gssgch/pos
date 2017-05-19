/*    */ package com.pos.table;
/*    */ 
/*    */ import java.awt.Component;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import javax.swing.JCheckBox;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.JLabel;
/*    */ import javax.swing.JTable;
/*    */ import javax.swing.UIManager;
/*    */ import javax.swing.table.JTableHeader;
/*    */ import javax.swing.table.TableCellRenderer;
/*    */ 
/*    */ 
/*    */ public class CheckHeaderCellRender
/*    */   implements TableCellRenderer
/*    */ {
/*    */   private TableModleProxy tableModel;
/*    */   private JTableHeader tableHeader;
/* 20 */   private JCheckBox selectBox = null;
/*    */   
/*    */   public CheckHeaderCellRender(JTable table) {
/* 23 */     this.tableModel = ((TableModleProxy)table.getModel());
/* 24 */     this.tableHeader = table.getTableHeader();
/* 25 */     this.selectBox = new JCheckBox();
/* 26 */     this.selectBox.setSelected(false);
/* 27 */     this.tableHeader.addMouseListener(new MouseListener()
/*    */     {
/*    */       public void mouseReleased(MouseEvent e) {}
/*    */       
/*    */ 
/*    */ 
/*    */       public void mousePressed(MouseEvent e) {}
/*    */       
/*    */ 
/*    */ 
/*    */       public void mouseExited(MouseEvent e) {}
/*    */       
/*    */ 
/*    */ 
/*    */       public void mouseEntered(MouseEvent e) {}
/*    */       
/*    */ 
/*    */ 
/*    */       public void mouseClicked(MouseEvent e)
/*    */       {
/* 47 */         if (e.getClickCount() > 0) {
/* 48 */           int selectColumn = CheckHeaderCellRender.this.tableHeader.columnAtPoint(e.getPoint());
/* 49 */           if (selectColumn == 0) {
/* 50 */             boolean value = !CheckHeaderCellRender.this.selectBox.isSelected();
/* 51 */             CheckHeaderCellRender.this.selectBox.setSelected(value);
/* 52 */             CheckHeaderCellRender.this.tableModel.selectAllOrNull(value);
/* 53 */             CheckHeaderCellRender.this.tableHeader.repaint();
/*    */           }
/*    */         }
/*    */       }
/*    */     });
/*    */   }
/*    */   
/*    */ 
/*    */   public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
/*    */   {
/* 63 */     String valueStr = (String)value;
/* 64 */     JLabel label = new JLabel(valueStr);
/* 65 */     label.setHorizontalAlignment(0);
/* 66 */     this.selectBox.setHorizontalAlignment(0);
/* 67 */     this.selectBox.setBorderPainted(true);
/* 68 */     JComponent component = column == 0 ? this.selectBox : label;
/*    */     
/* 70 */     component.setForeground(this.tableHeader.getForeground());
/* 71 */     component.setBackground(this.tableHeader.getBackground());
/* 72 */     component.setFont(this.tableHeader.getFont());
/* 73 */     component.setBorder(UIManager.getBorder("TableHeader.cellBorder"));
/*    */     
/* 75 */     return component;
/*    */   }
/*    */ }


/* Location:              F:\otec\pos软件\20170517\1.jar!\com\pos\table\CheckHeaderCellRender.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1
 */