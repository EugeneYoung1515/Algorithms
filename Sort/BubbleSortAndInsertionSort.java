public class BubbleSort<T extends Comparable<T>>{//或者<T extends Comparable<? super T>>
    public void sort(T[] array){
        for(int i=0;i<array.length;i++){
            for(int j=i+1;j<array.length;j++){
                if(array[j].compareTo(array[i])<0){//不是冒泡排序 看这一行
                    T tmp = array[i];
                    array[i] = array[j];
                    array[j]=tmp;
                }
            }
        }
    }

    public static void main(String[] args){
        Integer[] a = {7,12,4,3,9,1,5,2,8,0,-1};
        new BubbleSort<Integer>().sort(a);
        for(Integer b:a){
            System.out.println(b);
        }
    }
}
class BubbleSort2{//看以前自己写的冒泡排序 和 啊哈算法
    public static <T extends Comparable<T>> void sort(T[] array){
        for(int i=1;i<array.length;i++){//排定n-1个数 剩下的一个数也排定了
            for(int j=0;j<array.length-1-(i-1);j++){//j从0开始 影响的是 length后面要不要减1 i还影响下面两个数的比较形式
                if(array[j+1].compareTo(array[j])<0){
                    T tmp = array[j];
                    array[j] = array[j+1];
                    array[j+1]=tmp;

                }
            }
        }
    }
    public static void main(String[] args){
        Integer[] a = {7,12,4,3,9,1,5,2,8,0,-1};
        BubbleSort2.sort(a);
        for(Integer b:a){
            System.out.println(b);
        }
    }
}
class InsertionSort{
    public static <T extends Comparable<T>> void sort(T[] array){
        for(int i=1;i<array.length;i++){//从第二个数开始才有机会 挪到 之前的位置
            int j=i;
            for(;j>=1 && array[j].compareTo(array[j-1])<0;j--) {//以前自己写的 是在第二个for循环里 用一个if break结构来终止循环
                T tmp = array[j];
                array[j] = array[j - 1];
                array[j - 1] = tmp;
            }
        }
    }
    public static void main(String[] args){
        Integer[] a = {0,7,12,4,4,3,9,1,57,23,7,5,2,8,0,-1};
        InsertionSort.sort(a);
        for(Integer b:a){
            System.out.println(b);
        }
    }
}
//基于比较的排序算法 是两两比较
//但是 是前后两个数比较 还是 一个变量的数和其他数比较 要想