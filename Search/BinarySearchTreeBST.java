//与链表（或基于链表的无序查找）类似
//都是使用结点
//但是每个结点对象有一个实例变量指向左子结点 有另一个实例变量指向右子结点
//代码上都是使用私有内部类

//二分查找树是有序符号表

//在这个.java文件中写二分查找树
//和基于二分查找树的红黑树（就是2-3树的实现）

//难点 熟练链表的循环和递归写法

public class BinarySearchTreeBST<K extends Comparable<K>,V>{
	private Node root;
	
	private class Node{
		private K k;
		private V v;
		private Node left;
		private Node right;
		private Node sp;//special 离要删除结点最近的结点
		
		public Node(K k,V v){
			this.k = k;
			this.v = v;
		}
	}

	public V get2(K k){//循环版本
		for(Node first = root;first != null;){
			int tmp = k.compareTo(first.k);
			if(tmp < 0){
				first = first.left;
			}else if(tmp > 0){
				first = first.right;
			}else{
				return first.v;
			}
		}
		return null;
	}
	
	public V get(K k){//递归版本
		return get(root,k);
	}
	
	public V get(Node x,K k){
		if(x == null){
			return null;
		}
		int cmp = k.compareTo(x.k);
		if(cmp < 0){
			return get(x.left,k);
		}else if(cmp >0){
			return get(x.right,k);
		}else{
			return x.v;
		}
	}

	public void put(K k,V v){//查是查到没有子结点的最底下的叶结点，其左子结点是null，右子结点也是null
				//加put 也要先查 也就是新增结点的话 也是变成原叶结点的子结点
		put(root,k,v);
	}

	public void put(Node x,K k,V v){
/*
		if(x == null){
			return;
		}
*/
		if(x == null){//此时链表没有结点 接下来要加入的是node结点
			root = new Node(k,v);
			return;
		}

		int cmp = k.compareTo(x.k);
		if(cmp < 0){
			if(x.left == null){
				x.left = new Node(k,v);
			}else{
				put(x.left,k,v);
			}
		}else if(cmp > 0){
			if(x.right == null){
				x.right = new Node(k,v);
			}else{
				put(x.right,k,v);
			}
		}else{
			x.v = v;
		}
	}

//链表的循环和递归的写法

	public void put2(K k,V v){
		if(root == null){
			root = new Node(k,v);
			return;
		}
		for(Node first = root;first != null;){
			int tmp = k.compareTo(first.k);
			if(tmp < 0){
				if(first.left == null){
					first.left = new Node(k,v);
					break;
				}
				first = first.left;
			}else if(tmp > 0){
				if(first.right == null){
					first.right = new Node(k,v);
					break;
				}
				first = first.right;
			}else{
				first.v = v;
				break;
			}
		}
		
	}//4百万次打乱与插入 时间在2秒左右
	//上面的实验重复10次 put2循环 比put递归 少 40毫秒 少put递归的1/50到1/49

	public void put3(K k,V v){//算法第四版上的方法
		root = put3(root,k,v);
	}
	
	public Node put3(Node x,K k,V v){
		if(x == null){
			return new Node(k,v);
		}
		
		int cmp = k.compareTo(x.k);
		if(cmp < 0){
			x.left = put3(x.left,k,v);//用x.left等于null 结合 上面的 x==null 去想
		}else if(cmp > 0){
			x.right = put3(x.right,k,v);
		}else{
			return x;//没有再往下递归了
		}
		return x;//上面改了x.left或x.right 这里要返回x
	}//4百万次打乱与插入 时间在2秒左右
	//上面的实验重复10次 put3递归 比put2循环 多 124毫秒 多put2循环的1/20到1/10
	
	public void shuffle(int[] a){
		int N = a.length;
		for(int i = 0;i < N;i++){
			int r = i + (int)(Math.random()*(N-i));
			
			int tmp = a[i];
			a[i] = a[r];
			a[r] = tmp;
		}
	}

	public static void main(String... args){
		BinarySearchTreeBST<Integer,Integer> a = new BinarySearchTreeBST<Integer,Integer>();

		//int[] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		
		long calltime = System.currentTimeMillis();
		for(int i = 0; i<4000000;i++){
			a.shuffle(array);
			for(int j = 0; j < array.length;j++){
				a.put2(array[j],array[j]);
			}

			//for(int j = array.length-1;j >=0;j--){
				//System.out.println(a.get(j));
			//}
			//System.out.println();
		}
		System.out.println(System.currentTimeMillis()-calltime);
	}

	public void delete(K k){//画图 想情况 转化为条件和代码 想额外情况（其他情况）转化为条件和代码
				//就是要删除结点的左子树的右子树中的最大值 或者直接说左子树中的最大值 换掉要删除的结点
				//要删除结点的右子树的左子树中的最小值 或者直接说右子树中的最小值 换掉要输出的结点
				//总之 就是离要删除结点的最接的值换掉要删除结点
		delete(root,k);
		//要先找到要删除的结点
	}

	public void delete(Node x,K k){
		if(x == null){
			return;
		}
		int cmp = k.compareTo(x.k);
		if(cmp < 0){
			delete(x.left,k);
		}else if(cmp >0){
			delete(x.right,k);
		}else{//要在这里面写上替换的代码
			if(x.left != null){
			x.left = special(x.left);	
		}
	}
/*
	private Node deleteMin(Node x){//是找离要删除结点最近的结点才对
			if(x.right == null){
				return null;//变为null 返回最小值
			}
			if(x.right != null){
				x.right = deleteMin(x.right);
			}
			return x;
	}
	
	private Node deleteMin(Node x){//是找离要删除结点最近的结点才对
		if(x.right != null){
			if(x.right.right == null){
				Node tmp = x.right;
				x.right = null;
				return tmp;
			}
			return deleteMin(x);
		}
		return x;//还是不行 x.左枝的指向还要改
	}
*/
//上面两个都无法在 递归调用 中同时做到 1.把自己要的结点 在二叉树上变成null(自己要的节点的父节点指向null) 2.自己要的结点的子结点更换父节点 3.返回自己要的结点

	private Node special(Node x){//是找离要删除结点最近的结点才对
		//更新x的左右结点 返回x
		if(x.right != null){
			if(x.right.right == null){
				sp = x.right;
				x.right = x.right.left;
			}
			if(x.right.right != null)
				x.right = special(x.right);
			}
			return x;
		}
		sp = x;
		return x.left;
	}//还是错
}
	