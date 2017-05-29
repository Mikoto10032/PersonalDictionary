package ×ÖµäÊ÷;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
public class tree {
	private List<tree> childlist;
	private treenode node;
	private tree Father;
	public static int totalnum=0;
	tree(String initnode){
		node=new treenode(initnode);
	}
	public void add_child(String father,tree children){
		if(childlist==null){
			childlist=new ArrayList<tree>();
		}
		childlist.add(children);
	}
	public void set_num(int order_num){
		node.set_num(order_num);
	}
	
	public String get_string(){
		return node.get_string();
	}
	
	public tree if_leave_exist(String st){
		if(childlist==null) return null;
		int amount=childlist.size();		
		tree temp = null;
		
		for(int i=0;i<amount;i++){
			temp=childlist.get(i);
			String stt=temp.get_string();
			if(stt.equals(st))
				return temp;
		}
		return null;
	}
	
	public int get_leave_order_num(){
		return node.get_order_num();
	}
	
	public boolean if_leave(){
		return node.get_if_leave();
	}
	
	public void pump_it(LinkedList<tree> LL){		
		if(childlist==null) return;
		for(int i=0;i<childlist.size();i++)
			LL.add(childlist.get(i));
	}
	
	public void set_father(tree fa){
		this.Father=fa;
	}
	public tree get_father(){
		return this.Father;
	}
	public void delete_child(tree child){
		for(int i = 0 , len= childlist.size();i<len;i++){  
			  if(childlist.get(i)==child){  
			       childlist.remove(i);  
			       break;
			 }  
		}  
	}
	public int get_childlist_num(){
		if(childlist==null) return 0;
		return childlist.size();
	}
}