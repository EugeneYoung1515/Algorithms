class QuickSort{
	static int[]a = {0,110,3,457,1110,6,5,100,2,1,2000,0};//{0,10,9,8,7,6,5,4,3,2,1,0};
	static int n;
	static int h;
	static int k,x;
static void quickSort(int left,int right){
	int i,j,t,temp,tmp2;

	if(left>right)return;//虽然方法返回类型是void，但是这里用return来终止之后语句的执行
	//这里终止语句，是为了停止下面的递归
	//core java 第一卷末尾还是第二卷
	//有好几处用到这种技巧
/*
return;停止方法之后内容的执行
return;终止递归
return null;
return true;return false
*/

	
	temp=a[left];
	i=left;
	j=right;
	
	while(i<j){
 	//while(i!=j){//
		while(a[j]>=temp &&i<j)//为什么这个条件 也开始时就是 left<j 或者 i<right 不会超出要比较的块
			j--;
		while(a[i]<=temp &&i<j)//当i=j时，跳到下面i=j的部分
			i++;
		
		if(i<j){//
			t=a[i];
			a[i]=a[j];
			a[j]=t;
		}
	}
	//下面运行的条件其实是i=j 上面while(i!=j)
	//a[left]=a[i];
	//a[i]=temp;

	tmp2=a[i];
	a[i]=a[left];
	a[left]=tmp2;
	//调试
	System.out.println(temp);
	list();
	
	h++;
	System.out.println(h);

	System.out.println(".....");
	//调试
	quickSort(left,i-1);
	quickSort(i+1,right);

}

static void list(){
	for(int b:a)System.out.print(b+" ");
	System.out.println();
}

public static void main(String[] args){

	k=1;
	x=10;
	quickSort(1,10);

	for(int b:a)System.out.print(b+" ");
}
}
/*
上面的内容是把啊哈算法里的c语言程序翻译成java程序
*/

/*
c语言里的数组 和 java里数组分析

c语言里数组元素是个指针？

java数组是个对象，数组元素不一定是
使用引用变量指向一个数组对象
通过引用变量操作数组的元素

一个指向数组对象的引用变量改变了数组元素
另一个指向同一数组的应用变量得到的是改变后的数组

就像是通过指向一个对象的引用变量通过对象的方法（准确地说是引用变量的类型的方法，多态的原因）改变了对象的实例变量
另一个指向同一个对象的引用变量得到的是实例变量改变后的对象

多态
父类引用 = 子类对象 父类引用指向子类对象 接口类=接口实现类对象

父类引用.方法 这里的方法的是由引用的类型确定的

所以父类引用.方法 里的方法是父类的方法或者是接口类的方法 子类继承来 也可以说是 父类和子类（接口类和实现类）共有的方法
是父类的方法或者是接口类的方法 但是运行的内容是子类的方法内容 如果子类有覆盖 就按覆盖后的 子类没覆盖的话 父类方法和子类方法 内容是一样的 有没有覆盖都是子类的方法内容
					（接口实现类的方法的内容）

要通过引用使用子类特有的方法 要向下转型

子类引用=(子类）父类引用

*/

/*

c语言里的指针（go语言里的指针）与java里引用变量

java引用变量 对象的内存位置

*/

/*

c语言里址传递与java里值传递
go里两种传递都有

*/



