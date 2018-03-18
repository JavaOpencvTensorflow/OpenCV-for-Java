package ch13;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.ml.Boost;
import org.opencv.ml.Ml;
import org.opencv.ml.RTrees;


//�P13_9_2���(3��vs 2��)
public class Ch13_9_1BoostForIris {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
IrisDatabase iris=new IrisDatabase();

Boost boost=Boost.create();
boost.setBoostType(Boost.DISCRETE);
boost.setWeakCount(3);
boost.setMinSampleCount(4);
boost.setMaxCategories(4);
boost.setMaxDepth(2);


boolean r=boost.train(iris.getTrainingDataMat(), Ml.ROW_SAMPLE, iris.getTrainingLabelsMat());
System.out.println("�O�_���V�m���\="+r);

//�H�K��3�մ���

float result0=boost.predict(iris.getTestSample0FrTestMat());
System.out.println("Boost�w��result0="+result0+"��");

float result1=boost.predict(iris.getTestSample1FrTestMat());
System.out.println("Boost�w��result1="+result1+"��");

float result2=boost.predict(iris.getTestSample2FrTestMat());
System.out.println("Boost�w��result2="+result2+"��");

//���պ�ǭ�

//�w�����T�֥[1
int right=0;
float result;
float[] answer=iris.getTestingLabels();
//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
for(int i=0;i<iris.getTestingDataMat().rows();i++){

	//System.out.println(iris.getTestingDataMat().row(i).dump());
 result=boost.predict(iris.getTestingDataMat().row(i));
	if(result==answer[i]){
		right++;
	}else{
		System.out.println("�w�����~!Boost�w���O"+result+"���T�O="+answer[i]);
	}

}
System.out.println("Boost���պ�ǭ�="+((float)right/(float)iris.getTestingDataMat().rows())*100+"%");




	}

}
