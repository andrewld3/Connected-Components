import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

public class Main {

    public static void main(String[] args) {
        //TODO: Write the image input function

        // Loads the OpenCV Java Wrapper Library
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        // Turns a full color image into greyscale and then converts it into binary
        binarizeImage("original"); //TODO: Remove Test Code

        //Loads a Binary Image into Matrix
        Mat img = loadBinary("original_binary.jpg"); //TODO: Remove Test Code

        //TODO: Write 2 pass algo
        //TODO: Color the image


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

    private static void convertToArray(Mat m) {

    }
}