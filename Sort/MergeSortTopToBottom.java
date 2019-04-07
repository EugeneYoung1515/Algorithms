//自顶向下的归并排序
/*
使用递归
递归的极限条件 终止条件 (子)数组的长度等于1或等于0 也就是小于等于1

归并的过程就是排序的过程
归并过程是把两个有序的子数组变成一个有序的数组
只需要两个子数组长度长度之和的循环 就能把两个有序的子数组变成一个有序的数组
N的由来

使用归并排序要二分数组 也就是分治
logN的由来

*/
//先拆分 再归并


import java.lang.Comparable;

class MergeSortTopToBottom{
	static Comparable[] b;
	public static void sort(Comparable[] a,int low,int high){
		if(high-low <=0){
			return;//使用return 终止下面语句的执行
		}//似乎不会是0

		int mid = (low+high)/2;
		sort(a,low,mid);
		sort(a,mid+1,high);
		merge(a,low,mid,high);		
	}
	public static void merge(Comparable[] a,int low,int mid,int high){
//这个归并是难点
//这个归并写出来后发了很多时间重新检查有没有把所有情况(条件)都考虑到
//结果发现是上面递归的基线条件(递归的终止条件)写错了
//子数组只有一个元素 high-low ==0 high==low 只有low这个位置的元素或者说只有high这个位置的元素
//或没有元素high-low <=0

//一开始自己写成high-low <=1 通过下面##发现的
		//System.out.println(low+""+mid+""+high+"three");
		int i = low;
		int j = mid+1;

		int m = low;//为了从辅助数组放回原数组
//		while(true){//不知道什么时候终止循环，先写这个 循环里面break
//		    if(i<=high){
//把上面的循环和判断合并成一个

		while(i<=high){
			//System.out.println(low+""+j+"");
			//if(a[low]==null)System.out.println(low+"low");
			//if(a[j]==null)System.out.println(j+"j");
			if(low<=mid&&j<=high&&SortClass.less(a[low],a[j])){//原来是a[mid+1],下面不好自加
				//上面是同一个包的静态方法 用来判断两个数大小的
				b[i] = a[low];
				low++;
				i++;
				
			}else if(low<=mid&&j<=high&&!SortClass.less(a[low],a[j])){
				b[i] = a[j];
				j++;
				i++;
			//先考虑上面两种情况 左边子数组和右边子数组较小的数先排定（放到辅助数组中）一边排定一个数 较少一个数 继续看这边的下一个数 具体思考细节看算法第四版
			//前提是左右子数组都还有数
			//上面两种情况考虑完 在考虑剩下的情况
			//也就是一边子数组没数了

			}else if(low>mid){
				b[i] = a[j];
				j++;
				i++;
			}else if(j>high){
				b[i] = a[low];
				low++;
				i++;
			}
		}
		//再从辅助数组放回元素组
		for(i = i-1 ;i >= m;i--){
			a[i] = b[i];
			//System.out.print(a[i]+" ");//##
		}
		//System.out.println();//##
	}

/*
自己一般只写
1.if(){}
2.if(){}
  if(){}
3.if(){
    }else{}

最近开始使用else if
if 和else if 只能选一个执行
也就是说 else if的条件 其实是if的条件的对立面和else if条件的交集 或者说两个条件都要
*/

	public static void main(String... args){
		Integer[] a = {8,1,99,3,6,13,23,5,3,4,2,7,10,9,6,8};
		b = new Integer[a.length];
		sort(a,0,a.length-1);
		for(int i:a){
			System.out.print(i+ " ");
		}
	}
}
				