package ch13;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.ml.KNearest;
import org.opencv.ml.Ml;

public class Ch13_14_4PCAForIrisClassByKnn {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
IrisDatabase iris=new IrisDatabase();
KNearest knn = KNearest.create();

//1,3-->100%,2,5,6,7,8-->97.77,4-->95.5%,6
knn.setDefaultK(8);
knn.setIsClassifier(true);
//knn.setAlgorithmType(knn.KDTREE);
knn.setAlgorithmType(knn.BRUTE_FORCE);
knn.setEmax(1);


Mat meanTrain  = new Mat();
Mat vectorsTrain = new Mat();
Mat trainIrisDataT=iris.getTrainingDataMat().t();

Core.PCACompute(trainIrisDataT, meanTrain , vectorsTrain ,2);
Mat pcaTrainIris=vectorsTrain.t();

boolean r=knn.train(pcaTrainIris, Ml.ROW_SAMPLE, iris.getTrainingLabelsMat());
System.out.println("�O�_���V�m���\="+r);



Mat meanTest  = new Mat();
Mat vectorsTest = new Mat();
Mat testIrisDataT=iris.getTestingDataMat().t();
Core.PCACompute(testIrisDataT, meanTest , vectorsTest ,2);

Mat pcaTestIris=vectorsTest.t();


//���պ�ǭ�

//�w�����T�֥[1
int right=0;
float result;
float[] answer=iris.getTestingLabels();
//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
for(int i=0;i<iris.getTestingDataMat().rows();i++){

	//System.out.println(iris.getTestingDataMat().row(i).dump());
 result=knn.predict(pcaTestIris.row(i));
	if(result==answer[i]){
		right++;
	}else{
		System.out.println("�w�����~!KNN�w���O"+result+"���T�O="+answer[i]);
	}

}
System.out.println("KNN���պ�ǭ�="+((float)right/(float)iris.getTestingDataMat().rows())*100+"%");
	}

}
