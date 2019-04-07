//使用数组实现堆（优先队列）

//也就是堆满足队列的特性 对头出 队尾进

//写堆向下调整的方法
//写堆向上调整的方法
//写使用数组建立堆的方法 O(NlogN) 向上调整
//写使用数组建立堆的方法 O(N) 线性 向下调整 O(N) = O(N/2 * 2 )
//写删除堆顶的方法（删除堆顶，之后把堆的最后一个节点的值赋给堆顶，之后堆的节点总数减一，堆向下调整）

//两种堆排序O(NlogN)
//一种是打印或把堆顶放到一个新的数组中，之后删除堆顶，删除堆顶使用上面的删除堆顶的方法 从小到大堆排序 建立的是最小堆 堆顶是堆中最小的元素
//另一种是从小到大堆排序 建立的是最大堆 堆顶是堆中最大的元素 
//之后将堆顶的值与堆最后一个节点的值交换，堆的节点总数减一，堆向下调整
//堆的节点总数n减一，用来实现堆的数组有初始化的长度是不变的

class MyHeap{

	static int top;//堆顶 top = 1 下面没用到
	static int n;//堆的节点总数

	static int[] array  = {3,5,2,1,7,8,3,4,9,10};
	static int[] heap;//堆

	public static void shiftdown(int i){//向下调整
		int MINindex = i;//用来记录序号
		int tmp;
		boolean flag = true;
		while((i*2 <= n)&&flag){//i*2 左儿子的序号 ||i*2+1 <= n
			if(i*2 <= n){
				if(heap[i] > heap[i*2]){
					MINindex = i*2;
				}else{
					MINindex = i;
				}
			}
			if(i*2+1 <= n){
				if(heap[MINindex] > heap[i*2+1]){//前面一直没发现的地方 前面使用的是heap[i]
								//使用heap[i] > heap[i*2+1]，
								//并不能从父节点和两个子节点中获得最小值的序号

								//如果能获得最小值的序号
								//通过最小值序号将父节点的数与最小值交换
								//使得新的父节点的数比两个子节点都小

								//例子

								//   5
								// 2   3
								
								//因为第一次比较后，i并不是记录较小节点的序号
								//MINindex才是记录较小节点的序号
					MINindex = i*2+1;
				}
/*
				else{
					MINindex = i;//多这个也错
						//这个使得第一次比较不起作用了
				}
*/
			}
			if(MINindex != i){
				tmp = heap[i];
				heap[i] = heap[MINindex];
				heap[MINindex] = tmp;
				
				i = MINindex;//继续向下调整 重点
			}else{
				flag = false;// 重点
			}//上面是把一个结点的值 变成 这个结点和左结点或右结点中最小值(交换)

			//怎么发现shiftdown里的错误
			//使用creatHeap2里使用的是shiftdown，第一种堆排序使用的也是shiftdown
			//而且使用creatHeap2的产生的堆的堆顶不是最小的数
		}
	}
/*
	public staic void shiftdown2(int i){//向下调整
		int MINindex;//用来记录序号
		int tmp;
		boolean flag = true;
		while((i*2 <= n||i*2+1 <= n)&&flag){//i*2 左儿子的序号
		//i*2 <= n 已经把节点i有左儿子或右儿子的情况都包含进去了
			if(i*2 <= n){
				if(heap[i] > heap[i*2]){

					tmp = heap[i*2];
					heap[i*2] = heap[i];
					heap[i] = tmp;

				}
			}
			if(i*2+1 <= n){
				if(heap[i] > heap[i*2+1]){

					tmp = heap[i*2+1];
					heap[i*2+1] = heap[i];
					heap[i] = tmp;

				}
			}//上面这个调整完
			//不知道是那几个结点交换了
			//有交换才要继续向下调整
		}
	}
*/

	public static void shiftup(int i){//向上调整
		int MINindex;//用来记录序号
		int tmp;
		boolean flag = true;
		while(i/2 >= 1&&flag){
			if(heap[i/2] > heap[i]){
				tmp = heap[i];
				heap[i] = heap[i/2];
				heap[i/2] = tmp;
				
				i = i/2;
			}else{
				flag = false;
			}
		}
	}

	public static void createHeap(){
		heap = new int[array.length+1];
		n = array.length;
		System.out.println("结点总数"+n);
		for(int i = 0;i < array.length;i++){
			heap[i+1] = array[i];
			shiftup(i+1);
		}
	}

	public static void createHeap2(){
		heap = new int[array.length+1];
		n = array.length;
		System.out.println("结点总数"+n);
		for(int i = 0;i < array.length;i++){
			heap[i+1] = array[i];
		}
		for(int i = n/2;i >= 1;i--){//O(N/2)
		//上面i==1时能向下调整
			shiftdown(i);
		}
	}

	public static int deleteTop(){//删除堆顶
		return deleteMin();
	}
	public static int deleteMin(){//删除堆顶
	//这也是第一种堆排序的核心内容
		int tmp;
		tmp = heap[1];
		heap[1] = heap[n];
		n--;
		shiftdown(1);
		return tmp;
	}
	public static int heapSort(){
		return deleteMin();
	}

	public static int[] heapSort2(){
		//上面都是按最小堆来的，就是父节点的值小于两个子节点
		//下面建立最大堆	
		int[] maxHeap = new int[array.length+1];
		n = array.length;
		for(int i = 0;i < array.length;i++){
			maxHeap[i+1] = array[i];
		}
		for(int i = n/2;i >= 1;i--){//O(N/2)
		//上面i==1时能向下调整
			shiftdownformaxHeap(maxHeap,i,n);
		}

//另一种是从小到大堆排序 建立的是最大堆 堆顶是堆中最大的元素 
//之后将堆顶的值与堆最后一个节点的值交换，堆的节点总数减一，堆向下调整
//堆的节点总数n减一，用来实现堆的数组有初始化的长度是不变的
		int m = n;
		for(int i =1; i <= m;i++){//前面一直没发现的地方 原来是i <= n
					//下面有一处有把n的值赋给m，用m来定循环边界

					//如果用i <= n，循环的结果是 n-- i++
					//n i
					//10 1
					//9 2
					//8 3
					//7 4
					//6 5
					//5 6就不能进入循环了
					//i只取到了1到5


			int tmp = maxHeap[1];
			maxHeap[1] = maxHeap[n];
			maxHeap[n] = tmp;
			//System.out.println(tmp+ " ");

			n--;
			shiftdownformaxHeap(maxHeap,1,n);
		}
		return maxHeap;
	}
		
	public static void shiftdownformaxHeap(int[] maxHeap, int i,int n){//向下调整
		int Maxindex = i;//用来记录序号
		int tmp;
		boolean flag = true;
		while(i*2 <= n&&flag){//i*2 左儿子的序号
		//i*2 <= n 已经把节点i有左儿子或右儿子的情况都包含进去了
			if(i*2 <= n){
				if(maxHeap[i] < maxHeap[i*2]){
					Maxindex = i*2;
				}else{
					Maxindex = i;
				}
			}
			if(i*2+1 <= n){
				if(maxHeap[Maxindex] < maxHeap[i*2+1]){
					Maxindex = i*2+1;
				}
			}
			if(Maxindex != i){
				tmp = maxHeap[i];
				maxHeap[i] = maxHeap[Maxindex];
				maxHeap[Maxindex] = tmp;
				
				i = Maxindex;//继续向下调整 重点
			}else{
				flag = false;// 重点
			}//上面是把一个结点的值 变成 这个结点和左结点或右结点中最小值(交换)
		}
	}

	public static void main(String... args){
		createHeap();
		int m = n;
		//下面不能用n，因为deleteMin()中n会自减
		for(int i = 1;i <= m;i++){
			System.out.print(heap[i]+ " ");
		}
		System.out.println("下面是堆排序");
		for(int i = 1;i <= m;i++){
			System.out.print(deleteMin()+ " ");
		}

		System.out.println();
		createHeap2();
		for(int i = 1;i <= m;i++){
			System.out.print(heap[i]+ " ");
		}
		System.out.println("下面是堆排序");
		for(int i = 1;i <= m;i++){
			System.out.print(deleteMin()+ " ");
		}
		System.out.println();
		System.out.println("下面是堆排序");
		int[] maxHeap = heapSort2();
		for(int i = 1;i < maxHeap.length;i++){
			System.out.print(maxHeap[i]+ " ");
		}
		kmax(5);//第5大的数
		//第5大的数 第5大的数是从小到大排，右边数起第5个数
		kmax(8);//第8大的数 第8大的数是从小到大排，右边数起第5个数
	}
//下面写获取第k大的数
//建立有k个节点的最小堆
	public static void kmax(int k){//第k大的数
		int tmp;
		createHeapknode(k);
		for(int i = k;i < array.length;i++){
			if(array[i]>heap[1]){
				heap[1]=array[i];
				shiftdown(1);
			}
		}
		System.out.println("下面是第k大的数");
		System.out.println(heap[1]);
		System.out.println("下面是前k大的数");
		for(int i = 1;i<=k;i++){
			System.out.print(heap[i]+ " ");
		}
	}
		
	public static void createHeapknode(int k){
		heap = new int[k+1];
		n = k;
		System.out.println("结点总数"+n);
		for(int i = 0;i < k;i++){
			heap[i+1] = array[i];
		}
		for(int i = n/2;i >= 1;i--){//O(N/2)
		//上面i==1时能向下调整
			shiftdown(i);
		}
	}
}
		
		

	
	

			
			
	

				
			

	