package ch13;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;


//�q��g�Ʀr,10�Ʀr�@�_��Jmatrix,�w���Ʀr�h�L
public class Ch13_13_3MahalanobisStdForOcr {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
		
		Mat trainingDataNumberMat=new Mat(11, 144, CvType.CV_32FC1);
		Mat source;
		for(int i=0;i<10;i++){
			
					source = Imgcodecs.imread("C://opencv3.1//samples//ocr//"+i+"0.jpg",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
				
				
				Mat temp=source.reshape(1, 144);
				for(int j=0;j<144;j++){
					double[] data=new double[1];
					data=temp.get(j, 0);
					trainingDataNumberMat.put(i, j, data);
					
				}
				
			}
		
		Mat temp0=Imgcodecs.imread("C://opencv3.1//samples//ocr//number3.jpg",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
		Mat temp=temp0.reshape(1, 144);
		
		for(int j=0;j<144;j++){
			double[] data=new double[1];
			data=temp.get(j, 0);
			trainingDataNumberMat.put(10, j, data);
			
		}
		 //System.out.println("temp1="+temp1.dump());
		 Mat covar = new Mat(144, 144, CvType.CV_32F);
		
		Mat mean = new Mat(1, 144, CvType.CV_32F);
		
		 Core.calcCovarMatrix(trainingDataNumberMat, covar, mean, Core.COVAR_ROWS | Core.COVAR_NORMAL, CvType.CV_32F);
		 Core.invert(covar, covar, Core.DECOMP_SVD);
		 
		//�W2�Ӭٲ�,���T�S�ܧ�
//	        System.out.println("covar="+covar.dump());
//	        System.out.println("mean="+mean.dump());
		Mat line0 = trainingDataNumberMat.row(0);
		Mat line1 = trainingDataNumberMat.row(1);
		Mat line2 = trainingDataNumberMat.row(2);
		Mat line3 = trainingDataNumberMat.row(3);
		Mat line4 = trainingDataNumberMat.row(4);
		Mat line5 = trainingDataNumberMat.row(5);
		Mat line6 = trainingDataNumberMat.row(6);
		Mat line7 = trainingDataNumberMat.row(7);
		Mat line8 = trainingDataNumberMat.row(8);
		Mat line9 = trainingDataNumberMat.row(9);
		Mat line10 = trainingDataNumberMat.row(10);
		 //System.out.println("line3="+line3.dump());
		 double d = Core.Mahalanobis(line10, line0, covar);
		 	System.out.println("line10(�Ʀr3)�Pline0��Mahalanobis�Z��="+d);
	        d = Core.Mahalanobis(line10, line1, covar);
	        System.out.println("line10(�Ʀr3)�Pline1��Mahalanobis�Z��="+d);//NaN:���O�Ʀr
	        d = Core.Mahalanobis(line10, line2, covar);
	        System.out.println("line10(�Ʀr3)�Pline2��Mahalanobis�Z��="+d);//NaN:���O�Ʀr
	        d = Core.Mahalanobis(line10, line3, covar);
	        System.out.println("line10(�Ʀr3)�Pline3��Mahalanobis�Z��="+d);//NaN:���O�Ʀr
	        d = Core.Mahalanobis(line10, line4, covar);
	        System.out.println("line10(�Ʀr3)�Pline4��Mahalanobis�Z��="+d);//NaN:���O�Ʀr
	        d = Core.Mahalanobis(line10, line5, covar);
	        System.out.println("line10(�Ʀr3)�Pline5��Mahalanobis�Z��="+d);//NaN:���O�Ʀr
	        d = Core.Mahalanobis(line10, line6, covar);
	        System.out.println("line10(�Ʀr3)�Pline6��Mahalanobis�Z��="+d);//NaN:���O�Ʀr
	        d = Core.Mahalanobis(line10, line7, covar);
	        System.out.println("line10(�Ʀr3)�Pline7��Mahalanobis�Z��="+d);//NaN:���O�Ʀr
	        d = Core.Mahalanobis(line10, line8, covar);
	        System.out.println("line10(�Ʀr3)�Pline8��Mahalanobis�Z��="+d);//NaN:���O�Ʀr
	        d = Core.Mahalanobis(line10, line9, covar);
	        System.out.println("line10(�Ʀr3)�Pline9��Mahalanobis�Z��="+d);//NaN:���O�Ʀr
	}

}
