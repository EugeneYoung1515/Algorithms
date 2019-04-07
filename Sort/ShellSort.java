class ShellSort{

//按照算法第四版的思路
//希尔排序就是多了算递增序列h的计算 下面的递增序列要记住
//在插入排序的外面多加了一层循环 递减h的值直到1
//插入排序部分(内层循环) 递减h j=j-h
//h按照 h = 3*h+1 递增 之后按照h = h/3递减使用
//h = 3*h+1的反向 可以是 h = h／3
//(3*h+1)/3 = h+1/3 = h

//改造自己写的两种插入排序 在两种插入思路的基础上 产生两种的希尔排序

	public static int increment(int n){
		int h = 1;
		while(h > n/3){
			h = 3*h + 1;
		}
		return h;
	}
	public static void shellSort(int[] a){
		int h = increment(a.length);
		while(h >=1){
			for(int i = 1;i < a.length;i++){
				for(int j = i;j >= h;j=j-h){
					if(a[j] > a[j-h]){
						break;
					}
					if(a[j] <= a[j-h]){
						int tmp = a[j];
						a[j] = a[j-h];
						a[j-h] = tmp;
					}
				}
			}
			h = h/3;
		}
	}
	public static void shellSort2(int[] a){
		int h = increment(a.length);
		while(h >=1){
			for(int i = 1;i <a.length;i++){
				int j = i;
				for(;j >= h;j=j-h){
					if(a[j-h] < a[i]){
						break;
					}
				}
				int tmp = a[i];
				for(int z = i-h;z >= j;z = z-h){
					a[z+h] = a[z];
				}
				a[j] = tmp;
			}
			h = h/3;
		}
	}
	public static void main(String... args){
		int[] a = {3,4,2,1,66,53,4,7,8,1,9,10,12,34,27};
		shellSort(a);
		for(int i:a){
			System.out.print(i+" ");
		}

		System.out.println();
		int[] b = {3,4,2,1,66,53,4,7,8,1,9,10,12,34,27};
		shellSort2(b);
		for(int i:b){
			System.out.print(i+" ");
		}

	}
}