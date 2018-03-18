package ch12;
import java.util.List;

import javax.swing.JFrame;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.MatOfFloat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.objdetect.HOGDescriptor;
import org.opencv.video.BackgroundSubtractorMOG2;
import org.opencv.video.Video;
import org.opencv.videoio.VideoCapture;

public class Ch12_4_1BackgroundSubtractorMOG {
	static {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	  static BackgroundSubtractorMOG2 bg=new Video().createBackgroundSubtractorMOG2();
	public static void main(String arg[]) {
		VideoCapture capture = new VideoCapture();
		capture.open("C://opencv3.1//samples//768x576.avi");
		//capture.open("C://opencv249//PIC_1370 00_00_00-00_00_10.avi");
		JFrame frame1 = new JFrame("show image");
		frame1.setTitle("��H�˴�threshold");
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame1.setSize(640, 480);
		frame1.setBounds(0, 0, frame1.getWidth(), frame1.getHeight());
		Panel panel1 = new Panel();
		frame1.setContentPane(panel1);
		frame1.setVisible(true);
		
		JFrame frame2 = new JFrame("��H�˴�MOG2");
		frame2.setTitle("��H�˴�MOG2");
		frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame2.setSize(640, 480);
		frame2.setBounds(0, 0, frame2.getWidth(), frame2.getHeight());
		Panel panel2 = new Panel();
		frame2.setContentPane(panel2);
		frame2.setVisible(true);
		
		
		if (!capture.isOpened()) {
			System.out.println("Error");
		} else {
			Mat webcam_image = new Mat();
			capture.read(webcam_image);
			frame1.setSize(webcam_image.width() + 30,webcam_image.height() + 30);
			frame2.setSize(webcam_image.width() + 90,webcam_image.height() + 90);
			while (true) {
				capture.read(webcam_image);
				if( !webcam_image.empty() ){
					panel2.setimagewithMat(webcam_image);
					frame2.repaint();
					
					
					webcam_image=detect(webcam_image);  
					panel1.setimagewithMat(webcam_image);
					frame1.repaint();
				}else{
					System.out.println("�v��Ū������!");  
			           capture.release();
			           break;  
				}
			}
		}
	}
	  public static Mat detect(Mat inputframe){  
		
		  Mat fgmask=new Mat();
		  bg.apply(inputframe, fgmask, 0.01);  
           return fgmask;  
      } 
	
	
}
