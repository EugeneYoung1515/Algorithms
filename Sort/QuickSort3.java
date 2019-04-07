//图解算法的快排的原理
//把子数组分成小于基准数 等于基准数 大于基准数三部分 排定基准数
//啊哈算法 和 算法第四版的快排没有使用额外数组 形式更简洁 但是比较难理解
//不尽兴而外的交换 不对时才交换

//下面是算法第四版上的快排

import java.lang.Comparable;

public class QuickSort3{
	public static int partition(Comparable[] a,int low,int high){//切分（分割）方法
						//low和high是序号
			//要返回排定后的基准数位置 不然不能对左右两边进行递归

		Comparable tar = a[low];
		int tarIndex = low;
		int tmp = high;
		while(true){//不知道什么时候终止 先写这个 循环里面再写break
			if(!SortClass.less(tar,a[low])&&low<=tmp){	//if(a[low] <=tar){
							//原来是low<=high 也行 这种形式 在某些排列形式的子数组中 为了排定基准数会少几次访问数组
							//经过下面测试切分的方法测试 原来是low<=high 也行
				low++;
			}
			if(!SortClass.less(a[high],tar)&&tarIndex<=high){//和上面一样 low<=high 不能超出边界 可以在循环中用break替换
							//原来是low<=high 也行
							//经过下面测试切分的方法测试 原来是low<=high 也行
				high--;
			}
			if((low-high) < 1){	//(low-high)==1也就是其余数要么都比基准小（含等于）要么都比基准数大 没有部分大或部分小的情况
				//交换保持左边比基准数小 右边比准数数大 现在还不是交换基准数
				SortClass.swap(a,low,high);
			}else{//else和最近的if是一对 这里的条件就是(low-high)==1
				break;//跳出循环 是因为现在就什么基准数没有排到正确位置了
			}//用 4 1 2 3 5 6 7 8 9的例子想
			//或者用 4 1 2 3 4 4 5 6 7 8 9的例子想
		}
		//下面是拍定基准数 下面的语句要对上面的情况都成立
			low = low > tarIndex ? low-1:low;//这里low可能就是子数组的终止序号 也可能是比子数组的终止序号多1
			SortClass.swap(a,tarIndex,low);
			return low;
	}

	public static void sort(Comparable[] a,int low,int high){
		if(low >=high){
			return;
		}
		int partition = partition4(a,low,high);
		sort(a,low,partition - 1);
		sort(a,partition +1,high);
	}

	public static void main(String... args){
		Integer[] a = {8,1,99,8,8,3,6,13,5,23,5,3,0,-1,4,2,7,10,9,6,8,4,18,15,20,45};
		shuffle(a);
		sort(a,0,a.length-1);
		for(Comparable i:a){
			System.out.print(i+ " ");
		}
		System.out.println();
/*
		//测试切分的方法
		Integer[] b = {5,1,2,6,4,4,4,3,7,8,9,10};//{5,1,2,6,5,5,3,7,8,9,10}//{5,1,2,6,7,4,3,7,8,9,10}
		Integer[] c =new Integer[b.length];
		//System.out.println(b[partition(b,0,b.length-1)]);//测试切分方法

		for(int i = 0;i < 100;i++){
			for(int j = 0;j < c.length;j++){
				c[j] = b[j];
			}
			shuffle(c);//第一位一直是5 打乱时第一位不打乱 一直选第一位为切分元素 也就是选5为切分元素 看最终的输出是不是5
			System.out.println("--"+c[0]);
			for(Comparable d:c){
				System.out.print(d + " ");
			}
			System.out.println();
			System.out.println(c[partition2(c,0,c.length-1)]);
		}
*/
	}

	//随机打乱数组
	public static void shuffle(Comparable[] a){
		int N = a.length;
		for(int i = 0;i < N;i++){//使用上面的测试方法时要int i = 1
			int r = i + (int)(Math.random()*(N-i));
			SortClass.swap(a,i,r);
		}
	}

	// 下面是
	//不能超出边界 不使用low<=high 而在循环中用break替换

	public static int partition2(Comparable[] a,int low,int high){//切分（分割）方法
						//low和high是序号
			//要返回排定后的基准数位置 不然不能对左右两边进行递归

		Comparable tar = a[low];
		int tarIndex = low;
		int tmp = high;
		while(true){//不知道什么时候终止 先写这个 循环里面再写break
			if(!SortClass.less(tar,a[low])){	//if(a[low] <=tar){
				low++;
				if(low == tmp+1){
					break;
				}				
			}
			if(!SortClass.less(a[high],tar)){// 不能超出边界 不使用low<=high 而在循环中用break替换
				high--;
				if(tarIndex == high+1){
					break;
				}
			}
			if((low-high) < 1){
				//交换保持左边比基准数小 右边比准数数大 现在还不是交换基准数
				SortClass.swap(a,low,high);
			}
			else{//或者直接用else
				break;//跳出循环 是因为现在就就剩下基准数没有排到正确位置了
			}
		}
		//下面是排定基准数 限免的语句要对上面的情况都成
			low = low > tarIndex ? low-1:low;//这里low可能就是子数组的终止序号 也可能是比子数组的终止序号多1
			SortClass.swap(a,tarIndex,low);
			return low;
	}

	public static int partition3(Comparable[] a,int low,int high){//切分（分割）方法
						//low和high是序号
			//要返回排定后的基准数位置 不然不能对左右两边进行递归

		Comparable tar = a[low];
		int tarIndex = low;
		int tmp = high;
		while(true){//不知道什么时候终止 先写这个 循环里面再写break
			while(!SortClass.less(tar,a[low])){	//if(a[low] <=tar){
			//原来是if 换成 while就对了
				if(low == tmp){
					break;
				}
				low++;				
			}
			while(!SortClass.less(a[high],tar)){// 不能超出边界 不使用low<=high 而在循环中用break替换
			//原来是if 换成 while就对了
				if(tarIndex == high){
					break;
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
		//下面是排定基准数 限免的语句要对上面的情况都成
			SortClass.swap(a,tarIndex,high);
			return high;
	}
	public static int partition4(Comparable[] a,int low,int high){//切分（分割）方法
						//low和high是序号
			//要返回排定后的基准数位置 不然不能对左右两边进行递归

		Comparable tar = a[low];
		int tarIndex = low;
		int tmp = high;
		while(true){//不知道什么时候终止 先写这个 循环里面再写break
			while(low<=tmp&&!SortClass.less(tar,a[low])){	//if(a[low] <=tar){
				//如果low<=tmp放在后面 会报数组越界 low<=tmp放前面 结果是false 后面的条件就短路了 不会执行
							//原来是low<=high 也行 这种形式 在某些排列形式的子数组中 为了排定基准数会少几次访问数组
							//经过下面测试切分的方法测试 原来是low<=high 也行
				low++;
			}//if(low == tmp+1){break;}
			while(tarIndex<=high&&!SortClass.less(a[high],tar)){//和上面一样 low<=high 不能超出边界 可以在循环中用break替换
							//原来是low<=high 也行
							//经过下面测试切分的方法测试 原来是low<=high 也行
				high--;
			}//if(high == tarIndex-1){break;}
			if((low-high) < 1){	//(low-high)==1也就是其余数要么都比基准小（含等于）要么都比基准数大 没有部分大或部分小的情况
				//交换保持左边比基准数小 右边比准数数大 现在还不是交换基准数
				SortClass.swap(a,low,high);
			}else{//else和最近的if是一对 这里的条件就是(low-high)==1
				break;//跳出循环 是因为现在就什么基准数没有排到正确位置了
			}//用 4 1 2 3 5 6 7 8 9的例子想
			//或者用 4 1 2 3 4 4 5 6 7 8 9的例子想
		}
		//下面是拍定基准数 下面的语句要对上面的情况都成立
/*
			low = low > tarIndex ? low-1:low;//这里low可能就是子数组的终止序号 也可能是比子数组的终止序号多1
			SortClass.swap(a,tarIndex,low);
			return low;
*/
			high = high < tarIndex ? high+1 : high;
			SortClass.swap(a,tarIndex,high);
			return high;

	}

}
	

	
		
			