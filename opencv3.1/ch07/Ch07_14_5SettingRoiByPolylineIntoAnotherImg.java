package ch07;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opencv.core.MatOfPoint;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class Ch07_14_5SettingRoiByPolylineIntoAnotherImg {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	private JFrame frmjavaSwing;
	
	public List<Point> allPoint=new ArrayList<Point>(); 
	private List<Integer> pointX=new ArrayList<Integer>();
	private List<Integer> pointY=new ArrayList<Integer>();
    private int maxX,maxY,minX,minY; 
    private Mat globalRoi;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ch07_14_5SettingRoiByPolylineIntoAnotherImg window = new Ch07_14_5SettingRoiByPolylineIntoAnotherImg();
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
	public Ch07_14_5SettingRoiByPolylineIntoAnotherImg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		final Mat source = Imgcodecs.imread("C://opencv3.1//samples//DSC_0227.jpg");
		final Mat rightImg = Imgcodecs.imread("C://opencv3.1//samples//DSC_0250.jpg");
		
		BufferedImage image=matToBufferedImage(source);
		BufferedImage imageRight=matToBufferedImage(rightImg);
		
		frmjavaSwing = new JFrame();
		frmjavaSwing.setTitle("�ϥΦh����^�������W�hROI�ƻs��t�@�i�v��");
		frmjavaSwing.setBounds(100, 100, 922, 482);
		frmjavaSwing.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmjavaSwing.getContentPane().setLayout(null);
		
		final JLabel lblLocation = new JLabel("");
		lblLocation.setBounds(10, 10, 112, 25);
		frmjavaSwing.getContentPane().add(lblLocation);
		
		final JLabel lblLocation2 = new JLabel("");
		lblLocation2.setBounds(121, 10, 112, 25);
		frmjavaSwing.getContentPane().add(lblLocation2);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 106, 200, 242);
		frmjavaSwing.getContentPane().add(panel);
		
		final JLabel lblNewLabel = new JLabel("");
		panel.add(lblNewLabel);
		
		
		lblNewLabel.setIcon(new ImageIcon(image));
		
		final JLabel label_Right = new JLabel("");
		label_Right.setBounds(430, 92, 449, 346);
		
		label_Right.setIcon(new ImageIcon(imageRight));
		frmjavaSwing.getContentPane().add(label_Right);
		
		JButton btnNewButton = new JButton("�ʳ���");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				BufferedImage newImage=matToBufferedImage(paintAndSetPolyClose());
				lblNewLabel.setIcon(new ImageIcon(newImage));
			}
		});
		btnNewButton.setBounds(243, 10, 89, 25);
		frmjavaSwing.getContentPane().add(btnNewButton);
		
		final JLabel Label_mid = new JLabel("");
		Label_mid.setBounds(220, 106, 200, 242);
		frmjavaSwing.getContentPane().add(Label_mid);
		
		JButton btnNewButton_1 = new JButton("�^�������W�hROI�öK�W");
		btnNewButton_1.setBounds(342, 10, 175, 25);
		frmjavaSwing.getContentPane().add(btnNewButton_1);
		
		final JSlider sliderX = new JSlider();
		
		sliderX.setMaximum(465);
		sliderX.setBounds(577, 10, 248, 25);
		frmjavaSwing.getContentPane().add(sliderX);
		
		JLabel lblNewLabel_1 = new JLabel("X:");
		lblNewLabel_1.setBounds(542, 10, 34, 15);
		frmjavaSwing.getContentPane().add(lblNewLabel_1);
		
		final JSlider sliderY = new JSlider();
	
		sliderY.setMaximum(315);
		sliderY.setBounds(577, 45, 248, 25);
		frmjavaSwing.getContentPane().add(sliderY);
		
		JLabel lblY = new JLabel("Y:");
		lblY.setBounds(542, 45, 34, 15);
		frmjavaSwing.getContentPane().add(lblY);
		
		final JLabel showX_Val = new JLabel("50");
		showX_Val.setBounds(835, 10, 46, 15);
		frmjavaSwing.getContentPane().add(showX_Val);
		
		final JLabel showY_Val = new JLabel("50");
		showY_Val.setBounds(835, 45, 46, 15);
		frmjavaSwing.getContentPane().add(showY_Val);
		
		sliderX.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				showX_Val.setText(sliderX.getValue()+"");
				
				Mat currentRightImg = Imgcodecs.imread("C://opencv3.1//samples//DSC_0250.jpg");
				//�Y�OcurrentRightImg�ϥ�initialize()��rightImg�|���|�ϲ{�H
				Mat	rightSideImg=pasteToAnother(sliderX.getValue(),sliderY.getValue(), getGlobalRoi(),currentRightImg);
				BufferedImage	newImage=matToBufferedImage(rightSideImg);
				 label_Right.setIcon(new ImageIcon(newImage));
			}
		});
		
		sliderY.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				showY_Val.setText(sliderY.getValue()+"");
				
				Mat currentRightImg = Imgcodecs.imread("C://opencv3.1//samples//DSC_0250.jpg");
				Mat	rightSideImg=pasteToAnother(sliderX.getValue(),sliderY.getValue(), getGlobalRoi(),currentRightImg);
				BufferedImage	newImage=matToBufferedImage(rightSideImg);
				 label_Right.setIcon(new ImageIcon(newImage));
				
			}
		});
		//Listener�ƹ��I�����I
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				lblLocation2.setText("");
				lblLocation.setText("�[�J�I:X:"+arg0.getX()+",Y:"+arg0.getY());
				
				BufferedImage newImage=matToBufferedImage(paintPointAndPolylines(arg0.getX(),arg0.getY()));
				lblNewLabel.setIcon(new ImageIcon(newImage));
				
			}
		});	
		
		//�I���^�������W�hROI���s
				btnNewButton_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						
						Mat roi=getRoi(source);
						BufferedImage newImage=matToBufferedImage(roi);
						Label_mid.setIcon(new ImageIcon(newImage));
						
						//set global data
						setGlobalRoi(roi);
						
						
						//paste to another one,default position is (50,50)
					    Mat	rightSideImg=pasteToAnother(50,50, roi,rightImg);
						  newImage=matToBufferedImage(rightSideImg);
						 label_Right.setIcon(new ImageIcon(newImage));
						 
					}
				});
	}
	//ø�X�I�ΩҦ����u
	public Mat paintPointAndPolylines(int x,int y){
		 Mat src = Imgcodecs.imread("C://opencv3.1//samples//DSC_0227.jpg");
		 List<Integer> pointX=getPointX();
		 List<Integer> pointY=getPointY();
		 List<Point> allPoint=getAllPoint();
		allPoint.add(new Point(x,y));
		setAllPoint(allPoint);
		
		pointX.add(x);
		pointY.add(y);
		setPointX(pointX);
		setPointY(pointY);
		
		//�e�ƹ��I�����I
    	 Imgproc.circle(src, new Point(x,y), 2, new Scalar(0,55,255),2);
    	 
    	 //�e�XPolyline
    	 if(allPoint.size()>=2){
    		 MatOfPoint mop1=new MatOfPoint();
    		 List<MatOfPoint> allMatOfPoint=new ArrayList<MatOfPoint>(); 
    		 mop1.fromList(allPoint);
			 allMatOfPoint.add(mop1);
		     Imgproc.polylines(src, allMatOfPoint, false, new Scalar(255,255,255), 2);
    	 
    	 }
    	
		return src;
		
	}
	
	//�ʳ����W�h�u
	public Mat paintAndSetPolyClose(){
		Mat src = Imgcodecs.imread("C://opencv3.1//samples//DSC_0227.jpg");
		 List<Point> allPoint=getAllPoint();
		//�e�XPolyline
    	 if(allPoint.size()>=2){
    		 MatOfPoint mop1=new MatOfPoint();
    		 List<MatOfPoint> allMatOfPoint=new ArrayList<MatOfPoint>(); 
    		 mop1.fromList(allPoint);
			 allMatOfPoint.add(mop1);
		     Imgproc.polylines(src, allMatOfPoint, true, new Scalar(255,255,255), 2);
    	 
    	 }
		
		 return src;
	}
	//����ROI
	public Mat getRoi(Mat src){
		Mat RoiMat=new Mat();
		 List<Integer> pointX=getPointX();
		 List<Integer> pointY=getPointY();
		
		 
		 //��X�Ҧ��I���X��min��max,�H�Q�إ�ROI
		 int X0,Y0,X1,Y1,maxX,maxY,minX,minY;
		    maxX=Collections.max(pointX);
			maxY=Collections.max(pointY);
			minX=Collections.min(pointX);
			minY=Collections.min(pointY);
			
	     //�K�ܤj���ٻݭn�A�Ψ�
			setMaxX(maxX);
			setMinX(minX);
			setMaxY(maxY);
			setMinY(minY);
			
		
		//�����I�i�إߪ��x��ROI	
		Rect rect=new Rect(new Point(minX,minY),new Point(maxX,maxY));	
		RoiMat=src.submat(rect);

		//��photoshop��mask,0�¤��@��
		Mat  MaskTemplate=new Mat(RoiMat.size(),CvType.CV_8UC1,new Scalar(0));
		
		 List<Point> allPoint=getAllPoint();
		 List<Point> newPointSet=new ArrayList<Point>(); 
		// System.out.println("size="+allPoint.size());
		 List<MatOfPoint> allMatOfPoint=new ArrayList<MatOfPoint>(); 
			MatOfPoint mop1=new MatOfPoint();
			Point tmpPoint;
			//�I���X���Ҧ��y�Эn���m,ROI�����W���y�ЬO(minX,minY),�������n��h���W�H�k�s
			for(int i=0;i<allPoint.size();i++){
				tmpPoint=allPoint.get(i);
				newPointSet.add(new Point(tmpPoint.x-minX,tmpPoint.y-minY));
			}
			
			mop1.fromList(newPointSet);
			allMatOfPoint.add(mop1);
			//�ϥ�fillPoly��R�ڭ̭n���ϰ�,255�զ�,���@�ΰ�
			  Imgproc.fillPoly(MaskTemplate, allMatOfPoint,  new Scalar(255,255,255));
			
			  //���W�h��mat
			  Mat dst=new Mat();
			  Core.bitwise_and(RoiMat, RoiMat, dst,MaskTemplate);
			  //��Core.add�|���G
			  //Core.add(RoiMat, RoiMat, dst,MaskTemplate );
		return dst;
	}
	
	
	//ROI�K�ܥt�@�i�v��,roi�O���W�h�l�v��,anotherImg�O�w�Q�K�W���t�@�i�v��,x,y�O�Ӯy��
	public Mat pasteToAnother(int x,int y,Mat roi,Mat anotherImg){
		
		Rect dstRoi=new Rect(new Point(x,y),new Point(x+getMaxX()-getMinX(),y+getMaxY()-getMinY()));
		 Mat destinationROI = anotherImg.submat( dstRoi );
		 roi.copyTo( destinationROI , roi);
		
		 return anotherImg;
		
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

	

	public List<Point> getAllPoint() {
		return allPoint;
	}

	public void setAllPoint(List<Point> allPoint) {
		this.allPoint = allPoint;
	}

	public List<Integer> getPointX() {
		return pointX;
	}

	public void setPointX(List<Integer> pointX) {
		this.pointX = pointX;
	}

	public List<Integer> getPointY() {
		return pointY;
	}

	public void setPointY(List<Integer> pointY) {
		this.pointY = pointY;
	}

	public int getMaxX() {
		return maxX;
	}

	public void setMaxX(int maxX) {
		this.maxX = maxX;
	}

	public int getMaxY() {
		return maxY;
	}

	public void setMaxY(int maxY) {
		this.maxY = maxY;
	}

	public int getMinX() {
		return minX;
	}

	public void setMinX(int minX) {
		this.minX = minX;
	}

	public int getMinY() {
		return minY;
	}

	public void setMinY(int minY) {
		this.minY = minY;
	}

	public Mat getGlobalRoi() {
		return globalRoi;
	}

	public void setGlobalRoi(Mat globalRoi) {
		this.globalRoi = globalRoi;
	}
}
