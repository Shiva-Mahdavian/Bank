import java.util.LinkedList;
import java.util.Queue;

public class TwoDTree<T extends Coordinate> {

    private KDNode<T> root;

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
            KDNode rightMin = findMin(node.getRight(), horizontal, !isCurrentHorizontal);
            KDNode leftMin = findMin(node.getLeft(), horizontal, !isCurrentHorizontal);
            return KDNode.min3(leftMin, rightMin, node, horizontal);
        }
    }

    public T find (KDNode<T> currentNode, T coordinate, boolean isCurrentHorizontal) {
        if (currentNode == null)
            return null;
        if (Coordinate.equal(coordinate, currentNode.getValue()))
            return currentNode.getValue();
        if (Coordinate.compare(currentNode.getValue(), coordinate, isCurrentHorizontal) == 1)
            return (T) find(currentNode.getLeft(), coordinate, !isCurrentHorizontal);
        return (T) find(currentNode.getRight(), coordinate, !isCurrentHorizontal);
    }


//    public KDNode delete(T node) {
//        return delete(root, node, false);
//    }

    public KDNode delete(KDNode<T> currentNode, T coordinate, boolean isCurrentHorizontal){
        if (currentNode == null) {
            System.err.println("cannot find the wanted node to delete " + coordinate.toString()); //TODO handle this or let the outer function handle it?
            return null;
        }
        if (Coordinate.equal(currentNode.getValue(), coordinate)) {
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




    public T nearestNei(KDNode cNode, Coordinate point, double bDist, T best, boolean horizontal){
        if (cNode == null)
            return null;
        if (Coordinate.dist(cNode.getValue(), point) < bDist){
            best = (T) cNode.getValue();
            bDist = Coordinate.dist(cNode.getValue(), point);
        }
        if (cNode.getRight() != null && cNode.getLeft() != null) {
            if (Coordinate.isRightCloser(point, cNode.getValue(), horizontal)){
                best = nearestNei(cNode.getRight(), point, bDist, best, !horizontal);
                bDist = Coordinate.dist(cNode.getValue(), point);
                double dist = Coordinate.distFromLine(point,cNode.getValue(),horizontal);
                if (bDist > dist) {
                    best = nearestNei(cNode.getLeft(), point, bDist, best, !horizontal);
                }
            } else {
                best = nearestNei(cNode.getLeft(), point, bDist, best, !horizontal);
                bDist = Coordinate.dist(cNode.getValue(), point);
                double dist = Coordinate.distFromLine(point,cNode.getValue(),horizontal);
                if (bDist > dist) {
                    best = nearestNei(cNode.getRight(), point, bDist, best, !horizontal);
                }
            }
        } else if (cNode.getLeft() != null){
            best = nearestNei(cNode.getLeft(), point, bDist, best, !horizontal);
        } else if (cNode.getRight() != null){
            best = nearestNei(cNode.getRight(), point, bDist, best, !horizontal);
        }
        return best;

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
            return b;
        return a;
    }

    public static KDNode min3(KDNode a, KDNode b, KDNode c, boolean horizontal){
        return min(min(a, b, horizontal),c , horizontal);
    }


    public static void main(String[] args) {
        TwoDTree<Branch> twoDTree = new TwoDTree<>();
        System.out.println("want to add");
        twoDTree.insert(new Branch(34,90));
        twoDTree.insert(new Branch(10,75));
        twoDTree.insert(new Branch(25,10));
        twoDTree.insert(new Branch(20,50));
        twoDTree.insert(new Branch(70,80));
        twoDTree.insert(new Branch(80,40));
        twoDTree.insert(new Branch(50,90));
        twoDTree.insert(new Branch(70,30));
        twoDTree.insert(new Branch(90,60));
        twoDTree.insert(new Branch(50,25));
        twoDTree.insert(new Branch(60,10));

        System.out.println("finished" );

        System.out.println("root " + twoDTree.getRoot().getValue());
        System.out.println();
        System.out.println();
        Branch b = twoDTree.nearestNei(twoDTree.getRoot(), new Coordinate(40,50), Double.MAX_VALUE, null, false);
        System.out.println(b);
        System.out.println();



    }
}
