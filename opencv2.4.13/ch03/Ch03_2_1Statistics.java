package ch03;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfDouble;
import org.opencv.core.Scalar;

public class Ch03_2_1Statistics {
static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}
	public static void main(String[] args) {
		

		Mat m1 = new Mat(2, 2, CvType.CV_32FC1);
		m1.put(0, 0, 1);
		m1.put(0, 1, 2);
		m1.put(1, 0, 3);
		m1.put(1, 1, 4);

		Mat m2 = m1.clone();

		System.out.println("�x�}m2�O�ƻsm1,�Ҧ�����=" + m2.dump());
		
		Mat v1=new Mat();
		Mat v2=new Mat();
		Mat v3=new Mat();
		Mat v4=new Mat();
		Core.reduce(m2, v1, 0, Core.REDUCE_AVG);	
		Core.reduce(m2, v2, 0, Core.REDUCE_SUM);
		Core.reduce(m2, v3, 0, Core.REDUCE_MAX);
		Core.reduce(m2, v4, 0, Core.REDUCE_MIN);
		System.out.println("m2��avg(by col)="+v1.dump());
		System.out.println("m2��sum="+v2.dump());
		System.out.println("m2��max="+v3.dump());
		System.out.println("m2��min="+v4.dump());
		
		
		//sort
		Mat sortMat=new Mat();
		Core.sort(m1, sortMat, Core.SORT_ASCENDING);
		System.out.println("m1�ƧǥHRow���D,�ɧ�="+sortMat.dump());
		
		Core.sort(m1, sortMat, Core.SORT_DESCENDING);
		System.out.println("m1�ƧǥHRow���D,����="+sortMat.dump());
		
		m1.put(0, 0, 3);
		m1.put(0, 1, 4);
		m1.put(1, 0, 1);
		m1.put(1, 1, 2);
		System.out.println("m1="+m1.dump());
		Core.sort(m1, sortMat, Core.SORT_EVERY_COLUMN);
		System.out.println("m1�ƧǥHCol���D,�ɧ�="+sortMat.dump());
	}

}
