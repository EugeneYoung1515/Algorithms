class SelectionSort2{
	static void selectionSort(int[] a){
		int Max;
		int tmp;
		int MaxIndex;
		for(int b=0;b<a.length;b++){
			Max=a[b];
			MaxIndex=b;
			for(int c=b+1;c<a.length;c++){
				if(a[c]>Max){//比数大小
					Max=a[c];//这个和另一个选择排序，自己都忘了这一句 把最大值改变了
					MaxIndex=c;//但是用序号
				}
			}
			tmp=a[MaxIndex];
			a[MaxIndex]=a[b];
			a[b]=tmp;

		}
	}
	public static void main(String[] args){
		int[] a = {3,99,5,7,44,100,66,2,1,99,67,1,44,2,6,5,89,100,78};
		selectionSort(a);
		for(int c:a)System.out.print(c+" ");
	}
}
					