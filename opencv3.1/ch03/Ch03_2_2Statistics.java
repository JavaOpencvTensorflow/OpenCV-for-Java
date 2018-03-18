package ch03;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Scalar;

public class Ch03_2_2Statistics {
static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
	public static void main(String[] args) {
		

		Mat m1 = new Mat(2, 2, CvType.CV_32FC1);
		m1.put(0, 0, 1);
		m1.put(0, 1, 2);
		m1.put(1, 0, 3);
		m1.put(1, 1, 4);

		Mat m2 = m1.clone();

		System.out.println("�x�}m2�O�ƻsm1,�Ҧ�����=" + m2.dump());
		
		Mat m3=new Mat();
		Mat m4  = new Mat(2, 2, CvType.CV_32FC1,new Scalar(9));
		Core.max(m2, m4, m3);
		System.out.println("�x�}m2�Pm4�U�ۭ� Max=" + m3.dump());
		
		Mat m5=new Mat();
		Core.min(m2, m4, m5);
		System.out.println("�x�}m2�Pm4�U�ۭ� Min=" + m5.dump());
		
		System.out.println("�x�}m2����=" + Core.mean(m2));
		System.out.println("�x�}m4����=" + Core.mean(m4));
		
		MatOfDouble mean = new MatOfDouble();
		MatOfDouble stddev = new MatOfDouble();
		
		Core.meanStdDev(m2, mean, stddev);
		System.out.println("�x�}m2����=" + mean.get(0,0)[0]);
		System.out.println("�x�}m2�зǮt=" + stddev.get(0,0)[0]);
		System.out.println("�x�}m2�`�M=" + Core.sumElems(m2).val[0]);
		System.out.println("�x�}m2�D�s�����Ӽ�=" + Core.countNonZero(m1));
		
		MinMaxLocResult m6=new MinMaxLocResult();
		m6=Core.minMaxLoc(m2);
		System.out.println("�x�}m2���̤j��=" + m6.maxVal+",�̤p��="+m6.minVal);
		
		double norm=Core.norm(m1);
		System.out.println("�x�}m2�򥻽d��=" + norm);
		
		//���ä���
		Mat uniformlyDist=new Mat(3,3,CvType.CV_32FC1);
		Core.randu(uniformlyDist, 100, 150);
		System.out.println("�إ�3x3�����ä������H��(100~150)�x�}=" + uniformlyDist.dump());
		
		
		//�`�A����
		Mat normallyDist=new Mat(3,3,CvType.CV_32FC1);
		Core.randn(normallyDist, 10, 7.5);
		System.out.println("�إ�3x3���`�A������(������=10,�зǮt=7.5)�x�}=" + normallyDist.dump());
		
		
		
	}

}
