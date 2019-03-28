import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;

public class Labeling {

    private int width;
    private int height;
    private String fileName;
    private int counter;
    private int rgbValue;
    private int[][] img;
    private int[][] initLabel;
    private ArrayList<Pixel> pixels;
    private ArrayList<Integer> labels;
    private HashMap<Integer, ArrayList<Integer>> components;
    private HashMap<Integer, ArrayList<Pixel>> finalComp;
    private Random r;
    private BufferedImage connected;

    public Labeling(Parameters p) {

        //Image Description
        width = p.getWidth();
        height = p.getHeight();
        img = p.getArray();
        fileName = p.getFileName();

        //Initializations
        initLabel = initializeLabel(width, height);
        connected = initializeImage();

        pixels = new ArrayList<>();
        labels = new ArrayList<>();
        components = new HashMap<>();
        finalComp = new HashMap<>();

        counter = 0;

        //Coloring of Image
        rgbValue = 0;
        r = new Random();


    }

    public void labelImage() {
        firstPass();
    }

    private void firstPass() {
        int count = -1;
        for(int y = 0; y < height; y++) { // I
            for(int x = 0; x < width; x++) { // J
                if(img[x][y] < 200) {
                    Pixel p = new Pixel(x,y);
                    pixels.add(p);
                    //If Top Left Corner
                    if(x == 0 && y == 0) {
                        count++;
                        initLabel[x][y] = count;
                        ArrayList<Integer> a = new ArrayList<>();
                        components.put(count,a);
                    } else if(x == 0) {
                        if(initLabel[x][y-1] != 0) {
                            initLabel[x][y] = initLabel[x][y-1];
                        } else {
                            count++;
                            initLabel[x][y] = count;
                            ArrayList<Integer> a = new ArrayList<>();
                            components.put(count,a);
                        }
                    } else if (y == 0) {
                        if(initLabel[x-1][y] != 0) {
                            initLabel[x][y] = initLabel[x-1][y];
                        } else {
                            count++;
                            initLabel[x][y] = count;
                            ArrayList<Integer> a = new ArrayList<>();
                            components.put(count,a);
                        }
                    } else {
                        if(initLabel[x-1][y] == 0 && initLabel[x][y-1] == 0) {
                            count++;
                            initLabel[x][y] = count;
                            ArrayList<Integer> a = new ArrayList<>();
                            components.put(count,a);
                        } else if(initLabel[x][y-1] != 0) {
                            initLabel[x][y] = initLabel[x][y-1];
                            if(initLabel[x-1][y] != 0 && initLabel[x-1][y] != initLabel[x][y]) {
                                int c = initLabel[x-1][y];
                                ArrayList<Integer> a = components.get(initLabel[x][y]);
                                if(!a.contains(c)) {
                                    a.add(initLabel[x-1][y]);
                                    components.put(initLabel[x][y],a);
                                }
                            }
                        } else if(initLabel[x-1][y] != 0) {
                            initLabel[x][y] = initLabel[x-1][y];
                            if(initLabel[x][y-1] != 0 && initLabel[x][y-1] != initLabel[x][y]) {
                                int c = initLabel[x][y-1];
                                ArrayList<Integer> a = components.get(initLabel[x][y]);
                                if(!a.contains(c)) {
                                    a.add(initLabel[x][y-1]);
                                    components.put(initLabel[x][y],a);
                                }
                            }
                        }
                    }
                } else {
                   initLabel[x][y] = 0;
                }
            }
        }
        secondPass(initLabel,count);
    }

    private void secondPass(int[][] secLabel, int count) {
        boolean swap = true;
        while(swap) {
            swap = false;
            for (Pixel p : pixels) {
                int label = secLabel[p.getX()][p.getY()];
                for(int c = count; c >= 0; c--) {
                    ArrayList<Integer> a;
                    a = components.get(c);
                    if(a.contains(label)) {
                        secLabel[p.getX()][p.getY()] = c;
                        swap = true;
                    }
                }
            }
        }

        for (Pixel p : pixels) {
            int label = secLabel[p.getX()][p.getY()];
            if(finalComp.containsKey(label)) {
                ArrayList<Pixel> a = finalComp.get(label);
                a.add(p);
                finalComp.put(label,a);
            } else {
                ArrayList<Pixel> a = new ArrayList<>();
                a.add(p);
                finalComp.put(label,a);
                counter = label;
                labels.add(label);
            }
        }


        for (int label : labels) {
            Color color = new Color(r.nextInt(255),r.nextInt(255),r.nextInt(255),r.nextInt(255));
            rgbValue = (color.getAlpha()<<24) | (color.getRed()<<16) | (color.getGreen()<<8) | color.getBlue();
            for (Pixel p : finalComp.get(label)) {
                connected.setRGB(p.getX(),p.getY(),rgbValue);
            }
        }

        File outputFile = new File(fileName + "_connected.jpg");
        try {
            ImageIO.write(connected,"jpg",outputFile);
        }catch(IOException e) {
            e.printStackTrace();
        }


        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                System.out.printf("%3d", secLabel[x][y]);
                System.out.print(" ");
            }
            System.out.println();
        }

    }


    private int[][] initializeLabel(int width, int height) {
        int[][] a = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                a[i][j] = 0;
            }
        }
        return a;
    }

    private BufferedImage initializeImage() {
        try {
            connected = ImageIO.read(new File(fileName + ".jpg"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        return connected;
    }

}
