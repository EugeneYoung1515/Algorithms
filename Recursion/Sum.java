class Sum{
	public static void main(String... args){
		int[] a={1,2,3};
		int b=sum(a);
		System.out.println(b);
	}
	public static int sum(int[] a){
		if(a.length>1){
			int b=a[0];
			a=trim(a);
			return b+sum(a);
		}
		if(a.length<=1){
			return a[0];
		}
		return 0;//没这句会说缺少返回语句
	}
	public static int[] trim(int[] a){
		int[] b=new int[a.length-1];
		for(int i=1;i<a.length;i++){
			b[i-1]=a[i];
		}
		return b;
	}
}