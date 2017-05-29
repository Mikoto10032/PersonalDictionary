package º¯Êý;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CreateUser {
	private final static String rtntree=".\\treeset"; 
	private final static String rtnuser=".\\userlist.txt";
	private final static String rtndata=".\\data";
	public static void createuser(String admin,String password) throws IOException{
		//new treeset
		File path1=new File(rtntree);
		File file1=new File(path1,admin+".txt");
		if(!file1.exists())
			try{
				file1.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		//new data
		File path2=new File(rtndata);
		File file2=new File(path2,admin+".txt");
		if(!file2.exists())
			try{
				file2.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		//insert user
		try{
			FileWriter fw=new FileWriter(rtnuser,true);
			fw.write(admin+" "+password);
			fw.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
