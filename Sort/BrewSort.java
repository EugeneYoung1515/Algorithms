class BrewSort{
//桶排序

	public static void main(String[] args){
		int[] num = new int[1000];
		for(int a:num)a=0;
			for(int a = 0;a < args.length; a++){
				int b = Integer.parseInt(args[a]);
				num[b]++;
			}
		for(int a = 0; a<1000; a++){
			for(int i = 0; i < num[a]; i++)System.out.println(a);
			//num[a]中a表示要排序的数，num[a]的值表示a出现的次数
		}
		//System.out.println(num[0]);
		
	}
}
