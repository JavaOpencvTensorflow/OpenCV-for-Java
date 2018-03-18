package ch08;

import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Ch08_12_1LinearPolar {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	private JFrame frmjavaSwing;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ch08_12_1LinearPolar window = new Ch08_12_1LinearPolar();
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
	public Ch08_12_1LinearPolar() {
		initialize();
		System.out.println("Imgproc.CV_WARP_FILL_OUTLIERS="+Imgproc.CV_WARP_FILL_OUTLIERS);
		System.out.println("Imgproc.CV_WARP_INVERSE_MAP="+Imgproc.CV_WARP_INVERSE_MAP);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final Mat source = Imgcodecs.imread("C://opencv3.1//samples//linearPolar_test.jpg");
		
		BufferedImage image=matToBufferedImage(source);
		
		frmjavaSwing = new JFrame();
		frmjavaSwing.setTitle("�����x�γB�z�m��");
		frmjavaSwing.setBounds(100, 100, 520, 550);
		frmjavaSwing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmjavaSwing.getContentPane().setLayout(null);
		
		final JLabel showMaxRadiusValue = new JLabel("20");
		showMaxRadiusValue.setBounds(293, 21, 46, 15);
		frmjavaSwing.getContentPane().add(showMaxRadiusValue);
		
		final JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(10, 143, 438, 313);
		lblNewLabel.setIcon(new ImageIcon(image));
		frmjavaSwing.getContentPane().add(lblNewLabel);
		
		final JSlider slider_maxRadius = new JSlider();
		slider_maxRadius.setMaximum(360);
		slider_maxRadius.setMinimum(20);
		slider_maxRadius.setValue(20);
		
		slider_maxRadius.setBounds(83, 10, 200, 25);
		frmjavaSwing.getContentPane().add(slider_maxRadius);
		
		JLabel lblAlpha = new JLabel(" maxRadius");
		lblAlpha.setBounds(10, 21, 81, 15);
		frmjavaSwing.getContentPane().add(lblAlpha);
		
		JLabel lblFlag = new JLabel("flag");
		lblFlag.setBounds(20, 55, 37, 15);
		frmjavaSwing.getContentPane().add(lblFlag);
		
		final JSlider slider_flag = new JSlider();
		
		slider_flag.setValue(0);
		slider_flag.setMaximum(1);
		slider_flag.setBounds(71, 45, 81, 25);
		frmjavaSwing.getContentPane().add(slider_flag);
		
		final JLabel lblNewLabel_flag = new JLabel("0");
		lblNewLabel_flag.setBounds(153, 55, 46, 15);
		frmjavaSwing.getContentPane().add(lblNewLabel_flag);
		
		slider_flag.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				lblNewLabel_flag.setText(slider_flag.getValue()+"");
				BufferedImage newImage=matToBufferedImage(LinearPolar(slider_maxRadius.getValue(),slider_flag.getValue()));
				lblNewLabel.setIcon(new ImageIcon(newImage));
			}
		});
		
		slider_maxRadius.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				//System.out.println(slider_alpha.getValue());
				showMaxRadiusValue.setText(slider_maxRadius.getValue()+"");
				BufferedImage newImage=matToBufferedImage(LinearPolar(slider_maxRadius.getValue(),slider_flag.getValue()));
				lblNewLabel.setIcon(new ImageIcon(newImage));
			}
		});
		
		
	}
	public Mat LinearPolar(double maxRadius,int flag){
		int tempFlag=8;
		if (flag==0){
			tempFlag=8;//Imgproc.CV_WARP_FILL_OUTLIERS=8
		}else if(flag==1){
			tempFlag=16;//Imgproc.CV_WARP_INVERSE_MAP=16
		}
		
		Mat source = Imgcodecs.imread("C://opencv3.1//samples//linearPolar_test.jpg");
		 Mat dst=new Mat();
		Imgproc.linearPolar(source, dst, new Point(source.cols()/2,source.rows()/2), maxRadius, tempFlag);
		return dst;
		
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
