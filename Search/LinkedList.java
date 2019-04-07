//展示链表插入和删除的集中方法
//链表方便插入和删除 在两边或者内部
//但是见到的主要还是在两边
//如使用链表实现队列和栈
//使用链表实现符号表（例外:二叉查找树的删除操作和红黑树的删除操作）

//就是两种常见集合的递归写法要记住 所谓的套路
//数组递归的基线条件(终止递归的条件)
//子数组的长度等于1或等于0 就是子数组的长度小于等于1 就是子数组左边界大于等于右边界

//下面是链表递归
//链表递归的基线条件
//结点等于null 结点的下一个结点等于null
//上面两种情况可能都需要 也有可能只需要一种
//思考时还要考虑 链表中没有加入任何结点,链表是空的 这种情况

public class LinkedList<E>{

	Node first;

	private class Node{
		private E val;
		private Node next;
		
		public Node(E val){
			this.val = val;
		}
	}
	
	public void add1(E e){//使用循环 在末尾加
		if(first == null){
			first = new Node(e);
			return;
		}

		Node x = first;
		for(;x!=null;x = x.next){
			if(x.next == null){//用if和break指示最后一个结点
				break;
			}
		}
		x.next = new Node(e);
	}

	public void add2(E e){//使用递归 在末尾加
		add2(fist,e);
	}

	public void add2(Node x,E e){
		if(first == null){//x == null 也行
			first = new Node(e);
			return;
		}
		if(x.next == null){
			x.next = new Node(e);
		}else{
			add(x.next,e);
		}
	}

	public void add4(E e){//使用递归 在末尾加 算法第四版二叉查找树的那块的
		first = add4(fisrt,e)
	}

	public Node add4(Node x,e){//输入一个结点 更新这个结点的下一个结点 之后还是返回这个结点
			if(x == null){
				return new Node(e);
			}//链表中没有结点 和 结点的下一个结点等于null 都包含在内
			x.next = add4(x.next,e);
			return x;
	}//使用递归 在末尾加 最简洁的写法 就是有点不好理解

	public void add5(E e){//不用递归也不用循环 在开头加 在算法第四版中 基于无序链表的符号表那里
		Node tmp = first;
		first = new Node(e);
		first.next = tmp;
/*
		//如果Node类有这样的构造函数 Node(E e,Node next){this.val = e;this.next = next;}
		//上面三行可以变成以行
		first = new Node(e,first);
*/
	}//在开头加 最简洁的写法

	//其他技巧
	//结点的的一个实例变量指向下一个变量 使用链表实现队列
	//结点的一个实例变量也可以用来指向前一个变量 使用链表实现栈 算法第四版使用链表实现栈那里
	//两者的一个共同点是 可以只用一步实现 一个结点的删除
	//first = first.next
	//last = last.previous
		
	public void add6() throws Exception{//使用循环 在末尾加 以前参考啊哈算法写出来的
		try(Scanner in = new Scanner(System.in)){
			int a=in.nextInt();

			Node tmp = null;//用来指已有链表的最后一个结点
			Node p = null;//用来指每次新加的结点
	
			for(int i=0;i<a;i++){
				int b=in.nextInt();
				p=new Node(b);
				if(tmp!=null){//1.
					tmp.next=p;
				}
				if(first == null){//2.
					first=p;
				}
				tmp=p;//有新的最后一个结点//3.
			}
			//一开始 tmp和first都没有指向任何对象
			//first有志向对象后 tmp和head指向同一个对象(1. 2. 3.)
			//之后都是都是通过tmp加上一个对象 更新最后一个对象(1. 3.)
/*
			循环外 Node tmp = first;//也就是都等于null

			if(first == null){
				first=p;
				tmp = p;
			}
			//tmp.next=p直接放在这里，没有放在if里 不行
			if(tmp == first){
				tmp.next=p;
			}
			tmp = p;
*/
/*
			if(first == null){
				first=p;
			}
			//tmp.next=p直接放在这里，没有放在if里 不行
			if(tmp != null){
				tmp.next=p;
			}
			tmp = p;
			
			//怎么换都要有两个条件
*/

			while(first!=null){	
				System.out.print(first.val+" ");
				first = first.next;
			}
	}

/*
和自己按算法第四版上的思路使用使用链表实现队列相似
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

*/


		
		
	