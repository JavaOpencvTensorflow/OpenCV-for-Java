package ch13;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.ml.Ml;
import org.opencv.ml.RTrees;
import org.opencv.ml.SVM;

public class Ch13_11_1SvmForIris {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
IrisDatabase iris=new IrisDatabase();


SVM svm=SVM.create();
TermCriteria criteria=new TermCriteria(TermCriteria.MAX_ITER, 100, 1e-6);
svm.setKernel(svm.LINEAR);
svm.setType(svm.C_SVC);
svm.setGamma(0.5);
svm.setNu(0.5);
//1-->97.77778%,2-->100%
svm.setC(2);
svm.setTermCriteria(criteria);

boolean r=svm.train(iris.getTrainingDataMat(), Ml.ROW_SAMPLE, iris.getTrainingLabelsMat());
System.out.println("�O�_���V�m���\="+r);

//�H�K��3�մ���

float result0=svm.predict(iris.getTestSample0FrTestMat());
System.out.println("�䴩�V�q���w��result0="+result0+"��");

float result1=svm.predict(iris.getTestSample1FrTestMat());
System.out.println("�䴩�V�q���w��result1="+result1+"��");

float result2=svm.predict(iris.getTestSample2FrTestMat());
System.out.println("�䴩�V�q���w��result2="+result2+"��");

//���պ�ǭ�

//�w�����T�֥[1
int right=0;
float result;
float[] answer=iris.getTestingLabels();
//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
for(int i=0;i<iris.getTestingDataMat().rows();i++){

	//System.out.println(iris.getTestingDataMat().row(i).dump());
 result=svm.predict(iris.getTestingDataMat().row(i));
	if(result==answer[i]){
		right++;
	}else{
		System.out.println("�w�����~!�䴩�V�q���w���O"+result+"���T�O="+answer[i]);
	}

}
System.out.println("�䴩�V�q�����պ�ǭ�="+((float)right/(float)iris.getTestingDataMat().rows())*100+"%");




	}

}
