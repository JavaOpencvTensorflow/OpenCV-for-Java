package ch13;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.ml.DTrees;
import org.opencv.ml.Ml;

public class Ch13_4_1DtreeForIris {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
IrisDatabase iris=new IrisDatabase();
Mat dTreePriors=new Mat();
DTrees dtree=DTrees.create();//�Ы�dtree
dtree.setMaxDepth(8);//�]�w�̤j�`��
dtree.setMinSampleCount(2);
dtree.setRegressionAccuracy(0);
dtree.setUseSurrogates(false);
dtree.setMaxCategories(2);
dtree.setPriors(dTreePriors);
dtree.setUse1SERule(true);
dtree.setCVFolds(0);//��e����,//set 10,it will get Exception in thread "main" java.lang.Exception: unknown exception
dtree.setTruncatePrunedTree(true);

boolean r=dtree.train(iris.getTrainingDataMat(), Ml.ROW_SAMPLE, iris.getTrainingLabelsMat());
System.out.println("�O�_���V�m���\="+r);

//�H�K��3�մ���

float result0=dtree.predict(iris.getTestSample0FrTestMat());
System.out.println("�M����w��result0="+result0+"��");

float result1=dtree.predict(iris.getTestSample1FrTestMat());
System.out.println("�M����w��result1="+result1+"��");

float result2=dtree.predict(iris.getTestSample2FrTestMat());
System.out.println("�M����w��result2="+result2+"��");

//���պ�ǭ�

//�w�����T�֥[1
int right=0;
float result;
float[] answer=iris.getTestingLabels();
//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
for(int i=0;i<iris.getTestingDataMat().rows();i++){

	//System.out.println(iris.getTestingDataMat().row(i).dump());
 result=dtree.predict(iris.getTestingDataMat().row(i));
	if(result==answer[i]){
		right++;
	}else{
		System.out.println("�w�����~!�M����w���O"+result+"���T�O="+answer[i]);
	}

}
//97.77%
System.out.println("�M������պ�ǭ�="+((float)right/(float)iris.getTestingDataMat().rows())*100+"%");




	}

}
