package 找词;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
//此类用于通过偏移量找到成语
public class FindWordByOffset {
	public static String FindWordByOffset(int offset) throws IOException{
		RandomAccessFile rf=new RandomAccessFile(".\\data\\sys.txt","r");
		rf.seek(offset);
		//在Windows下rf会默认编码成8859_1,所以要转化为GBK编码
		String res=new String(rf.readLine().getBytes("8859_1"),"gbk");
		System.out.println(res);
		return res;
	}
}
