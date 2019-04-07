//链表的另一种实现方式
//使用两个数组
//数组模拟

import java.util.*;
import java.io.*;

class MyLinkedListAno{
	static int[] a;//第一个数组存链表各个元素的值
	static int[] b;//第二个数组纯某个元素的下一个元素的序号
		//a[i]表示序号为i的链表元素
		//b[i]表示元素a[i]的下一个元素的序号
		//也就是 a[b[i]]表示a[i]的下一个元素
	public static void main(String[] args) throws Exception{

		try(Scanner in = new Scanner(System.in)){
			int c = in.nextInt();
			a = new int[c];//c个元素的链表
			b = new int[c];

			for(int i=0;i<c;i++){
				a[i]=in.nextInt();
			}

			for(int i=0;i<c;i++){
				if(i!=c-1){
					b[i]=i+1;
				}
				if(i==c-1){
					b[i]=-1;//b[c-1]==-1表示链表的没有下一个元素
						//同样可以用两个数组实现循环链表
						//这里b[c-1]=0就表示“最后一个元素”指向“第一个元素”
				}
			}

			int[] e = new int[2*c];
			for(int i =0;i<c;i++){//数组容量翻倍
				e[i]=a[i];
			}
			a=e;//这样就还能使用原来的a[i];

			int[] f = new int[2*c];
			for(int i =0;i<c;i++){//数组容量翻倍
				f[i]=b[i];
			}
			b=f;//这样就还能使用原来的b[i];

/*
			int d = in.nextInt();
			a[c]=d;//这里c也是新的数组a中有初始化的部分的长度
			
			for(int i=0;i<=c;i++){//上面a[c]有赋值了 这里要i<=c
				if(a[i]<d&&a[b[i]]>d){
					b[c]=b[i];
					b[i]=c;//在链表中插入一个元素，使上一个元素指向这一个元素，这一个元素指向下一个元素
					//上面改变指向是核心
					
				}
			}
			int t=0;//用来表示初始序号
			while(t!=-1){//前面的b[i]=0用来确定 没有下一个元素
				//输出时需要用到b[i]
				System.out.print(a[t]+ " ");
				t=b[t];
			}
*/
		insert(in.nextInt(),in);
		}
	}
	static void insert(int n,Scanner in){//在原链表第n（序号，从0开始）个元素前（也就是n-1和n之间插入一个新的元素，形成新的第n个元素
		int c=in.nextInt();//要插入的元素的值
		
		int i=0;
		for(;i<a.length;i++){
			if(a[i]==0){//这一步其实是不太对 为了获得新的数组中有初始化的部分的长度
				break;
			}
		}
		a[i]=c;
		
		int tt=0;
		if(n==0){
			tt=i;
			b[i]=0;//新开头
		}

		if(n==i){
			a[i]=c;

			int t=0;
			int m=0;
			while(t!=-1){
				m=t;//这一步是为了记录末尾的数的序号				
				t=b[t];
			}
			b[m]=i;
			b[i]=-1;//新末尾
		}

		if(n>0&&n<i){//下面不能用到数组a
			int t=0;
			int m=0;//访问的是第几个元素
			while(t!=-1){
				if(m==n-1){
					b[i]=b[t];
					b[t]=i;
					break;
				}
				t=b[t];
				m++;
			}
		}

		int t=tt;//上面的加了新开头 这里要吧t的值变成新开头的序号
		while(t!=-1){
		System.out.print(a[t]+ " ");
		t=b[t];
			}

	}			
}
//用两个数组实现链表
//实际上还需要一个变量记录 起始元素的序号 因为起始元素的序号是会变的 比如插入一个新的开头元素
//上面t开始时就是记录起始元素的序号，通过一个中间变量，把新的起始元素的序号传给t

//使用数组实现链表 第一个数组记录元素的值 第二个 数组记录下一个元素的序号 这个序号是在数组上的序号
//链表元素的序号（链表的第几个元素）和数组的序号没有直接关系
//用一个变量记录链表第一个元素（开头）的序号 访问链表第一个元素 之后通过第二个数组获得获得链表第二个元素在数组上的序号

//可以
//使用两个数组实现链表
//使用三个数组实现双向链表
//使用两个数组实现循环链表
//使用两个数组实现循环数组
//使用循环数组实现队列
 
					