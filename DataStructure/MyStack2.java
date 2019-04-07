class MyStack2{

//下面的tail应该换成top 表示栈顶
//注释里的栈的末尾 应该换成 栈顶

	static int tail=0;
	static MyLinkedList l;
	
	public static void main(String... args) throws Exception{
		l=MyLinkedList2.ForOut();//这个方法就会调用nextInt()用来确定要创建包含几个元素的链表 也就是包含几个元素的栈
		
			MyLinkedList lhead=l;
			while(lhead!=null){
				lhead = lhead.l;
				tail++;
			}
			tail--;//让tail指向栈的最后一个元素，不减的话tail对应的是null

		//栈末尾添加一个元素 值是8
		System.out.println("在末尾加一个数8");
		int a =8;
		MyLinkedList n  = new MyLinkedList();
		n.a=a;
		n.l=null;
		
		//还需要原来的末尾元素指向这个新的末尾元素
		lhead=l;
		int head=0;
		MyLinkedList tmp;
		while(lhead!=null){
			tmp=lhead.l;
			if(head==tail){
				break;//加这个是为了能用上面的lhead 而不是用下面的lhead
			}
			lhead = tmp;
			head++;
		}
		lhead.l=n;
		tail++;

/*		//栈末尾删除一个元素
		lhead=l;
		head=0;
		MyLinkedList tmp;
		while(lhead!=null){
			tmp=lhead.l;
			if(head==tail){
				break;//加一个是为了能用上面的lhead 而不是用下面的lhead
			}
			lhead = tmp;
		}
		lhead.l=...;//这里不行 要让链表末尾元素变成null lhead指向null (lhead=null) 但是原来和lhead指向同一个对象的引用变量 还是指着那个对象
				//或者说倒数第二个元素的下一个“元素”是指null （lhead.l=null 这里lhead代表倒数第二个元素）

		tail++;
*/
//上面的被注释掉了
		System.out.println("之后再末尾删除三个数");
		int tailtmp=tail;
		tailtmp = repeat(tailtmp);
		tailtmp = repeat(tailtmp);
		tailtmp = repeat(tailtmp);//总共末尾减少三个数

			lhead=l;
			while(lhead!=null){
				System.out.print(lhead.a+" ");
				lhead = lhead.l;
			}		
	}

	static int repeat(int tail){//运行一次 栈末尾减少一个元素
		//栈末尾删除一个元素
		MyLinkedList lhead=l;
		int head=0;
		MyLinkedList tmp;
		while(lhead!=null){
			tmp=lhead.l;
			if(head==tail-1){
				break;//加一个是为了能用上面的lhead 而不是用下面的lhead
			}
			lhead = tmp;
			head++;
		}
		lhead.l=null;
		tail--;
	
		return tail;
	}
}


		



			
			