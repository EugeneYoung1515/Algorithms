//有这个（QuickSort4）是因为
//把QuickSort3里好几处因该是while的地方写成了if
//QuickSort3中是把low-1位置的数与基准数交换 这在某些情况下是不对的
//应该是 把high位置的数与基准数交换才对

//还有QuickSort3里写的有点乱 这里捋一捋

//QuickSort3里注释可以看

//这个还不一定对
//因为在QuickSort3把条件改一下之后 排序结果会很相似

//自己在v2ex上回答别人快排问题的答案可以参考

import java.lang.Comparable;
class QuickSort4{
	public static int partition(Comparable[] a,int low,int high){//切分（分割）方法
						//low和high是序号
			//要返回排定后的基准数位置 不然不能对左右两边进行递归

		Comparable tar = a[low];
		int tarIndex = low;
		int tmp = high;
		while(true){//不知道什么时候终止 先写这个 循环里面再写break
			while(!SortClass.less(tar,a[low])){	//a[low] <=tar
				if(low == tmp){
					break;//不能超出边界
					//这里break 下面else那里也会break
				}
				low++;				
			}
			while(!SortClass.less(a[high],tar)){
				if(tarIndex == high){
					break;//不能超出边界
					//这里break 下面else那里也会break
				}
				high--;
			}
			if(low < high){
				//交换保持左边比基准数小 右边比准数数大 现在还不是交换基准数
				SortClass.swap(a,low,high);
			}else{//low >= high
				break;//跳出循环 是因为现在就就剩下基准数没有排到正确位置了
			}
		}
		//下面是排定基准数 下面的语句要对上面的情况都成立
			SortClass.swap(a,tarIndex,high);
			return high;
	}

	public static void sort(Comparable[] a,int low,int high){
		if(low >=high){
			return;
		}
		int partition = partition(a,low,high);
		sort(a,low,partition - 1);
		sort(a,partition +1,high);
	}

	//随机打乱数组
	//因为一直选子数组第一个为基准数 如果基准数一直是子数组的最值 复杂度会变成O(N2)
	public static void shuffle(Comparable[] a){
		int N = a.length;
		for(int i = 0;i < N;i++){
			int r = i + (int)(Math.random()*(N-i));
			SortClass.swap(a,i,r);
		}
	}

	public static void main(String... args){
		Integer[] a = {8,1,99,6,8,8,3,6,13,5,30,23,5,3,0,-1,4,2,7,10,9,6,8,4,18,15,20,45};
		shuffle(a);
		sort(a,0,a.length-1);
		for(Comparable i:a){
			System.out.print(i+ " ");
		}
		System.out.println();
/*
		Integer[] b = {8,1,99,8,8,3,6,13,5,23,30,5,6,3,0,-1,4,2,7,10,9,6,8,4,18,15,20,45};
		Integer[] c =new Integer[b.length];
		for(int i = 0;i < 100;i++){
			for(int j = 0;j < c.length;j++){
				c[j] = b[j];
			}
			shuffle(c);
			sort(c,0,c.length-1);
			for(Comparable d:c){
				System.out.print(d+ " ");
			}
			System.out.println();
		}
*/
	}

	public static int partition2(Comparable[] a,int low,int high){//切分（分割）方法
						//low和high是序号
			//要返回排定后的基准数位置 不然不能对左右两边进行递归

		Comparable tar = a[low];
		int tarIndex = low;
		int tmp = high;
		while(true){//不知道什么时候终止 先写这个 循环里面再写break
			while(low<=tmp&&!SortClass.less(tar,a[low])){	//a[low] <=tar
				//如果low<=tmp放在后面 会报数组越界 low<=tmp放前面 结果是false 后面的条件就短路了 不会执行
				low++;
			}
			while(tarIndex<=high&&!SortClass.less(a[high],tar)){//和上面一样 low<=high 不能超出边界 可以在循环中用break替换
				high--;
			}
			if((low-high) < 1){	//(low-high)>=1也就是其余数要么都比基准小（含等于）要么都比基准数大 没有部分大或部分小的情况
				//交换保持左边比基准数小 右边比准数数大 现在还不是交换基准数
				SortClass.swap(a,low,high);
			}else{//else和最近的if是一对 这里的条件就是(low-high)==1
				break;//跳出循环 是因为现在就什么基准数没有排到正确位置了
			}
		}
		//下面是排基准数 下面的语句要对上面的情况都成立
			high = high < tarIndex ? high+1 : high;
			SortClass.swap(a,tarIndex,high);
			return high;
	}

	public static int partition3(Comparable[] a,int low,int high){//切分（分割）方法
						//low和high是序号
			//要返回排定后的基准数位置 不然不能对左右两边进行递归

		Comparable tar = a[low];
		int tarIndex = low;
		int tmp = high;
		while(true){//不知道什么时候终止 先写这个 循环里面再写break
			while(!SortClass.less(tar,a[low])){	//a[low] <=tar
				low++;				
				if(low == tmp+1){
					break;//不能超出边界
					//这里break 下面else那里也会break
				}
			}
			while(!SortClass.less(a[high],tar)){
				high--;
				if(tarIndex == high+1){
					break;//不能超出边界
					//这里break 下面else那里也会break
				}
			}
			if(low < high){
				//交换保持左边比基准数小 右边比准数数大 现在还不是交换基准数
				SortClass.swap(a,low,high);
			}else{//low >= high
				break;//跳出循环 是因为现在就就剩下基准数没有排到正确位置了
			}
		}
		//下面是排定基准数 下面的语句要对上面的情况都成立
			high = high < tarIndex ? high+1 : high;
			SortClass.swap(a,tarIndex,high);
			return high;
	}

	public static int partition4(Comparable[] a,int low,int high){//切分（分割）方法
						//low和high是序号
			//要返回排定后的基准数位置 不然不能对左右两边进行递归

		Comparable tar = a[low];
		int tarIndex = low;
		int tmp = high;
		while(low < high){//从 1里抽出一个low < high 去掉了一个else
			while(!SortClass.less(tar,a[low])){	//a[low] <=tar
				if(low == tmp){
					break;//不能超出边界
					//这里break 下面else那里也会break
				}
				low++;				
			}
			while(!SortClass.less(a[high],tar)){
				if(tarIndex == high){
					break;//不能超出边界
					//这里break 下面else那里也会break
				}
				high--;
			}
			if(low < high){
				//交换保持左边比基准数小 右边比准数数大 现在还不是交换基准数
				SortClass.swap(a,low,high);
			}
		}
		//下面是排定基准数 下面的语句要对上面的情况都成立
			SortClass.swap(a,tarIndex,high);
			return high;
	}
	//就和啊哈算法上面的的很接近了
	//基准数都是选最左边的数
	// 啊哈算法上面是从右边开始 最终是 SortClass.swap(a,tarIndex,low);
	// 自己      是从左边开始 最终是 SortClass.swap(a,tarIndex,high);

	//1 2 3三处主要是 用不用break 以及break用在哪里的区别
	//4 从 1里抽出一个low < high 去掉了一个else
}


