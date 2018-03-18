package ch03;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;

public class Ch03_1_3LinearAlgebra {
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
		System.out.println("�x�}m2��trace(��)=" + Core.trace(m2).val[0]);
		
		//�Deigenvalues,eigenvectors
		Mat eigenvalues=new Mat();
		Mat eigenvectors=new Mat();
		boolean computeEigenvectors = true;
		Core.eigen(m1, computeEigenvectors, eigenvalues, eigenvectors);
		System.out.println("m1�S�x��="+eigenvalues.dump());
		System.out.println("m1�S�x�V�q="+eigenvectors.dump());
		
		//���t�x�}
		Mat covar = new Mat(2, 2, CvType.CV_32FC1);
		Mat mean = new Mat(1, 2, CvType.CV_32F);
		Core.calcCovarMatrix(m2, covar, mean, Core.COVAR_ROWS | Core.COVAR_NORMAL, CvType.CV_32F);
		System.out.println( "m2�����t�x�}="+covar.dump()+"by col����"+mean.dump());
	
		//�����
		Mat compare=new Mat();
		Mat m4=new Mat(2,2,CvType.CV_32FC1,new Scalar(9));
		Core.compare(m1, m4, compare, Core.CMP_GT);//�O�_�j��
		System.out.println("m1�O�_�j��m4�U����:"+compare.dump());
		
		compare=new Mat();
		Core.compare(m1, m4, compare, Core.CMP_LT);//�O�_�p��
		System.out.println("m1�O�_�p��m4�U����:"+compare.dump());
		
		compare=new Mat();
		Core.compare(m1, m4, compare, Core.CMP_EQ);//�O�_����
		System.out.println("m1�O�_����m4�U����:"+compare.dump());
		
		compare=new Mat();
		Core.compare(m1, m4, compare, Core.CMP_GT);//�O�_�j�󵥩�
		System.out.println("m1�O�_�j�󵥩�m4�U����:"+compare.dump());
		
		compare=new Mat();
		Core.compare(m1, m4, compare, Core.CMP_LT);//�O�_�p�󵥩�
		System.out.println("m1�O�_�p�󵥩�m4�U����:"+compare.dump());
	}

}
