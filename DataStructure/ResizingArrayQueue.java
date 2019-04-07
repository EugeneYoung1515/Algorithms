import java.util.*;

class ResizingArrayQueue<T> implements MyQueue3<T>{
	
	 T[] a = (T[]) new Object[0];
	private int tail = -1;//1
	private int head = 1;//2

	public boolean isEmpty(){
		if(tail - head +2 == 0 ){//3
			return true;
		}
		return false;
	}
	
	public int size(){
		return tail-head+2;
	}

	private void resize(int max){
		System.out.println();
		System.out.println(max);
		System.out.println();
		
		T[] tmp = (T[])new Object[max];//T[] tmp = new T[max]();这样不行
		for(int i = head-1;i <= tail;i++){//4
			tmp[i-head+1] = a[i];//5
		}
		a = tmp;
		tail = tail-head+1;//6
		head = 1;//7
	}
	
	public void enqueue(T t){
		if(tail - head+2== a.length){
			resize(2*a.length+1);
		}
		a[tail+1] = t;
		tail++;
	}

	public T dequeue(){
	if(tail - head+2 > 0 ){
		if(tail - head+2 == a.length/4){
			resize(a.length/2);
		}
		T tmp = a[head-1];
		a[head-1] = null;
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
			if(itail - jhead+2 > 0){
				return true;
			}
			return false;
		}
		public T next(){
			T tmp = a[jhead-1];
			jhead++;
			return tmp;
		}
		public void remove(){}
	}
	public static void main(String... args){
		ResizingArrayQueue<Integer> b = new ResizingArrayQueue<Integer>();
		for(int i=0;i <100; i++){
			b.enqueue(i);
		}

		for(Integer i:b){
			System.out.println(i);
		}
		for(int i=0;i <100; i++){
			System.out.println(b.dequeue());
		}
		System.out.println(b.dequeue());

	}
}
	
		
	