//并查集

public class WeightedQuickUnionUF {//路径压缩的的加权quick-union算法
    private int[] id;//索引是一个点 值是父节点
    private int[] sz;//由节点构成的树中的 节点数目
    private int count;//树的数目

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        for(int i =0;i < N;i++){
            id[i] = i;
        }
        sz = new int[N];
        for (int i=0;i< N;i++){
            sz[i]=1;
        }
    }

    public int find(int p){//顺着父亲找到根节点 并把一路上的遇到的节点的父亲改成根节点
        //使用递归

        if(id[p] == p){
            return p;
        }
        id[p] = find(id[p]);
        return id[p];
        //还能再优化吗
        //第一次递归后 把沿路节点的父节点都改为根节点
        //之后递归 每个节节的父节点都是根结点

        //上面的递归换成循环怎么写
        //用两个循环？
        //一个循环找到根节点
        //另一个循环 让沿路节点的父节点都变成根结点

    }//啊哈算法也有这一个内容

    public void union(int p,int q){
        int i = find(p);
        int j = find(q);
        if(i == j)return;
        if(sz[i]>sz[j]){
            id[j]=i;
            sz[i]+=sz[j];
        }else{
            id[i]=j;
            sz[j]+=sz[i];
        }
        count--;
    }

    public boolean isConnected(int p,int q){
        return find(p)==find(q);
    }

    public static void main(String[] args){
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(10);
        uf.union(1,9);
        uf.union(1,8);
        uf.union(9,4);
        uf.union(3,8);

        System.out.println(uf.isConnected(1,3));

        System.out.println(uf.isConnected(1,9));
        System.out.println(uf.sz[2]);
        System.out.println(uf.count);
    }
}
//上面的内容换成链表怎么做 模仿平衡二叉树 或红黑树 使用链表 使用递归？

class WeightedQuickUnionUF2 {
    private Node[] nodes;//用这个位置住 Node的引用 防止被回收 同时能用用数组下标对应到Node
    private int count;

    private class Node{
        private Node parentNode = this;
        private int Num;
        private int sz=1;
        //private int count;

        public Node(int num) {
            Num = num;
        }
    }

    public WeightedQuickUnionUF2(int N) {
        count = N;
        nodes = new Node[N];
        for(int i=0;i < N;i++){
            nodes[i] = new Node(i);
        }
    }

    public Node find(Node p){
        if(p.parentNode == p){
            return p;
        }
        p.parentNode = find(p.parentNode);//更新父节点
        p.sz = p.parentNode.sz+1;
        return p.parentNode;//返回父节点
    }

    public void union(Node p,Node q){
        Node i = find(p);
        Node j = find(q);
        if(i.parentNode == j.parentNode)return;
        if(i.sz>j.sz){
            j.parentNode=i;
            i.sz+=j.sz;
        }else{
            i.parentNode=j;
            j.sz+=i.sz;
        }
        count--;
    }

    public boolean isConnected(int p,int q){
        return find(nodes[p])==find(nodes[q]);
    }

    public static void main(String[] args){
        WeightedQuickUnionUF2 uf = new WeightedQuickUnionUF2(10);
        uf.union(uf.nodes[1],uf.nodes[9]);//能访问到私有属性是因为main方法再同一个类中
        uf.union(uf.nodes[1],uf.nodes[8]);
        uf.union(uf.nodes[9],uf.nodes[4]);
        uf.union(uf.nodes[3],uf.nodes[8]);
        System.out.println(uf.isConnected(1,9));
        System.out.println(uf.nodes[2].parentNode.sz);
        System.out.println(uf.count);
    }
}
class Main{
    public static void main(String[] args){
        WeightedQuickUnionUF2 uf = new WeightedQuickUnionUF2(10);
    }
}
