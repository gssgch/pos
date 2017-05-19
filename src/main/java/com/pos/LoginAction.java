package com.pos;

import com.pos.action.MenuAction;
import com.pos.db.BaseDao;
import com.pos.iframe.PosLoginIFrame;
import com.pos.util.DimensionUtil;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;

public class LoginAction
        extends JFrame
{
    private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();

    public static void addIFame(JInternalFrame iframe) {
        DESKTOP_PANE.add(iframe);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            new PosLoginIFrame();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public LoginAction(String userName, PosLoginIFrame posLogin) {
        if ((userName == null) || ("".equals(userName))) {
            JOptionPane.showMessageDialog(null, "非合法用户登陆，请联系管理员！");
            posLogin.setVisible(false);
            posLogin.dispose();
            System.exit(0);
        }
        Dimension ds = DimensionUtil.getDimension();
        setDefaultCloseOperation(3);
        setLocationByPlatform(true);

        setBounds(ds.width / 12, ds.height / 12, 800, 800);
        setTitle("POS打印系统");

        JMenuBar menuBar = null;
        try {
            menuBar = createMenu();
        } catch (Exception e1) {
            e1.printStackTrace();
            JOptionPane.showMessageDialog(null, e1.getMessage());
        }
        setJMenuBar(menuBar);
        JToolBar toolBar = createToolBar();
        getContentPane().add(toolBar, "North");
        final JLabel label = new JLabel();
        label.setBounds(0, 0, 0, 0);
        label.setIcon(null);

        DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                label.setSize(e.getComponent().getSize());
            }
        });
        DESKTOP_PANE.add(label, new Integer(Integer.MIN_VALUE));
        getContentPane().add(DESKTOP_PANE);
    }



    private JToolBar createToolBar()
    {
        JToolBar toolBar = new JToolBar();
        toolBar.setFloatable(false);
        toolBar.setBorder(new BevelBorder(0));
        return toolBar;
    }


    private JMenuBar createMenu()
            throws Exception
    {
        JMenuBar menuBar = new JMenuBar();

        JMenu regionMenu = new JMenu();
        regionMenu.setText("地区列表");

        List<Map> list = null;
        try {
            list =
                    new BaseDao().queryDbBySql("select * from Navigation order by Navigation_ID asc");
        } catch (SQLException e) {
            throw new Exception("地区列表获取失败！");
        }
        if ((list == null) || (list.size() == 0)) {
            throw new Exception("地区列表为空，请联系管理员!");
        }

        for (int i = 0; i < list.size(); i++) {
            Map map = (Map)list.get(i);
            MenuAction ma = new MenuAction((String)map.get("NAVIGATION_NAME"));
            regionMenu.add(ma.REGION);
        }

        JMenu db = new JMenu("数据维护");
        MenuAction userUpdate = new MenuAction("用户信息维护");
        MenuAction details = new MenuAction("商品信息维护");
        db.add(userUpdate.db);
        db.add(details.db);
        JMenu sysMenu = new JMenu("系统");
        sysMenu.add(new MenuAction().EXIT);
        menuBar.add(regionMenu);
        menuBar.add(db);
        menuBar.add(sysMenu);

        return menuBar;
    }
}
