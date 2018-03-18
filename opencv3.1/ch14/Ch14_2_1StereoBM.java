package ch14;

import org.opencv.calib3d.StereoBM;
import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Ch14_2_1StereoBM {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	
	public static void main(String[] args) {
		Mat imgLeft = Imgcodecs.imread("C://opencv3.1//samples//scene_l.bmp",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
		Mat imgRight = Imgcodecs.imread("C://opencv3.1//samples//scene_r.bmp",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
	
	
		 //�Ыاڭ̭ץ����t�Z�Ϥ�
		  Mat imgDisparity16S = new Mat( imgLeft.rows(), imgLeft.cols(), CvType.CV_16S );
		  Mat imgDisparity8U = new Mat( imgLeft.rows(), imgLeft.cols(), CvType.CV_8UC1 );

	
		  //�ǳ�StereoBM�غc�l�һݰѼ�
		  int RangeOfDisparity  = 16*5;   // ���t�d��
		  int BlockWindowSize = 21; // block window�϶��������j�p,���_�� 

		  // �p����t�v��
		  StereoBM sbm = StereoBM.create( RangeOfDisparity, BlockWindowSize );
		  sbm.compute( imgLeft, imgRight, imgDisparity16S );
		  
		  //�ˬd���ݭ�
		  MinMaxLocResult minMaxVal=new MinMaxLocResult();
		  double minVal; double maxVal;
		  
		  
		  minMaxVal=Core.minMaxLoc( imgDisparity16S);
		  minVal= minMaxVal.minVal;
		  maxVal=minMaxVal.maxVal;

		  System.out.println("Min value="+minVal+", Max value="+maxVal);
		  
		  //�ϥ� CV_8UC1�榡���v��
		  imgDisparity16S.convertTo( imgDisparity8U, CvType.CV_8UC1, 255/(maxVal - minVal));
		  
		  Imgcodecs.imwrite("C://opencv3.1//samples//SBM_sample.png", imgDisparity16S);
	}
}
