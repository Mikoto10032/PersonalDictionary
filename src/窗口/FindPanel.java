package ����;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import ����.File_Reader;
import ����.GetLeaveNode;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import �ֵ���.*;
import �Ҵ�.FindWordByOffset;
import �Ҵ�.FindWordByOffsetUser;
public class FindPanel extends JFrame{
	private static String username;
	private static tree root_sys;
	private static tree root_user;
	private JTextField input=new JTextField();
	private JButton find =new JButton("����");
	private JTextArea output =new JTextArea();
	private JPanel panel=new JPanel();
	private JPanel panel2=new JPanel();
	private JPanel panel3=new JPanel();
	private JLabel user;
	private ImagePanel img;
	public void launchFind(String username,tree root_sys,tree root_user){
		this.username=username;
		this.root_sys=root_sys;
		this.root_user=root_user;
		Font font = new Font("����",Font.BOLD,20);  //����һ�������Ա���������
		input.setEditable(true);
		output.setEditable(false);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		//input.setOpaque(false);//����͸��
		input.setColumns(40);
		input.setPreferredSize(new Dimension(450, 50));//���ó�
		//������ɫ�ı߿�
		input.setBorder(new LineBorder(new Color(0,0,255)));
		//input.setBorder(BorderFactory.createRaisedBevelBorder()); // ͹�߿�
		//input.setBorder(BorderFactory.createLoweredBevelBorder()); // ���߿�,��������������ɫ�߿򲻼���
		input.setFont(font);
		find.setPreferredSize(new Dimension(70, 50));
		//����
		find.setBackground(new Color(0,191,255));
		find.setFont(new Font("����", Font.PLAIN, 16));
		//������ʽ
		find.setForeground(Color.WHITE);
		//����Ϊ����
		find.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		user=new JLabel(username);
		user.setForeground(Color.BLUE);
		user.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		user.setFont(font);
		panel.add(input);
		panel.add(find);
		user.setBounds(670, 0, 200, 50);
		this.getContentPane().add(user);
		panel.setBackground(Color.white);
		output.setRows(12);
		output.setColumns(70);
		output.setLineWrap(true);
		output.setEditable(false);
		output.setText("");
		output.setFont(font);
		output.setBorder(new LineBorder(new Color(0,0,255)));
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 5));
		panel2.add(output);
		panel2.setBackground(Color.WHITE);
		img=new ImagePanel();
		img.setSize(232,86);
		this.getContentPane().add(img);
		this.getContentPane().add(panel, "North");
		this.getContentPane().add(panel2, "Center");
		this.setSize(800, 400);
		int windowWidth = this.getWidth();                     //��ô��ڿ�
	    int windowHeight = this.getHeight();                   //��ô��ڸ�
	    Toolkit kit = Toolkit.getDefaultToolkit();              //���幤�߰�
	    Dimension screenSize = kit.getScreenSize();             //��ȡ��Ļ�ĳߴ�
	    int screenWidth = screenSize.width;                     //��ȡ��Ļ�Ŀ�
	    int screenHeight = screenSize.height;                   //��ȡ��Ļ�ĸ�
		this.setLocation(screenWidth/2-windowWidth/2, screenHeight/2-windowHeight/2);//���ô��ھ�����ʾ  
		this.setTitle(username);
		this.setVisible(true);
		//this.setDefaultCloseOperation(2);
		find.addActionListener(new Find_Listener());//�����¼�
		user.addMouseListener(new MouseAdp());
		this.addWindowListener(new WindowAdapter() {
	          @Override
	          public void windowClosing(WindowEvent e)
	          {
	             super.windowClosing(e);
	             try {
					LoginFrame.get_userfile().filesave();
					LoginFrame.get_sysfile().filesave();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	          }
		});
	}
	//�ڲ��࣬�¼�����
	private class Find_Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String word=input.getText();
			//�õ�word��ƫ����
			int offset;
			String res="";
			tree rootleave=GetLeaveNode.getleavenode(word, root_sys);
			tree userleave=GetLeaveNode.getleavenode(word, root_user);
			if(userleave!=null&&userleave.if_leave()){
				offset=userleave.get_leave_order_num();
				try {
					res=FindWordByOffsetUser.FindWordByOffset(offset,root_user.get_string());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else
				if(rootleave!=null){
					offset=rootleave.get_leave_order_num();
					try {
						res=FindWordByOffset.FindWordByOffset(offset);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}else{
					//not found
					offset=-1;
					
				}
			if(!word.equals("")){
				if(!res.equals("")){
					output.setText("");
					String pinyin;
					String jieshi;
					String chuchu;
					String shili;
					Pattern p1=Pattern.compile("(.ƴ��.*����)"); 
					Matcher m1=p1.matcher(res); 
					m1.find(); //�ҵ�ƴ��
					pinyin=m1.group(1);
					int s=0;
					for(s=1;s<pinyin.length();s++)
					{
						if(pinyin.charAt(s)=='��'){
							break;
						}
					}
					pinyin=pinyin.substring(0,s);
					Pattern p2=Pattern.compile("(.����.*����)"); 
					Matcher m2=p2.matcher(res); 
					m2.find(); //�ҵ�
					jieshi=m2.group(1);
					s=0;
					for(s=1;s<jieshi.length();s++)
					{
						if(jieshi.charAt(s)=='��'){
							break;
						}
					}
					jieshi=jieshi.substring(0,s);
					Pattern p3=Pattern.compile("(.����.*ʾ��)"); 
					Matcher m3=p3.matcher(res); 
					m3.find(); //�ҵ�
					chuchu=m3.group(1);
					s=0;
					for(s=1;s<chuchu.length();s++)
					{
						if(chuchu.charAt(s)=='��'){
							break;
						}
					}
					chuchu=chuchu.substring(0,s);
					Pattern p4=Pattern.compile("(��ʾ����.*)"); 
					Matcher m4=p4.matcher(res); 
					m4.find(); //�ҵ�
					shili=m4.group(1);
					output.append(word+"\r\n");
					output.append(pinyin+"\r\n");
					output.append(jieshi+"\r\n");
					output.append(chuchu+"\r\n");
					output.append(shili);
					if(shili.length()<=5){
						output.append("����");
					}
				}
				else {
					output.setText("û���ҵ�������������!");
				}
		}
		else {
			output.setText("û���ҵ�������������!");
		}
	}
	}
	private class MouseAdp implements MouseListener{
        public MouseAdp(){}
        @Override
	public void mouseClicked(MouseEvent e) {
       System.out.println("�������!");
       UserPanel u=new UserPanel();
		try {
			u.launch_userpanel(username,root_sys,root_user);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        }
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	}
	private class User_Listener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			UserPanel u=new UserPanel();
			try {
				u.launch_userpanel(username,root_sys,root_user);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public static void BuildFrame(tree systree,tree usertree){
		FindPanel Find =new FindPanel();
		username=usertree.get_string();
		root_sys=systree;
		root_user=usertree;
		Find.launchFind(username,root_sys,root_user);
	}
}
class ImagePanel extends JPanel{
	Image image;
	public void paint(Graphics g){
		try{
			image=ImageIO.read(new File("./logo.jpg"));
			g.drawImage(image,0,0,137,50,null);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
