package prep;

import java.util.*;

public class HuffmanTextBook {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        Map<Character, Integer> freq = new HashMap<>();

        String test = in.next();
        int length = test.length();
        for(int i = 0; i < length; ++i){
            freq.put(test.charAt(i),freq.getOrDefault(test.charAt(i),0)+1);
        }

//        freq.put('a', 5);
//        freq.put('b', 9);
//        freq.put('c', 12);
//        freq.put('d', 13);
//        freq.put('e', 16);
//        freq.put('f', 45);

        Node root = buildHuffmanTree(freq);

        Map<Character, String> codes = new HashMap<>();
        generateCode(root, "", codes);

        System.out.println("Huffman Codes:");
        for (var e : codes.entrySet()) {
            System.out.println(e.getKey() + ": " + e.getValue());
        }
    }

    static class Node {
        char symbol;
        int freq;
        Node left, right;

        Node(char symbol, int freq) {
            this.symbol = symbol;
            this.freq = freq;
            this.left = this.right = null;
        }
        Node(int freq, Node left, Node right){
            this.symbol = '\0';
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }
    static class NodeCompare implements Comparator<Node>{
        @Override
        public int compare(Node a, Node b) {
            return a.freq - b.freq;
        }
    }

    public static Node buildHuffmanTree(Map<Character, Integer> freq){
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeCompare());

        for(Map.Entry<Character, Integer> entry : freq.entrySet()){
            Node p = new Node(entry.getKey(), entry.getValue());
            pq.add(p);
        }
        int n = pq.size();

        for(int i = 1; i <= n - 1; i++){
            Node p = pq.poll();
            Node q = pq.poll();
            Node r = new Node(p.freq + q.freq,p,q);
            pq.add(r);
        }
        return pq.poll();
    }
    //dfs
    public static void generateCode(Node root, String code, Map<Character, String> codes){
        if(root == null) return;
        if(root.left == null && root.right == null){
            codes.put(root.symbol,code);
            return;
        }
        generateCode(root.left, code+"0",codes);
        generateCode(root.right, code+"1",codes);
    }
}
