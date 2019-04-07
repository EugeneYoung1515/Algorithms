public class BinarySearch {//算法第四版上的二分 凭印象写出来的
    public static int search(int[] a,int target){
        int lo = 0;
        int hi = a.length-1;
        int mid;
        while(lo <= hi){
            mid = lo+(hi-lo)/2;
            if(target>a[mid]){
                lo = mid+1;
            }else if(target < a[mid]){
                hi = mid-1;
            }else{
                return mid;
            }
        }
        return -1;
    }
    public static int search2(int[] a,int target){
        return search2(a,0,a.length-1,target);
    }
    public static int search2(int[] a, int lo,int hi,int target){
        if(lo >hi){
            return -1;
        }
        int mid = lo+(hi-lo)/2;
        if(target>a[mid]){
            lo = mid+1;
            return search2(a,lo,hi,target);//注意return 后面才递归
        }else if(target < a[mid]){
            hi = mid-1;
            return search2(a,lo,hi,target);
        }else{
            return mid;
        }

    }
    public static void main(String[] args){
        int[] a = {1,4,5,7,10,11,23,45,46,50,60,100};
        System.out.println(search(a,7));
        System.out.println(search2(a,7));
    }
}
/*
二分查找有很多变式
如 找到第一个大于什么的数 。。。

自己写的二分
比最小数小 直接没找到 返回-1
比最大数大 直接没找到 返回-1
等于最大数 返回最大数的位置
等于最小数 返回最小数的位置
这样保证使用二分查找一定能找到

循环的条件 进行递归的条件
子数组左边界小于等于有边界
或者是
找到了数

自己肯定有一个地方写的不好
mid = (lo+hi)/2 这里可能会溢出
换成mid =lo+(hi-lo)/2
 */

//知乎上也有讨论
