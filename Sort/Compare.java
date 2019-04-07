class ClassForCompare{
	String name;
	int id;
}
class Compare{
public static void main(String... args){
	ClassForCompare o1 = new ClassForCompare();
	o1.name="a";
	o1.id=1;

	ClassForCompare o2 = new ClassForCompare();
	o2.name="d";
	o2.id=4;
	
	ClassForCompare[] a = {o1,o2};

	if(a[0].id<a[1].id){//比较id 但是交换的是对象
		ClassForCompare tmp = a[1];
		a[1]=a[0];
		a[0]= tmp;
	}
		//上面3个式子
		//tmp和a[1]指向同一个对象#1
		//a[1]指向a[0]指向的对象#2，但是tmp还是指向#1
		//a[0]指向tmp指向的对象#1，但是a[1]还是指向#2

		//结果是a[0]指向#1,a[1]指向#2
		//原来是a[0]指向#2,a[1]指向#1
		//交换a[0]和a[1]的指向
		
/*
int a[2]={1,2};
int tmp = a[1];
a[1] = a[0];
a[0]=tmp;

这里是把a[1]的值赋给tmp；
把a[0]的值赋给a[1]；
把a[0]的值赋给a[1]；
把tmp的值赋给a[0];

交换a[0]和a[1]的值
*/
	for(ClassForCompare o:a)System.out.print(o.name+" "+o.id+" ");

	Main();

}
static void Main(){

	ClassForCompare o1 = new ClassForCompare();
	o1.name="a";
	o1.id=1;

	ClassForCompare o2 = new ClassForCompare();
	o2.name="d";
	o2.id=4;
	
	ClassForCompare[] b = {o1,o2};
	int[] c={o1.id,o2.id};
	String[] d = {o1.name,o2.name};//这里和上面一样，都可以吧.id、.name换成.getId()、.getName()

/*
用两个数组实现链表
一个数组纪录链表元素的值
另一个数组纪录下一个元素的序号
*/
	
	if(c[0]<c[1]){
		ClassForCompare Max = b[1];
		String MaxString = d[1];
		
		System.out.println(Max.name+"aaa");
		System.out.println(MaxString+"aaa");
	}
}

}
	

	