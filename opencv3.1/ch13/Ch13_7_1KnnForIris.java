package ch13;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.ml.KNearest;
import org.opencv.ml.Ml;

public class Ch13_7_1KnnForIris {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
IrisDatabase iris=new IrisDatabase();
KNearest knn = KNearest.create();

System.out.println("knn.BRUTE_FORCE="+knn.BRUTE_FORCE);
System.out.println("knn.KDTREE="+knn.KDTREE);
//1,3-->100%,2,5,6,7,8-->97.77,4-->95.5%,6
knn.setDefaultK(8);
knn.setIsClassifier(true);

boolean r=knn.train(iris.getTrainingDataMat(), Ml.ROW_SAMPLE, iris.getTrainingLabelsMat());
System.out.println("�O�_���V�m���\="+r);

//�H�K��3�մ���

float result0=knn.predict(iris.getTestSample0FrTestMat());
System.out.println("KNN�w��result0="+result0+"��");

float result1=knn.predict(iris.getTestSample1FrTestMat());
System.out.println("KNN�w��result1="+result1+"��");

float result2=knn.predict(iris.getTestSample2FrTestMat());
System.out.println("KNN�w��result2="+result2+"��");

//���պ�ǭ�

//�w�����T�֥[1
int right=0;
float result;
float[] answer=iris.getTestingLabels();
//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
for(int i=0;i<iris.getTestingDataMat().rows();i++){

	//System.out.println(iris.getTestingDataMat().row(i).dump());
 result=knn.predict(iris.getTestingDataMat().row(i));
	if(result==answer[i]){
		right++;
	}else{
		System.out.println("�w�����~!KNN�w���O"+result+"���T�O="+answer[i]);
	}

}
System.out.println("KNN���պ�ǭ�="+((float)right/(float)iris.getTestingDataMat().rows())*100+"%");




	}

}
