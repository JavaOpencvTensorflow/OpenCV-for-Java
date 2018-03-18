package ch03;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;

public class Ch03_1_999HandleMat_TestError {

	public static void main(String[] args) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		//Mat m1 = new Mat(2, 2, CvType.CV_8UC1);
		Mat m1 = new Mat(3, 2, CvType.CV_32FC1);
		m1.put(0, 0, 1);
		m1.put(0, 1, 2);
		m1.put(1, 0, 3);
		m1.put(1, 1, 4);
	

		Mat m2 = Mat.eye(2, 2, CvType.CV_8UC1);

		System.out.println("�x�}m1�Ҧ�����=" + m1.dump());
		System.out.println("�x�}m1����m�x�}=" + m1.t().dump());
		System.out.println("�x�}m1���ϯx�}/�f�x�}=" + m1.inv().dump());
		System.out.println("�x�}m1�����x�}=" + m2.dump());
		
		 Mat m3  = Mat.ones(2, 2,CvType.CV_8UC1);
		 System.out.println("2X2��1�x�}m3�Ҧ�����=" + m3.dump());
		
		Mat m4=m1.reshape(4,1);
		System.out.println("�x�}m4�Om1�૬��1�C4�檺�x�},��Ҧ�����=" + m4.dump());

		Mat m5=m1.reshape(1,4);
		System.out.println("�x�}m5�Om1�૬��4�C1�檺�x�},��Ҧ�����=" + m5.dump());
	}

}
