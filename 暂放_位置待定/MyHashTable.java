import test.Employee;
import test.Manager;
class Employee2 extends Employee{
	public Employee2(String name, double salary, int year, int month, int day){
		     super(name, salary, year, month, day);
	}
		
	public int hashCode(){
		return 16;
	}
}

class MyHashTable{
	public static void main(String... args){
		//把哈希函数（散列函数）返回值与数组的序号建立联系
		//如果直接放到序号为哈希函数返回值的位置上 那要很大的函数
		
		double[] a = new double[21];
		Employee alice = new Employee("Alice Adams", 75000, 1987, 12, 15);
		Employee bob = new Employee("Bob Brandson", 50000, 1989, 10, 1);
		Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		Employee Jack = new Employee("Jack", 67000,1995,11,12);
		Employee Lina = new Employee("Lina", 67000,1993,4,12);
		Employee Luis = new Employee2("Luis", 20000,1999,2,7);


		a[Math.abs(alice.hashCode()/100000000)] = alice.getSalary();
		a[Math.abs(bob.hashCode()/100000000)] = bob.getSalary();
		a[Math.abs(carl.hashCode()/100000000)] = carl.getSalary();

		for(double b:a){
			System.out.print(b+" ");
		}
		
		System.out.println();
	
		//这里是哈希函数返回值除以数组长度取余 为了尽量让数组的每一个位置都有元素
		double[] c = new double[20];
		c[Math.abs(alice.hashCode()%20)] = alice.getSalary();
		
		if(Math.abs(bob.hashCode()%20)==0){
			c[Math.abs(bob.hashCode()%20)] = bob.getSalary();
		}

		c[Math.abs(carl.hashCode()%20)] = carl.getSalary();
		c[Math.abs(Jack.hashCode()%20)] = Jack.getSalary();
		c[Math.abs(Lina.hashCode()%20)] = Lina.getSalary();
		//c[Math.abs(Luis.hashCode()%20)] = Luis.getSalary();
		//取余后，carl和Luis余数一样

		//打印余数
		System.out.println(Math.abs(alice.hashCode()%20));
		System.out.println(Math.abs(bob.hashCode()%20));
		System.out.println(Math.abs(carl.hashCode()%20));
		System.out.println(Math.abs(Jack.hashCode()%20));
		System.out.println(Math.abs(Lina.hashCode()%20));
		System.out.println(Math.abs(Luis.hashCode()%20));

		for(double b:c){
			System.out.print(b+" ");
		}

		System.out.println();
		c[Math.abs(Luis.hashCode()%20)] = Luis.getSalary();
		for(double b:c){
			System.out.print(b+" ");
		}

		//上面是使用散列函数+数组
		//下边是使用散列函数+数组+链表

		//应该要放到散列表的东西 求哈希函数返回值
		//自己变成对象求哈希函数返回值 放对象的一个实例变量进去

		System.out.println();
		MyLinkedList[] d = new MyLinkedList[20];
		hashTableInsert(d,Math.abs(Lina.hashCode()%20),Lina.getSalary());
		hashTableInsert(d,Math.abs(carl.hashCode()%20),carl.getSalary());
		listElenments(d);

		hashTableInsert(d,Math.abs(Luis.hashCode()%20),Luis.getSalary());
		System.out.println();
		listElenments(d);

		
		//把对象放到哈希表里去
		//而不是像上面一样 把对象的实例变量放进去
		System.out.println();
		MyLinkedListObject[] d2 = new MyLinkedListObject[20];
		hashTableInsert(d2,Math.abs(Lina.hashCode()%20),Lina);
		hashTableInsert(d2,Math.abs(carl.hashCode()%20),carl);
		listElenments(d2);

		hashTableInsert(d2,Math.abs(Luis.hashCode()%20),Luis);
		System.out.println();
		listElenments(d2);

	}
	public static void hashTableInsert(MyLinkedList[] d, int b,double c){
		if(d[b]==null){
			d[b] = new MyLinkedList();
			d[b].a =c;
		}
		if(d[b]!=null){
			MyLinkedList head = d[b];
			while(head != null){
				MyLinkedList tmp = head.l;
				if(tmp == null){
					break;
				}
				head=tmp;
			}
			head.l = new MyLinkedList();
			head.l.a = c;
		}	
	}

	//上面的方法的重载
	//实际上只是把参数类型换成了Object
	public static void hashTableInsert(MyLinkedListObject[] d, int b,Object c){
		if(d[b]==null){
			d[b] = new MyLinkedListObject();
			d[b].a =c;
		}
		if(d[b]!=null){
			MyLinkedListObject head = d[b];
			while(head != null){
				MyLinkedListObject tmp = head.l;
				if(tmp == null){
					break;
				}
				head=tmp;
			}
			head.l = new MyLinkedListObject();
			head.l.a = c;
		}	
	}
	public static void listElenments(MyLinkedList[] d){
	    for(MyLinkedList a:d){
		if(a!=null){
			System.out.print(a.a+" ");
				if(a.l==null){}
				if(a.l!=null){
					MyLinkedList head = a.l;
					while(head != null){
						System.out.print(head.a+" ");
						MyLinkedList tmp = head.l;
						head=tmp;
					}
				}
		}
		if(a==null){
			System.out.print(0+" ");
		}
	    }
	}

	//上面的方法的重载
	public static void listElenments(MyLinkedListObject[] d){
	    for(MyLinkedListObject a:d){
		if(a!=null){
			System.out.print(a.a+" ");
				if(a.l==null){}
				if(a.l!=null){
					MyLinkedListObject head = a.l;
					while(head != null){
						System.out.print(head.a+" ");
						MyLinkedListObject tmp = head.l;
						head=tmp;
					}
				}
		}
		if(a==null){
			System.out.print(0+" ");
		}
	    }
	}	
	
}
//使用两个数组实现map
//一个放key
//另一个放value
		
		