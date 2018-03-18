package ch10;
import javax.swing.JFrame;  
import org.opencv.core.Core;  
import org.opencv.core.Mat;   
import org.opencv.core.Point;  
import org.opencv.core.Scalar;  
import org.opencv.core.Size;  
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.CvType;  
public class Ch10_15_2CheckBlueBallByHSVandHoughCirclesDilate {  
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	   public static void main(String arg[]){  
	     JFrame frame1 = new JFrame("WebCamera");  
	     frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	     frame1.setSize(640,480);  
	     frame1.setBounds(0, 0, frame1.getWidth(), frame1.getHeight());  
	     Panel panel1 = new Panel();  
	     frame1.setContentPane(panel1);  
	     frame1.setVisible(true);  
	     JFrame frame2 = new JFrame("HSV��m�Ŷ�");  
	     frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	     frame2.setSize(640,480);  
	     frame2.setBounds(300,100, frame2.getWidth()+300, 100+frame2.getHeight());  
	     Panel panel2 = new Panel();  
	     frame2.setContentPane(panel2);  
	     frame2.setVisible(true);  
	     JFrame frame3 = new JFrame("Threshold�G�Ȥ�");  
	     frame3.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	     frame3.setSize(640,480);  
	     frame3.setBounds(600,200, frame2.getWidth()+900, 300+frame2.getHeight());  
	     Panel panel3 = new Panel();  
	     frame3.setContentPane(panel3);      
	     frame3.setVisible(true);  
	     //Ū�Jwebcam�v��
	     VideoCapture capture = new VideoCapture();
	     capture.open(0);   
	     Mat webcam_image=new Mat();  
	     Mat hsv_image=new Mat();  
	     Mat thresholded=new Mat();  
	      capture.read(webcam_image);  
	      frame1.setSize(webcam_image.width()+40,webcam_image.height()+60);  
	      frame2.setSize(webcam_image.width()+40,webcam_image.height()+60);  
	      frame3.setSize(webcam_image.width()+40,webcam_image.height()+60);  
	     Mat circles = new Mat();  
	     
	     //����Ŧ�ok
	     Scalar hsv_min = new Scalar(100, 90, 90, 0);  
	     Scalar hsv_max = new Scalar(140, 255, 255, 0);  
	     
	     if( capture.isOpened())  
	     {  
	      while( true )  
	      {  
	        capture.read(webcam_image);  
	        if( !webcam_image.empty() )  
	         {  
	          // �ഫ  
	          Imgproc.cvtColor(webcam_image, hsv_image, Imgproc.COLOR_BGR2HSV);  
	          Core.inRange(hsv_image, hsv_min, hsv_max, thresholded);           
	          // ����
	          Imgproc.GaussianBlur(thresholded, thresholded, new Size(9,9),0,0);  
	         //Dilate(����)�B�z
	          Mat element = Imgproc.getStructuringElement(Imgproc.MORPH_ELLIPSE,new Size(55, 55));
	 		  Imgproc.dilate(thresholded, thresholded, element);
	          //�N�ҰҶ��ܴ�
	          Imgproc.HoughCircles(thresholded, circles, Imgproc.CV_HOUGH_GRADIENT, 2, thresholded.height()/4, 500, 50, 0, 0);   
	          // �ŦX��B�O��ø�X�~��
	           int rows = circles.rows();  
	           int elemSize = (int)circles.elemSize(); // Returns 12 (3 * 4bytes in a float)  
	           float[] data2 = new float[rows * elemSize/4];  
	           if (data2.length>0){  
	             circles.get(0, 0, data2); 
	             for(int i=0; i<data2.length; i=i+3) {  
	               Point center= new Point(data2[i], data2[i+1]);  
	               //Core.ellipse( this, center, new Size( rect.width*0.5, rect.height*0.5), 0, 0, 360, new Scalar( 255, 0, 255 ), 4, 8, 0 );  
	               Imgproc.ellipse( webcam_image, center, new Size((double)data2[i+2], (double)data2[i+2]), 0, 0, 360, new Scalar( 255, 0, 255 ), 4, 8, 0 );  
	             }  
	           }  
	          //ø�X&���
	            panel1.setimagewithMat(webcam_image);  
	            panel2.setimagewithMat(hsv_image);  
	            panel3.setimagewithMat(thresholded);  
	            frame1.repaint();  
	            frame2.repaint();  
                frame3.repaint();  
	         }  
	         else  
	         {  
	           System.out.println(" Webcam�S���v��-���`!");  
	           break;  
	         }  
	        }  
	       }  
	     return;  
	   }  
	 }   
