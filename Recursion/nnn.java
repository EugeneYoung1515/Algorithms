//列出全排列的情况

import java.util.*;
import java.io.*;

class nnn{
	public static void main(String... args){
		try(Scanner in = new Scanner(System.in)){
			int n = in.nextInt();
			int[] a = core(n);
			for(int b:a){
				System.out.print(b+" ");
			}
		}
	}
	static int[] core(int n){
		if(n==2){
			int[] i = new int[jiecheng(n)];
			int m = 0;
			for(int a = 1;a<3;a++)
				for(int b = 1;b<3;b++)
					if(a!=b){
						i[m] = 10*a+b;
						m++;
					}
			return i;
		}//把全排列数变成十进制数
		//排列形式存到数组里时 用十进制存
		if(n>2){
			int[] i = core(n-1);//获得n-1个数全排列 是吧十进制形式存到数组中
			int[] j =new int[jiecheng(n)];//用来放n个数全排列 十进制形式
			int[] d = new int[n-1];//用来暂存一个排列形式 这个数组的各位用来暂存排列形式的各个位置的数
			int m = 1;
			int c = 0;
				for(int a =0 ;a < i.length;a++){//i中的每个数
					//System.out.println(i[a]+"m");//连同下面的println 是用来调试 断点调试
					while(i[a]/10!=0){
						//System.out.println(i[a]+"m");
						//System.out.println(d.length-m);
						//System.out.println(i[a]%10+"m");
						d[d.length-m] = i[a]%10;
						//System.out.println(d[d.length-m]);
						i[a] = i[a]/10;
						m++;
						//System.out.println(m+"m");
					}
					d[d.length-m]=i[a];//上面连同这句 是把十进制数变成全排列数 排列形式
								//下面在排列形式中插入新的数
					//System.out.println(d[d.length-m]);
					for(int b = 0;b <= d.length;b++){
						int sum=0;
						sum += n*chengyi(d.length-b);
						for(int e = 0;e < d.length;e++){
							int tmp = e<b?d[e]*chengyi(d.length-e):d[e]*chengyi(d.length-e-1);//三元操作符
							sum += tmp;
						}
						j[c]=sum;//把全排列 排列形式变成十进制
							//把插入新数后的排列形式 变成十进制数
						//System.out.println(j[c]+"话");
						c++;
					}
					m=1;//重点是这个 m=0也错了
					//m重新变成1 重新从数组d的最后一位元素开始赋值 往前赋
					//System.out.println(m+"m");
				}
			return j;//用来放n个数全排列 十进制形式
		}
		return new int[0];
	}
			
	static int jiecheng(int n){//阶乘
			if(n == 1)return 1;
			if(n > 1){
				int a = n*jiecheng(n-1);
				return a;
			}
			return -1;
			
	}
	static int chengyi(int n){//10的n次方
		if(n ==1)return 10;
		if(n > 1){
			int a = 10*chengyi(n-1);
			return a;
		}
		return 1;
	}
}
//循环用递归换
//循环套循环 循环层数不定 看能不能用递归换

//上面也可以不把排列形式 变成 十进制数存到 数组里
//直接把排列形式存到数组里
//有n!个排列形式 每个排列形式占n位元素 需要长度为n*n!的数组 数组每n个元素存一个排列形式
0到n-1是一个排列形式 除以n求余 是0到n-1 n到2n-1是一个排列形式 除以n求余 是0到n-1 用求余的方式取出排列形式

