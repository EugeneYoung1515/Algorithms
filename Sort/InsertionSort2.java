//按照算法第四版的排序api模板
//思路也是按照算法第四版的
//就是两两交换，向前换（向序号小的换）实现“在前面插入一个数”


import java.lang.Comparable;
public class InsertionSort2{
	private static boolean less(Comparable v,Comparable w){//less表示第一个参数比第二个参数小
		return v.compareTo(w) < 0;
	}
	private static void swap(Comparable[] a,int i,int j){
		Comparable tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
	private static void show(Comparable[] a){
		for(Comparable i:a){
			System.out.print(i + " ");//算法第四版中也和自己一样，使用+ " "，加空格，来简单地格式化展示数据
		}
	}
	public static boolean isSorted(Comparable[] a){
		for(int i = 0;i < a.length;i++){
			if(!less(a[i],a[i+1])){
				return false;
			}
		}
		return true;
	}
	public static void sort(Comparable[] a){
		for(int i=1;i < a.length; i++){
			for(int j = i-1;j >=0;j--){
				if(less(a[j+1],a[j])){
					swap(a,j+1,j);
				}
			}
		}
	}
	//上面的做法会多做额外的循环
	//比过了位置对了但是又再比
	//就是和冒泡的做法一样

//就是两两交换，向前换（向序号小的换）实现“在前面插入一个数”

	public static void sort2(Comparable[] a){
		for(int i=1;i < a.length; i++){
			for(int j = i-1;j >=0;j--){
/*
				if(less(a[j+1],a[j])){
					swap(a,j+1,j);
				}
				if(!less(a[j+1],a[j])){
					break;
				}//不做额外的循环
				上面这样错了
				交换完再比较 肯定满足条件
				之后退出循环
				也就是只交换了一次
				break部分放在前面就行了
*/
				if(!less(a[j+1],a[j])){
					break;
				}//不做额外的循环
				if(less(a[j+1],a[j])){
					swap(a,j+1,j);
				}

			}
		}
	}

	public static void sort3(Comparable[] a){//算法第四版的做法 不使用break
						//来不进行而外的循环
		for(int i=1;i < a.length; i++){
			for(int j = i-1;j >=0 && less(a[j+1],a[j]);j--){
				swap(a,j+1,j);
			}
		}
	}

	private static class Employee implements Comparable<Employee>{
		int salary;
		Employee(int salary){
			this.salary = salary;
		}
		public int compareTo(Employee e){
			if(this.salary < e.salary)
				return -1;
			if(this.salary == e.salary)
				return 0;
				return 1;
		}
		public String toString(){
			return salary+"";
		}
	}
	
	public static void main(String... args){
		String[] a = {"aaa","aab","aba","abb","bbb","bab","bba","baa"};
		//char[] b = {'a','z','y','c','d','f'};
		int[] c = {3,5,2,7,4,9,2,5,66,8,12,25};
		Integer[] d = {1,6,3,5,2,7,4,9,2,5,66,8,12,25};
		Integer[] e = {1,6,3,5,2,7,4,9,2,5,66,8,12,25};
		Integer[] f = {1,6,3,5,2,7,4,9,2,5,66,8,12,25};
		
		sort(a);
		show(a);//可以用于字符串比较

		System.out.println();
		//sort(b);
		//show(b);
		sort(d);
		show(d);

		System.out.println();
		sort2(e);
		show(e);

		System.out.println();
		sort3(f);
		show(f);

		Employee[] g = {new Employee(2),new Employee(5),new Employee(7),new Employee(1),new Employee(10),new Employee(4),new Employee(6)};
		System.out.println();
		sort3(g);
		show(g);

	}
}