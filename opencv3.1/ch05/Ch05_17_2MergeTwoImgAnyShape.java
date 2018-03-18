package ch05;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
//2�i�v���X��,�p�ϬO���W�h��
   public class Ch05_17_2MergeTwoImgAnyShape {
      public static void main( String[] args )
      {
         try{
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
            //�j��(����)
            Mat source = Imgcodecs.imread("C://opencv3.1//samples//ncku.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
            
            //�p��(�l��)
            Mat source1 = Imgcodecs.imread("C://opencv3.1//samples//jelly_studio_logo.jpg",Imgcodecs.CV_LOAD_IMAGE_COLOR);
            Mat destination=source.clone();
            
         // to make the white region transparent
            Mat mask2=new Mat();
            Mat dst=new Mat();
           
            
            Imgproc.cvtColor(source1,mask2,Imgproc.COLOR_BGR2GRAY);
            Imgproc.threshold(mask2,mask2,230,255,Imgproc.THRESH_BINARY_INV); 
            List<Mat> planes = new ArrayList<Mat>() ;
            List<Mat> result = new ArrayList<Mat>() ;
            Mat result1=new Mat();
            Mat result2=new Mat();
            Mat result3=new Mat();
            
            
            Core.split(source1, planes);
           
            Core.bitwise_and(planes.get(0), mask2, result1);
            Core.bitwise_and(planes.get(1), mask2, result2);
            Core.bitwise_and(planes.get(2), mask2, result3);
            
            result.add(result1);
            result.add(result2);
            result.add(result3);
           Core.merge(result, dst);
           //�H�W�զ��ܳz��
           
           
           //�A��p��copy��j��
           Rect roi=new Rect(50,50,90,62);//������Ϥj,�Τp
           Mat destinationROI = source.submat( roi );
           dst.copyTo( destinationROI , dst);
         
        
        Imgcodecs.imwrite("C://opencv3.1//samples//merge3.jpg", source);
           }catch (Exception e) {
              System.out.println("error: " + e.getMessage());
           }
   }
}