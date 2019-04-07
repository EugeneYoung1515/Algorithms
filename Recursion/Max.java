class Max{
	public static void main(String... args){
		int[] a= {1,2,9,3,34,55,3,44,5};
		int b = max(a);
		System.out.println(b);
		b=max2(a);
		System.out.println(b);
		b=max3(a);
		System.out.println(b);

	}
	static int max(int[] a){
	if(a.length>1){
		int b=a[0];
		a=Sum.trim(a);
		return b<max(a)?max(a):b;//三元操作符 1.关系符 2.？ 3.:
		}
	if(a.length<=1){
		return a[0];
	}
	return 0;//这里会说缺少返回语句
	}

	static int max2(int[] a){
	if(a.length>1){
		int b=a[0];
		a=Sum.trim(a);
		if(b<max2(a))return max2(a);
		 else return b;
		}
	if(a.length<=1){
		return a[0];
	}
	return 0;//这里会说缺少返回语句
	}

	static int max3(int[] a){
	if(a.length>1){
		int b=a[0];
		a=Sum.trim(a);
		int max = b;
		if(b<max3(a)){
			max = max(a);
		}
		return max;//这个return要放if外,不然这一块有时候不输出最大值
		}
	if(a.length<=1){
		return a[0];
	}
	return 0;//这里会说缺少返回语句
	}
}