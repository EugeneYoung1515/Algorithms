//使用和Comparable同样的功能
//使用冒泡排序
interface MyComparable{
	boolean myComparable(MyComparable mc);
}
class TestObject implements MyComparable{
	TestObject(String name,int id){
		this.name = name;
		this.id = id;
	}

	private String name;
	private int id;

	public String getName(){return name;}

	public int getId(){return id;}

	public boolean myComparable(MyComparable mc){
		TestObject t =(TestObject)mc;
		if(t.getId() > this.getId()){
			return true;
		}
		return false;//要有这一行 不然if里是false时，就没有返回
	}
	
	public static void main(String... args){

		TestObject[] testsObject = {new TestObject("a",1),
		new TestObject("a",1),
		new TestObject("b",2),
		new TestObject("c",3),
		new TestObject("d",4)};
		sort(testsObject);//使用排序 ，就这一句
		for(TestObject t : testsObject){
			System.out.print(t.getName()+" ");
		}
	}
		
	static void sort(TestObject[] t){
		TestObject tmp;	
		for(int i=0;i<t.length;i++){
			for(int j=0; j< t.length-1;j++){
				if(t[j].myComparable(t[j+1])){//接口用在这
					tmp = t[j+1];
					t[j+1] = t[j];
					t[j] = tmp;
				}
			}
		}
	}
}	
		
	
