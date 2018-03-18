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
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
public class Ch10_4_5ConvexHullContainUnknowPoints {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
		Mat img = Mat.zeros(250, 250, CvType.CV_8UC3);
		
		MatOfPoint contours = new MatOfPoint();
		List<Point> points = new ArrayList<Point>();
		MatOfInt hull = new MatOfInt();
		//contours
		int X,Y;
		for(int i=0;i<20;i++){
			X=(int)(Math.random()*200+40);
			Y=(int)(Math.random()*200+40);
		Core.circle(img, new Point(X,Y), 2, new Scalar(0, 0, 255));	//red
		points.add(new Point(X,Y));
		
		}
		
		contours.fromList(points);
		Imgproc.convexHull(contours, hull);
		 int index=(int)hull.get(((int)hull.size().height)-1,0)[0];  
		
		 Point pt, pt0 = new Point(contours.get(index, 0)[0], contours.get(index, 0)[1]);
         for(int j = 0; j < hull.size().height  ; j++){//hull.size().height-1���ܤ��|ø�X�ʳ�
             index = (int) hull.get(j, 0)[0];
             pt = new Point(contours.get(index, 0)[0], contours.get(index, 0)[1]);
             Core.line(img, pt0, pt, new Scalar(255, 0, 100), 1);
             pt0 = pt;
         }
		Highgui.imwrite("C://opencv3.1//samples//rectContainPoint-convexHull.jpg", img );
		
	}

}
