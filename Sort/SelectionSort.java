class SelectionSort{
	static int[] trim(int[] a,int d){
		int[] b=new int[a.length-1];
		for(int c=0;c <a.length;c++){
			if(c>d){
				b[c-1]=a[c];
			}
			if(c<d){
				b[c]=a[c];
			}
			if(c==d);
			
		}
		return b;
	}
	//难点是上面
	//把一个数组去掉一个元素，剩下的赋到另一个数组去
	static int selectingForMax(int[] a){
		int Max = a[0];
		int MaxIndex=0;
		for(int b=1;b<a.length;b++){
			if(a[b]>Max){//比较数的大小
				Max=a[b];//这个和另一个选择排序，自己都忘了这一句 把最大值改变了
				MaxIndex=b;//但是返回索引
			}
		}
		return MaxIndex;
	}
	static int[] quickSort(int[] a){
		int[] c=a;
		int[] d = new int[a.length];
		int e;
		int f = 0;
		for(int b=0;b<a.length;b++){
			e=selectingForMax(c);
			System.out.print(c[e]+" ");
			d[f]=c[e];
			f++;
			c=trim(c,e);
		}
		return d;
	}
	public static void main(String... args){
		int[] a = {3,99,5,7,44,100,66,2,1,99,67,1,44,2,6,5,89,100,78};
		int[] b =quickSort(a);//for(int c:b)System.out.print(c+" ");
	}
}
		
				
			
			