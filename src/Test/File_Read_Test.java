package Test;

//类描述：用于成语数据的预处理，把源成语数据转化为比较方便操作的格式
/*具体说明：
1.把原始数据：
佹得佹失	
<font size=5 color=red>佹得佹失<br><br>\n</font>
<font size=3 color=blue>【拼音】guǐ  dé  guǐ  shī<br>\n</font>
<font size=3 color=orange>【解释】佹：出于偶然的。指得失出于偶然。<br>\n</font>
<font size=3 color=green>【出处】《列子·力命》：“佹佹成者，俏成者也，初非成也。佹佹败者，俏败者也，初非败也。”<br>\n</font>
<font size=3 color=purple>【示例】\n</font>
转化为：
佹得佹失 
【拼音】guǐ  dé  guǐ  shī 【解释】佹：出于偶然的。指得失出于偶然。 
【出处】《列子·力命》：“佹佹成者，俏成者也，初非成也。佹佹败者，俏败者也，初非败也。” 
【示例】
2.把每个成语在文件的偏移量读取并保存
*/
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class File_Read_Test {
	
	public static void main(String []args) throws IOException{
		//File_Reader.File_to();
		//File_Reader.File_to_Offset();
		//File_Reader.word_offset();
	}
	
}
