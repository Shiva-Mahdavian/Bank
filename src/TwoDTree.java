public class TwoDTree<T extends Coordinate> {

    private KDNode<T> root;

    public TwoDTree() {
        this.root = null;
    }

    public void insert(T node){
        insert(this.root, node, false);
    }

    public void insert (KDNode root, T node , boolean horizontal) {
        if (root == null)
            root = new KDNode<>(node, horizontal);
        else  {
            int cmpr = Coordinate.compare(root.getValue(), node, horizontal);
            if (cmpr == 1) {
                insert(root.getLeft(),node,!horizontal);
            } else if (cmpr == -1){
                insert(root.getRight(), node, !horizontal);
            }
            else if (Coordinate.equal(root.getValue(), node)){
                System.err.println("The inserted node, already exists! ");
            } else {
                insert(root.getRight(),node,!horizontal);
            }
        }
    }

//    public void delete(T node) {
//        delete(root, node, false);
//    }
//
//    public void delete(KDNode<T> root, T node, boolean horizontal){
//        if (root == null) {
//            System.err.println("cannot find the wanted node to delete");
//            return;
//        }
//        if (Coordinate.equal(root.getValue(), node)) {
//
//        }
//    }

}

class KDNode<T extends  Coordinate> {
    private T value;
    private KDNode right;
    private KDNode left;
    private boolean horizontal;

    KDNode(T value , boolean horizontal) {
        this.value = value;
        this.right = null;
        this.left = null;
        this.horizontal = horizontal;
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
}
