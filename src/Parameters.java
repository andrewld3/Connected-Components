public class Parameters {
    private int width;
    private int height;
    private int[][] array;
    private String filename;

    public Parameters(int w, int h, int[][] a, String fn) {
        width = w;
        height = h;
        array = a;
        filename = fn;
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

    public String getFileName() {
        return filename;
    }
}
