class BinarySearch{
	static boolean whichOrder(int[] array, int target){
		if(array[array.length-1]>array[0] && (array[0] > target|array[array.length-1]<target)){
			return false;
		}
		if(array[array.length-1]<array[0] && (array[0] <target|array[array.length-1]>target)){
			return false;
		}
		return true;
	}
	static int binarySearch(int[] array, int target){
		if(whichOrder(array, target)){

				int low = 0;
				int high = array.length-1;
				boolean notGet = true;
				int mid = (low+high)/2;

				if(array[array.length-1]==target){
					return array.length-1;
				}
				if(array[0] == target){
					return 0;
				}

			if(array[array.length-1]>array[0]){
				while(notGet){
					if(array[mid]>target){
						high = mid;这里换成mid-1也行
					}
					if(array[mid]==target){
						return mid;
					}
					if(array[mid]<target){
						low = mid+1;这里换成mid也行
					}
					mid = (low+high)/2;
				}
			}
			if(array[array.length-1]<array[0]){
				while(notGet){
					if(array[mid]>target){
						low = mid;
					}
					if(array[mid]==target){
						return mid;
					}
					if(array[mid]<target){
						high = mid+1;
					}
					mid = (low+high)/2;
				}
			}
		}
		return -1;
	}//技巧：使用return 退出循环 使用return 使后面的语句不执行
	public static void main(String[] args){
		int[] a = {4,3,2,1};
		int target =3 ;
		int num = BinarySearch.binarySearch(a,target);
		System.out.println(num+1);//确定是第几个数字 第一个数字就打印1
		//这个程序至少有1个问题，数组中出现重复的数，只会返回第一个
	}
}

			

					
			
 
