import java.util.Stack;

public class DFS {

    private int width;
    private int height;
    private int[][] img;
    private int[][] visit;
    private int component;
    private HashMap components;

    public DFS() {

    }

    public DFS(Parameters p) {
        width = p.getWidth();
        height = p.getHeight();
        img = p.getArray();
        visit = initializeVisit(width, height);
        component = 0;
        components = new HashMap(); //MAX COMPONENTS 128
    }

    public void componentSearch( ) {
        for(int x = 0; x < width; ++x) {
            for(int y = 0; y < height; ++y) {
                if(visit[x][y] == 0 && img[x][y] < 250) {
                    dfs(x,y,++component);
                }
            }
        }
    }

    private void dfs(int x, int y, int currentComponent) {
        if (x < 0 || x == width)
            return;
        if (y < 0 || y == height)
            return;
        if (visit[x][y] == 1 || img[x][y] > 250)
            return;

        //Mark and label current pixel
        Pixel temp = new Pixel(x,y);
        components.put(currentComponent,temp);

        //Check Neighbors (4- Way)
        for(int dir = 0; dir < 4; dir++) {
            // 0- Top 1- Right 2- Bottom 3- Left
            switch(dir) {
                case 0:
                    dfs(x, y - 1, currentComponent);
                    break;
                case 1:
                    dfs(x + 1, y, currentComponent);
                    break;
                case 2:
                    dfs(x, y + 1, currentComponent);
                    break;
                case 3:
                    dfs(x - 1, y, currentComponent);
                    break;
            }
        }
    }

    public HashMap getMap( ) {
        return components;
    }

    private int[][] initializeVisit(int width, int height) {
        int[][] a = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                a[i][j] = 0;
            }
        }
        return a;
    }
}
