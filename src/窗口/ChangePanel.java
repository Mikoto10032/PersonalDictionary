package ����;

import �ֵ���.*;
import ����.File_Reader;
import ����.GetLeaveNode;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ChangePanel extends JFrame{
	int index;
	String username;
	tree root_sys,root_user;
	JLabel word=new JLabel("�����");
	JLabel pinyin=new JLabel("��ƴ����");
	JLabel jieshi=new JLabel("�����͡�");
	JLabel chuchu=new JLabel("��������");
	JLabel shili=new JLabel("��ʾ����");
	JTextArea ta_word=new JTextArea();
	JTextArea ta_pinyin=new JTextArea();
	JTextArea ta_jieshi=new JTextArea();
	JTextArea ta_chuchu=new JTextArea();
	JTextArea ta_shili=new JTextArea();
	JButton  sure=new JButton("ȷ���޸ġ�");
	public void launch_changeword(String username,String wd,tree root_sys,tree root_user,int index){
		this.username=username;
		this.root_sys=root_sys;
		this.root_user=root_user;
		this.index=index;
		Font font = new Font("����",Font.BOLD,22);  //����һ�������Ա���������
		word.setFont(font);
		pinyin.setFont(font);
		jieshi.setFont(font);
		chuchu.setFont(font);
		shili.setFont(font);
		ta_word.setFont(font);
		ta_word.setText(wd);
		ta_word.setBorder(new LineBorder(new Color(0,0,255)));
		ta_pinyin.setFont(font);
		ta_pinyin.setBorder(new LineBorder(new Color(0,0,255)));
		ta_jieshi.setFont(font);
		ta_jieshi.setBorder(new LineBorder(new Color(0,0,255)));
		ta_chuchu.setFont(font);
		ta_chuchu.setBorder(new LineBorder(new Color(0,0,255)));
		ta_shili.setFont(font);
		ta_shili.setBorder(new LineBorder(new Color(0,0,255)));
		this.setTitle(username);
		this.setLayout(new GridLayout(11,1));
		this.getContentPane().add(word);
		ta_word.setRows(1);
		ta_word.setColumns(4);
		ta_word.setLineWrap(true);
		this.getContentPane().add(ta_word);
		this.getContentPane().add(pinyin);
		ta_pinyin.setRows(1);
		ta_pinyin.setColumns(15);
		ta_pinyin.setLineWrap(true);
		this.getContentPane().add(ta_pinyin);
		this.getContentPane().add(jieshi);
		ta_jieshi.setRows(5);
		ta_jieshi.setColumns(10);
		ta_jieshi.setLineWrap(true);
		this.getContentPane().add(ta_jieshi);
		this.getContentPane().add(chuchu);
		ta_chuchu.setRows(5);
		ta_chuchu.setColumns(10);
		ta_chuchu.setLineWrap(true);
		this.getContentPane().add(ta_chuchu);
		this.getContentPane().add(shili);
		ta_shili.setRows(5);
		ta_shili.setColumns(10);
		ta_shili.setLineWrap(true);
		this.getContentPane().add(ta_shili);
		sure.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		sure.setBackground(Color.WHITE);
		sure.setFont(font);
		this.getContentPane().add(sure);
		this.setSize(800, 800);
		int windowWidth = this.getWidth();                     //��ô��ڿ�
	    int windowHeight = this.getHeight();                   //��ô��ڸ�
	    Toolkit kit = Toolkit.getDefaultToolkit();              //���幤�߰�
	    Dimension screenSize = kit.getScreenSize();             //��ȡ��Ļ�ĳߴ�
	    int screenWidth = screenSize.width;                     //��ȡ��Ļ�Ŀ�
	    int screenHeight = screenSize.height;                   //��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//���ô��ھ�����ʾ  
		this.getContentPane().setBackground(Color.WHITE);
		this.setVisible(true);
		this.setDefaultCloseOperation(2);
		sure.addActionListener(new Change_Listener());
	}
	//�ڲ��࣬�¼�����
		private class Change_Listener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String word=ta_word.getText(),word1=ta_word.getText();
				String pinyin=ta_pinyin.getText();
				String jieshi=ta_jieshi.getText();
				String chuchu=ta_chuchu.getText();
				String shili=ta_shili.getText();
				if(word.length()!=4){
					JOptionPane.showConfirmDialog(null, "��ʾ���������ĸ���Ϊ�ģ�", "Message", JOptionPane.YES_NO_OPTION); 
				}
				else {
					String all=word+" ��ƴ����"+pinyin+" �����͡�"+jieshi+" ��������"+chuchu+" ��ʾ����"+shili;
					System.out.println(all);
					//������Ӻ���
					JOptionPane.showConfirmDialog(null, "��ӳɹ���", "Message", JOptionPane.YES_NO_OPTION);
					//�ҵ�offset
					int offset=GetLeaveNode.getleavenode(word1, root_user).get_leave_order_num();
					try {
						File_Reader.chang_word(all, offset, username);
						UserPanel.refresh2(word,index);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		}
	}
	public static void main(String args[]){
		ChangePanel c=new ChangePanel();
		c.launch_changeword("1", "0000", null, null,0);
	}
}
