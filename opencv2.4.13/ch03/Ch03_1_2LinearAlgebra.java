package ch03;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class Ch03_1_2LinearAlgebra {
static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
	public static void main(String[] args) {
		

		Mat m1 = new Mat(2, 2, CvType.CV_32FC1);
		m1.put(0, 0, 1);
		m1.put(0, 1, 2);
		m1.put(1, 0, 3);
		m1.put(1, 1, 4);

		Mat m2 = m1.clone();

		System.out.println("�x�}m2�O�ƻsm1,�Ҧ�����=" + m2.dump());
		
		//�[
		Mat m3=new Mat();
		Core.add(m1, m2, m3);
		System.out.println("m1+m2=�x�}m3�Ҧ�����=" + m3.dump());
		m3=new Mat();
		Core.scaleAdd(m1, 1, m2, m3);
		System.out.println("m1+m2=�x�}m3�t�@�g�k=" + m3.dump());
		
		//��
		Mat m4=new Mat();
		Core.subtract(m1, m2, m4);
		System.out.println("m1-m2=�x�}m4�Ҧ�����=" + m4.dump());
		
		//��
		Mat m5=new Mat();
		Core.gemm(m1, m2, 1, new Mat(), 0, m5);
		System.out.println("m1*m2=�x�}m5�Ҧ�����=" + m5.dump());
		
		Mat m6=m1.mul(m2);
		System.out.println("m1,m2=�x�}�������ﭼm6�Ҧ�����=" + m6.dump());
		
	
		Mat m7 = new Mat(2, 2, CvType.CV_32FC1, new Scalar(3));
		Mat m8=new Mat();
		m8=m1.mul(m7);
		System.out.println("m1�x�}���Ҧ����������H3,�Y3*[matrix]=" + m8.dump());
		
		//��
		Mat m9=new Mat(2, 2, CvType.CV_32FC1);
		Core.divide(m1, Scalar.all(3), m9);
		System.out.println("(1/3)m1�x�}m9�Ҧ�����=" + m9.dump());
		
		Mat m10  = new Mat(2, 2, CvType.CV_32FC1,new Scalar(2));
		Mat m11=new Mat();
		Core.divide(m1, m10, m11);
		System.out.println("(1/2)m1�x�}m11�Ҧ�����=" + m11.dump());
		
		//mask
		Mat mask=new Mat(2, 2, CvType.CV_8UC1);
		mask.put(0, 0, 1);
		mask.put(0, 1, 1);
		mask.put(1, 0, 1);
		mask.put(1, 1, 0);
		
		m3=new Mat();
		Core.add(m1, m2, m3,mask);
		System.out.println("m1+m2=�x�}m3�ΨϥξB�n(1,1)��m�B�z=" + m3.dump());
		
		
	}

}
