//自底向上的归并排序
//用循环换递归
//数组递归，递归的基线条件是数组的长度为0或1
//数组只有一个元素时，数组就排序了

//用循环换递归
//也是数组只有一个元素时，数组就排序了
//两个数组各有一个元素 都排序了 归并后 排序了 成一个更大的数组

//复用自顶向下的归并排序的归并方法
import java.lang.Comparable;
class MergeSortBottomToTop{
	public static void sort(Comparable[] a){

/*
		for(int k = 2;k <= a.length;k=2*k){
		//k从2开始而不是从1开始
		//这里k是两个子数组归后的数组的长度
		//要是从1开始 k就是子数组的长度
		//不管k是从1开始还是从2开始
		//第一步都是把两个长度为1的子数组归并成为长度为2的子数组

		上边写的注释是对的
		
		算法第四版中 k就是子数组的长度


			for(int i = 0;i < a.length-k+1;i = i+k){//注意 是i < a.length-k+1 不是i < a.length
				//System.out.println(i);
				//System.out.println(i+" "+(i+k/2-1)+" "+(i+k-1));

				//MergeSortTopToBottom.merge(a,i,i+k/2-1,i+k-1);//i+k/2-1是i和i+k-1的均值
*/
			//上面注释掉是因为数组长度可能不是2的倍数
		for(int k = 2;k <= 2*a.length;k=2*k){//难的地方 两个条件 1. 2*a.length a.length是17时 k能取到32 因为17比16多一点 k能取到的最大值设为m 用a表示a.length 即 (a/(m/2)+1)*(m/2) = m 即 a+m/2 = m 即 m = 2*a 或者 m = 2*a-1在a等于3时不行 其他情况下成立 如果还用 m = 2*a a等于16时 还要m等于32 也就是k能取到32 多循环一次
			for(int i = 0;i < a.length-k/2;i = i+k){//2. a.length-k/2 就是要归并的子数组 至少留一个元素给后一个数组 也就是中间数（是归前一个数组的）i+k/2-1 < a.length -1 或者说 i+k/2 < a.length 计算出 i < a.length - k/2
				MergeSortTopToBottom.merge(a, i, i+k/2-1, Math.min(i+k-1,a.length-1));//i+k/2-1是i和i+k-1的均值
			}
		}
	}
	//和希尔排序有点像 改变增量k的值

	public static void main(String... args){
		Integer[] a = {8,1,99,3,6,13,23,5,3,4,2,7,10,9,6,8,4,18,15,20,45};
		MergeSortTopToBottom.b = new Integer[a.length];//要这句因为归并过程中使用了辅助数组 不然汇报空指针异常
		sort(a);
		for(Comparable i:a){
			System.out.print(i+ " ");
		}
	}	
}

//这种自底向上的归并排序 也叫自然的归并排序