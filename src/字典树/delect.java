package �ֵ���;
import java.io.IOException;

import ����.GetLeaveNode;
public class delect {
	public static void delect_word(String word,tree root){
		tree tr=GetLeaveNode.getleavenode(word, root);
		tree fa=tr.get_father();
		if(tr.get_childlist_num()>0){
			//�к��ӽڵ㲢�ҵ�ǰ����ƫ����,-1Ϊɾ��Ҷ�ӽڵ��ʾ
			tr.set_num(-1);
		}else{
			while(true){
				fa.delete_child(tr);
				if(!fa.if_leave()&&fa.get_childlist_num()==0&&fa!=root){
					tr=fa;
					fa=tr.get_father();
				}else break;
			}
		}
		
	}
	//test
	/*
	public static void main(String args[]) throws IOException{
		FileSaveLoad fsl=new FileSaveLoad();
		tree tr=fsl.get_root("test");
		delect.delect_word("be",tr);
		delect.delect_word("cf", tr);
		delect.delect_word("gh", tr);
		fsl.filesave();
	}*/
}
