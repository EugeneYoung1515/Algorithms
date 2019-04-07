public interface MyStack3<T> {
    void push(T t);
    T pop();
}

//是要序号自加之后 再赋元素
//还是要赋元素之后 再序号自加

class ArrayStack<T> implements MyStack3<T>{
    private T[] array;
    private int m;

    @SuppressWarnings("unchecked")
    public ArrayStack(int n) {
        array = (T[])new Object[n];
    }

    @Override
    public void push(T t) {
        if(m < array.length) {
            array[m] = t;
            m++;
        }else{
            throw new RuntimeException("实现栈的数组长度不够");
        }
    }

    @Override
    public T pop() {
        T tmp=null;
        if(m>0) {
            m--;
            tmp = array[m];
            array[m] = null;
        }//else{
            //throw new RuntimeException("栈中已经没有元素了，不能再弹出队列");
        //}
        return tmp;
    }

    public static void main(String[] args){
        MyStack3<Integer> stack = new ArrayStack<>(4);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        //stack.push(5);//如果是运行时异常 下面那一句就 不会执行了
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(1);
        System.out.println(stack.pop());
    }
}

class MyLinkedListStack<T> implements MyStack3<T>{
    private Node top;
    private Node bottom;

    private class Node{
        T obj;
        Node next;
    }

    @Override
    public void push(T t) {
        if(bottom==null){
            bottom = new Node();
            bottom.obj = t;
            top = bottom;
        }else{
            top.next = new Node();
            top.next.obj = t;
            top = top.next;
        }
    }//o(1)

    @Override
    public T pop() {//这个实现不好 时间复杂度o(n)
        //要让使用链表实现的队列和栈
        //增删都是o(1)

        if(bottom == top){
            Node tmp = top;
            bottom=null;
            top = null;
            return tmp == null? null:tmp.obj;
        }
        for(Node tmp = bottom;tmp != top;tmp = tmp.next){
            if(tmp.next == top){
                Node tmp2 = tmp.next;
                tmp.next = null;
                top = tmp;
                return tmp2.obj;
            }
        }
        return null;
    }

    public static void main(String[] args){
        MyStack3<Integer> stack = new MyLinkedListStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        //stack.push(5);//如果是运行时异常 下面那一句就 不会执行了
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}
class MyLinkedListStack2<T> implements MyStack3<T>{
    private Node top;

    private class Node{
        private T obj;
        private Node previous;//栈顶之下的元素
    }

    @Override
    public void push(T t) {
        if(top==null){
            top = new Node();
            top.obj=t;
        }else{
            Node tmp = new Node();
            tmp.obj=t;
            tmp.previous = top;
            top = tmp;
        }
    }

    @Override
    public T pop() {//这个实现 就是o(1)
        //System.out.println("--"+top.obj);
        Node tmp = null;
        if(top != null) {
            tmp = top;
            top = top.previous;
        }
        return tmp == null ? null : tmp.obj;
    }
    public static void main(String[] args){
        MyStack3<Integer> stack = new MyLinkedListStack2<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        //stack.push(5);//如果是运行时异常 下面那一句就 不会执行了
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
    }
}//还差一个可以使用动态调整大小的数组实现的栈

