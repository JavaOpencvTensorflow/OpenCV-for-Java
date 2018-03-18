package ch06;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Ch06_4_2SobelFilter {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	private JFrame frmjavaSwing;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ch06_4_2SobelFilter window = new Ch06_4_2SobelFilter();
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
	public Ch06_4_2SobelFilter() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final Mat source = Highgui.imread("C://opencv3.1//samples//lena.jpg");
		 
		BufferedImage image=matToBufferedImage(source);
		
		frmjavaSwing = new JFrame();
		frmjavaSwing.setTitle("opencv Sobel API�m��1");
		frmjavaSwing.setBounds(100, 100, 560, 620);
		frmjavaSwing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmjavaSwing.getContentPane().setLayout(null);
		 
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(5, 60, image.getHeight()+10, image.getWidth()+10);
		lblNewLabel.setIcon(new ImageIcon(image));
		frmjavaSwing.getContentPane().add(lblNewLabel);
		
		JLabel lblKsize = new JLabel("dx");
		lblKsize.setBounds(10, 10, 46, 15);
		frmjavaSwing.getContentPane().add(lblKsize);
		
		final JSlider slider_dx = new JSlider();
		slider_dx.setMaximum(2);
		
		slider_dx.setValue(1);
		slider_dx.setBounds(50, 0, 200, 25);
		frmjavaSwing.getContentPane().add(slider_dx);
		
		final JLabel lblKsize_Val = new JLabel("1");
		lblKsize_Val.setBounds(253, 10, 46, 15);
		frmjavaSwing.getContentPane().add(lblKsize_Val);
		
		JLabel lblScale = new JLabel(" dy");
		lblScale.setBounds(5, 35, 27, 15);
		frmjavaSwing.getContentPane().add(lblScale);
		
		final JSlider slider_dy = new JSlider();
		slider_dy.setMaximum(2);
		
		slider_dy.setValue(1);
		slider_dy.setBounds(50, 35, 200, 25);
		frmjavaSwing.getContentPane().add(slider_dy);
		
		final JLabel lblScale_Val = new JLabel("1");
		lblScale_Val.setBounds(253, 35, 46, 15);
		frmjavaSwing.getContentPane().add(lblScale_Val);
		
		slider_dx.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				
				lblKsize_Val.setText(slider_dx.getValue()+"");
				BufferedImage newImage=matToBufferedImage(Sobel(source,slider_dx.getValue(),slider_dy.getValue()));
				lblNewLabel.setIcon(new ImageIcon(newImage));
			}
		});
		
		slider_dy.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblScale_Val.setText(slider_dy.getValue()+"");
				BufferedImage newImage=matToBufferedImage(Sobel(source,slider_dx.getValue(),slider_dy.getValue()));
				lblNewLabel.setIcon(new ImageIcon(newImage));
				
			}
		});
	}
	
	public Mat Sobel(Mat source,int dx,int dy){
		 Mat destination=new Mat(source.rows(),source.cols(),source.type());
		 if((dx==0)&&(dy==0)){
			 dx=1;
		 }
		Imgproc.Sobel(source, destination, -1,dx,dy);
		return destination;
		
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
