class InsertionSort{
	static int[] a = {1,2,33,99,3,5,7,3,5,1,9,10,45,32,2,8,7};

	public static void insertionSort(int[] a){
		for(int i = 1;i < a.length;i++){
			int j = i;
			for(;j >= 0;j--){//这里要大于等于0，只是大于0的话，最终第一元素位置是错的
				if(a[i]>a[j]){
					break;
				}
			}
			int tmp = a[i];
			for(int z = i;z > j+1;z--){
				a[z] = a[z-1];
			}
			 a[j+1] = tmp;//上面这招是翻编程珠玑时学到的
					//也就是是用一个临时变量
					//实现把数组里每个元素都往后移一个或者
					//把数组中每个元素都往前移一个
		}
	}
	public static void main(String[] args){
		insertionSort(a);
		for(int i:a){
			System.out.print(i+ " ");
		}
	}
}