class dddd{
	public static void main(String[] args) {
			int[] i = {1234,2134};//n-1个数全排列
			int[] d = new int[4];
			int m = 1;
				for(int a =0 ;a < i.length;a++){//i中的每个数
					while(i[a]/10!=0){
						d[d.length-m] = i[a]%10;
						System.out.println(d[d.length-m]);
						i[a] = i[a]/10;
						m++;
					}
					d[d.length-m]=i[a];
					System.out.println(d[d.length-m]);
					m=1;
				}

		
	}
}