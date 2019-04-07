//按算法第四版的思路实现符号表
//散列表
//基于拉链法的散列表
//就是数组的每一个元素是一个链表

//树上还有基于线性探测法的散列表

//先实现基于无序链表的顺序查找
//SequentialSearchST
//ST 符号表

/*
  约定：1.找不到key（key不存在），返回 value为null
       2.value被赋为null，表示删除
       3.合起来就是 找不到key或者value为null 都是value为null 符号表中不包含（contains）
*/

abstract class ST<K,V>{
	abstract V get(K k);
	abstract void put(K k,V v);
	void delete(K k){
		put(k,null);
	}
	boolean contains(K k){
		return get(k)!=null;
	}
}	

class SequentialSearchST<K,V> extends ST<K,V>{
	Node first;

	private class Node{
		private K k;
		private V v;
		private Node next;
		public Node(K k,V v){
			this.k = k;
			this.v = v;
		}
	}

	public V get(K k){
		for(Node tmp = first;tmp != null;tmp = tmp.next){
			if(k.equals(tmp.k)){
				return tmp.v;
			}
		}
		return null;//找不到返回null 注意要加上这个
	}

	public void put(K k,V v){
		if(first == null){
			first = new Node(k,v);
			return;
		}
		Node tmp = first;
		for(;tmp != null;tmp = tmp.next){
			//System.out.println("test");
			if(k.equals(tmp.k)){
				tmp.v = v;
				return;//这里加上return 更好
			}
			if(tmp.next == null){
				break;
			}
		}
		tmp.next = new Node(k,v);
	}//算法第四版是找不到就在开头加
	//自己是在末尾加

	public static void main(String... args){
		SequentialSearchST<Integer,Integer> a = new SequentialSearchST<Integer,Integer>();
		a.put(1,1);
		a.put(2,2);
		a.put(3,3);
		System.out.println(a.get(1));
		System.out.println(a.get(2));
		System.out.println(a.get(3));
		a.delete(2);
		System.out.println(a.contains(2));
		System.out.println(a.get(2));
		a.delete(3);
		a.delete(1);
		System.out.println(a.get(3));
	}
}

public class HashTable<K,V> extends ST<K,V>{
	private SequentialSearchST<K,V>[] st;
	private int M;

	@SuppressWarnings("unchecked")
	public HashTable(int M){
		this.M = M;
		st = (SequentialSearchST<K,V>[])new SequentialSearchST[M];//M数组元素数 或者是 链表条数
		//不能创建泛型数组的替代方案

		for(int i = 0;i < M;i++){
			st[i] = new SequentialSearchST<K,V>();
		}			
	}
	public HashTable(){//设置默认值
		this(997);//M选质数 或者更好的的是键值对数量N的四分之一左右的质数
	}

	private int hash(K k){
		return (k.hashCode() & 0xfffffff)%M;//按位运算 去掉符号位 不是取绝对值
	}//key的哈希值除以数组长度，按余数确定分配的数组位置

	public V get(K k){
		return st[hash(k)].get(k);
	}

	public void put(K k,V v){
		int tmp = hash(k);
		//if(st[tmp] == null){
			//st[tmp] = new SequentialSearchST<K,V>();
		//}用的时候再初始化不行 用的时候再初始化 
		//a.get(4) 就没初始化 是null.get(...) NullPointerException
		st[hash(k)].put(k,v);
	}

	public static void main(String... args){
		ST<Integer,Integer> a = new HashTable<Integer,Integer>();
		a.put(1,1);
		a.put(2,2);
		a.put(3,3);
		System.out.println(a.get(1));
		System.out.println(a.get(2));
		System.out.println(a.get(3));
		a.delete(2);
		System.out.println(a.contains(2));
		System.out.println(a.get(2));
		a.delete(3);
		a.delete(1);
		System.out.println(a.get(1));
		System.out.println(a.get(4));
	}
}
		

