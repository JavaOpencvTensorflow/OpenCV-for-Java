package ch07;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;

public class Ch07_8_1Text {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	private JFrame frmjavaSwing;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ch07_8_1Text window = new Ch07_8_1Text();
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
	public Ch07_8_1Text() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		 Mat source =  paint();
		BufferedImage image=matToBufferedImage(source);
		
		frmjavaSwing = new JFrame();
		frmjavaSwing.setTitle("opencv ��J��r�m��");
		frmjavaSwing.setBounds(100, 100, 300, 300);
		frmjavaSwing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmjavaSwing.getContentPane().setLayout(null);
		 
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 5, image.getHeight()+10, image.getWidth()+10);
		lblNewLabel.setIcon(new ImageIcon(image));
		frmjavaSwing.getContentPane().add(lblNewLabel);
	}
	public Mat paint(){
		
		 Mat source = new Mat(250,250, CvType.CV_8UC3, new Scalar(255,255,255));
		
		Core.putText(source,new String("I love Opencv"), new Point(125,25), 0, 0.5, new Scalar(255, 0, 0));
		Core.putText(source,new String("I love Opencv"), new Point(125,50), 1, 0.5, new Scalar(255, 0, 0));
		Core.putText(source,new String("I love Opencv"), new Point(125,75), 2, 0.5, new Scalar(255, 0, 0));
		Core.putText(source,new String("I love Opencv"), new Point(125,100), 3, 0.5, new Scalar(255, 0, 0));
		Core.putText(source,new String("I love Opencv"), new Point(125,125), 4, 0.5, new Scalar(255, 0, 0));
		Core.putText(source,new String("I love Opencv"), new Point(125,150), 5, 0.5, new Scalar(255, 0, 0));
		Core.putText(source,new String("I love Opencv"), new Point(125,175), 6, 0.5, new Scalar(255, 0, 0));
		Core.putText(source,new String("I love Opencv"), new Point(125,200), 7, 0.5, new Scalar(255, 0, 0));
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
	  
//	  BufferedImage to Mat
//
//	  BufferedImage image = myBufferedImage;
//	  byte[] data = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//	  Mat mat = new Mat(image.getHeight(), image.getWidth, CvType.CV_8UC3);
//	  mat.put(0, 0, data);
}
