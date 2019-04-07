//图解算法里的快排
//感觉有问题

//可能有问题
//但是作为递归的练习是可以的

//只写从小到大排

class QuickSort2{
	static int[] a = {0,110,3,457,1110,6,5,100,2,1,2000,0,8,9,10};

	public static void main(String... args){
		int[] b = quicksort(a);
		for(int c:b){
			System.out.print(c+ " ");
		}
	}

	static int[] quicksort(int[] a){
		int[] b = new int[a.length];//存比基准值大的数
		int[] c = new int[a.length];//存比基准值小的数
		int[] e = new int[a.length];//存基准值还有和基准值相等的数
		int i=0;//记录上面数组b的元素个数
		int j=0;//记录上面数组c的元素个数
		int m=0;//记录上面数组e的元素个数
		
		if(a.length==0)return a;//java里数组长度可以为0
		if(a.length==1)return a;
		if(a.length==2){
			if(a[0]<a[1])return a;
			if(a[0]>a[1]){

				int tmp;
				tmp = a[1];
				a[1] = a[0];
				a[0] = tmp;
				
				return a;
			}
		}
		if(a.length>2){
		for(int d:a){
			if(d > a[0]){
				b[i]=d;
				i++;
			}
			if(d < a[0]){
				c[j]=d;
				j++;
			}
			if(d == a[0]){
				e[j]=d;
				m++;
			}
		}
		b=trueLength(b,i);
		c=trueLength(c,j);
		e=trueLength(e,m);
		return arrayAppend(quicksort(c),e,quicksort(b));
		}
		return new int[0];//自己写的递归都会被编译器提醒这里要返回语句
	}

	static int[] arrayAppend(int[] a,int[] b,int[] c){
		int e = a.length+b.length+c.length;
		int[] d = new int[e];
		int g = 0;
		for(int f:a){
			d[g] = f;
			g++;
		}
		for(int f:b){
			d[g] = f;
			g++;
		}
		for(int f:c){
			d[g] = f;
			g++;
		}
		return d;
	}

	static int[] trueLength(int[] a,int b){
		int[] c = new int[b];
		for(int i=0;i<b;i++){
			c[i] = a[i];
		}
		return c;
	}
}