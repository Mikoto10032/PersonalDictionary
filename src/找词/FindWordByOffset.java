package �Ҵ�;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
//��������ͨ��ƫ�����ҵ�����
public class FindWordByOffset {
	public static String FindWordByOffset(int offset) throws IOException{
		RandomAccessFile rf=new RandomAccessFile(".\\data\\sys.txt","r");
		rf.seek(offset);
		//��Windows��rf��Ĭ�ϱ����8859_1,����Ҫת��ΪGBK����
		String res=new String(rf.readLine().getBytes("8859_1"),"gbk");
		System.out.println(res);
		return res;
	}
}
