package prep;

import java.util.LinkedList;
import java.util.Queue;

public class DFSandBFS {
    private class Node{
        int val;
        Node left;
        Node right;
        public Node(){}
        public Node(int val){ this.val = val;}
        public Node(int val, Node left, Node right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static void dfs(Node root){
        if(root == null) return;
        System.out.println(root.val);
        dfs(root.left);
        dfs(root.right);
    }

    public static void bfs(Node root){
        if(root == null) return;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.println(node.val);

            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }
}
