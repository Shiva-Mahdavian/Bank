public class Branch extends Coordinate {
    private String bank;

    public Branch(int x, int y, String bank) {
        super(x, y);
        this.bank = bank;
    }

    public String getBank() {
        return bank;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public Branch(int x, int y) {
        super(x, y);
    }
}
