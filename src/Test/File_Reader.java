package Test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class File_Reader{
	public static void File_to() throws IOException{
		FileReader file_r=new FileReader(".\\源词典.txt");
		BufferedReader buffin=new BufferedReader(file_r);
		FileWriter file_w=new FileWriter(".\\目标词典.txt");
		BufferedWriter buffout=new BufferedWriter(file_w);
		String line;
		while((line=buffin.readLine())!=null){
			StringBuilder sb = new StringBuilder();
			//正则匹配找到目标数据
			Pattern p=Pattern.compile("(\\S*)"); //(\\S*)
			Matcher m=p.matcher(line); 
			m.find(); //找到成语
			String word=m.group(1);
			System.out.println(word);
			sb.append(word+" ");
			Pattern p1=Pattern.compile("(.拼音\\S*\\s*\\S*\\s*\\S*\\s*\\S{0,6}<*?)"); 
			Matcher m1=p1.matcher(line); 
			m1.find(); //找到拼音
			String word1=m1.group(1);
			//一些不规范的数据的处理
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
			Pattern p2=Pattern.compile("(.解释.\\S*)"); 
			Matcher m2=p2.matcher(line); 
			m2.find(); //找到解释
			String word2=m2.group(1);
			//一些不规范的数据的处理
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
			Pattern p3=Pattern.compile("(.出处.\\S*)"); 
			Matcher m3=p3.matcher(line); 
			m3.find(); //找到出处
			String word3=m3.group(1);
			//一些不规范的数据的处理
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
			Pattern p4=Pattern.compile("(.示例.\\S*)"); 
			Matcher m4=p4.matcher(line); 
			m4.find(); //找到出处
			String word4=m4.group(1);
			//一些不规范的数据的处理
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
	//获取成语词典每个成语的每一行的字符数和字节数,并且转化为相对于文件开头的偏移量
	public static void File_to_Offset() throws IOException{
		FileReader file_r=new FileReader(".\\目标词典.txt");
		BufferedReader buffin=new BufferedReader(file_r);
		String line;
		/*FileWriter file_r1=new FileWriter(".\\目标词典偏移量_字符数.txt");
		BufferedWriter buffout0=new BufferedWriter(file_r1);
		FileWriter file_r2=new FileWriter(".\\目标词典偏移量_字节数.txt");
		BufferedWriter buffout=new BufferedWriter(file_r2);*/
		FileWriter file_r3=new FileWriter(".\\目标词典真实偏移量_字节数.txt");
		BufferedWriter buffout3=new BufferedWriter(file_r3);
		//buffout.write("0\r\n");
		int bt=0;
		buffout3.write("0\r\n");
		int num=0;
		while((line=buffin.readLine())!=null){
			//+2是\r\n
			//buffout0.write(Integer.toString(line.length()+2)+"\r\n");
			byte[] data = line.getBytes();
			//+2是\r\n
			int os=data.length+2;
			String offset=""+os;
			//String offset=Integer.toString(os);
			//buffout.write(offset+"\r\n");
			int Bt=bt+os;
			if(Bt==3110613){
				int t;
				t=1;
			}
			String B=""+Bt+"\r\n";
			buffout3.write(B);
			bt=Bt;
			num++;
			//buffout.flush();
			//buffout0.flush();
			buffout3.flush();
			if(num==14500){
				System.out.println(line);
			}
		}
		System.out.println(num);
		buffin.close();
		buffout3.close();
	}
	public void word_offset(ArrayList<String> list_word,ArrayList<String> list_offset) throws IOException{
		FileReader file_r=new FileReader(".\\data\\sys.txt");
		BufferedReader buffin=new BufferedReader(file_r);
		FileReader file_r1=new FileReader(".\\data\\目标词典真实偏移量_字节数.txt");
		BufferedReader buffin1=new BufferedReader(file_r1);
		String word,offset;
		while((word=buffin.readLine())!=null&&(offset=buffin1.readLine())!=null){
			word=word.substring(0, 4);
			list_word.add(word);
			list_offset.add(offset);
		}
		buffin.close();
		buffin1.close();
		System.out.println(list_word.size());
		System.out.println(list_offset.size());
		System.out.println(list_word);
		System.out.println(list_offset);
	}
}