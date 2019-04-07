//使用两个数组实现HashMap

import test.Employee;
import test.Manager;

class MyHashMap{

	static Object[] keyArray;
	static Object[] valueArray;
	
	public static void main(String[] args){
		keyArray = new Object[20];
		valueArray = new Object[20];

		Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		Double salary = carl.getSalary();
		HashMapInsert(carl,salary);
		Employee Lina = new Employee("Lina", 67000,1993,4,12);
		salary = Lina.getSalary();
		HashMapInsert(Lina,salary);

		//展示key
		for(Object a:keyArray){
			if(a!=null){
				System.out.println(a+" ");
			}
		}
		
		//展示value
		for(Object a:valueArray){
			if(a!=null){
				System.out.println((Double)a+" ");
			}
		}

		//Employee Luis = new Employee2("Luis", 20000,1999,2,7);
		//salary = Luis.getSalary();
		//HashMapInsert(Luis,salary);
		
		carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
		salary = carl.getSalary()+20000;
		HashMapInsert(carl,salary);

		//展示key
		for(Object a:keyArray){
			if(a!=null){
				System.out.println(a+" ");
			}
		}
		
		//展示value
		for(Object a:valueArray){
			if(a!=null){
				System.out.println((Double)a+" ");
			}
		}
	}
	
	static void HashMapInsert(Object key,Object value){
		int index = Math.abs(key.hashCode()%20);
		if(keyArray[index]==null){
			keyArray[index] = key;
			valueArray[index] = value;
		}else{
			System.out.println(key+"存在，下面要把它替换掉");

			keyArray[index] = key;
			valueArray[index] = value;
		}
	}
}