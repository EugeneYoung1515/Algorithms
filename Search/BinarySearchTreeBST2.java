//与链表（或基于链表的无序查找）类似
//都是使用结点
//但是每个结点对象有一个实例变量指向左子结点 有另一个实例变量指向右子结点
//代码上都是使用私有内部类

//二分查找树是有序符号表

//在这个.java文件中写二分查找树
//和基于二分查找树的红黑树（就是2-3树的实现）

//难点 熟练链表的循环和递归写法

public class BinarySearchTreeBST2<K extends Comparable<K>,V>{
	private Node root;
	private Node sp;//special 离要删除结点最近的结点

	private class Node{
		private K k;
		private V v;
		private Node left;
		private Node right;
		
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
			//return x;//没有再往下递归了
			x.v = v;
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
		BinarySearchTreeBST2<Integer,Integer> a = new BinarySearchTreeBST2<Integer,Integer>();

		//int[] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		
		long calltime = System.currentTimeMillis();
		for(int i = 0; i<4000000;i++){
			a.shuffle(array);
			for(int j = 0; j < array.length;j++){
				a.put3(array[j],array[j]);
			}

			//for(int j = array.length-1;j >=0;j--){
				//System.out.println(a.get(j));
			//}
			//System.out.println();

			a = new BinarySearchTreeBST2<Integer,Integer>();
		}
		System.out.println(System.currentTimeMillis()-calltime);
	}

	public void delete(K k){//画图 想情况 转化为条件和代码 想额外情况（其他情况）转化为条件和代码
				//就是要删除结点的左子树的右子树中的最大值 或者直接说左子树中的最大值 换掉要删除的结点
				//要删除结点的右子树的左子树中的最小值 或者直接说右子树中的最小值 换掉要输出的结点
				//总之 就是离要删除结点的最接的值换掉要删除结点
		root=delete(root,k);
		//要先找到要删除的结点
	}

/*
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
				x.left = deleteMax(x.left);
				Node tmp = x;
				//要用sp结点换掉要要删除的结点
				//同样要用到要删除结点的上一个结点
				//要让上一个结点指向sp结点

			}else if(x.right != null){
				x.right = deleteMin(x.right);
			}else{//就是x.left == null && x.right ==null
			//也就是要删除的结点是底下的叶结点
			//得让要删除结点的上一个结点指向nullcaixing
			//不能把null赋给x
					
		}
	}
*/
//换成输入结点 更新左右结点
//返回结点
//这种递归就行了

	public Node delete(Node x,K k){

		if(x == null){
			return null;
		}

		int cmp = k.compareTo(x.k);
		if(cmp < 0){
			x.left=delete(x.left,k);
		}else if(cmp >0){
			x.right=delete(x.right,k);
		}else{//要在这里面写上替换的代码
			if(x.left != null){
				x.left = deleteMax(x.left);
			}else if(x.right != null){
				x.right=deleteMin(x.right);
			}else{//就是x.left==null&&x.right==null
				return null;
			}
			sp.left=x.left;
			sp.right=x.right;
			return sp;					
		}
		return x;
	}//思路 使用更新子结点的递归方式 找到要删除的结点
	//用离子结点最近的结点替换掉子结点 也就是要删除结点的左子树(以要删除结点的左子结点为根结点的树)的最大值 要删除结点的右子树的最小值
	//写删除最值的方法 也是 使用更新子结点的递归方式 要实现下面三点
	//3.返回自己要的结点 通过实例变量sp实现


	//要同时递归调用 中同时做到 1.把自己要的结点 在二叉树上变成null(自己要的节点的父节点指向null) 2.自己要的结点的子结点更换父节点 3.返回自己要的结点

	private Node deleteMax(Node x){//x是某一个子树的根
		//传入一个结点 更新这个结点的右结点
		//之后返回这个这个结点

		//这个方法的作用是删除 以x为根结点的子树中的最大值 并在实例变量sp中记录这个最大值

		if(x.right != null){
			x.right = deleteMax(x.right);
			return x;
		}
		if(x.right == null){
			sp = x;//x是最大值结点
			return x.left;//使x没有被指着 也就是删除x 
		}
		return x;
	}

	private Node deleteMin(Node x){//把deleteMax中left换成right right换成left就行了

		if(x.left != null){
			x.left = deleteMin(x.left);
			return x;
		}
		if(x.left == null){
			sp = x;//x是最大值结点
			return x.right;//使x没有被指着 也就是删除x 
		}
		return x;
	}

	public void deleteMinTest(){
		root=deleteMax(root);
		System.out.println(sp.k);
	}

	public K min(){
		return min(root).k;
	}
	public Node min(Node x){
		if(x == null){
			return null;
		}
		if(x.left==null){
			return x;
		}else{
			return min(x.left);
		}
	}

			
/*
	public static void main(String... args){
		BinarySearchTreeBST2<Integer,Integer> a = new BinarySearchTreeBST2<Integer,Integer>();
		int[] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		for(int j = 0; j < array.length;j++){
			a.put2(array[j],array[j]);
		}
		for(int j = array.length-1;j >=0;j--){
			System.out.println(a.get(j));
		}
		a.delete(10);
		System.out.println(a.get(11));
		//a.deleteMinTest();
		//System.out.println(a.get(13));
		//System.out.println(a.get(4));

	}
*/

/*
	public static void main(String... args){
		BinarySearchTreeBST2<Integer,Integer> a = new BinarySearchTreeBST2<Integer,Integer>();

		//int[] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		
		long calltime = System.currentTimeMillis();
		for(int i = 0; i<4000000;i++){
			a.shuffle(array);//三个main测试方法都有问题 就第一次打乱有用 第一次把所有的数加入二叉树之后 第二次连同之后的所有次 put方法 就相当于get
					//因为还是在操作同一个二叉树
					//应该在循环末尾加上 a = new BinarySearchTreeBST2<Integer,Integer>(); 让a指向性的符号表
			for(int j = 0; j < array.length;j++){
				a.put2(array[j],array[j]);
			}
		
			a.delete(4);
			if(a.get(4)!=null){
				System.out.println(a.get(4));
			}
			if(a.get(5)==null){
				System.out.println(a.get(5));
			}

			//for(int j = array.length-1;j >=0;j--){
				//System.out.println(a.get(j));
			//}
			//System.out.println();
		
			a = new BinarySearchTreeBST2<Integer,Integer>();
		}
		System.out.println(System.currentTimeMillis()-calltime);
	}
*/
}
	