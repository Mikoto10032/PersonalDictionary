package ����;
//����Ҷ�ӽڵ��tree,�����ڷ���null
import �ֵ���.tree;

public class GetLeaveNode {
	public static tree getleavenode(String word,tree root){
		tree tr=root;
		int i;
		boolean bo=true;
		for(i=0;i<word.length();i++){
			tree childtree=tr.if_leave_exist(String.valueOf(word.charAt(i)));
			if(childtree==null){
				bo=false;
				break;
			}else{
				tr=childtree;
			}
		}
		if(!bo) tr=null;
		return tr;
	}
}
