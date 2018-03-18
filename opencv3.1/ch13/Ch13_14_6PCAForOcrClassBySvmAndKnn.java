package ch13;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.ml.KNearest;
import org.opencv.ml.Ml;
import org.opencv.ml.SVM;

public class Ch13_14_6PCAForOcrClassBySvmAndKnn {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
		OcrDatabase ocr=new OcrDatabase();


Mat meanTrain  = new Mat();
Mat vectorsTrain = new Mat();
Mat trainOcrDataT=ocr.getTrainingDataMat().t();

Core.PCACompute(trainOcrDataT, meanTrain , vectorsTrain ,10);
Mat pcaTrainOcr=vectorsTrain.t();


SVM svm=SVM.create();
TermCriteria criteria=new TermCriteria(TermCriteria.MAX_ITER, 100, 1e-6);
svm.setKernel(svm.LINEAR);
svm.setType(svm.C_SVC);
svm.setGamma(0.5);
svm.setNu(0.5);
//1-->97.77778%,2-->100%
svm.setC(2);
svm.setTermCriteria(criteria);

boolean r=svm.train(pcaTrainOcr, Ml.ROW_SAMPLE, ocr.getTrainingLabelsMat());
System.out.println("SVM�O�_���V�m���\="+r);

/////////////////////////////////////////////////////////////////////////////////


KNearest knn = KNearest.create();

knn.setDefaultK(8);
knn.setIsClassifier(true);
//knn.setAlgorithmType(knn.KDTREE);
knn.setAlgorithmType(knn.BRUTE_FORCE);
knn.setEmax(1);

boolean Knnr=knn.train(pcaTrainOcr, Ml.ROW_SAMPLE, ocr.getTrainingLabelsMat());
System.out.println("KNN�O�_���V�m���\="+r);



Mat meanTest  = new Mat();
Mat vectorsTest = new Mat();
Mat testIrisDataT=ocr.getTestingDataMat().t();
Core.PCACompute(testIrisDataT, meanTest , vectorsTest ,10);

Mat pcaTestOcr=vectorsTest.t();
//System.out.println(pcaTestOcr.dump());

//���պ�ǭ�

//�w�����T�֥[1
int rightSVM=0;
float resultSVM;

int rightKnn=0;
float resultKnn;

float[] answer=ocr.getTestingLabels();
//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
for(int i=0;i<pcaTestOcr.rows();i++){

	//System.out.println(iris.getTestingDataMat().row(i).dump());
	resultSVM=svm.predict(pcaTestOcr.row(i));
	if(resultSVM==answer[i]){
		rightSVM++;
	}else{
		System.out.println("�w�����~!�䴩�V�q���w���O"+resultSVM+",���T�O="+answer[i]);
	}
	//////////////////////////////////////
	resultKnn=knn.predict(pcaTestOcr.row(i));
	if(resultKnn==answer[i]){
		rightKnn++;
	}else{
		System.out.println("�w�����~!KNN�w���O"+resultKnn+",���T�O="+answer[i]);
	}
	
}
System.out.println("///////////////////////////////////////////////////////////");

System.out.println("�䴩�V�q�����պ�ǭ�="+((float)rightSVM/(float)pcaTestOcr.rows())*100+"%");
System.out.println("KNN���պ�ǭ�="+((float)rightKnn/(float)pcaTestOcr.rows())*100+"%");
	}

}
