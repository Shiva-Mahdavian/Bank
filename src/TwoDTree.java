import java.util.LinkedList;
import java.util.Queue;

public class TwoDTree<T extends Coordinate> {

    public KDNode<T> root;

    public KDNode<T> getRoot() {
        return root;
    }

    public TwoDTree() {
        this.root = null;
    }

    public void insert(T node){
        if (this.root == null)
            this.root = new KDNode<>(node);
        else
            insert(this.root, node, false);
    }

    public void insert (KDNode root, T node , boolean horizontal) {
        if (Coordinate.equal(root.getValue(), node)){
            System.err.println("The inserted node, already exists! " + node.toString());
            return;
        }
        int cmpr = Coordinate.compare(root.getValue(), node, horizontal);
        if (cmpr == 1) {
            if (root.getLeft() == null)
                root.setLeft(new KDNode(node));
            else
                insert(root.getLeft(),node,!horizontal);
        } else {
            if (root.getRight() == null)
                root.setRight(new KDNode(node));
            else
                insert(root.getRight(), node, !horizontal);
        }
    }

    public void bfsTest(KDNode root) {
        Queue<KDNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            KDNode node = queue.poll();
            if (node.getLeft() != null)
                queue.add(node.getLeft());
            if (node.getRight() != null)
                queue.add(node.getRight());
            System.out.println(node.getValue());
        }
    }

//TODO do i need this one below?
//    public KDNode findMin(boolean horizontal) {
//        return findMin(root, horizontal);
//    }

    public KDNode findMin(KDNode node, boolean horizontal, boolean isCurrentHorizontal){
        if (node == null)
            return null;
        if (isCurrentHorizontal == horizontal){
            if (node.getLeft() == null)
                return node;
            return findMin(node.getLeft(), horizontal, !isCurrentHorizontal);
        } else {
            return KDNode.min3(findMin(node.getLeft(), horizontal, !isCurrentHorizontal), findMin(node.getRight(), horizontal, !isCurrentHorizontal), node, horizontal);
        }
    }

    public KDNode delete(T node) {
        return delete(root, node, false);
    }

    public KDNode delete(KDNode<T> currentNode, T coordinate, boolean isCurrentHorizontal){
        if (currentNode == null) {
            System.err.println("cannot find the wanted node to delete " + coordinate.toString()); //TODO handle this or let the outer function handle it?
            return null;
        }
        if (Coordinate.equal(currentNode.getValue(), coordinate)) {
//            T result = currentNode.getValue();
            if (currentNode.getRight() != null) {
                currentNode.setValue((T) findMin(currentNode.getRight(), isCurrentHorizontal, !isCurrentHorizontal).getValue());
                currentNode.setRight(delete(currentNode.getRight(), currentNode.getValue(), !isCurrentHorizontal));
            } else if (currentNode.getLeft() != null) {
                currentNode.setValue((T) findMin(currentNode.getLeft(), isCurrentHorizontal, !isCurrentHorizontal).getValue());
                currentNode.setRight(delete(currentNode.getLeft(), currentNode.getValue(), !isCurrentHorizontal));
                currentNode.setLeft(null);
            }
            else {
                currentNode = null;
            }
//            return result;
        }
        else {
            if (Coordinate.compare(currentNode.getValue(), coordinate, isCurrentHorizontal) == 1)
                currentNode.setLeft(delete(currentNode.getLeft(), coordinate, !isCurrentHorizontal));
            else
                currentNode.setRight(delete(currentNode.getRight(), coordinate, !isCurrentHorizontal));
        }
        return currentNode;
    }

}



class KDNode<T extends  Coordinate> {
    private T value;
    private KDNode right;
    private KDNode left;

    KDNode(T value ) {
        this.value = value;
        this.right = null;
        this.left = null;
    }

    public KDNode getLeft() {
        return left;
    }

    public void setLeft(KDNode left) {
        this.left = left;
    }

    public KDNode getRight() {
        return right;
    }

    public void setRight(KDNode right) {
        this.right = right;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }


    public static KDNode min(KDNode a, KDNode b, boolean horizontal){
        if (a == null && b == null)
            return null;
        if (a == null)
            return b;
        if (b == null)
            return a;

        if (Coordinate.compare(a.getValue(), b.getValue(), horizontal) == 1)
            return a;
        return b;
    }

    public static KDNode min3(KDNode a, KDNode b, KDNode c, boolean horizontal){
        return min(min(a, b, horizontal),c , horizontal);
    }


    public static void main(String[] args) {
        TwoDTree<Branch> twoDTree = new TwoDTree<>();
        System.out.println("want to add");
        twoDTree.insert(new Branch(35,60));
        twoDTree.insert(new Branch(60,80));
        twoDTree.insert(new Branch(80,40));
        twoDTree.insert(new Branch(90,60));
        twoDTree.insert(new Branch(50,30));
        twoDTree.insert(new Branch(70,25));
        twoDTree.insert(new Branch(60,10));
        twoDTree.insert(new Branch(20,45));
        twoDTree.insert(new Branch(10,35));
        twoDTree.insert(new Branch(20,20));

        System.out.println("finished" );

        System.out.println("root " + twoDTree.getRoot().getValue());
        System.out.println();

        twoDTree.bfsTest(twoDTree.getRoot());
        System.out.println();
        twoDTree.delete(twoDTree.getRoot(), new Branch(35,60), false);
        System.out.println();
        twoDTree.bfsTest(twoDTree.getRoot());


    }
}
