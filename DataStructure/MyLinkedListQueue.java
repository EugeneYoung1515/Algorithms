import java.util.*;

class MyLinkedListQueue<T> implements MyQueue3<T>{
	private Node first;
	private Node last;
	private int n;//队列中的元素个数

	private class Node{//私有内部类
		T t;
		Node next;
	}

	public boolean isEmpty(){
		if(first == null){//不能是first == last
				//first == last 还有一个元素
			return true;
		}
		return false;
	}

	public int size(){
		return n;
	}

	public void enqueue(T t){
		if(isEmpty()){
			first = new Node();
			first.t = t;
			last = first;//队列是空的，之后队列尾部加了一个元素，是first == last，两者都指向一个Node对象
		}else{
			last.next = new Node();
			(last.next).t = t;
			last = last.next;
		}
		n++;
	}
	//这里的添加一个元素和下面的去掉一个元素，都要考虑到改变first和last的指向

	//要考虑到下面几种情况
	//1.集合元素为空时，添加一个元素
	//2.集合为空时，去掉一个元素
	//3.集合只有一个元素时，去掉最后一个元素

	public T dequeue(){
		if(isEmpty()!=true){
			T t = first.t;
			first = first.next;
				if(isEmpty()){//看上面，也就是first == null
					last = null;//也就是first == last，都指向null
				}			
			n--;
			return t;
		}else{
			return null;
		}
	}

	public Iterator<T> iterator(){
		return new LinkedListIterator();
	}//泛型的一个疑问
	//Iterator<T> = new LinkedListIterator();

	//这里是LinkedListIterator implements Iterator<T>
	private class LinkedListIterator implements Iterator<T>{
		private Node ifirst = first;
		int nn = n;
		
		public boolean hasNext(){
			if(nn != 0){
				return true;
			}
			return false;
		}
		
		public T next(){//这里就不用考虑会不会越界
				//越界是上面的hasNext()考虑的
			T t = ifirst.t;
			ifirst = ifirst.next;
			nn--;
			return t;
		}
		public void remove(){}
	}

	public static void main(String... args){
		MyLinkedListQueue<Integer> a = new MyLinkedListQueue<Integer>();
		System.out.println(a.dequeue());
		a.enqueue(1);
		a.enqueue(2);
		System.out.println(a.dequeue());
		System.out.println(a.dequeue());
		System.out.println(a.dequeue());

		System.out.println();
		a.enqueue(1);
		a.enqueue(2);
		for(Integer i:a){
			System.out.println(i);
		}

	}
}
/*
这个类和ResizingArrayQueue及ResizingArrayQueue2都是MyQueue3的子类 参照算法第四版的思路
分别使用链表和数组实现队列 同时考虑了泛型和迭代
可以照这些思路使用链表或数组实现栈
*／


