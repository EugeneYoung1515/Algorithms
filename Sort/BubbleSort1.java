class BubbleSort{

	static int[] swap(int a,int b){
		int tmp=a;
		a=b;
		b=tmp;
		int[] array = {a,b};
		return array;
	}

	static int[] bubbleSort(int[] a){
	//数组是对象
		int[] array;
		for(int j = 1;j < a.length;j++){
		//上面j从0开始或者从1开始都行
			for(int i = 0;i < a.length-1;i++){
		//上面j从0或者1开始 这里都可以a.length-1 就是比过了 再比
		//上面j从0开始 这里就是a.length-1-j
		//上面j从1开始 这里就是a.length-1-(j-1) 也就是a.length-j
		//原因是第一趟拿到最小的数 第二躺拿到第二小的数 已经不用和最小的数比较 在第一趟时已经比较过来
				if(a[i] < a[i+1]){
					array = swap(a[i],a[i+1]);
					a[i+1] = array[1];
					a[i] = array[0];//小的排在后面
				}
			}//这个循环把一个数排到最后面
		}//外层又从第一个开始看,此时第一个数可能不一样了
		return a;
	}

	static int[] bubbleSortReverse(int[] a){
	//数组是对象
		int[] array;
		for(int j = 0;j < a.length;j++){
			for(int i = 0;i < a.length-1;i++){
				if(a[i] > a[i+1]){
					array = swap(a[i],a[i+1]);
					a[i+1] = array[1];
					a[i] = array[0];//小的排在后面
				}
			}//这个循环把一个数排到最后面
		}//外层又从第一个开始看,此时第一个数可能不一样了
		return a;
	}
	
	public static void main(String[] args){
		int[] array = {1,1,4,2,6,6,8,9,10,3,7,10};
		int[] array2 = bubbleSort(array);
		for(int a: array2){
			System.out.print(a+" ");
		}

		System.out.println();

		for(int a:array){
			System.out.print(a+" ");//数组是对象
			//但是数组元素不一定是对象
		}

		System.out.println();

		array2 = bubbleSortReverse(array);
		for(int a:array){
			System.out.print(a+" ");//数组是对象
			//但是数组元素不一定是对象

		}
	}
}
class ObjectForBubbleSort{
//用冒泡排序 来排序对象 实质上排序对象中能排序的部分
	String name;
	int id;
	ObjectForBubbleSort(String name,int id){
		this.name = name;
		this.id = id;
	}
	//也可以上封装 set get
	public static void main(String... args){
		ObjectForBubbleSort[] objectsForBubbleSort = {new ObjectForBubbleSort("a",1),
		new ObjectForBubbleSort("a",1),
		new ObjectForBubbleSort("b",2),
		new ObjectForBubbleSort("c",3),
		new ObjectForBubbleSort("d",4)};
		ObjectForBubbleSort[] a = objectsForBubbleSort;//名字太长了，用短的
		ObjectForBubbleSort tmp;
		for(int i =0;i < 5;i++){
			for(int j=0;j<4;j++){
				if(a[j].id<a[j+1].id){
				//用id比，但是交换的是整个对象
					tmp = a[j];
					a[j] = a[j+1];
					a[j+1] = tmp;//改变了引用变量的指向
				}
			}
		}
		for(ObjectForBubbleSort b:a){
			System.out.print(b.name+" ");
		//想 Comparable 和 Comparator的例子
		//可以自己实现Comparable 和 Comparator 指源码 实现和Comparable 或 Comparator一样的功能
		}					
	}
}
//算法和数据结构的学习
//用java 的流做
//或者用Go做


