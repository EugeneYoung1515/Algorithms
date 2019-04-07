import java.util.*;

class ResizingArrayQueue2<T> implements MyQueue3<T>{
	
	 T[] a = (T[]) new Object[0];
	private int tail;//1 队列末尾元素的序号
	private int head;//2 队列开头元素的序号

	public boolean isEmpty(){
		if(tail - head == 0 ){//3
			return true;
		}
		return false;
	}
	
	public int size(){
		return tail-head;
	}

	private void resize(int max){
		System.out.println();
		System.out.println(max);
		System.out.println();
		
		T[] tmp = (T[])new Object[max];//T[] tmp = new T[max]();这样不行
		for(int i = head;i <= tail-1;i++){//4//注意这里
			tmp[i-head] = a[i];//5
		}
		a = tmp;
		tail= tail-head;//6
		head = 0;//7
	}
	
	public void enqueue(T t){
		if(tail - head == a.length){
			resize(2*a.length+1);
		}
		a[tail] = t;
		tail++;//注意这里
	
		//两处注意这里
		//在对尾加一个元素后，tail会自加
		//再次在队尾加一个元素，可能需要调整数组长度，就是拷到另一个数组
		//但是这时不会漏tail所指的元素，因为此时tail所指处没有没有元素（初始化），原因是上面的tail自加
	}

	public T dequeue(){
	if(tail - head > 0 ){
		if(tail - head == a.length/4){
			resize(a.length/2);
		}
		T tmp = a[head];
		a[head] = null;
		head++;
		return tmp;
	}
	return null;
	}
	public Iterator<T> iterator(){
		return new ArrayIterator();
	}
	private class ArrayIterator implements Iterator<T>{
		private int itail = tail;
		private int jhead = head;
		public boolean hasNext(){
			if(itail - jhead > 0){
				return true;
			}
			return false;
		}
		public T next(){
			T tmp = a[jhead];
			jhead++;
			return tmp;
		}
		public void remove(){}
	}
	public static void main(String... args){
		ResizingArrayQueue<Integer> b = new ResizingArrayQueue<Integer>();
		for(int i=0;i < 4; i++){
			b.enqueue(i);
		}

		for(Integer i:b){
			System.out.println(i);
		}
		for(int i=0;i < 4; i++){
			System.out.println(b.dequeue());
		}
		System.out.println(b.dequeue());

	}
}
/*

开始时head等于tail等于0
head 队列开头元素的序号
tail 队列末尾元素的序号
在tail位置加一个元素后 tail自加

队列元素为空 是 head等于tail
head等于tail时 不会漏掉堆的最后一个元素
加第一个元素后，立刻减掉一个元素，之后head是等于tail等于 开始时加第一个元素的下一个位置
开始时head等于tail等于0 堆中没有元素 满足 队列元素为空 是 head等于tail
