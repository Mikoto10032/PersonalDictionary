package 窗口;

import 字典树.*;
import 函数.File_Reader;
import 函数.GetLeaveNode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class UserPanel extends JFrame{
	String username;
	tree root_sys,root_user;
	private static Vector<String> v = new Vector<String>();// 可实现自动增长对象数组
	private JPanel panel=new JPanel();
	JScrollPane ScrollPane1 ;
	static JList word_list;
	JButton add=new JButton("添加");
	JButton delete=new JButton("删除");
	JButton change=new JButton("修改");
	public void launch_userpanel(String username,tree root_sys,tree root_user) throws IOException{
		this.username=username;
		this.root_sys=root_sys;
		this.root_user=root_user;
		Font font = new Font("宋体",Font.BOLD,22);  //创建一个字体以便其它调用
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		add.setPreferredSize(new Dimension(70, 50));
		add.setBackground(new Color(0,191,255));
		add.setFont(new Font("宋体", Font.PLAIN, 16));
		add.setForeground(Color.WHITE);
	    add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    delete.setPreferredSize(new Dimension(70, 50));
		delete.setBackground(new Color(0,191,255));
		delete.setFont(new Font("宋体", Font.PLAIN, 16));
		delete.setForeground(Color.WHITE);
	    delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    change.setPreferredSize(new Dimension(70, 50));
		change.setBackground(new Color(0,191,255));
		change.setFont(new Font("宋体", Font.PLAIN, 16));
		change.setForeground(Color.WHITE);
	    change.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		panel.add(add);
		panel.add(delete);
		panel.add(change);
		init_v(username);
		word_list=new JList(v);
		word_list.setFont(font);
		word_list.setForeground(new Color(255,255,0));
		word_list.setBackground(new Color(205,100,60));
		word_list.setBorder(BorderFactory.createTitledBorder("我的个人词典"));
		ScrollPane1=new JScrollPane(word_list);
		this.getContentPane().add(panel, "North");
		this.getContentPane().add(ScrollPane1);
		this.setSize(500, 500);
		int windowWidth = this.getWidth();                     //获得窗口宽
	    int windowHeight = this.getHeight();                   //获得窗口高
	    Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
	    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	    int screenWidth = screenSize.width;                     //获取屏幕的宽
	    int screenHeight = screenSize.height;                   //获取屏幕的高
		this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示  
		this.setTitle(username);
		this.setVisible(true);
		this.setDefaultCloseOperation(2);
		add.addActionListener(new Add_Listener());
		delete.addActionListener(new Delete_Listener());
		change.addActionListener(new Change_Listener());
	}
	//内部类，事件监听
		private class Add_Listener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddPanel add=new AddPanel();
				add.launch_addword(username,root_sys,root_user);
		}
		}
		private class Delete_Listener implements ActionListener{
			public void actionPerformed(ActionEvent e){
				if (word_list.getSelectedValues().length > 0) {
                    Object[] objArr = word_list.getSelectedValues();
                    for (int i = 0; i < objArr.length; i++) {
                    	System.out.println(objArr[i]);
                        v.remove(objArr[i]);
                    }
                    word_list.setListData(v);
                    String word=(String) objArr[0];
                    //找到偏移量offset
                    int offset=GetLeaveNode.getleavenode(word, root_user).get_leave_order_num();
                    delect.delect_word(word,root_user);
                    
                    try {
						File_Reader.delete(offset,username);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                } else {
                    JOptionPane.showMessageDialog(null, "请选中成语!");
                }
			}
		}
		private class Change_Listener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (word_list.getSelectedValues().length > 0) {
                    Object[] objArr = word_list.getSelectedValues();
                    int index[]=word_list.getSelectedIndices();
                    String word=(String) objArr[0];//四字成语
                    ChangePanel c=new ChangePanel();
					 c.launch_changeword(username, word, root_sys, root_user,index[0]);
                } else {
                    JOptionPane.showMessageDialog(null, "请选中成语!");
                }
		}
		}
	public void init_v(String username) throws IOException{
		RandomAccessFile rf=new RandomAccessFile(".\\data\\"+username+".txt","rw");
		String word;
		String line;
		int num=0;
		while((line=rf.readLine())!=null){
			word=new String(line.getBytes("8859_1"),"gbk");
			word=word.substring(0, 4);
			if(!word.equals("####"))
			{
				v.add(word);
			}
			num++;
			rf.seek(num*400);
		}
		rf.close();
	}
	public static void refresh(String word){
		v.add(word);
		 word_list.setListData(v);
	}
	public static void refresh2(String word,int index){
		v.set(index, word);
		 word_list.setListData(v);
	}
}