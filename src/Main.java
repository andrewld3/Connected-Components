import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Parameter;

public class Main {

    public static void main(String[] args) throws IOException {
         // Parameter object contains height, width, and array of pixels
        Parameters p;

        // Display Window with Menu
        //TODO: Write the image input function

        // Loads the OpenCV Java Wrapper Library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Turns a full color image into greyscale and then converts it into binary
        binarizeImage("original"); //TODO: Remove Test Code

        //Loads a Binary Image into Matrix
        Mat img = loadBinary("original_binary.jpg"); //TODO: Remove Test Code

        //Converts to 2D array
        p = convertToArray(img);
        //Label pixels and then colors image appropriately.
        //TODO: Write DFS
        DFS search = new DFS(p);
        search.componentSearch();
        //TODO: Color the image

        //Display the Images
        //TODO: Display Images

    }

    private static void binarizeImage(String fileName) {
        Mat src = Imgcodecs.imread(fileName + ".jpg",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
        Mat dst = new Mat();
        //TODO: Test against multiple images
        Imgproc.threshold(src,dst,150,255,Imgproc.THRESH_BINARY);
        Imgcodecs.imwrite(fileName + "_binary.jpg", dst);
    }

    private static Mat loadBinary(String fileName) {
        Mat img = Imgcodecs.imread(fileName, 0);
        return img;
    }

    private static Parameters convertToArray(Mat m) throws IOException{
        int width, height;
        String input;

        //Converts size into width and height integers
        input = m.size().toString();
        String delims = "x";
        String[] tokens = input.split(delims);
        width = Integer.parseInt(tokens[0]);
        height = Integer.parseInt(tokens[1]);

        //Creates 2D array and Image
        int[][] array = new int[width][height];
        BufferedImage img = ImageIO.read(new File("original_binary.jpg"));

        //Populates array
        //Starting point is Top Left Corner at (0,0)
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++) {
                Color c = new Color(img.getRGB(x,y));
                array[x][y] = c.getRed();
            }
        }

        //Load Parameter object and return
        Parameters p = new Parameters(width, height, array);
        return p;
    }

    private static void printArray(Parameters p) {
        int[][] a = p.getArray();
        for(int y = 0; y < p.getHeight(); y++) {
            for(int x = 0; x < p.getWidth(); x++) {
                System.out.printf("%3d", a[x][y]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}