package ch11;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfKeyPoint;
import org.opencv.features2d.FeatureDetector;
import org.opencv.features2d.Features2d;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Ch11_4_1FeatureDetector {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	private JFrame frmjavaSwing;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ch11_4_1FeatureDetector window = new Ch11_4_1FeatureDetector();
					window.frmjavaSwing.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ch11_4_1FeatureDetector() {
		initialize();
		System.out.println("FeatureDetector.SURF="+FeatureDetector.SURF);
		System.out.println("FeatureDetector.SIFT="+FeatureDetector.SIFT);
		System.out.println("FeatureDetector.STAR="+FeatureDetector.STAR);
		System.out.println("FeatureDetector.FAST="+FeatureDetector.FAST);
		System.out.println("FeatureDetector.ORB="+FeatureDetector.ORB);
		System.out.println("FeatureDetector.BRISK="+FeatureDetector.BRISK);
		System.out.println("FeatureDetector.MSER="+FeatureDetector.MSER);
		System.out.println("FeatureDetector.HARRIS="+FeatureDetector.HARRIS);
		System.out.println("FeatureDetector.Dense="+FeatureDetector.DENSE);
		System.out.println("FeatureDetector.GFTT="+FeatureDetector.GFTT);
		System.out.println("FeatureDetector.SimpleBlob="+FeatureDetector.SIMPLEBLOB);
		System.out.println("FeatureDetector.DYNAMIC_BRISK="+FeatureDetector.DYNAMIC_BRISK);
		System.out.println("FeatureDetector.DYNAMIC_DENSE="+FeatureDetector.DYNAMIC_DENSE);
		System.out.println("FeatureDetector.DYNAMIC_FAST="+FeatureDetector.DYNAMIC_FAST);
		System.out.println("FeatureDetector.DYNAMIC_GFTT="+FeatureDetector.DYNAMIC_GFTT);
		System.out.println("FeatureDetector.DYNAMIC_HARRIS="+FeatureDetector.DYNAMIC_HARRIS);
		System.out.println("FeatureDetector.DYNAMIC_MSER="+FeatureDetector.DYNAMIC_MSER);
		System.out.println("FeatureDetector.DYNAMIC_ORB="+FeatureDetector.DYNAMIC_ORB);
		System.out.println("FeatureDetector.DYNAMIC_SIFT="+FeatureDetector.DYNAMIC_SIFT);
		System.out.println("FeatureDetector.DYNAMIC_SIMPLEBLOB="+FeatureDetector.DYNAMIC_SIMPLEBLOB);
		System.out.println("FeatureDetector.DYNAMIC_STAR="+FeatureDetector.DYNAMIC_STAR);
		System.out.println("FeatureDetector.DYNAMIC_SURF="+FeatureDetector.DYNAMIC_SURF);
		System.out.println("FeatureDetector.GRID_BRISK="+FeatureDetector.GRID_BRISK);
		System.out.println("FeatureDetector.GRID_DENSE="+FeatureDetector.GRID_DENSE);
		System.out.println("FeatureDetector.GRID_FAST="+FeatureDetector.GRID_FAST);
		System.out.println("FeatureDetector.GRID_GFTT="+FeatureDetector.GRID_GFTT);
		System.out.println("FeatureDetector.GRID_HARRIS="+FeatureDetector.GRID_HARRIS);
		System.out.println("FeatureDetector.GRID_MSER="+FeatureDetector.GRID_MSER);
		System.out.println("FeatureDetector.GRID_ORB="+FeatureDetector.GRID_ORB);
		System.out.println("FeatureDetector.GRID_SIFT="+FeatureDetector.GRID_SIFT);
		System.out.println("FeatureDetector.GRID_SIMPLEBLOB="+FeatureDetector.GRID_SIMPLEBLOB);
		System.out.println("FeatureDetector.GRID_STAR="+FeatureDetector.GRID_STAR);
		System.out.println("FeatureDetector.GRID_SURF="+FeatureDetector.GRID_SURF);
		System.out.println("FeatureDetector.PYRAMID_BRISK="+FeatureDetector.PYRAMID_BRISK);
		System.out.println("FeatureDetector.PYRAMID_DENSE="+FeatureDetector.PYRAMID_DENSE);
		System.out.println("FeatureDetector.PYRAMID_FAST="+FeatureDetector.PYRAMID_FAST);
		System.out.println("FeatureDetector.PYRAMID_GFTT="+FeatureDetector.PYRAMID_GFTT);
		System.out.println("FeatureDetector.PYRAMID_HARRIS="+FeatureDetector.PYRAMID_HARRIS);
		System.out.println("FeatureDetector.PYRAMID_MSER="+FeatureDetector.PYRAMID_MSER);
		System.out.println("FeatureDetector.PYRAMID_ORB="+FeatureDetector.PYRAMID_ORB);
		System.out.println("FeatureDetector.PYRAMID_SIFT="+FeatureDetector.PYRAMID_SIFT);
		System.out.println("FeatureDetector.PYRAMID_SIMPLEBLOB="+FeatureDetector.PYRAMID_SIMPLEBLOB);
		System.out.println("FeatureDetector.PYRAMID_STAR="+FeatureDetector.PYRAMID_STAR);
		System.out.println("FeatureDetector.PYRAMID_SURF="+FeatureDetector.PYRAMID_SURF);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final Mat source = Highgui.imread("C://opencv3.1//samples//lena.jpg",Highgui.CV_LOAD_IMAGE_COLOR);
		
		BufferedImage image=matToBufferedImage(source);
		
		frmjavaSwing = new JFrame();
		frmjavaSwing.setTitle("�S�x�I�˴�");
		frmjavaSwing.setBounds(100, 100, 520, 550);
		frmjavaSwing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmjavaSwing.getContentPane().setLayout(null);
		
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 68, 438, 438);
		lblNewLabel.setIcon(new ImageIcon(image));
		frmjavaSwing.getContentPane().add(lblNewLabel);
		
		final JSlider slider = new JSlider();
	
		slider.setValue(1);
		slider.setMinimum(1);
		slider.setMaximum(44);
		slider.setBounds(97, 10, 226, 25);
		frmjavaSwing.getContentPane().add(slider);
		
		JLabel lblNewLabel_1 = new JLabel("DetectorType");
		lblNewLabel_1.setBounds(10, 10, 91, 15);
		frmjavaSwing.getContentPane().add(lblNewLabel_1);
		
		final JLabel lblNewLabel_2 = new JLabel("1");
		lblNewLabel_2.setBounds(353, 20, 46, 15);
		frmjavaSwing.getContentPane().add(lblNewLabel_2);
		
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				BufferedImage newImage = null;
				if(slider.getValue()<12){
					lblNewLabel_2.setText(slider.getValue()+"");
					newImage=matToBufferedImage(FeatureDetector(slider.getValue()));
				}else if(slider.getValue()>=12 && slider.getValue()<23){
					lblNewLabel_2.setText((slider.getValue()-11+1000)+"");
					newImage=matToBufferedImage(FeatureDetector((slider.getValue()-11+1000)));
				}else if(slider.getValue()>=23 && slider.getValue()<34){
					lblNewLabel_2.setText((slider.getValue()-22+2000)+"");
					newImage=matToBufferedImage(FeatureDetector((slider.getValue()-22+2000)));
				}else{
					lblNewLabel_2.setText((slider.getValue()-33+3000)+"");
					newImage=matToBufferedImage(FeatureDetector((slider.getValue()-33+3000)));
					
				}
				
				lblNewLabel.setIcon(new ImageIcon(newImage));
			}
		});
		
		
	}
	public Mat FeatureDetector(int DetectorType){
		Mat source = Highgui.imread("C://opencv3.1//samples//lena.jpg",Highgui.CV_LOAD_IMAGE_COLOR);
		Mat destination=source.clone();
        // Mat destination1=new Mat();
         Imgproc.cvtColor(destination, destination, Imgproc.COLOR_BGR2GRAY);
         MatOfKeyPoint      keyPoints=new MatOfKeyPoint();
         
         FeatureDetector  surf =FeatureDetector.create(DetectorType);
         
         surf.detect(destination, keyPoints);
         Features2d.drawKeypoints(destination, keyPoints, source);
		return source;
	}
	
	  public BufferedImage matToBufferedImage(Mat matrix) {  
		    int cols = matrix.cols();  
		    int rows = matrix.rows();  
		    int elemSize = (int)matrix.elemSize();  
		    byte[] data = new byte[cols * rows * elemSize];  
		    int type;  
		    matrix.get(0, 0, data);  
		    switch (matrix.channels()) {  
		      case 1:  
		        type = BufferedImage.TYPE_BYTE_GRAY;  
		        break;  
		      case 3:  
		        type = BufferedImage.TYPE_3BYTE_BGR;  
		        // bgr to rgb  
		        byte b;  
		        for(int i=0; i<data.length; i=i+3) {  
		          b = data[i];  
		          data[i] = data[i+2];  
		          data[i+2] = b;  
		        }  
		        break;  
		      default:  
		        return null;  
		    }  
		    BufferedImage image2 = new BufferedImage(cols, rows, type);  
		    image2.getRaster().setDataElements(0, 0, cols, rows, data);  
		    return image2;  
		  }  
}
