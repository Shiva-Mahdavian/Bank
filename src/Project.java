import java.util.Scanner;

public class Project {
    static Trie<Region> regions;
    static Trie<Bank> banks;
    static TwoDTree<Branch> branches;
    static Scanner scanner;
    public static void main(String[] args) {
        regions = new Trie<>();
        banks = new Trie<>();
        branches = new TwoDTree<>();
        scanner = new Scanner(System.in);
        while (true) {
            String cmd = scanner.next();
            switch (cmd){
                case "addBank":
                    addBank();
                    break;
                case "addRegion":
                    addRegion();
                    break;
                case "removeBank":
                    removeBank();
                    break;
                case "listOfBranches":
                    listOfBranches();
                    break;
                case "listOfBanks":
                    listOfBanks();
                    break;
                case "nearestBank":
                    nearestBank();
                    break;
                case "nearestBranch":
                    nearestBranch();
                    break;
                case "availableBanks":
                    availableBanks();
                    break;
                case "bankWhithMaxNumOfBranches" :
                    bankWhithMaxNumOfBranches();
                    break;
            }
        }
    }

    public static void addBank() {
        int a, b;
        String name;
        a = scanner.nextInt();
        b = scanner.nextInt();
        name = scanner.next();
        Branch branch = new Branch(a, b, name);
        branches.insert(branch);
        Bank bank = banks.get(name);
        if (bank == null)
            banks.insert(name, new Bank(name));
        banks.get(name).insert(branch);
    }

    public static void addRegion() {
        int a, b , c, d;
        String name;
        a = scanner.nextInt();
        b = scanner.nextInt();
        c = scanner.nextInt();
        d = scanner.nextInt();
        name = scanner.next();
        Region region = new Region(new Coordinate(a, b), new Coordinate(c, d), name);
        regions.insert(name, region);
    }

    public static void removeBank() {
        int a, b;
        a = scanner.nextInt();
        b = scanner.nextInt();
        String name = branches.find(new Branch(a, b)).getBank();
        if (name != null) {
            Branch branch = new Branch(a, b);
            Bank bank = banks.get(name);
            bank.remove(branch);
            branches.delete(branch);
        }
    }

    public static void listOfBranches() {
        String name = scanner.next();
        Bank bank = banks.get(name);
        bank.branches.print();
    }

    public static void listOfBanks() {
        String name = scanner.next();
        Region region = regions.get(name);
        branches.recQuery(region.getA(), region.getB());
        branches.stack.print();
    }

    public static void nearestBank() {
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        Branch branch = branches.nearestNeighbor(new Coordinate(a, b));
        System.out.println(branch.getBank() +  branch);
    }

    public static void nearestBranch() {
        String name = scanner.next();
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        Bank bank = banks.get(name);
        Branch branch = bank.branches.nearestNeighbor(new Coordinate(a, b));
        System.out.println(branch);
    }

    public static void availableBanks() {
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        double r = scanner.nextDouble();
        branches.circleQuery(r, new Coordinate(a, b));
        branches.stack.print();
    }

    public static void bankWhithMaxNumOfBranches() {
        String name = null;
        int num = 0;
        for (TrieNode tNode : banks.children) {
            if (tNode != null && tNode.isEnd() && tNode.getData() != null) {
                Bank b = (Bank) tNode.getData();
                if (b != null && b.size > num) {
                    num = b.size;
                    name = b.name;
                }

            }
        }
        if (name != null)
            System.out.println(name);
    }
}
