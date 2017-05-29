package 字典树;

import java.io.*;
import java.util.LinkedList;
public class FileSaveLoad{
	private String rtn=".\\treeset\\";
	private String user;
	//private String rtn2=".\\testsa.txt";
	private String tempfa,tempch;
	private tree root;
	public void fsdfs(tree father,BufferedWriter bout) throws IOException{
		String rootst,childst;
		LinkedList<tree> queue=new LinkedList<tree>();
		father.pump_it(queue);
		
		try{
			if(father.if_leave()){
				rootst=father.get_string();
				bout.write(rootst+"/");
				childst=""+father.get_leave_order_num();
				bout.write("<if_leave>1"+"/");
				bout.write(childst);
				bout.newLine();
				
				//test
				//System.out.println(rootst+" "+childst);
			}
			while(!queue.isEmpty()){
				tree s=queue.removeFirst();	
				rootst=father.get_string();		
				bout.write(rootst+"/");
				childst=s.get_string();
				bout.write("<if_leave>0"+"/");
				bout.write(childst);
				bout.newLine();
				
				//test
				//System.out.println(rootst+" "+childst);
				
				fsdfs(s,bout);
			}
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("error:fileoperation.fsdfs");
		}	
	}
	public void filesave() throws IOException{
		try{
			BufferedWriter bout=new BufferedWriter(new FileWriter(rtn+this.user+".txt"));
			fsdfs(root,bout);
			bout.flush();
			bout.close();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("error:fileoperation.filesave");
		}
	}
	public void fldfs(tree father,BufferedReader bin)throws IOException{
		String nodeset[]=new String[3];
		while(true){
			try{
				if(tempfa.equals("end"))return;//break judge
				
				if(tempfa.equals("")&&tempch.equals("")){
					String str=bin.readLine();
					if(str==null) return;
					nodeset=str.split("/");
				}
				else
					if(tempfa.equals(nodeset[0])){
						nodeset[2]=tempch;
					}else return;
				if(nodeset[1].equals("<if_leave>0")){
					if(!nodeset[0].equals(father.get_string()))return;
					tree childtree=new tree(nodeset[2]);
					father.add_child(father.get_string(), childtree);
					childtree.set_father(father);
					//test
					//System.out.println(father.get_string()+" "+nodeset[2]);
					
					tempfa="";tempch="";
					fldfs(childtree,bin);
				}else if(nodeset[1].equals("<if_leave>1")){
					int temp=Integer.valueOf(nodeset[2]).intValue();
					father.set_num(temp);
					tree.totalnum++;
					//test
					//System.out.println(father.get_string()+" "+temp);
					
					String str=bin.readLine();
					if(str==null){
						tempfa="end";
						return;
					}
					nodeset=str.split("/");
					tempfa=nodeset[0];
					tempch=nodeset[2];
					if(!tempfa.equals(father.get_string()))return;
				}else{
					//抛出异常待写
				}
			}catch(IOException e){
				e.printStackTrace();
				System.out.println("error:fileoperation.fldfs");
			}
		}
	}
	
	public void fileload(String user) throws IOException{
		try{
			BufferedReader bin=new BufferedReader(new FileReader(rtn+this.user+".txt"));
			root=new tree(user);
			tempfa="";tempch="";
			fldfs(root,bin);
			bin.close();
		}catch(IOException e){
			e.printStackTrace();
			System.out.println("error:fileoperation.fileload");
		}
	}
	
	public tree get_root(String user) throws IOException{
		//path表示你所创建文件的路径
		String path = ".\\treeset\\";
		File f = new File(path);
		if(!f.exists()){
		f.mkdirs();
		}
		// fileName表示你创建的文件名；为txt类型；
		String fileName=user+".txt";
		File file = new File(f,fileName);
		if(!file.exists()){
			try {
				file.createNewFile();
				} catch (IOException e) {
				// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		this.user=user;
		this.fileload(user);
		return root;
	}
}