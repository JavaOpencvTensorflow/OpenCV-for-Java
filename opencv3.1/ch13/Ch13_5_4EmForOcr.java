package ch13;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.ml.DTrees;
import org.opencv.ml.EM;
import org.opencv.ml.Ml;
import org.opencv.ml.NormalBayesClassifier;


public class Ch13_5_4EmForOcr {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	
	public static void main(String[] args) {
		OcrDatabase ocr=new OcrDatabase();
		EM em=EM.create();
		//9,10,11-->20%,2-->10%,3-->0%,4-->30%,5,6,8-->10%
		 em.setClustersNumber(10);
		 em.setCovarianceMatrixType(2);
		 TermCriteria criteria=new TermCriteria(TermCriteria.EPS+TermCriteria.MAX_ITER, 300, 0.1);
		 em.setTermCriteria(criteria);
		 Mat logLikelihoods=new Mat();
		 Mat probs=new Mat();
		
		boolean r=em.train(ocr.getTrainingDataMat(), Ml.ROW_SAMPLE, ocr.getTrainingLabelsMat());
		
		System.out.println("�O�_���V�m���\="+r);
		
		//�H�K��3�մ���
		
			float result0=em.predict(ocr.getTestSample0FrTestMat());
			System.out.println("�̤j�����result0="+result0+"��");
			
			float result1=em.predict(ocr.getTestSample1FrTestMat());
			System.out.println("�̤j�����result1="+result1+"��");

			float result2=em.predict(ocr.getTestSample2FrTestMat());
			System.out.println("�̤j�����result2="+result2+"��");
		
		//���պ�ǭ�
			
			//�w�����T�֥[1
			int right=0;
			float result;
			float[] answer=ocr.getTestingLabels();
			//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
			for(int i=0;i<ocr.getTestingDataMat().rows();i++){
			
				//System.out.println(iris.getTestingDataMat().row(i).dump());
			 result=em.predict(ocr.getTestingDataMat().row(i));
				if(result==answer[i]){
					right++;
				}else{
					System.out.println("�w�����~!�̤j����ȹw���O"+result+",���T�O="+answer[i]);
				}

			}
			//30%
			System.out.println("�̤j����ȴ��պ�ǭ�="+((float)right/(float)ocr.getTestingDataMat().rows())*100+"%");
		
			//again test 7
			 result=em.predict(ocr.sample7);
			 System.out.println("�w��7���G="+result);
		
	
	}
}
