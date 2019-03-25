public class Pixel {

    private int label;
    private int x;
    private int y;

    public Pixel(int l, int xValue, int yValue) {
        label = l;
        x = xValue;
        y = yValue;
    }

    public Pixel(int xValue, int yValue) {
        x = xValue;
        y = yValue;
    }

    public Pixel() {

    }

    public void setX(int xValue) {
        x = xValue;
    }

    public void setY(int yValue) {
        y = yValue;
    }

    public void setLabel(int l) {
        label = l;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLabel() {
        return label;
    }
}
