import test.Manager;
import test.Employee;

class EmployeeTest{
	public static void main(String... args){
		System.out.println(new Employee("Jack", 67000,1995,11,12).hashCode());
		System.out.println(new Employee("Lina", 67000,1993,4,12).hashCode());
	}
}