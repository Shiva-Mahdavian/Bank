public class Region {
    private Coordinate a;
    private Coordinate b;
    private String name;

    public Region(Coordinate a, Coordinate b, String name) {
        this.a = a;
        this.b = b;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinate getB() {
        return b;
    }

    public void setB(Coordinate b) {
        this.b = b;
    }

    public Coordinate getA() {
        return a;
    }

    public void setA(Coordinate a) {
        this.a = a;
    }
}
