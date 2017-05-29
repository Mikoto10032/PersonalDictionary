package ����;

import �ֵ���.*;
import ����.File_Reader;
import ����.GetLeaveNode;

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
	private static Vector<String> v = new Vector<String>();// ��ʵ���Զ�������������
	private JPanel panel=new JPanel();
	JScrollPane ScrollPane1 ;
	static JList word_list;
	JButton add=new JButton("���");
	JButton delete=new JButton("ɾ��");
	JButton change=new JButton("�޸�");
	public void launch_userpanel(String username,tree root_sys,tree root_user) throws IOException{
		this.username=username;
		this.root_sys=root_sys;
		this.root_user=root_user;
		Font font = new Font("����",Font.BOLD,22);  //����һ�������Ա���������
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
		add.setPreferredSize(new Dimension(70, 50));
		add.setBackground(new Color(0,191,255));
		add.setFont(new Font("����", Font.PLAIN, 16));
		add.setForeground(Color.WHITE);
	    add.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    delete.setPreferredSize(new Dimension(70, 50));
		delete.setBackground(new Color(0,191,255));
		delete.setFont(new Font("����", Font.PLAIN, 16));
		delete.setForeground(Color.WHITE);
	    delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	    change.setPreferredSize(new Dimension(70, 50));
		change.setBackground(new Color(0,191,255));
		change.setFont(new Font("����", Font.PLAIN, 16));
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
		word_list.setBorder(BorderFactory.createTitledBorder("�ҵĸ��˴ʵ�"));
		ScrollPane1=new JScrollPane(word_list);
		this.getContentPane().add(panel, "North");
		this.getContentPane().add(ScrollPane1);
		this.setSize(500, 500);
		int windowWidth = this.getWidth();                     //��ô��ڿ�
	    int windowHeight = this.getHeight();                   //��ô��ڸ�
	    Toolkit kit = Toolkit.getDefaultToolkit();              //���幤�߰�
	    Dimension screenSize = kit.getScreenSize();             //��ȡ��Ļ�ĳߴ�
	    int screenWidth = screenSize.width;                     //��ȡ��Ļ�Ŀ�
	    int screenHeight = screenSize.height;                   //��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//���ô��ھ�����ʾ  
		this.setTitle(username);
		this.setVisible(true);
		this.setDefaultCloseOperation(2);
		add.addActionListener(new Add_Listener());
		delete.addActionListener(new Delete_Listener());
		change.addActionListener(new Change_Listener());
	}
	//�ڲ��࣬�¼�����
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
                    //�ҵ�ƫ����offset
                    int offset=GetLeaveNode.getleavenode(word, root_user).get_leave_order_num();
                    delect.delect_word(word,root_user);
                    
                    try {
						File_Reader.delete(offset,username);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    
                } else {
                    JOptionPane.showMessageDialog(null, "��ѡ�г���!");
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
                    String word=(String) objArr[0];//���ֳ���
                    ChangePanel c=new ChangePanel();
					 c.launch_changeword(username, word, root_sys, root_user,index[0]);
                } else {
                    JOptionPane.showMessageDialog(null, "��ѡ�г���!");
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