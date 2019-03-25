public class Parameters {
    private int width;
    private int height;
    private int[][] array;

    public Parameters(int w, int h, int[][] a) {
        width = w;
        height = h;
        array = a;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int[][] getArray() {
        return array;
    }

    public void setArray(int[][] a) {
        array = a;
    }
}
