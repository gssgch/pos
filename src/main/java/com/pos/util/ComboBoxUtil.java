package com.pos.util;

import com.pos.vo.Item;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

public class ComboBoxUtil
{
  private String sql;
  
  public void setComboBox(JComboBox comb, String flag, String navigationId, String shopId)
  {
    List<Map> list = null;
    if ("navigationComp".equals(flag)) {
      this.sql = "select navigation_id ,navigation_name  from navigation where 1=1 ";
      if ((navigationId != null) && (!"".equals(navigationId))) {
        this.sql = (this.sql + " and navigation_id='" + navigationId + "' ");
      }
    } else if ("shopTypeComp".equals(flag)) {
      this.sql = 
        ("select type_id,type_name from shop_type where navigation_id='" + navigationId + "'");
      if ((shopId != null) && (!"".equals(shopId))) {
        this.sql = (this.sql + " and type_id='" + shopId + "' ");
      }
    }
    try {
      list = new com.pos.db.BaseDao().queryDbBySql(this.sql);
      if ((list != null) && (list.size() > 0)) {
        DefaultComboBoxModel model = new DefaultComboBoxModel();
        Map m = null;
        Item it = null;
        comb.setModel(model);
        for (int i = 0; i < list.size(); i++) {
          it = new Item();
          m = (Map)list.get(i);
          it = new Item();
          if ("navigationComp".equals(flag)) {
            it.setKey((String)m.get("NAVIGATION_ID"));
            it.setValue((String)m.get("NAVIGATION_NAME"));
            if ((navigationId != null) && (!"".equals(navigationId)) && (it.getKey().equals(navigationId))) {
              comb.setSelectedItem(it);
            }
          } else if ("shopTypeComp".equals(flag)) {
            it.setKey((String)m.get("TYPE_ID"));
            it.setValue((String)m.get("TYPE_NAME"));
            if ((shopId != null) && (!"".equals(shopId)) && (it.getKey().equals(shopId))) {
              comb.setSelectedItem(it);
            }
          }
          model.addElement(it);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
