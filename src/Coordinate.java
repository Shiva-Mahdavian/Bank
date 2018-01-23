public class Coordinate
{
    private int x;
    private int y;

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public static int compare(Coordinate a, Coordinate b, boolean horizontal) {
        if (horizontal) {
            return compareY(a, b);
        } else {
            return compareX(a, b);
        }

    }

    public static boolean equal (Coordinate a, Coordinate b){
        if (compareY(a, b) == 0 && compareX(a, b) == 0)
            return true;
        return false;
    }


    private static int compareY(Coordinate a, Coordinate b) {
        if (a.getY() > b.getY())
            return 1;
        else if (a.getY() == b.getY())
            return 0;
        return -1;
    }


    private static int compareX(Coordinate a, Coordinate b){
        if (a.getX() > b.getX())
            return 1;
        else if (a.getX() == b.getX())
            return 0;
        return -1;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
