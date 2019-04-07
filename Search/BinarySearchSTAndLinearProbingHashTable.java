//二分查找符号表（基于有序数组）
//和
//基于线性探测法的散列表

//两个都使用多个数组来实现一个数据结构
//同一序号下不同数组的元素 表示 数据结构一个元素的不同性质
//因为使用数组来实现数据结构 当数组元素快满时 都要给数组扩容

//区别 二分查找符号表是有序的
//基于线性探测法的散列表是无序的
//重点是下面的第一个rank方法

class BinarySearchST<K extends Comparable<K>,V>{//不用<K,V> 是因为这里是有序符号表符号表元素的键之间要比较大小，要用compareTo方法。只有<K,V>,是不能调用K类的对象的compareTo方法的。（无序符号表，元素间的键只要考虑等或不等就行了）
	K[] keys;
	V[] values;
	int n;
	int m;//有初始化部分的长度

	public BinarySearchST(){
		this(8);//用BinarySearchST()会报错
	}

	@SuppressWarnings("unchecked")
	public BinarySearchST(int n){
		this.n = n;
		//keys = new K[n];不行 这样就是泛型数组
		//values = new V[n];

		keys = (K[])new Comparable[n];
		values = (V[])new Object[n];//整个符号表这一块一直要出现这样的方法
	}
	
	private int rank(K k){//put方法和get方法的核心
	//就是查找小于等于k的最大键的序号 查找
	//因为k不一定在符号表中

	//就是原来二分查找代码
	//使用循环或者递归都可以

		if( m == 0 ){
			return 0;
		}
		if(keys[m-1].compareTo(k)<0){//一开始m等于0 这里就序号-1了
			return m;////注意这个返回的就不是 小于等于k的最大键的序号
		}
		if(keys[0].compareTo(k)>0){
			return 0;//注意这个返回的就不是 小于等于k的最大键的序号
		}//这两个 下面put方法里调整数组时要用
		return rank(k,0,m-1);
	}//自己以前写的二分查找可能要改一下
			
	private int rank(K k,int low,int high){
		if(low>=high){
			return low;
		}
		int mid = (low+high)/2;
		int tmp = k.compareTo(keys[mid]);
		if(tmp>0){
			return rank(k,mid+1,high);
		}else if(tmp<0){
			return rank(k,low,mid-1);
		}else{
			return mid;
		}
	}

	public V get(K k){
		int i = rank(k);
		if(i < m && k.compareTo(keys[i]) == 0){
			return values[i];
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void resize(int n){
		//调整数组大小
		
		K[] tmpKeys = (K[])new Comparable[n];
		V[] tmpValues = (V[])new Object[n];
		for(int i = 0;i < m;i++){
			tmpKeys[i] = keys[i];
			tmpValues[i] = values[i];
		}

		keys = tmpKeys;
		values = tmpValues;
	}

	public void put(K k,V v){
		int i = rank(k);
		if(i < m && k.compareTo(keys[i]) ==0 ){
			values[i] = v;
			return;//用来代替else
		}//更新的情况 底下是插入
		m++;

		if( m == n/2){
			resize(2*n);
		}

		if(i == (m-1)){
			keys[i] = k;
			values[i] = v;
			return;
		}

		int x = m-1;
		for(;x > i;x--){
			keys[x+1] = keys[x];
			values[x+1] = values[x];
		}
		keys[x] = k;
		values[x] = v;
	}

	public void delete(K k){
		int i = rank(k);
		if(i < m && k.compareTo(keys[i]) == 0){
			for(int x = i+1;x < m;x++){
				keys[x-1] = keys[x];
				values[x-1] = values[x];
			}
			m--;
			if(m == n/4){
				resize(n/2);
			}
		}
	}

	public static void main(String... args){
		BinarySearchST<Integer,Integer>	a = new BinarySearchST<Integer,Integer>();
		for(int i = 0;i < 8;i++){
			a.put(i,i);
		}
		System.out.println(a.get(4));
		System.out.println(a.get(6));
		System.out.println(a.get(5));
		System.out.println(a.get(7));
		//a.delete(5);
		for(int i = 0;i < 8;i++){
			a.delete(i);
		}
		//System.out.println(a.get(5));	

		for(int i = 0;i < 8;i++){
			System.out.println(a.get(i));
		}
	}
}



class LinearProbingHashTable<K,V>{
//原理 就是余数确定的数组位置有键值对时，看下一个位置
//到数组末尾时 回到数组开头 i=(i+1)%n
//键值对数目要比数组长度小

	K[] keys;
	V[] values;
	int n;//数组长度
	int m;//使用部分的长度

	public LinearProbingHashTable(){
		this(16);//用LinearProbingHashTable()会报错
	}

	@SuppressWarnings("unchecked")
	public LinearProbingHashTable(int n){
		this.n = n;

		keys = (K[])new Object[n];
		values = (V[])new Object[n];//整个符号表这一块一直要出现这样的方法
	}

	private int hash(K k){
		return (k.hashCode() & 0xfffffff)%n;//按位运算 去掉符号位 不是取绝对值
	}//key的哈希值除以数组长度，按余数确定分配的数组位置

	public V get(K k){
		for(int i = hash(k);keys[i]!=null;i=(i+1)%n){
			if(keys[i].equals(k)){
				return values[i];
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private void resize(int z){//put会用到resize,resize会用到put
		LinearProbingHashTable<K,V> tmp = new LinearProbingHashTable<K,V>(z);
		for(int i = 0;i < n;i++){
			if(keys[i]!=null){//keys[i]==null的不处理 就是原来是空的 还处理它干吗
				tmp.put(keys[i],values[i]);
			}
		}
		keys = tmp.keys;
		values = tmp.values;
		n = z;
	}
	
	public void put(K k,V v){
		if(m == n/2){
			resize(2*n);
		}
		int i = hash(k);
		for(;keys[i]!=null;i=(i+1)%n){
			if(keys[i].equals(k)){
				values[i] = v;
				return;
			}
		}
		keys[i] = k;
		values[i] = v;
		m++;
	}

	public void delete(K k){//delete和调整都要重新散列
	//这个可能还要再看书理解
		if(m == n/8){
			resize(n/2);
		}
		if(get(k) == null){
			return;//没找到就不用删除
		}
		int i = hash(k);
		for(;keys[i]!=null;i = (i+1)%n){
			if(keys[i].equals(k)){
				keys[i] = null;
				values[i] = null;
				break;
			}
		}//m--放这里行吗 下面的for循环里 put里第一句会用到m（看上面）
		for(int j =(i+1)%n;keys[i]!=null;j = (j+1)%n){
		//int j = i+1错了 j%n == i错了（循环终止条件）
			K tmpk = keys[i];
			V tmpv = values[i];
			keys[i] = null;
			values[i] = null;
			m--;//自减 就是减1 是因为下面put里 会加1 保持数目不变
			put(tmpk,tmpv);
		}
		m--;
	}

	public static void main(String... args){
		LinearProbingHashTable<Integer,Integer>	a = new LinearProbingHashTable<Integer,Integer>();
		for(int i = 0;i < 32;i++){
			a.put(i,i);
		}
		System.out.println(a.get(4));
		System.out.println(a.get(6));
		System.out.println(a.get(5));
		System.out.println(a.get(7));
		//a.delete(5);
		System.out.println(a.get(31));
		for(int i = 0;i < 32;i++){
			a.delete(i);
		}
		//System.out.println(a.get(5));	

		for(int i = 0;i < 32;i++){
			System.out.println(a.get(i));
		}
	}		
}
	
	
		
		
	

	
