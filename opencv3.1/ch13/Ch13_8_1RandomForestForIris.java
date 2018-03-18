package ch13;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.ml.Ml;
import org.opencv.ml.RTrees;

public class Ch13_8_1RandomForestForIris {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
IrisDatabase iris=new IrisDatabase();

Mat rTreePriors=new Mat();
TermCriteria criteria=new TermCriteria(TermCriteria.EPS+TermCriteria.MAX_ITER, 50, 0.1);

RTrees rtree=RTrees.create();
rtree.setMaxDepth(4);
rtree.setMinSampleCount(2);
rtree.setRegressionAccuracy(0);
rtree.setUseSurrogates(false);
rtree.setMaxCategories(2);
rtree.setPriors(rTreePriors);
rtree.setCalculateVarImportance(false);
rtree.setActiveVarCount(10);
rtree.setTermCriteria(criteria);


boolean r=rtree.train(iris.getTrainingDataMat(), Ml.ROW_SAMPLE, iris.getTrainingLabelsMat());
System.out.println("�O�_���V�m���\="+r);

//�H�K��3�մ���

float result0=rtree.predict(iris.getTestSample0FrTestMat());
System.out.println("�H���˪L�w��result0="+result0+"��");

float result1=rtree.predict(iris.getTestSample1FrTestMat());
System.out.println("�H���˪L�w��result1="+result1+"��");

float result2=rtree.predict(iris.getTestSample2FrTestMat());
System.out.println("�H���˪L�w��result2="+result2+"��");

//���պ�ǭ�

//�w�����T�֥[1
int right=0;
float result;
float[] answer=iris.getTestingLabels();
//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
for(int i=0;i<iris.getTestingDataMat().rows();i++){

	//System.out.println(iris.getTestingDataMat().row(i).dump());
 result=rtree.predict(iris.getTestingDataMat().row(i));
	if(result==answer[i]){
		right++;
	}else{
		System.out.println("�w�����~!�H���˪L�w���O"+result+"���T�O="+answer[i]);
	}

}
System.out.println("�H���˪L���պ�ǭ�="+((float)right/(float)iris.getTestingDataMat().rows())*100+"%");




	}

}
