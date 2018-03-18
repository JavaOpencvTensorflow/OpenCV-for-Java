package ch13;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.ml.DTrees;
import org.opencv.ml.Ml;
import org.opencv.ml.NormalBayesClassifier;


public class Ch13_4_2DtreeForOcr {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	
	public static void main(String[] args) {
		OcrDatabase ocr=new OcrDatabase();
		Mat dTreePriors=new Mat();
		DTrees dtree=DTrees.create();//�Ы�dtree
		//1->20%,2,3->30%,5,6,8,9->50%
		dtree.setMaxDepth(8);//�]�w�̤j�`��
		dtree.setMinSampleCount(2);
		dtree.setRegressionAccuracy(0);
		dtree.setUseSurrogates(false);
		dtree.setMaxCategories(2);
		dtree.setPriors(dTreePriors);
		dtree.setUse1SERule(true);
		dtree.setCVFolds(0);//��e����,//set 10,it will get Exception in thread "main" java.lang.Exception: unknown exception
		dtree.setTruncatePrunedTree(true);
		
		boolean r=dtree.train(ocr.getTrainingDataMat(), Ml.ROW_SAMPLE, ocr.getTrainingLabelsMat());
		
		System.out.println("�O�_���V�m���\="+r);
		
		//�H�K��3�մ���
		
			float result0=dtree.predict(ocr.getTestSample0FrTestMat());
			System.out.println("�M����w��result0="+result0+"��");
			
			float result1=dtree.predict(ocr.getTestSample1FrTestMat());
			System.out.println("�M����w��result1="+result1+"��");

			float result2=dtree.predict(ocr.getTestSample2FrTestMat());
			System.out.println("�M����w��result2="+result2+"��");
		
		//���պ�ǭ�
			
			//�w�����T�֥[1
			int right=0;
			float result;
			float[] answer=ocr.getTestingLabels();
			//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
			for(int i=0;i<ocr.getTestingDataMat().rows();i++){
			
				//System.out.println(iris.getTestingDataMat().row(i).dump());
			 result=dtree.predict(ocr.getTestingDataMat().row(i));
				if(result==answer[i]){
					right++;
				}else{
					System.out.println("�w�����~!�M����w���O"+result+",���T�O="+answer[i]);
				}

			}
			System.out.println("�M������պ�ǭ�="+((float)right/(float)ocr.getTestingDataMat().rows())*100+"%");
		
			//again test 7
			 result=dtree.predict(ocr.sample7);
			 System.out.println("�w��7���G="+result);
		
	
	}
}
