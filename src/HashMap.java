public class HashMap<T> {
    HMNode<T> arr[];

    public HashMap() {
        arr =  new HMNode[5];
    }

    public int hashing(char c) {
        if (c >= 'A')
            c -= 'A';
        else
            c -= 'a';
        return  ((int) c) % 5;
    }



}

class HMNode<T> {
    private char c;
    HMNode next;
    T data;

    public HMNode(){
         c = '.';
         data = null;
    }

    public HMNode(char c, T data) {
        this.c = c;
        this.data = data;
        this.next = null;
    }
}
