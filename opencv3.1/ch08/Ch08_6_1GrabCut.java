package ch08;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

   public class Ch08_6_1GrabCut {
      public static void main( String[] args )
      {
         try{
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
            Mat source = Imgcodecs.imread("C://opencv3.1//samples//lena.jpg", 
            Imgcodecs.CV_LOAD_IMAGE_COLOR);
            bgSubtracting(source);
           }catch (Exception e) {
              System.out.println("error: " + e.getMessage());
           }
   }
      
      private static void bgSubtracting(Mat img) {
  		Mat firstMask = new Mat();
  		Mat bgModel = new Mat();//grabCut�����B��ϥ�
  		Mat fgModel = new Mat();//grabCut�����B��ϥ�
  		Mat mask;
  		Mat source = new Mat(1, 1, CvType.CV_8U, new Scalar(3.0));
  		//�w�q�x��
  		Rect rect = new Rect(0, 0,150,200);
  		
  		///����grabCut�B�z(����)��
  		Imgproc.grabCut(img, firstMask, rect, bgModel, fgModel, 1, 0 );
  		
  		
  		//�o��e��
  		Core.compare(firstMask, source, firstMask, Core.CMP_EQ);
  		//�ͦ���X�Ϲ�
  		Mat foreground = new Mat(img.size(), CvType.CV_8UC3, new Scalar(255,
  				255, 255));
  		//�ƻs�e���ƾ�,�ŦX���X���ϰ�
  		img.copyTo(foreground, firstMask);
  		  
  		   mask = new Mat(foreground.size(), CvType.CV_8UC1, new Scalar(255, 255, 255));

  		Imgproc.cvtColor(foreground, mask, Imgproc.COLOR_BGR2GRAY);
  		Imgproc.threshold(mask, mask, 254, 255, Imgproc.THRESH_BINARY_INV); 
  		//���X�᪺���Ncolor
  		Mat vals = new Mat(1, 1, CvType.CV_8UC3, new Scalar(255.0));

  		img.setTo(vals, mask);
  		Imgcodecs.imwrite("C://opencv3.1//samples//grabcut-lena.jpg", img);
  	}
}