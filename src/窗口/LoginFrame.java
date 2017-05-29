package 窗口;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import 函数.FindUser;

import 字典树.FileSaveLoad;
import 字典树.tree;

import javax.swing.*;
public class LoginFrame extends JFrame{
	private static tree systree;
	private tree usertree;
	private static FileSaveLoad sysfile;
	private static FileSaveLoad userfile;
	public 	static JFrame frm;
	
	public LoginFrame(){
		frm=new JFrame();
		frm.setTitle("个人定制词典");
		frm.setSize(400 ,250);
		int windowwidth=frm.getWidth();
		int windowheight=frm.getHeight();
		Toolkit kit=Toolkit.getDefaultToolkit();
		Dimension screensize=kit.getScreenSize();
		int screenwidth=screensize.width;
		int screenheight=screensize.height;
		frm.setLocation((screenwidth-windowwidth)/2,(screenheight-windowheight)/2 );
		frm.setDefaultCloseOperation(3);
		//布局管理器——网格
		/*
		GridLayout layout=new GridLayout(0,3);
		layout.setHgap(40);
		layout.setVgap(20);
		frm.setLayout(layout);
		*/
		//布局管理器——箱形
		
		Font font=new Font("宋体",Font.BOLD,19);
		
		JLabel jl1=new JLabel();
		jl1.setText("用户:");
		jl1.setFont(font);

		JTextField jta=new JTextField();
		jta.setColumns(15);
		jta.setEditable(true);
		jta.setMaximumSize(jta.getPreferredSize());
		
		jta.setFont(font);

		JLabel jl2=new JLabel();
		jl2.setText("密码:");
		jl2.setFont(font);
		
		JPasswordField jpf=new JPasswordField();
		jpf.setColumns(15);
		jpf.setMaximumSize(jpf.getPreferredSize());
		jpf.setFont(font);
		
		JButton jb1=new JButton();
		jb1.setText("登陆");
		jb1.setFont(font);
		jb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				String admin=jta.getText();
				@SuppressWarnings("deprecation")
				String password=jpf.getText();
				//String password=jpf.getPassword().toString().trim();
				try {
					if(FindUser.finduser(admin, password)){
						//next frame
						userfile=new FileSaveLoad();
						usertree=userfile.get_root(admin);
						FindPanel.BuildFrame(systree,usertree);
						frm.dispose();
						//log
						System.out.println("login is successed");
					}else{
						JOptionPane.showMessageDialog(null,"用户不存在或密码错误","登陆失败",JOptionPane.ERROR_MESSAGE);
						jta.setText("");
						jpf.setText("");
					}
				} catch (HeadlessException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		
		JButton jb2=new JButton();
		jb2.setText("注册");
		jb2.setFont(font);
		jb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frm.setVisible(false);
				rigister ri=new rigister();
				jta.setText("");
				jpf.setText("");
			}
		});

		/*
		frm.getContentPane().add(new JLabel(""));
		frm.getContentPane().add(new JLabel(""));
		frm.getContentPane().add(new JLabel(""));
		frm.getContentPane().add(new JLabel(""));
		frm.getContentPane().add(jta);
		frm.getContentPane().add(new JLabel(""));
		frm.getContentPane().add(new JLabel(""));
		frm.getContentPane().add(jpf);
		frm.getContentPane().add(new JLabel(""));
		frm.getContentPane().add(new JLabel(""));
		frm.getContentPane().add(jb1);
		frm.getContentPane().add(jb2);
		*/
		Box box1=Box.createHorizontalBox();
		box1.add(jl1);
		box1.add(Box.createHorizontalStrut(20));
		box1.add(jta);
		Box box2=Box.createHorizontalBox();
		box2.add(jl2);
		box2.add(Box.createHorizontalStrut(20));
		box2.add(jpf);
		Box box3=Box.createHorizontalBox();
		box3.add(jb1);
		box3.add(Box.createHorizontalStrut(40));
		box3.add(jb2);
		Box boxall=Box.createVerticalBox();
		boxall.add(box1);
		boxall.add(box2);
		boxall.add(box3);
		frm.setContentPane(boxall);
		frm.setVisible(true);
		
	}
	public static FileSaveLoad get_sysfile(){
		return sysfile;
	}
	public static FileSaveLoad get_userfile(){
		return userfile;
	}
	public static void main(String[] args) throws IOException{
		sysfile=new FileSaveLoad();
		systree=sysfile.get_root("sys");
		LoginFrame lf=new LoginFrame();
	}
}
