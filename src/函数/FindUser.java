package 函数;
import java.io.*;
public class FindUser {
	private final static String rtn=".\\userlist.txt";
	public static boolean finduser(String user,String password) throws IOException{
		boolean bo=false;
		try{
			BufferedReader bin=new BufferedReader(new FileReader(rtn));
			String str;
			String set=user+password;

			while((str=bin.readLine())!=null){
				
				if(password.equals("syspasswordkirakira")){ 
					//检查注册是否重复
					int t=str.indexOf(" ");
					String temp=str.substring(0, t);
					if(temp.equals(user)){
						bo=true;
						break;
					}
				}else{
					String temp=str.replace(" ", "");
					if(temp.equals(set)){
						bo=true;
						break;
					}
				}
				
			}
			bin.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		return bo;
	}
}
