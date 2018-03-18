package ch04;
import org.opencv.core.*;
import org.opencv.highgui.Highgui;        
import org.opencv.highgui.VideoCapture;        
        
public class Ch04_2_1TakeAPicByWebCam {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
    public static void main (String args[]){
    	VideoCapture camera = new VideoCapture(0);
    	if(!camera.isOpened()){
    		System.out.println("Error");
    	}
    	else {
    		Mat frame = new Mat();
    	    		try {
	    	    		System.out.println("�ϥ�webcam���");
	    	    		camera.read(frame);
	    	    		Highgui.imwrite("c:\\opencv3\\samples\\camera.jpg", frame);
	    	    		System.out.println("��ӧ���!");
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    	}
    	camera.release();
    }
}   