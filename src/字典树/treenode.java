package ×ÖµäÊ÷;

public class treenode {
	private String string;
	private int order_num;
	private boolean if_leave;
	
	treenode(String st){
		this.string=st;
		this.if_leave=false;
	}
	public void set_num(int no){
		this.order_num=no;
		if(no==-1) 
			//-1ÎªÉ¾³ý¸ÃµãµÄÆ«ÒÆ
			this.if_leave=false;
		else
			this.if_leave=true;
	}
	
	public String get_string(){
		return string;
	}
	
	public int get_order_num(){
		return this.order_num;
	}
	
	public boolean get_if_leave(){
		return this.if_leave;
	}

}
