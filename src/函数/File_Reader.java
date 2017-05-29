package ����;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import �ֵ���.insert;
import �ֵ���.tree;

public class File_Reader{
	public static void File_to() throws IOException{
		FileReader file_r=new FileReader(".\\Դ�ʵ�.txt");
		BufferedReader buffin=new BufferedReader(file_r);
		FileWriter file_w=new FileWriter(".\\Ŀ��ʵ�.txt");
		BufferedWriter buffout=new BufferedWriter(file_w);
		String line;
		while((line=buffin.readLine())!=null){
			StringBuilder sb = new StringBuilder();
			//����ƥ���ҵ�Ŀ������
			Pattern p=Pattern.compile("(\\S*)"); //(\\S*)
			Matcher m=p.matcher(line); 
			m.find(); //�ҵ�����
			String word=m.group(1);
			System.out.println(word);
			sb.append(word+" ");
			Pattern p1=Pattern.compile("(.ƴ��\\S*\\s*\\S*\\s*\\S*\\s*\\S{0,6}<*?)"); 
			Matcher m1=p1.matcher(line); 
			m1.find(); //�ҵ�ƴ��
			String word1=m1.group(1);
			//һЩ���淶�����ݵĴ���
		    int len=word1.length();
			int i=0;
			while(len--!=0){
				if(word1.charAt(i++)=='<'){
					break;
				}
			}
			word1=word1.substring(0,i-1);
			System.out.println(word1);
			sb.append(word1+" ");
			Pattern p2=Pattern.compile("(.����.\\S*)"); 
			Matcher m2=p2.matcher(line); 
			m2.find(); //�ҵ�����
			String word2=m2.group(1);
			//һЩ���淶�����ݵĴ���
			len=word2.length();
			i=0;
			while(len--!=0){
				if(word2.charAt(i++)=='<'){
					break;
				}
			}
			word2=word2.substring(0,i-1);
			System.out.println(word2);
			sb.append(word2+" ");
			Pattern p3=Pattern.compile("(.����.\\S*)"); 
			Matcher m3=p3.matcher(line); 
			m3.find(); //�ҵ�����
			String word3=m3.group(1);
			//һЩ���淶�����ݵĴ���
			len=word3.length();
			i=0;
			while(len--!=0){
				if(word3.charAt(i++)=='<'){
					break;
				}
			}
			word3=word3.substring(0,i-1);
			System.out.println(word3);
			sb.append(word3+" ");
			Pattern p4=Pattern.compile("(.ʾ��.\\S*)"); 
			Matcher m4=p4.matcher(line); 
			m4.find(); //ʾ��
			String word4=m4.group(1);
			//һЩ���淶�����ݵĴ���
			len=word4.length();
			i=0;
			while(len--!=0){
				if(word4.charAt(i++)=='\\'){
					break;
				}
			}
			word4=word4.substring(0,i-1);
			System.out.println(word4);
			sb.append(word4+" ");
			buffout.write(sb.toString());
			buffout.write("\r\n");
		}
		buffin.close();
		buffout.close();
	}
	//��ȡ����ʵ�ÿ�������ÿһ�е��ַ������ֽ���,����ת��Ϊ������ļ���ͷ��ƫ����
	public static void File_to_Offset() throws IOException{
		FileReader file_r=new FileReader(".\\data\\�ʵ�_�̶�ƫ��.txt");
		BufferedReader buffin=new BufferedReader(file_r);
		String line;
		/*FileWriter file_r1=new FileWriter(".\\Ŀ��ʵ�ƫ����_�ַ���.txt");
		BufferedWriter buffout0=new BufferedWriter(file_r1);
		FileWriter file_r2=new FileWriter(".\\Ŀ��ʵ�ƫ����_�ֽ���.txt");
		BufferedWriter buffout=new BufferedWriter(file_r2);*/
		FileWriter file_r3=new FileWriter(".\\data\\ƫ����_����.txt");
		BufferedWriter buffout3=new BufferedWriter(file_r3);
		//buffout.write("0\r\n");
		int bt=0;
		int num=0;
		while((line=buffin.readLine())!=null){
			String ss=""+num+"\r\n";
			buffout3.write(ss);
			buffout3.flush();
			num++;
			System.out.println(line);
		}
		System.out.println(num);
		//buffout3.close();
		//buffin.close();
		
	}
	/**
	 * ת��Ϊ400byte�ĳ���
	 * @throws IOException
	 */
	/*
	public static void fixedlength() throws IOException{
		FileReader file_r=new FileReader(".\\data\\sys.txt");
		BufferedReader buffin=new BufferedReader(file_r);
		RandomAccessFile rf=new RandomAccessFile(".\\data\\�ʵ�_�̶�ƫ��.txt","rw");
		String s;
		int n=0;
		while((s=buffin.readLine())!=null){
			rf.seek(400*n);
			System.out.println(s);
			s=s+"\r\n";
			rf.write(s.getBytes());
			n++;
		}
	}*/
	public  void word_offset(ArrayList<String> list_word,ArrayList<String> list_offset) throws IOException{
		RandomAccessFile rf=new RandomAccessFile(".\\data\\�ʵ�_�̶�ƫ��.txt","r");
		String word;
		String line;
		int num=0;
		while((line=rf.readLine())!=null){
			word=new String(line.getBytes("8859_1"),"gbk");
			word=word.substring(0, 4);
			list_word.add(word);
			list_offset.add(""+num);
			num++;
			rf.seek(num*400);
		}
		rf.close();
		System.out.println(list_word.size());
		System.out.println(list_offset.size());
		System.out.println(list_word);
		System.out.println(list_offset);
	}
	public void word_offset_user(ArrayList<String> list_word,ArrayList<String> list_offset,String username) throws IOException{
		RandomAccessFile rf=new RandomAccessFile(".\\data\\"+username+".txt","rw");
		String word;
		String line;
		int num=0;
		while((line=rf.readLine())!=null){
			word=new String(line.getBytes("8859_1"),"gbk");
			word=word.substring(0, 4);
			if(!word.equals("####"))
			{
				list_word.add(word);
				list_offset.add(""+num);
			}
			num++;
			rf.seek(num*400);
		}
		rf.close();
		System.out.println(list_word.size());
		System.out.println(list_offset.size());
		System.out.println(list_word);
		System.out.println(list_offset);
	}
	public static void add_word(String subword,String word,String username,tree user_root) throws IOException{
		RandomAccessFile rf=new RandomAccessFile(".\\data\\"+username+".txt","rw");
		String line;
		int num=0;
		while((line=rf.readLine())!=null){
			num++;
		}
		rf.seek(num*400);
		word=word+"\r\n";
		rf.write(word.getBytes());
		insert.put_word(subword, num, user_root);
	}
	public static void delete(int len,String username) throws IOException{
		chang_word("##########",len,username);
		
	}
	public static void chang_word(String Word,int len,String username) throws IOException{
		RandomAccessFile rf=new RandomAccessFile(".\\data\\"+username+".txt","rw");
		ArrayList<String> list_word=new ArrayList<>();
		String line;
		int num=0;
		while((line=rf.readLine())!=null){
			 String word=new String(line.getBytes("8859_1"),"gbk");
			if(num!=len) list_word.add(word);
			else {
				list_word.add(Word);
			}
			num++;
			rf.seek(num*400);
		}
		rf.close();
		FileOutputStream a=new FileOutputStream(".\\data\\"+username+".txt",false);
		a.close();
		RandomAccessFile rf1=new RandomAccessFile(".\\data\\"+username+".txt","rw");
		String s;
		int n=0;
		int nn=0;
		int size=list_word.size();
		while(true){
			rf1.seek(400*n);
			s=list_word.get(n);
			s=s+"\r\n";
				rf1.write(s.getBytes());
				System.out.println(s);
			n++;
			if(n==size) break;
		}
		rf1.close();
	}
}