package ch13;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.ml.KNearest;
import org.opencv.ml.Ml;


public class Ch13_7_3KnnForOcr {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	
	public static void main(String[] args) {
		OcrDatabase ocr=new OcrDatabase();
		Mat results=new Mat(); 
		Mat neighborResponses=new Mat();
		Mat dist=new Mat();
		
		KNearest knn = KNearest.create();
		//knn.setDefaultK(10);
		//knn.setIsClassifier(true);
		boolean r=knn.train(ocr.getTrainingDataMat(), Ml.ROW_SAMPLE, ocr.getTrainingLabelsMat());
		
		
		System.out.println("�O�_���V�m���\="+r);
		
		//�H�K��3�մ���
		
			float result0=knn.predict(ocr.getTestSample0FrTestMat());
			System.out.println("KNN�w��result0="+result0+"��");
			
			float result1=knn.predict(ocr.getTestSample1FrTestMat());
			System.out.println("KNN�w��result1="+result1+"��");

			float result2=knn.predict(ocr.getTestSample2FrTestMat());
			System.out.println("KNN�w��result2="+result2+"��");
		
		//���պ�ǭ�
				
			//�w�����T�֥[1
			int right=0;
			float result;
			float[] answer=ocr.getTestingLabels();
			//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
			for(int i=0;i<ocr.getTestingDataMat().rows();i++){
			
				//System.out.println(iris.getTestingDataMat().row(i).dump());
			    // result=knn.predict(ocr.getTestingDataMat().row(i));
				 result=knn.findNearest(ocr.getTestingDataMat().row(i), 10, results, neighborResponses, dist);
				if(result==answer[i]){
					right++;
				}else{
					System.out.println("�w�����~!KNN�w���O"+result+",���T�O="+answer[i]);
				}

			}
			//100%
			System.out.println("KNN���պ�ǭ�="+((float)right/(float)ocr.getTestingDataMat().rows())*100+"%");
			
			//again test 7
			 result=knn.predict(ocr.sample7);
			 System.out.println("�w��7���G="+result);
	}
}
