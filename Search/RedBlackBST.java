//红黑树
//重用二分查找树的查找(get)算法

//用红色左链接加二叉树 实现 2-3树

//不能出现哦红色右链接
//不能有连续相连的红色左链接 违反下面第二条
//该树是完美黑色平衡的，即任意空链接到根结点的路径上的黑色链接数量相同	 这个花草图时有用	能检查自己画的是不是红黑树

//看书 一种等价的定义
//红链接均为左链接
//没有一个任何一个结点同时和两条链接相连
//该树是完美黑色平衡的，即任意空链接到根结点的路径上的黑色链接数量相同

public class RedBlackBST<K extends Comparable<K>,V> extends BinarySearchTreeBST2<K,V>{

	private static final boolean RED = true;
	private static final boolean BLACK = false;

	Node root;

	private class Node{
		K k;
		V v;
		Node left;
		Node right;
		boolean color;

		public Node(K k,V v,Boolean color){
			this.k=k;
			this.v=v;
			this.color = color;//父节点指向它的链接的颜色
		}
	}

	//这里直接用BinarySearchTreeBST2<K,V> 会有问题 似乎与私有内部类有关
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

	private boolean isRed(Node x){//是红色吗
		if(x == null){//约定空链接为黑色	这个的作用是下面put方法才能用3个if概括所有情况
			return false;
		}
		if(x.color == RED){
			return true;
		}
		return false;
	}
	
	//下面的方法rotateLeft、rotateRight和flipColors都是为了不要有红色右链接
	private Node rotateLeft(Node h){//左旋	h的红色右链接左旋 变成右链接
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		//h.color = x.color 这句错了
		//还要把h所指结点的颜色给x所指结点

		x.color = h.color;
		h.color = RED;
		//上面两句的顺序不能调换		

		return x;
	}

	private Node rotateRight(Node h){//右旋	h的红色左链接右旋 变成右链接
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		//h.color = x.color;

		x.color = h.color;
		h.color = RED;
		//上面两句的顺序不能调换		

		return x;
	}

	private void flipColors(Node h){//把h的两条子结点(的链接)的颜色 从红色变成黑色 之后把h(的链接)的颜色变成红色 就是把颜色向上传递
					//实际上写的时候 就没有判断把h的两条子结点(的链接)的颜色
		h.left.color = BLACK;
		h.right.color = BLACK;
		h.color = RED;
	}

	public void put(K k,V v){//在算法第四版的二叉树的put方法上加几步
		root = put(root,k,v);	
		root.color=BLACK;//*
	}

	public Node put(Node x,K k,V v){//再插入新结点后 要进行判断 加上旋转和变色的操作
					//准确的说 是每次递归后 都要进行判断 看要不要旋转和变色
					//判断要不要旋转和变色 是在 递归后

	//链表递归的一种写法
	//传进去一个 (可能)更新这个结点的左右子结点 返回这个结点

	//脱离递归情景 这样的写法
	//传给一个方法一个对象 在这个方法中更改这个对象的实例变量 之后把这个对象传出来

	//用面向对象的方法写面向函数？ 函数式编程？

		if(x == null){
			return new Node(k,v,RED);
		}
		
		int cmp = k.compareTo(x.k);
		if(cmp < 0){
			x.left = put(x.left,k,v);//用x.left等于null 结合 上面的 x==null 去想
		}else if(cmp > 0){
			x.right = put(x.right,k,v);
		}else{
			x.v = v;//没有再往下递归了
		}
		//* 这个标志是	在算法第四版的二叉树的put方法上加的步骤
		
		if(isRed(x.right) && !isRed(x.left)){//*	//这几个条件要理解和记忆	特别是这一个条件	这个条件表示的情况有点多
			x=rotateLeft(x);//*
		}//*
	//System.out.println(1+" "+x.k);
		if(isRed(x.left) && isRed(x.left.left)){//*
			x=rotateRight(x);//*
		}//*
	//System.out.println(2+" "+x.k);
		if(isRed(x.left) && isRed(x.right)){//*
			flipColors(x);//*
		}//*	
	//System.out.println(3+" "+x.k);

		return x;//上面改了x.left或x.right 这里要返回x
	}

	//红黑树的删除
	//先写红黑树的删除最小键 删除最大键
	//删除中的一些情况是和删除最小键 删除最大键 一样的
	//就是if 是一样的

	//2-3树 <--> 红黑树 2-3树的变换 <--> 红黑树的变换
	//出现非 2-结点 才能删除

	//重要:左旋和右旋
	//难点:2-3树的情况(if语句)转变为红黑树的情况(if语句)
	//	2-3树的变换转变为红黑树的变换
	//注意:颜色反转有两种 一种是去掉4-结点 一种是产生4-结点

	//现在是只能一半理解一半记忆    就是有一些情况不理解(if语句)

	private void flipColors2(Node h){//这个反转颜色和上面的反转颜色作用不一样
					//上面的反转颜色是要去掉4-结点
					//这里的反转颜色是要产生4-结点
		h.color = BLACK;
		h.left.color = RED;
		h.right.color = RED;		
	}

	private Node moveRedLeft(Node h){//看名字想作用 红链接都是左链接 这个方法的作用是 让产生的红色右连接(因为4-结点产生的) 变为红色左链接 让所有的红色连接都是左链接
	//返回不是void
		flipColors2(h);
		if(isRed(h.right.left)){//if(!isRed(h.right.left)){//后面这个条件写反了
			h.right = rotateRight(h.right);//h.right = rotateLeft(h.right);//旋转方向反了
			h = rotateLeft(h);//h=rotateRight(h);//旋转方向反了
			flipColors(h);//这里不是flipColors2(h) 是把4-结点去掉
		}
		return h;
	}

	private Node moveRedRight(Node h){//看名字想作用 这个方法的作用是 让所有的红色连接都是右连接
	//返回不是void

		flipColors2(h);
		if(isRed(h.left.left)){
			h = rotateRight(h);
			flipColors(h);
		}
		return h;	
	}

	private Node balance(Node h){//和put后面几句一样(除了第一个条件(if))
	//put后面几句
	//加一个结点颜色先是红色再调整

		//返回不是void
		//因为h可能会改变指向
		//或者说上一个结点 指向的结点(下一个结点) 可能会变
		
		if(isRed(h.right)){//put那里是if(isRed(h.right) && !isRed(h.left)){
		//这里的条件更松
			h=rotateLeft(h);
		}
		if(isRed(h.left) && isRed(h.left.left)){
			h=rotateRight(h);
		}
		if(isRed(h.left) && isRed(h.right)){
			flipColors(h);
		}

		return h;
		
	}

	public void deleteMin(){
		if(!isRed(root.left) && (!isRed(root.right))){//这个if deleteMin deleteMax delete都会出现
		//根结点不是3-结点
		//就把根结点变成3-结点
			root.color = RED;
		}
		root = deleteMin(root);

		//root.color = BLACK;//看上面的put那里 最后也是把根结点的颜色变成黑色
		if(root != null){//root == null 整个树就是空的 树是空的 改变根结点的颜色干什么 root != null 整个树不是空的
			root.color = BLACK;
		}
	}

	private Node deleteMin(Node h){
		if (h.left == null){//if(h == null){//写后面这个错了
			return null;
		}
		if(!isRed(h.left) && (!isRed(h.left.left))){//左子结点是2-结点
							//另一个条件 在moveRedLeft(...)里 if(isRed(h.right.left)) 左子结点的亲兄弟结点是3-结点(不是2-结点)
											//不用if(!isRed(h.right)) 因为赋flipColors完 h.right肯定是红的
			h = moveRedLeft(h);
		}
		h.left = deleteMin(h.left);//递归
		return balance(h);

		//返回不是void
		//因为h可能会改变指向
		//或者说上一个结点 指向的结点(下一个结点) 可能会变
	}

	public void deleteMax(){
		if(!isRed(root.left) && (!isRed(root.right))){//这个if deleteMin deleteMax delete都会出现
		//根结点不是3-结点
		//就把根结点变成3-结点
			root.color = RED;
		}
		root = deleteMax(root);

		//root.color = BLACK;//看上面的put那里 最后也是把根结点的颜色变成黑色
		if(root != null){//root == null 整个树就是空的 树是空的 改变根结点的颜色干什么 root != null 整个树不是空的
			root.color = BLACK;
		}
	}

	private Node deleteMax(Node h){

		if(isRed(h.left)){//*1
			h=rotateRight(h);//左链接右旋	//红色左链接右旋就有红色右连接了
		//红色连接都是左链接 这一个右旋之后 变成红色右连接 右子结点是3-结点
		}
		if(h.right == null){//这一个不能放在前面//if(h == null){//后面这个错了
			return null;
		}
		if(!isRed(h.right) && (!isRed(h.right.left))){//*1处执行了 这里就不会执行
		//右子结点不是3-结点
		//另一个条件 在moveRedRight(...)里 if(isRed(h.left.left)) 右子结点的亲兄弟结点是3-结点(非2-结点)
						//不用if(!isRed(h.left)) 
			h=moveRedRight(h);	
		
		}//deleteMax的变化完 红链接都是右连接
			
		h.right = deleteMax(h.right);
		return balance(h);
	}

	//删除最小键和最大键
	//根结点不是3-结点(非2-结点)(根结点左链接和右结点右连接都是非红),就是把根结点变成3-结点
	//删除完 和put一样 把根结点变成黑色 2-结点

	//删除最小键 1.左子结点是2-结点 2.左子结点的亲兄弟结点是3-结点(非2-结点)	//变换完 红色链接都是左链接
	//删除最大键 1.右子结点是2-结点 2.有子结点的亲兄弟结点是3-结点(非2-结点)	//变换完 红色链接都是右连接
	//删除最大键时 红链接都是左链接 左链接右旋 就有红色链接是右连接 右子结点是3-结点(非2-结点)

	//递归完沿路回来(向上) 还要修复 把4-结点去掉(作用不止这个)

	//红黑树的删除
	//先写红黑树的删除最小键 删除最大键
	//删除中的一些情况是和删除最小键 删除最大键 一样的
	//就是if 是一样的

	//要删除的结点在非2-结点中
	public void delete(K k){

		if(!isRed(root.left) && (!isRed(root.right))){//这个if deleteMin deleteMax delete都会出现
		//根结点不是3-结点
		//就把根结点变成3-结点
			root.color = RED;
		}
		root = delete(root,k);
		//root.color = BLACK;//看上面的put那里 最后也是把根结点的颜色变成黑色
		if(root != null){//root == null 整个树就是空的 树是空的 改变根结点的颜色干什么 root != null 整个树不是空的
			root.color = BLACK;
		}		
	}

	private Node delete(Node h,K k){
		if(k.compareTo(h.k)<0){//可能在左子结点 按删除最小键(始终只看左子结点)的变换来
			if(!isRed(h.left) && (!isRed(h.left.left))){
				h=moveRedLeft(h);
			}
			h.left = delete(h.left,k);
			//return balance(h);
		}else{//匹配到了 或者可能在右子结点 按删除最大键(始终只看右子节点)的变换来 类似
			if(isRed(h.left)){//左链接右旋  //*A
				h=rotateRight(h);
			}
			if(k.compareTo(h.k) == 0 && h.right == null){//deleteMax相应位置的是 if(h == null)
				return null;
			}
			if(!isRed(h.right) && (!isRed(h.right.left))){//*A处执行 这里就不会执行
			//要删除靠近右 靠近下 在底部
				h=moveRedRight(h);
			}
			if(k.compareTo(h.k) == 0){
				Node tmp = min(h.right);//h的右子树(以h的右子结点为根的子树)的最大值 最靠近h 替换掉h
						    //min(...)只是查而已 从BinarySearchTreeBST2<K,V>继承下来的
				h.k = tmp.k;
				h.v = tmp.v;

				h.right = deleteMin(h.right);
			}else{
				h.right = delete(h.right,k);
			}
		}
		return balance(h);//有变换就要恢复
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

	public static void main(String... args){
		RedBlackBST<Integer,Integer> a = new RedBlackBST<Integer,Integer>();

		//int[] array = {0,1,2,3,4,5,6,7,8,9,10,11,12,13};
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		
		long calltime = System.currentTimeMillis();
		for(int i = 0; i<40000;i++){//00000
			a.shuffle(array);//三个main测试方法都有问题 就第一次打乱有用 第一次把所有的数加入二叉树之后 第二次连同之后的所有次 put方法 就相当于get
					//因为还是在操作同一个二叉树
					//应该在循环末尾加上 a = new BinarySearchTreeBST2<Integer,Integer>(); 让a指向性的符号表
			for(int j = 0; j < array.length;j++){
				a.put(array[j],array[j]);
				//System.out.println(array[j]);
			}		
			a.delete(4);
			if(a.get(4)!=null){
				System.out.println(4+" "+a.get(4));
			}
			if(a.get(5)==null){
				System.out.println(5+" "+a.get(5));
			}
/*
			for(int j = array.length-1;j >=0;j--){
				System.out.println(a.get(j));
			}
			System.out.println();
*/		
			a = new RedBlackBST<Integer,Integer>();
		}
		System.out.println(System.currentTimeMillis()-calltime);
	}
}
//常用的局部变量命名 tmp x

//常见错误
//1.旋转方向反了
//2.条件写反了(写了正确条件的相反条件)
//注意 一些方法都是对称存在 一个方法里写错了 另一个方法可能也写错了
//3.满足不满足都要递归
//满足条件就要进行变换 不满足条件就不用递归 满不满足条件都要递归 递归要放在if外