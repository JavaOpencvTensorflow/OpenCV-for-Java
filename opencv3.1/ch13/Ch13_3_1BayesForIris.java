package ch13;

import org.opencv.core.Core;
import org.opencv.ml.Ml;
import org.opencv.ml.NormalBayesClassifier;


public class Ch13_3_1BayesForIris {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	
	public static void main(String[] args) {
		IrisDatabase iris=new IrisDatabase();

		NormalBayesClassifier nBayes=NormalBayesClassifier.create();
		
		boolean r=nBayes.train(iris.getTrainingDataMat(), Ml.ROW_SAMPLE, iris.getTrainingLabelsMat());
		
		System.out.println("�O�_���V�m���\="+r);
		
		//�H�K��3�մ���
		
			float result0=nBayes.predict(iris.getTestSample0FrTestMat());
			System.out.println("�`�A�������������w��result0="+result0+"��");
			
			float result1=nBayes.predict(iris.getTestSample1FrTestMat());
			System.out.println("�`�A�������������w��result1="+result1+"��");

			float result2=nBayes.predict(iris.getTestSample2FrTestMat());
			System.out.println("�`�A�������������w��result2="+result2+"��");
		
		//���պ�ǭ�
			
			//�w�����T�֥[1
			int right=0;
			float result;
			float[] answer=iris.getTestingLabels();
			//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
			for(int i=0;i<iris.getTestingDataMat().rows();i++){
			
				//System.out.println(iris.getTestingDataMat().row(i).dump());
			 result=nBayes.predict(iris.getTestingDataMat().row(i));
				if(result==answer[i]){
					right++;
				}else{
					System.out.println("�w�����~!�`�A�������������w���O"+result+"���T�O="+answer[i]);
				}

			}
			System.out.println("�`�A���������������պ�ǭ�="+((float)right/(float)iris.getTestingDataMat().rows())*100+"%");
	}
}
