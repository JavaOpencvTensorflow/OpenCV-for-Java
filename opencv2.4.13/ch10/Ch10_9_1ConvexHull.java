package ch10;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

   public class Ch10_9_1ConvexHull {
      public static void main( String[] args )
      {
         try{
            System.loadLibrary( Core.NATIVE_LIBRARY_NAME );
            Mat source = Highgui.imread("C://opencv3.1//samples//palm6.jpg",
            Highgui.CV_LOAD_IMAGE_COLOR);
            Mat srcClone=source.clone();
            Mat target = new Mat(source.size(),CvType.CV_8U);
            // �ন�Ƕ���
            Imgproc.cvtColor(source, target, Imgproc.COLOR_RGB2GRAY);
            ///�i��ҽk�B�z�H�������I��
            Imgproc.GaussianBlur(target, target, new Size(5,5), 0, 0);
         
            Mat threshold_output=new Mat(source.rows(),source.cols(),source.type());
            //�i��G�ȤƳB�z
            Imgproc.threshold(target, threshold_output, 40, 255, Imgproc.THRESH_BINARY);
            
            Mat hierarchy = new Mat(target.rows(),target.cols(),CvType.CV_8UC1,new Scalar(0));
            List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
           
            
            //�M��~�����
            Imgproc.findContours(threshold_output, contours, hierarchy, Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);
          
            for(int i=0;i<contours.size();i++){
            	MatOfInt hull = new MatOfInt();
                MatOfPoint tempContour=contours.get(i);
                //�w��C�@�ӥ~������i��Y�]�p��
                Imgproc.convexHull(tempContour, hull,false);
               
                
              //ø�X�ӥ~��������Y�h���
              int index=(int)hull.get(((int)hull.size().height)-1,0)[0];  
              Point pt, pt0 = new Point(tempContour.get(index, 0)[0], tempContour.get(index, 0)[1]);
              for(int j = 0; j < hull.size().height -1 ; j++){
                  index = (int) hull.get(j, 0)[0];
                  pt = new Point(tempContour.get(index, 0)[0], tempContour.get(index, 0)[1]);
                  Core.line(srcClone, pt0, pt, new Scalar(255, 0, 100), 3);
                  pt0 = pt;
              }
                
          } 
            
            Highgui.imwrite("C://opencv3.1//samples//ConvexHull3a.jpg", srcClone);
           }catch (Exception e) {
              System.out.println("error: " + e.getMessage());
           }
   }
}