package ch02;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class Ch02_7_4CreateMat {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		
		Mat m1  = new Mat(2, 2, CvType.CV_8UC1,new Scalar(9));
		
		
		System.out.println("�x�}m1�����="+m1.cols());
		System.out.println("�x�}m1���C��="+m1.rows());
		System.out.println("�x�}m1�Ҧ������Ӽ�="+m1.total());
		System.out.println("�x�}m1��Size="+m1.size());
		System.out.println("�x�}m1(1,1)������="+m1.get(0,0)[0]);
		System.out.println("�x�}m1(2,2)������="+m1.get(1,1)[0]);
		System.out.println("�x�}m1�Ҧ�����="+m1.dump());
		
	}
	
}
