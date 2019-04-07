import java.util.Arrays;

public class DFS {
    private static int[][] graph;
    private static int[][] next = {{1,0},{-1,0},{0,1},{0,-1}};//用多个一维数组表示二维数组
    //用来表示 上下左右走一步 坐标的变化情况
    //上下左右的方向试

    //private static int[][] next = {{0,1},{1,0},{0,-1},{-1,0}};//用多个一维数组表示二维数组
    //右下左右的方向试

    private static int[][] tags;

    //这里连同底下的//1 可以保留
    //1private static Stack stack = new Stack();
    //1private static Stack minStack = new Stack();

    private static Stack2 stack = new Stack2();
    private static Stack2 minStack = new Stack2();

    private static int minSteps = Integer.MAX_VALUE;
    private static int steps;

    private static class Stack{//使用数组表示栈
        private int[][] array = new int[50][2];
        private int top = 0;
    }

    private static class Stack2{
        private Node[] nodes = new Node[50];
        private int top = 0;

        private static class Node{
            int x;
            int y;
        }
    }

    private static int[][] copy(int[][] b){
        int[][] a = new int[50][2];
        for(int i=0;i<50;i++){
            for (int j=0;j<2;j++){
                a[i][j] = b[i][j];
            }
        }
        return a;
    }

    public static void dfs(int x,int y){

        int nx;
        int ny;

        if(x == 4 && y == 3){
            //System.out.println("test");
             if(steps < minSteps){
                minSteps = steps;

                //minStack = stack;
                //1 minStack.top = stack.top;
                //minStack.array = Arrays.copyOf(stack.array,stack.array.length);//对二维数组使用这个克隆方法 是有问题的 会影响到最后的打印
                 //1 minStack.array=copy(stack.array);

                 minStack.top = stack.top;
                 minStack.nodes = Arrays.copyOf(stack.nodes,stack.nodes.length);
                //把stack拷贝给minStack

                 //for(int i = 1;i <= minStack.top;i++){
                     //System.out.println(minStack.array[i][0]+" "+minStack.array[i][1]);
                 //}
                 //System.out.println("h");
             }

            return;
        }

        for(int i=0;i<4;i++){//循环4个方向
            nx = x+next[i][0];
            ny = y+next[i][1];
            //System.out.println(nx+" "+ny);

            if(nx > 0 && nx < 6 && ny >0 && ny < 5 && graph[nx][ny]!=1 && tags[nx][ny]!=1){
                tags[nx][ny] = 1;
                steps++;

                stack.top++;
                //1 stack.array[stack.top][0] = nx;
                //1 stack.array[stack.top][1] = ny;

                stack.nodes[stack.top] = new Stack2.Node();
                stack.nodes[stack.top].x = nx;
                stack.nodes[stack.top].y = ny;

                dfs(nx,ny);
                tags[nx][ny] = 0;
                steps--;
                stack.top--;
            }
        }
    }

    public static void main(String[] args){
        graph = new int[6][5];
        graph[1][3] = 1;
        graph[3][3] = 1;
        graph[4][2] = 1;
        graph[5][4] = 1;

        tags = new int[6][5];

        tags[1][1] = 1;
        dfs(1,1);
        System.out.println(minSteps);
        //System.out.println(minStack.top);

        for(int i = 1;i <= minStack.top;i++){
            //1 System.out.println(minStack.array[i][0]+" "+minStack.array[i][1]);

            System.out.println(minStack.nodes[i].x+" "+minStack.nodes[i].y);
        }
    }
}
//深度优先搜索的特征
//递归
//递归的终止条件 到达目标 或者 目标的下一个

//循环中间某一步的各种可能性
//标记使用过
//递归
//重新标记为未使用过

/*
使用深度优先搜索 列出所有情况 或者是找最值(最短路径)
在中间的每一步打印结果
或者把中间每一步的结果方法一个放到一个数组中 或者放到一个栈中 或者一个字符串中(或者StringBuilder)

知道最终的结果有多少个中间结果组成 就用数组
不知道最终的结果由多少个中间结果组成的 就用栈或者字符串

 */
