class BinarySearchRecursion{
//二分搜索 递归版
//基线条件 中间位置等于目标值
//基线条件 终止递归的条件
//可以是 返回一个不是由自身方法确定的数
//在递归条件里不再调用自身方法

static int[] a = {10,7,6,5,4,3,2,1};
	public static void main(String... args){
		int b = 5;//使用二分法找到元素3对应的序号
		int c =binarySearchRecursion(a,b);
		System.out.println(c);
	}
	static int binarySearchRecursion(int[] a,int b){
		if((a[0]>b&&a[0]<=a[a.length-1])||(a[a.length-1]<b&&a[0]<=a[a.length-1])||(a[a.length-1]>b&&a[0]>=a[a.length-1])||(a[0]<b&&a[0]>=a[a.length-1]))
		return -1;
		if(a[0]==b)return 0;
		if(a[a.length-1]==b)return a.length-1;
		
		int c = 0;
		int d = a.length-1;
		return binarySearchRecursionCore(c,d,b);
	}
	static int binarySearchRecursionCore(int c, int d, int b){
		int mid = (c+d)/2;
		if((a[c]<=a[d]&&a[mid]<b)||(a[c]>=a[d]&&a[mid]>b)){
			return binarySearchRecursionCore(mid,d,b);
		}
		if((a[c]<=a[d]&&a[mid]>b)||(a[c]>=a[d]&&a[mid]<b)){
			return binarySearchRecursionCore(c,mid,b);
		}
		if(a[mid]==b){
			return mid;
		}
		return -1;	
	}
}
	