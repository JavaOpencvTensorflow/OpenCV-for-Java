package ch13;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.ml.KNearest;
import org.opencv.ml.Ml;

public class Ch13_7_2KnnForIris {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
IrisDatabase iris=new IrisDatabase();
KNearest knn = KNearest.create();
//1,3-->100%,2,5,6,7,8-->97.77,4-->95.5%,6
//knn.setDefaultK(8);
//knn.setIsClassifier(true);
boolean r=knn.train(iris.getTrainingDataMat(), Ml.ROW_SAMPLE, iris.getTrainingLabelsMat());
System.out.println("�O�_���V�m���\="+r);

//�H�K��3�մ���
Mat results=new Mat(); 
Mat neighborResponses=new Mat();
Mat dist=new Mat();

//k=3
float result0=knn.findNearest(iris.getTestSample0FrTestMat(), 3, results, neighborResponses, dist);
System.out.println("KNN�w��result0="+result0+"��");
System.out.println("results="+results.dump());
System.out.println("neighborResponses="+neighborResponses.dump());
System.out.println("dists="+dist.dump());
System.out.println("=============================================");


float result1=knn.findNearest(iris.getTestSample1FrTestMat(), 4, results, neighborResponses, dist);
System.out.println("KNN�w��result1="+result1+"��");
System.out.println("results="+results.dump());
System.out.println("neighborResponses="+neighborResponses.dump());
System.out.println("dists="+dist.dump());
System.out.println("=============================================");

float result2=knn.findNearest(iris.getTestSample2FrTestMat(), 4, results, neighborResponses, dist);
System.out.println("KNN�w��result2="+result2+"��");
System.out.println("results="+results.dump());
System.out.println("neighborResponses="+neighborResponses.dump());
System.out.println("dists="+dist.dump());
System.out.println("=============================================");
//���պ�ǭ�

//�w�����T�֥[1
int right=0;
float result;
float[] answer=iris.getTestingLabels();
//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
for(int i=0;i<iris.getTestingDataMat().rows();i++){

	//System.out.println(iris.getTestingDataMat().row(i).dump());
	//1.3-->100,2,4,5-->97.77778%
 result=knn.findNearest(iris.getTestingDataMat().row(i), 8, results, neighborResponses, dist);
	if(result==answer[i]){
		right++;
	}else{
		System.out.println("�w�����~!KNN�w���O"+result+"���T�O="+answer[i]);
	}

}
//95%
System.out.println("KNN���պ�ǭ�="+((float)right/(float)iris.getTestingDataMat().rows())*100+"%");




	}

}
