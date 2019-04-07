import java.util.*;
import java.io.*;

class MyStack{
//用链表实现栈不太合适
//使用数组实现栈

//下面的tail应该换成top 表示栈顶
//注释里的栈的末尾 应该换成 栈顶
	
	static int[] a;
	static int tail;//指示栈的末尾 后进先出 tail减到栈第一个元素之前的位置 这个位置咩有初始化
	
	public static void main(String... args) throws Exception{
		try(Scanner in = new Scanner(System.in)){
			int b = in.nextInt();
			a=new int[b+1];//数组里有初始化的部分（有初始化的部分就是栈）的上一个元素，tail减到这时，完成遍历栈
			
			for(int i=1;i<a.length;i++){//数组有初始化的部分从序号1开始
				a[i]=in.nextInt();
			}
			
			tail=a.length-1;

			
			System.out.println("栈末尾添加一个元素");
			//栈末尾添加一个元素
			//数组数量翻倍
			int[] c = new int[a.length*2];
			for(int i = 0;i<a.length;i++){
				c[i]=a[i];
			}
			a=c;
			
			int d = in.nextInt();
			tail++;
			a[tail]=d;

			//栈末尾减少两个元素
			tail--;//栈末尾减少一个元素
			tail--;//栈末尾再减少一个元素

			int e = tail;			
			while(e!=0){//实际上是tail==0;这里是打印栈中的元素 并不是真的把栈的元素减少 所以把tail的值赋给另一个变量 用这个变量递减来访问栈的元素
				System.out.print(a[e]+ " ");
				e--;
			}
		}
	}
}
				
			
			
			