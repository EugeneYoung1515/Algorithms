import java.util.*;
import java.io.*;

class MyLinkedList{
	double a;
	MyLinkedList l;//用来指向链表的下一个元素
}
class MyLinkedListObject{
	Object a;
	MyLinkedListObject l;
}
class MyLinkedList2{
	public static MyLinkedList main(String... args) throws Exception{
			int a=Integer.parseInt(args[0]);

			MyLinkedList tmp=null;
			MyLinkedList head = null;
			MyLinkedList p;

			for(int i=0;i<a;i++){
				int b=5;//in.nextInt();
				p=new MyLinkedList();
				p.a=b;
				p.l=null;
				if(tmp!=null){
					tmp.l=p;}
				if(head == null){
					head=p;
				}
				tmp=p;
			}

/*
				while(head!=null){
				System.out.print(head.a+" ");
				head = head.l;
				}
*/
				return head;
	}
	public static MyLinkedList ForOut(String arg) throws Exception{//给外部使用
		return main(arg);
	}
}