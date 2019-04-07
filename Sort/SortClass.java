import java.lang.Comparable;
public class SortClass {
	public static boolean less(Comparable v,Comparable w){//less表示第一个参数比第二个参数小
		return v.compareTo(w) < 0;
	}
	public static void swap(Comparable[] a,int i,int j){
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
}