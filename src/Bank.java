public class Bank {
    String name;
    TwoDTree<Branch> branches;
    int size;

    public Bank(String name) {
        this.name = name;
        this.size = 0;
        this.branches = new TwoDTree<>();
    }

    public void insert(Branch b) {
        if(branches.insert(b))
            size++;
    }

    public void remove(Branch b) {
        if (branches.delete(b) != null)
            size --;
    }

}
