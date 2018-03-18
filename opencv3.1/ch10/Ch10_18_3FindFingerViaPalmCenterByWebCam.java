package ch10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.swing.JFrame;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfInt4;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Range;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.videoio.VideoCapture;
import org.opencv.core.CvType;


//�i�Ҽ{�Ӫ�2���I��2�Ӥ��īH
public class Ch10_18_3FindFingerViaPalmCenterByWebCam {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	static ArrayList<Double> HullList = new ArrayList<Double>();

	public static void main(String arg[]) throws Exception {
		JFrame frame1 = new JFrame("Camera");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(640, 480);
		frame1.setBounds(0, 0, frame1.getWidth(), frame1.getHeight());
		Panel panel1 = new Panel();
		frame1.setContentPane(panel1);
		frame1.setVisible(true);
		JFrame frame2 = new JFrame("Threshold");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(640, 480);
		frame2.setBounds(300, 100, frame1.getWidth() + 50,
				50 + frame1.getHeight());
		Panel panel2 = new Panel();
		frame2.setContentPane(panel2);
		frame2.setVisible(true);
		// -- 2. Read the video stream
		 VideoCapture capture = new VideoCapture();
	     capture.open(0); 
		Mat webcam_image = new Mat();
		Mat hsv_image = new Mat();
		Mat thresholded = new Mat();
		capture.read(webcam_image);
		frame1.setSize(webcam_image.width() + 40, webcam_image.height() + 60);
		frame2.setSize(webcam_image.width() + 40, webcam_image.height() + 60);
		Mat array255 = new Mat(webcam_image.height(), webcam_image.width(),
				CvType.CV_8UC1);
		array255.setTo(new Scalar(255));
		Scalar hsv_min = new Scalar(0, 10, 60);
		Scalar hsv_max = new Scalar(20, 150, 255);

		Mat firstArea = webcam_image.colRange(new Range(10, 320)).rowRange(70,
				430);
		
		List<Point> CorrectPt1 = null;
		if (capture.isOpened()) {
			while (true) {
				Thread.sleep(1000);
				capture.read(webcam_image);
				if (!webcam_image.empty()) {
					CorrectPt1=new ArrayList<Point>();
					Imgproc.cvtColor(firstArea, hsv_image,
							Imgproc.COLOR_BGR2HSV);
					Core.inRange(hsv_image, hsv_min, hsv_max, thresholded);
					Imgproc.GaussianBlur(thresholded, thresholded, new Size(15,
							15), 0, 0);
					Mat threshold_output = new Mat(webcam_image.rows(),
							webcam_image.cols(), CvType.CV_8UC1);
					Imgproc.threshold(thresholded, threshold_output, 50, 255,
							Imgproc.THRESH_BINARY);

					List<MatOfPoint> contours = new ArrayList<MatOfPoint>();
					Mat hierarchy = new Mat(webcam_image.rows(),
							webcam_image.cols(), CvType.CV_8UC1, new Scalar(0));

					Mat destination1 = new Mat();

					// �����
					Imgproc.findContours(threshold_output, contours, hierarchy,
							Imgproc.RETR_TREE, Imgproc.CHAIN_APPROX_SIMPLE);

					int s = findBiggestContour(contours);
					
					Mat drawing = new Mat(threshold_output.size(),CvType.CV_8UC1);
					Imgproc.drawContours(drawing, contours, s, new Scalar(255,0, 0, 255), -1);
					 int count=0;
					Imgproc.distanceTransform(drawing, destination1,
							Imgproc.CV_DIST_L2, 3);
					int temp = 0;
					// �b�|
					int R = 0;

					// ���
					int centerX = 0;
					int centerY = 0;

					// �Z��
					double d = 0;

					// �M��,�̾A��B�z
					for (int i = 0; i < drawing.rows(); i++) {
						for (int j = 0; j < drawing.cols(); j++) {
							// �ˬd���I�O�_�b������,�p���I�������ɶZ��
							// d=Imgproc.pointPolygonTest(new
							// MatOfPoint2f(contours.get(0).toArray()), new
							// Point(j,i), true);
							// System.out.println("d="+d);
							// if(d>0){
							temp = (int) destination1.get(i, j)[0];
							if (temp > R) {
								R = temp;
								centerX = j;
								centerY = i;
							}

							// }
						}
					}
					// �b�|�j��22�~�X�z
					if (R > 22) {
						//�e�x��
						
						//System.out.println("centerX=" + centerX + ",centerY="+ centerY + ",R=" + R);
						Imgproc.circle(firstArea, new Point(centerX ,centerY), R, new Scalar(255, 0, 0));
						Imgproc.line(firstArea, new Point(centerX ,centerY ), new Point(centerX,centerY ), new Scalar(255,0, 0), 5);
						
						Imgproc.putText(firstArea, "("+centerX+","+centerY+")", new Point(centerX ,centerY ), 0, 0.3, new Scalar(255, 0, 0));//��ܴx�߮y��
						
						
						//�B�z���
					       for(int i=0;i<contours.size();i++){
				               	MatOfInt4 mConvexityDefectsMatOfInt4=new MatOfInt4();
				               	MatOfInt hull = new MatOfInt();
				                   MatOfPoint tempContour=contours.get(i);
				                   Imgproc.convexHull(tempContour, hull,false);
				                   //System.out.println("hull1:"+hull.size());
				                  
				                 int index=(int)hull.get(((int)hull.size().height)-1,0)[0];  
				                 Point pt, pt0 = new Point(tempContour.get(index, 0)[0], tempContour.get(index, 0)[1]);
				                 for(int j = 0; j < hull.size().height -1 ; j++){
				                 //for(int j = 0; j < 3 ; j++){
				                     index = (int) hull.get(j, 0)[0];
				                     pt = new Point(tempContour.get(index, 0)[0], tempContour.get(index, 0)[1]);
				                     
				                  double distance1=Math.sqrt(Math.pow(pt0.x-pt.x, 2)+Math.pow(pt0.y-pt.y, 2));
				                 
				                //�c�����u��
				                  if   (distance1>=30){
				                	  Imgproc.line(firstArea, pt0, pt, new Scalar(255, 0, 100), 2);//2�O�p��
				                	  
				                	  HullList.add(distance1);
				                	  
				                  }
				                    // System.out.println("distance-hull-"+j+":" + distance);
				                     pt0 = pt;
				                 }
				                 //for�p��convexityDefects
				                 if(contours.size()>0 && hull.rows()>3){
				                 	Imgproc.convexityDefects(tempContour, hull, mConvexityDefectsMatOfInt4);
				                 
				                 
				                 	 //System.out.println( mConvexityDefectsMatOfInt4.height()); 
				 	                 int index2=(int)mConvexityDefectsMatOfInt4.toArray().length;  
				 	                //System.out.println( "index2="+index2); 
				 	                 int index3;
				 	                 
				 	                 
				 	              if (tempContour.get(index2, 0)!=null) { 
					                 Point pt3,pt1,old_pt1=null,old_pt3 = null;
					                 for(int j = 0; j < mConvexityDefectsMatOfInt4.size().height -1 ; j++){
					                     index2 = (int) mConvexityDefectsMatOfInt4.get(j, 0)[0];//0�Otop�Y���y,2�Odown,�������
					                     index3 = (int) mConvexityDefectsMatOfInt4.get(j, 0)[2];
					                     pt1 = new Point(tempContour.get(index2, 0)[0], tempContour.get(index2, 0)[1]);//�y
					                     pt3 = new Point(tempContour.get(index3, 0)[0], tempContour.get(index3, 0)[1]);//��
					                     
					                     
					                     
					                     if((pt1.x>30 && pt1.x<300)&&(pt1.y>90 && pt1.y<420 )&&(centerY-pt1.y>30) ){  
					                  
					                    	 if(CorrectPt1.isEmpty()){
					                    		 CorrectPt1.add(pt1); 
					                    	 }else{
					                    		 double tempDistance=getDistance(pt1,(Point)CorrectPt1.get(CorrectPt1.size()-1));  
					                    		 if (tempDistance>0 && tempDistance<20){//�Y�ܪ�
						                    		  
						                    	  }else{
						                    		  CorrectPt1.add(pt1); 
						                    	  }
					                    		 
					                    	 }
					                    	 
					                     }
					                   	 
					                   	
					                   	 for(int kk=0;kk<CorrectPt1.size();kk++){
					                   		 
					                   		Imgproc.line(firstArea, CorrectPt1.get(kk),new Point(centerX ,centerY ), new Scalar(255,0, 0), 2);
					                   		 
					                   	 }
					                   	 
					                    double distance1=Math.sqrt(Math.pow(pt1.x-pt3.x, 2)+Math.pow(pt1.y-pt3.y, 2));
					                     old_pt3 = pt3;
					                     old_pt1=pt1;
					                 }
					                  
				 	              } 
				 	                 
				                 }
				       
				               }  
						
					}
		            count=0;

					// ø�X��x�P�_�X�Ӥ���Y�P����ϰ�,�x��
		            Imgproc.rectangle(webcam_image, new Point(30, 90), new Point(
							300, 420), new Scalar(250, 250, 250), 3);
					// ø�X�x�߸m���,�H�Q�r���
		            Imgproc.line(webcam_image, new Point(130, 320), new Point(180,
							320), new Scalar(250, 250, 250), 2);// ��
		            Imgproc.line(webcam_image, new Point(155, 295), new Point(155,
							345), new Scalar(250, 250, 250), 2);// ��

					// -- 5. Display the image
					panel1.setimagewithMat(webcam_image);
					panel2.setimagewithMat(drawing);
					
					frame1.repaint();
					frame2.repaint();
				} else {
					System.out.println(" --(!) No captured frame -- Break!");
					break;
				}
			}
		}
		return;
	}

	public static int findBiggestContour(List<MatOfPoint> contours) {
		int indexOfBiggestContour = -1;
		int sizeOfBiggestContour = 0;
		for (int i = 0; i < contours.size(); i++) {
			if (contours.get(i).height() > sizeOfBiggestContour) {
				sizeOfBiggestContour = contours.get(i).height();
				indexOfBiggestContour = i;
			}
		}
		return indexOfBiggestContour;
	}

	public static double getAngle(Point a, Point b, Point c) {
		double line1 = Math.sqrt(Math.pow(a.x - b.x, 2)
				+ Math.pow(a.y - b.y, 2));
		double line2 = Math.sqrt(Math.pow(c.x - b.x, 2)
				+ Math.pow(c.y - b.y, 2));
		double dot = (a.x - b.x) * (c.x - b.x) + (a.y - b.y) * (c.y - b.y);
		double angle = Math.acos(dot / (line1 * line2));
		angle = angle * 180 / Math.PI;
		return Math.round(100 * angle) / 100;

	}

	public static double getDistance(Point a, Point b) {
		// return Math.round(Math.sqrt(Math.pow(a.x-b.x, 2)+Math.pow(a.y-b.y,
		// 2)*100)/100);
		return Math.sqrt(Math.pow(a.x - b.x, 2) + Math.pow(a.y - b.y, 2));
	}

}
