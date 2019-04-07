//使用数组实现队列
//head是队列的第一个元素 tail是队列末尾元素的下一个
//或者说head数组的第一个元素 tail是数组有初始化部分下一个元素
//也就是说用head遍历队列或数组时，head==tail时，tail位置是为初始化的

import java.util.*;
import java.io.*;

class MyQueue2{
	static int head;
	static int tail;
	static int[] queue;
	public static void main(String... args) throws Exception{
		System.out.println("队列元素长度：");
		try(Scanner in = new Scanner(System.in)){
			int a = in.nextInt();
			queue=new int[a+1];//最开始的数组长度
			head = 0;
			tail = a;
			
			//给队列元素赋值
		System.out.println("队列元素赋值：");
			int b = head;
			while(b!=tail){//实际上就是head!=tail
				queue[b]=in.nextInt();
				b++;
			}
			
			//队列开头移除一个元素
		System.out.println("队列移除开头一个元素：");
			head++;

			b=head;
			while(b!=tail){//实际上就是head!=tail
				System.out.print(queue[b]+" ");
				b++;
			}

			//队列末尾添加一个元素
			//先把数组容量翻倍
			if(tail==queue.length-1){
				int[] c = new int[queue.length*2];
				for(int d = 0;d < queue.length;d++){
					c[d] = queue[d];
				}
			queue=c;
			}
			
			//末尾添加一个元素
		System.out.println("队列末尾添加一个元素：");
			queue[tail]=in.nextInt();
			tail++;

			b=head;
			while(b!=tail){//实际上就是head!=tail
				System.out.print(queue[b]+" ");
				b++;
			}
		}
	}
}

