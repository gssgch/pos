package com.pos.iframe;

import com.pos.LoginAction;
import com.pos.util.CheckUtil;
import com.pos.util.DimensionUtil;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class PosLoginIFrame
  extends JFrame
{
  private JPasswordField password;
  private JTextField username;
  private JButton login;
  private JButton reset;
  private Dimension ds = DimensionUtil.getDimension();
  
  public PosLoginIFrame() {
    super("POS打印系统登录窗口");
    
    setDefaultCloseOperation(3);
    BorderLayout borderLayout = new BorderLayout();
    borderLayout.setVgap(20);
    getContentPane().setLayout(borderLayout);
    setBounds(this.ds.width / 3, this.ds.height / 3, 320, 230);
    

    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(new EmptyBorder(0, 0, 0, 0));
    getContentPane().add(panel);
    

    JLabel tupianLabel = new JLabel();
    

    tupianLabel.setOpaque(true);
    tupianLabel.setBackground(Color.GREEN);
    tupianLabel.setPreferredSize(new Dimension(260, 60));
    panel.add(tupianLabel, "North");
    

    JPanel panel_2 = new JPanel();
    panel_2.setLayout(null);
    JLabel userLabel = new JLabel("用  户  名：");
    JLabel passWdLabel = new JLabel("密      码：");
    
    this.username = new JTextField(20);
    this.password = new JPasswordField(20);
    
    userLabel.setBounds(70, 20, 80, 20);
    this.username.setBounds(150, 20, 140, 20);
    passWdLabel.setBounds(70, 50, 80, 20);
    this.password.setBounds(150, 50, 140, 20);
    
    panel_2.add(this.username);
    panel_2.add(userLabel);
    
    panel_2.add(passWdLabel);
    panel_2.add(this.password);
    
    this.login = new JButton("登录");
    this.reset = new JButton("重置");
    this.login.setBounds(70, 80, 60, 30);
    this.reset.setBounds(160, 80, 60, 30);
    
    this.login.addActionListener(new PosLoginAction());
    this.reset.addActionListener(new PosResetAction(null));
    panel_2.add(this.login);
    panel_2.add(this.reset);
    
    panel.add(panel_2);
    
    setVisible(true);
    setResizable(false);
  }
  
  private class PosResetAction implements ActionListener { private PosResetAction() {}
    
    public void actionPerformed(ActionEvent e) { PosLoginIFrame.this.username.setText("");
      PosLoginIFrame.this.password.setText("");
    }
  }
  
  class PosLoginAction implements ActionListener {
    PosLoginAction() {}
    
    public void actionPerformed(ActionEvent e) { String userName = PosLoginIFrame.this.username.getText();
      String passWord = PosLoginIFrame.this.password.getText();
      try
      {
        if (CheckUtil.checkValide(userName, passWord)) {
          JOptionPane.showMessageDialog(null, "用户不合法，请联系管理员！");
          PosLoginIFrame.this.username.setText("");
          PosLoginIFrame.this.password.setText("");
          return;
        }
      } catch (Exception e1) {
        e1.printStackTrace();
        JOptionPane.showMessageDialog(null, "用户验证异常，请联系管理员！");
        return;
      }
      PosLoginAction t = this;
      if (userName != null) {
        try {
          LoginAction frame = new LoginAction(userName, 
            PosLoginIFrame.this);
          frame.setVisible(true);
          PosLoginIFrame.this.setVisible(false);
          PosLoginIFrame.this.dispose();
        } catch (Exception ex) {
          ex.printStackTrace();
        }
      } else {
        JOptionPane.showMessageDialog(null, "用户验证失败，请联系管理员！");
        PosLoginIFrame.this.username.setText("");
        PosLoginIFrame.this.password.setText("");
      }
    }
  }
}
