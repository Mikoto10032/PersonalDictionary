package 窗口;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.*;
import 函数.FindUser;
import 函数.CreateUser;;
public class rigister{
	private JPanel jp1;
	private JPanel jpsub1;
	private JPanel jpsub2;
	private JPanel jpsub3;
	private JPanel jp2;
	private JPanel jp3;
	
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;
	
	private JTextField jtf1;
	private JTextField jtf2;
	private JTextField jtf3;
	
	private JButton jb;
	public rigister(){
		JFrame frm=new JFrame();
		frm.setTitle("注册");
		frm.setSize(800 ,400);
		int windowwidth=frm.getWidth();
		int windowheight=frm.getHeight();
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screensize=kit.getScreenSize();
		int screenwidth=screensize.width;
		int screenheight=screensize.height;
		frm.setLocation((screenwidth-windowwidth)/2,(screenheight-windowheight)/2 );
		//frm.setDefaultCloseOperation(3);
		frm.setLayout(new GridLayout(3,0));
		
		Font font=new Font("黑体",Font.PLAIN,28);
		Font hint=new Font("黑体",Font.ITALIC,20);
		
		jp1=new JPanel();
		jp1.setLayout(new GridLayout(0,3));
		
		jpsub1=new JPanel();
		jpsub1.setLayout(new GridLayout(3,0));
		jpsub2=new JPanel();
		jpsub2.setLayout(new GridLayout(3,0));
		//填充
		jpsub3=new JPanel();
		jpsub3.setLayout(new BorderLayout());
		jpsub3.add(new JLabel(""));
		
		jp2=new JPanel();
		jp2.setLayout(new FlowLayout());
		jp3=new JPanel();
		jp3.setLayout(new FlowLayout());
		
		jl1=new JLabel();
		jl1.setText("用户名:");
		jl1.setFont(font);
		jl1.setHorizontalAlignment(JLabel.RIGHT);
		jl2=new JLabel();
		jl2.setText("密码:");
		jl2.setFont(font);
		jl2.setHorizontalAlignment(JLabel.RIGHT);
		jl3=new JLabel();
		jl3.setText("密码确认:");
		jl3.setFont(font);
		jl3.setHorizontalAlignment(JLabel.RIGHT);
		
		jtf1=new JTextField();
		jtf1.setEditable(true);
		jtf1.setColumns(25);
		jtf1.setFont(hint);
		jtf1.setText("请输入15字内的用户名");
		jtf1.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                jtf1.setText("");
                jtf1.setFont(font);
            }
        });
		

		jtf2=new JTextField();
		jtf2.setEditable(true);
		jtf2.setColumns(25);
		jtf2.setFont(hint);
		jtf2.setText("请输入15字内的密码");
		jtf2.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                jtf2.setText("");
                jtf2.setFont(font);
            }
        });
		jtf3=new JTextField();
		jtf3.setEditable(true);
		jtf3.setColumns(25);
		jtf3.setFont(hint);
		jtf3.setText("请再输入一次密码");
		jtf3.addFocusListener(new FocusAdapter()
        {
            @Override
            public void focusGained(FocusEvent e)
            {
                jtf3.setText("");
                jtf3.setFont(font);
            }
            
        });
		
		jb=new JButton();
		jb.setText("注册");
		jb.setFont(font);
		jb.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String admin=jtf1.getText();
				String pw1=jtf2.getText();
				String pw2=jtf3.getText();
				if(admin.equals("")){
					JOptionPane.showMessageDialog(null,"用户名不能为空","注册失败",JOptionPane.ERROR_MESSAGE);
				}else{
					if(!pw1.equals(pw2)){
						JOptionPane.showMessageDialog(null,"两次密码输入不对应","注册失败",JOptionPane.ERROR_MESSAGE);
						jtf2.setText("");
						jtf3.setText("");
					} else{
						if(pw1.equals("")){
							JOptionPane.showMessageDialog(null,"密码不能为空","注册失败",JOptionPane.ERROR_MESSAGE);
						}else{
							try {
								if(FindUser.finduser(jtf1.getText(), "syspasswordkirakira")){
									JOptionPane.showMessageDialog(null,"用户名已存在","注册失败",JOptionPane.ERROR_MESSAGE);
									jtf1.setText("");
									jtf2.setText("");
									jtf3.setText("");
								}else{
									//注册成功
									CreateUser.createuser(admin, pw1);
									frm.dispose();
									LoginFrame.frm.setVisible(true);
									//to search frame
								}
							} catch (HeadlessException | IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();	
								}
						}
					}
				}
			}
				
		});
		
		frm.addWindowListener(new WindowAdapter() {
	          @Override
	          public void windowClosing(WindowEvent e)
	          {
	             frm.dispose();
	             LoginFrame.frm.setVisible(true);
	          }
		});
		SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                jb.requestFocusInWindow();
            }
        });      
		jpsub1.add(jl1);
		jpsub1.add(jl2);
		jpsub1.add(jl3);
		
		jpsub2.add(jtf1);
		jpsub2.add(jtf2);
		jpsub2.add(jtf3);
		
		jp1.add(jpsub1);
		jp1.add(jpsub2);
		jp1.add(jpsub3);
		
		jp2.add(jb);
		
		frm.getContentPane().add(jp3);
		frm.getContentPane().add(jp1);
		frm.getContentPane().add(jp2);
		
		frm.setVisible(true);
	}
}
