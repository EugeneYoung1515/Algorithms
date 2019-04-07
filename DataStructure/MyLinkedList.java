import java.util.*;
import java.io.*;

class MyLinkedList{
	int a;
	MyLinkedList l;//用来指向链表的下一个元素
}
class MyLinkedList2{
	public static MyLinkedList main(String... args) throws Exception{
		try(Scanner in = new Scanner(System.in)){
			int a=in.nextInt();

			MyLinkedList tmp=null;
			MyLinkedList head = null;
			MyLinkedList p;

			for(int i=0;i<a;i++){
				int b=in.nextInt();
				p=new MyLinkedList();
				p.a=b;
				p.l=null;
				if(tmp!=null){
					tmp.l=p;}
				if(head == null){
					head=p;
				}
				tmp=p;
			}//注意作用域 这些引用变量的作用域是这个语句块 这些对象没人指着就等着被回收吧
			//注意一个对象有实例变量指着下一个对象，下一个对象有实例变量指着下下个对象...也就是对象都会被指着，不会被回收
			//不过还要考虑作用域 p在语句块外 应该就不会被回收(p指向的对象在语句块里new的 但是不会被回收）
			
			//上面循环完，对象都还在，引用变量tmp指向倒数第二个对象（或者说元素)，p指向最后一个对象，head指向第一个对象。
			//一个对象有实例变量指着下一个对象，下一个对象有实例变量指着下下个对象...也就是对象都会被指着，不会被回收

			//语句块里对象一个指着一个，第一个对象有语句块外的head指着，不会都不会被回收

/*
				while(head!=null){
				System.out.print(head.a+" ");
				head = head.l;
				}
*/
				return head;	
		}
	}
	public static MyLinkedList ForOut() throws Exception{//给外部使用
		return main("t");
	}
}
//计划
//用引用变量和new实现链表
//用两个数组实现链表

//用数组实现队列
//用链表实现队列

//用数组实现栈
//能用链表实现栈吗？

	