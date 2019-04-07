//使用链表实现队列

import java.util.*;

class MyQueue{
	static int head=0;
	static int tail=0;
	MyLinkedList l;

	public static void main(String... args) throws Exception{
		MyQueue myqueue = new MyQueue();
		myqueue.l=MyLinkedList2.ForOut();

		MyLinkedList lhead=myqueue.l;//lhead开始时时指向链表的第一个元素
			while(lhead!=null){
				lhead = lhead.l;
				tail++;
			}

		System.out.println("在末尾加一个数8");
		//Scanner in = new Scanner(System.in);
		int a =8;//in.nextInt();
		MyLinkedList n  = new MyLinkedList();
		n.a=a;
		n.l=null;
		
		//还需要原来的末尾元素指向这个新的末尾元素
		lhead=myqueue.l;
		MyLinkedList tmp;
		while(head!=tail){
			tmp=lhead.l;
			if(head==tail-1){
				break;//加一个是为了能用上面的lhead 而不是用下面的lhead
			}
			lhead = tmp;
			head++;
		}
		lhead.l=n;
		tail++;

		head=0;
		lhead=myqueue.l;
		while(head!=tail){
			System.out.print(lhead.a+" ");
			lhead = lhead.l;
			head++;
		}
		//队列增加元素的时候 队列末尾加一个元素 tail++
		//队列减少元素的时候 队列开头减少一个元素 head++
		//读会遍历数组元素的时候 把head 赋给一个变量 这个变量自加

		//上面其实没有写出如何队列减少元素 只是遍历队列元素
		//队列开头减少一个元素
		System.out.println("队列开头减少一个元素");
		head=0;
		lhead=myqueue.l;
		if(head!=tail){//把上面的while换成if
			lhead = lhead.l;
			myqueue.l = lhead;
			head++;
		}
		
		//遍历队列元素 确认是否减少一个元素
		lhead=myqueue.l;
		和上面比 没有重新把head变为0
		while(head!=tail){
			System.out.print(lhead.a+" ");
			lhead = lhead.l;
			head++;
		}
	}
}