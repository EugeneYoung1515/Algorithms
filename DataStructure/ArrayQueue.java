class ArrayQueue {
	Object[] a;
	int head;
	int tail;
	ArrayQueue(int n){
		a = new Object[n];
	}

	public void enqueue(Object t){

		if(tail - head != a.length){
			a[tail] = t;
			tail++;
		}else{
			System.out.println("越界了");
		}
	}

	public Object dequeue(){
		if(tail - head > 0 ){
			Object tmp = a[head];
			a[head] = null;
			head++;
			return tmp;
		}
		return null;
	}

	public static void main(String... args){
		ArrayQueue a = new ArrayQueue(3);
Integer b = 1;
		a.enqueue(b);
b = 2;
		a.enqueue(b);
b = 3;
		a.enqueue(b);
b = 4;
		a.enqueue(b);
		
		for(int i = 0;i < 3;i++){
			System.out.println((Integer)a.dequeue());
		}
		System.out.println((Integer)a.dequeue());
	}
}
//使用从ResizingArrayQueue2.java整理出的思路 写的定长队列
//可能不能算是定长队列 定长
//只能说冲末尾加 从开头减

／*
原来给数组初始化时(int i;i < a.length;i++)a[i] = ...;
		(int i;i < a.length;i++)System.out.println(a[i]);
就是从末尾加
从开头减

先进先出
*／

／*
原来给数组初始化时(int i = 0;i < a.length;i++)a[i] = ...;
		(int i = a.length-1;i >= 0 ;i--)System.out.println(a[i]);
就是从末尾加
末尾减

后进先出

栈
*／





	