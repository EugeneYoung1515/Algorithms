//三向切分的快速排序

import java.lang.Comparable;
class ThreeWayQuickSort{
    public static void sort(Comparable[] a,int low,int high){
	//没有切分方法，直接是排序方法
	if(low >= high){
		return;
	}
	int lowlow = low;
	int highhigh = high;
	int lowPO = low;
	//三项切分 上面三个变量
	//lowlow表示等于基准数部分的第一个位置
	//highhigh表示大于基准数的第一个位置的前一个位置

	Comparable tar = a[low];

	while(lowPO <= highhigh){//使用lowPO遍历
		//int com = a[lowPO].compareTo(a[low]);这样错了 low位置的数后面会变 不会是基准数
		int com = a[lowPO].compareTo(tar);
		if(com == 0){
			lowPO++;
		}//遇到等于基准数的数 没有交换操作 序号自加

		if(com < 0){
			SortClass.swap(a,lowPO,lowlow);	
			lowPO++;
			lowlow++;
		}//上面的结果是把基准数（或等于基准数的数）不断往中间移动
		//把当前序号的数(lowPO)与上一个数(或者更前面的数 用lowlow表示 等于基准数部分的第一个位置)交换
		//交换结果使得等于基准数的数在后(右) 比基准数小的数在前(左)

		if(com > 0){
			SortClass.swap(a,lowPO,highhigh);
			highhigh--;
			//这里没有lowPO++
			//这里换过来的数 与基准数的大小关系不知道
		}
	}
	//要把上面部分变成一个切分方法也行 就是要输出一个数组 就是两个数 lowlow和highhigh

	//把子数组变成小于基准数 等于基准数 大于基准数三部分

	//System.out.println(a[lowlow-1]+" "+a[lowlow]+" "+a[highhigh]+" "+a[highhigh+1]);
	sort(a,low,lowlow-1);
	sort(a,highhigh+1,high);
    }

    public static void main(String... args){
	Integer[] a = {8,1,99,6,8,8,3,6,13,5,30,23,5,3,0,-1,4,2,7,10,9,6,8,4,18,15,20,45};
	sort(a,0,a.length-1);
	for(Integer i:a){
		System.out.print(i+ " ");
	}
    }

    public static void sort2(Comparable[] a,int low,int high){
	//没有切分方法，直接是排序方法
	if(low >= high){
		return;
	}
	int lowlow = low;
	int highhigh = high;
	int lowPO = low+1;
	//三项切分 上面三个变量
	//lowlow表示等于基准数部分的第一个位置
	//highhigh表示大于基准数的第一个位置的前一个位置

	Comparable tar = a[low];

	while(lowPO <= highhigh){//使用lowPO遍历
		//int com = a[lowPO].compareTo(a[low]);这样错了 low位置的数后面会变 不会是基准数
		int com = a[lowPO].compareTo(tar);
		if(com < 0){
			SortClass.swap(a,lowPO,lowlow);	
			lowPO++;
			lowlow++;
		}//上面的结果是把基准数（或等于基准数的数）不断往中间移动
		//把当前序号的数(lowPO)与上一个数(或者更前面的数 用lowlow表示 等于基准数部分的第一个位置)交换
		//交换结果使得等于基准数的数在后(右) 比基准数小的数在前(左)

		if(com > 0){
			SortClass.swap(a,lowPO,highhigh);
			highhigh--;
			//这里没有lowPO++
			//这里换过来的数 与基准数的大小关系不知道
		}if(com == 0){//这里换成else会错 else和最近的if是一对
				//这里换成else 上面换成else if可以
			lowPO++;
		}//遇到等于基准数的数 没有交换操作 序号自加
	}
	//要把上面部分变成一个切分方法也行 就是要输出一个数组 就是两个数 lowlow和highhigh

	//把子数组变成小于基准数 等于基准数 大于基准数三部分

	//System.out.println(a[lowlow-1]+" "+a[lowlow]+" "+a[highhigh]+" "+a[highhigh+1]);
	sort2(a,low,lowlow-1);
	sort2(a,highhigh+1,high);
    }
}
	