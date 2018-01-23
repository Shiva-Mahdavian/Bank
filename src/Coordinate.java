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

    public static double dist (Coordinate a, Coordinate b) {
        return Math.sqrt(Math.pow(a.getX() - b.getX(), 2) + Math.pow(a.getY() - b.getY(), 2));
    }

    public static double distFromLine(Coordinate point, Coordinate node, boolean horizontal){
        if (horizontal)
            return point.getY() - node.getY();
        return  point.getX() - node.getX();
    }

    public static boolean isRightCloser(Coordinate point, Coordinate node, boolean horizontal) {
        if (distFromLine(point, node, horizontal)> 0)
            return true;
        return false;
    }

    public boolean containsInX (Coordinate a , Coordinate b) {
        if ((a.getX() < this.getX() && b.getX() > this.getX()) || (b.getX() < this.getX() && a.getX() > this.getX()))
            return true;
        return false;
    }

    public boolean containsInY (Coordinate a, Coordinate b) {
        if ((a.getY() < this.getY() && b.getY() > this.getY()) || (b.getY() < this.getY() && a.getY() > this.getY()))
            return true;
        return false;
    }

    public boolean hasIntersect(Coordinate a, Coordinate b, boolean isHorizontal) {
        if (isHorizontal && this.containsInY(a, b))
            return true;
        if (!isHorizontal && this.containsInX(a, b))
            return true;
        return false;
    }

    public boolean containsInRange(Coordinate a , Coordinate b) {
        if ( this.containsInX(a, b) && containsInY(a, b))
            return  true;
        return false;
    }
}
