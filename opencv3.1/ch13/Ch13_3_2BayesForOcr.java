package ch13;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.ml.Ml;
import org.opencv.ml.NormalBayesClassifier;


public class Ch13_3_2BayesForOcr {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	
	public static void main(String[] args) {
		OcrDatabase ocr=new OcrDatabase();
		NormalBayesClassifier nBayes=NormalBayesClassifier.create();
		boolean r=nBayes.train(ocr.getTrainingDataMat(), Ml.ROW_SAMPLE, ocr.getTrainingLabelsMat());
		
		System.out.println("�O�_���V�m���\="+r);
		
		//�H�K��3�մ���
		
			float result0=nBayes.predict(ocr.getTestSample0FrTestMat());
			System.out.println("�`�A�������������w��result0="+result0+"��");
			
			float result1=nBayes.predict(ocr.getTestSample1FrTestMat());
			System.out.println("�`�A�������������w��result1="+result1+"��");

			float result2=nBayes.predict(ocr.getTestSample2FrTestMat());
			System.out.println("�`�A�������������w��result2="+result2+"��");
		
		//���պ�ǭ�
			
			//�w�����T�֥[1
			int right=0;
			float result;
			float[] answer=ocr.getTestingLabels();
			//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
			for(int i=0;i<ocr.getTestingDataMat().rows();i++){
			
				//System.out.println(iris.getTestingDataMat().row(i).dump());
			 result=nBayes.predict(ocr.getTestingDataMat().row(i));
				if(result==answer[i]){
					right++;
				}else{
					System.out.println("�w�����~!�`�A�������������w���O"+result+",���T�O="+answer[i]);
				}

			}
		
			System.out.println("�`�A���������������պ�ǭ�="+((float)right/(float)ocr.getTestingDataMat().rows())*100+"%");
		
			//again test 7
			 result=nBayes.predict(ocr.sample7);
			 System.out.println("�w��7���G="+result);
	}
}
