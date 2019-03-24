import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;

public class BinarizeImage {
    public void run() {
            System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
            String base = "C:/Users/andrew/Code/csc365/Connected-Components";

            Mat image = Imgcodecs.imread(base + "/original.jpg");

            Imgcodecs.imwrite("test.png", image);
    }
}
