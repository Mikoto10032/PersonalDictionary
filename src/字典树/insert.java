package 字典树;
//所有标示掉的代码均为手动输入第三方词典时使用
import java.io.IOException;
import Test.File_Reader;
import java.util.ArrayList;

public class insert {
	//rivate static FileSaveLoad root;
	//private static tree roottr;
	public static void put_word(String word,int offset,tree roottr)throws IOException{
		int i;
		tree tr=roottr;
		for(i=0;i<word.length();i++){
			
			tree childtree=tr.if_leave_exist(String.valueOf(word.charAt(i)));
			if(childtree==null){
				tree temptree=new tree(String.valueOf(word.charAt(i)));
				tr.add_child(tr.get_string(),temptree);
				tr=temptree;
			}else{
				tr=childtree;
			}
		}
		tr.set_num(offset);
	}
	/*
	public void insert_word(String user,int offset,String word) throws IOException{
		root=new FileSaveLoad();
		roottr=root.get_root(user);
		this.put_word(word, offset, user);
	}
	*/
	
	/*
	//system dictionary insert
	public static void main(String[] args) throws IOException{
		ArrayList<String> wordlist=new ArrayList<String>();
		ArrayList<String> offsetlist=new ArrayList<String>();
		File_Reader fr=new File_Reader();
		fr.word_offset(wordlist,offsetlist);
		int len=offsetlist.size();
		String word;
		int offset;
		root=new FileSaveLoad();
		roottr=root.get_root("sys");
		insert in=new insert();
		for(int i=0;i<len;i++){
			word=wordlist.get(i);
			offset=Integer.valueOf(offsetlist.get(i)).intValue();
			in.put_word(word, offset, "sys");
		}
		root.filesave();
	}
	*/
}
