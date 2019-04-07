public class BFS {
    private static Queue queue;

    private static int[][] graph;
    private static int[][] next = {{1,0},{-1,0},{0,1},{0,-1}};
    //private static int[][] next = {{0,1},{1,0},{0,-1},{-1,0}};
    private static int[][] tags;
    private static int flag;

    private static class Queue{
        private Node[] nodes;
        private int head = 0;
        private int tail = 0;
        private static class Node{
            private int x;
            private int y;
            private int father;//记录每一个点的父亲是谁 也就是记录每个点又哪个点扩展出来的 最后由目标点一直往上找找父亲 逆推回去 找到起始点
            //可能需要 使用栈 反向顺序

            private int steps;//从起始点到该点的步数
        }
    }

    private static class Stack{
        private Queue.Node[] nodes;
        private int top = 0;
    }

    public static void bfs(){
        while (queue.head < queue.tail){
            //System.out.println(queue.head +" "+ queue.tail);
            for (int i = 0;i < 4;i++) {
                Queue.Node node = queue.nodes[queue.head];
                Queue.Node newNode = new Queue.Node();
                int nx = node.x+next[i][0];
                int ny = node.y+next[i][1];

                if (nx > 0 && nx < 6 && ny >0 && ny < 5 && graph[nx][ny]!=1 && tags[nx][ny]!=1) {
                    tags[nx][ny]=1;

                    newNode.x = nx;
                    newNode.y = ny;
                    newNode.father = queue.head;//由head扩展出来的 父亲是head
                    newNode.steps = node.steps + 1;//比父亲的步数多一

                    queue.nodes[queue.tail] = newNode;
                    queue.tail++;

                }

                if(nx==4 && ny==3){
                    flag = 1;
                    //System.out.println(queue.tail-1);
                    break;
                }
            }
            if(flag==1){
                break;
            }
            queue.head++;
        }
        if(flag==1){
            Stack stack = new Stack();
            stack.nodes = new Queue.Node[50];

            Queue.Node node = queue.nodes[queue.tail-1];;
            while (node.father!=-1){
                stack.top++;
                //System.out.println(node.x+" "+node.y);
                stack.nodes[stack.top] = node;
                node = queue.nodes[node.father];
            }
            System.out.println(queue.nodes[queue.tail-1].steps);
            while (stack.top >0){
                node = stack.nodes[stack.top];
                stack.top--;
                System.out.println(node.x+" "+node.y);
            }
        }
    }
    /*
    循环队列元素
    循环体中会有新元素加入队列(入队)
    使用标记数组标记加入到队列的元素
    只有 标记用过
    没有 标记没用过

    循环体中有break终止循环
    两个break终止两层循环
     */


    public static void main(String[] args){
        graph = new int[6][5];
        graph[1][3] = 1;
        graph[3][3] = 1;
        graph[4][2] = 1;
        graph[5][4] = 1;

        tags = new int[6][5];

        tags[1][1] = 1;

        Queue.Node node = new Queue.Node();
        queue = new Queue();
        queue.nodes = new Queue.Node[50];
        queue.nodes[queue.tail] = node;
        queue.tail++;
        node.x = 1;
        node.y = 1;
        node.father = -1;

        bfs();
    }
}
//啊哈算法 82页 107页 121页
//啊哈算法中还有关于着色(染色）的内容 着色法
//使用深度优先搜索 或者 广度优先搜索 都可以
//就是标记用过之后 不会 再重新标记为没用过
//广度优先搜索 本来 就不会 再重新标记为没用过
