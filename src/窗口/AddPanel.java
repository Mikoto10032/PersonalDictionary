package 窗口;

import 字典树.*;
import 函数.File_Reader;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class AddPanel extends JFrame{
	String username;
	tree root_sys,root_user;
	JLabel word=new JLabel("【成语】");
	JLabel pinyin=new JLabel("【拼音】");
	JLabel jieshi=new JLabel("【解释】");
	JLabel chuchu=new JLabel("【出处】");
	JLabel shili=new JLabel("【示例】");
	JTextArea ta_word=new JTextArea();
	JTextArea ta_pinyin=new JTextArea();
	JTextArea ta_jieshi=new JTextArea();
	JTextArea ta_chuchu=new JTextArea();
	JTextArea ta_shili=new JTextArea();
	JButton  sure=new JButton("确认添加√");
	public void launch_addword(String username,tree root_sys,tree root_user){
		this.username=username;
		this.root_sys=root_sys;
		this.root_user=root_user;
		Font font = new Font("宋体",Font.BOLD,22);  //创建一个字体以便其它调用
		word.setFont(font);
		pinyin.setFont(font);
		jieshi.setFont(font);
		chuchu.setFont(font);
		shili.setFont(font);
		ta_word.setFont(font);
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
		int windowWidth = this.getWidth();                     //获得窗口宽
	    int windowHeight = this.getHeight();                   //获得窗口高
	    Toolkit kit = Toolkit.getDefaultToolkit();              //定义工具包
	    Dimension screenSize = kit.getScreenSize();             //获取屏幕的尺寸
	    int screenWidth = screenSize.width;                     //获取屏幕的宽
	    int screenHeight = screenSize.height;                   //获取屏幕的高
		this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//设置窗口居中显示  
		this.getContentPane().setBackground(Color.WHITE);
		this.setVisible(true);
		this.setDefaultCloseOperation(2);
		sure.addActionListener(new Add_Listener());
	}
	//内部类，事件监听
		private class Add_Listener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String word=ta_word.getText(),word1=ta_word.getText();
				String pinyin=ta_pinyin.getText();
				String jieshi=ta_jieshi.getText();
				String chuchu=ta_chuchu.getText();
				String shili=ta_shili.getText();
				if(word.length()!=4){
					JOptionPane.showConfirmDialog(null, "提示：输入成语的个数为四！", "Message", JOptionPane.YES_NO_OPTION); 
				}
				else {
					String all=word+" 【拼音】"+pinyin+" 【解释】"+jieshi+" 【出处】"+chuchu+" 【示例】"+shili;
					System.out.println(all);
					//调用添加函数
					JOptionPane.showConfirmDialog(null, "添加成功！", "Message", JOptionPane.YES_NO_OPTION); 
					UserPanel.refresh(word1);
					try {
						File_Reader.add_word(word1,all,username,root_user);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
		}
	}
		
}

