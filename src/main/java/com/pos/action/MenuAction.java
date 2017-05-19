package com.pos.action;

import com.pos.LoginAction;
import com.pos.iframe.DetailsDbIFrame;
import com.pos.iframe.RegionIFrame;
import com.pos.iframe.UserDbIFrame;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;
import javax.swing.AbstractAction;
import javax.swing.JInternalFrame;






public class MenuAction
{
  private static Map<String, JInternalFrame> frames;
  public ExitAction EXIT;
  public RegionAction REGION;
  public DBAction db;
  
  public MenuAction(String regionStr)
  {
    frames = new HashMap();
    this.REGION = new RegionAction(regionStr);
    this.db = new DBAction(regionStr);
  }
  
  public MenuAction() {
    frames = new HashMap();
    this.EXIT = new ExitAction();
  }
  
  public static class DBAction extends AbstractAction {
    private String region;
    
    public String getRegion() {
      return this.region;
    }
    
    public void setRegion(String region) {
      this.region = region;
    }
    
    public DBAction(String region) {
      super();
      this.region = region;
      putValue("LongDescription", region + "操作");
      putValue("ShortDescription", region);
    }
    
    public void actionPerformed(ActionEvent e)
    {
      if ((!MenuAction.frames.containsKey(this.region)) || (((JInternalFrame)MenuAction.frames.get(this.region)).isClosed())) {
        if ("用户信息维护".equals(this.region)) {
          UserDbIFrame ri = new UserDbIFrame(this.region);
          ri.setMaximizable(true);
          ri.setResizable(true);
          MenuAction.frames.put(this.region, ri);
        } else if ("商品信息维护".equals(this.region)) {
          DetailsDbIFrame ri = new DetailsDbIFrame(this.region);
          ri.setMaximizable(true);
          ri.setResizable(true);
          MenuAction.frames.put(this.region, ri);
        }
        
        LoginAction.addIFame((JInternalFrame)MenuAction.frames.get(this.region));
      }
    }
  }
  
  public static class RegionAction extends AbstractAction
  {
    private String region;
    
    public String getRegion()
    {
      return this.region;
    }
    
    public void setRegion(String region) {
      this.region = region;
    }
    
    public RegionAction(String region) {
      super();
      this.region = region;
      putValue("LongDescription", region + "POS打印");
      putValue("ShortDescription", region);
    }
    
    public void actionPerformed(ActionEvent e)
    {
      if ((!MenuAction.frames.containsKey(this.region)) || (((JInternalFrame)MenuAction.frames.get(this.region)).isClosed())) {
        RegionIFrame ri = new RegionIFrame(this.region);
        ri.setMaximizable(true);
        ri.setResizable(true);
        MenuAction.frames.put(this.region, ri);
        LoginAction.addIFame((JInternalFrame)MenuAction.frames.get(this.region));
      }
    }
  }
  


  private static class ExitAction
    extends AbstractAction
  {
    public ExitAction()
    {
      super(null);
      putValue("LongDescription", "退出POS打印系统");
      putValue("ShortDescription", "退出系统");
    }
    
    public void actionPerformed(ActionEvent e)
    {
      System.exit(0);
    }
  }
}
