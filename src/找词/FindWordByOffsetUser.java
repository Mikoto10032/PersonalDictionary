package �Ҵ�;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
//��������ͨ��ƫ�����ҵ�����
public class FindWordByOffsetUser {
	public static String FindWordByOffset(int line,String User) throws IOException{
		RandomAccessFile rf=new RandomAccessFile(".\\data\\"+User+".txt","r");
		rf.seek(line*400);
		//��Windows��rf��Ĭ�ϱ����8859_1,����Ҫת��ΪGBK����
		String res=new String(rf.readLine().getBytes("8859_1"),"gbk");
		System.out.println(res);
		return res;
	}
}
