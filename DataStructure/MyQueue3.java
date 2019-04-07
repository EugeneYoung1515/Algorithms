import java.util.*;

interface MyQueue3<T> extends Iterable<T>{
	void enqueue(T t);
	T dequeue();
	boolean isEmpty();
	int size();
}
	