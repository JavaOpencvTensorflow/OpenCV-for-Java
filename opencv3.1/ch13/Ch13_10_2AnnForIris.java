package ch13;

import org.opencv.core.Core;
import org.opencv.core.Core.MinMaxLocResult;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.ml.ANN_MLP;
import org.opencv.ml.Ml;

//�x��������http://docs.opencv.org/3.0-beta/modules/ml/doc/neural_networks.html
public class Ch13_10_2AnnForIris {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
		
		IrisDatabase iris=new IrisDatabase();
		
		ANN_MLP ann=ANN_MLP.create();
		
		Mat layerSize=new Mat(3,1,CvType.CV_32SC1);
		int[] layerSizeAry={4, 10, 3};
		layerSize.put(0,0,layerSizeAry[0]);
		layerSize.put(1,0,layerSizeAry[1]);
		layerSize.put(2,0,layerSizeAry[2]);
//		layerSize.put(0,3,layerSizeAry[3]);
		//System.out.println(layerSize.dump());		
		
		ann.setLayerSizes(layerSize);
		//ann.setTrainMethod(ann.BACKPROP);
	  	TermCriteria criteria=new TermCriteria(TermCriteria.MAX_ITER|TermCriteria.EPS, 300, 0.001);
		ann.setTrainMethod(ann.RPROP);
		ann.setTermCriteria(criteria);//����:97.77778%,open:100%
//			ann.setBackpropMomentumScale(0.01);
//			ann.setBackpropWeightScale(0.01);
//			ann.setRpropDW0(0.1);
//			ann.setRpropDWPlus(1.2);
//			ann.setRpropDWMinus(0.5);
//			ann.setRpropDWMin(1);
//			ann.setRpropDWMax(50);
			
			ann.setActivationFunction(ann.SIGMOID_SYM);//�n�bset layer����
		
		boolean r=ann.train(iris.getTrainingDataMat(), Ml.ROW_SAMPLE, iris.getTrainingLabelsFloatMat());
		System.out.println("�O�_���V�m���\="+r);
		Mat results=new Mat(); 
		
		
		//�H�K��3�մ���
		
		float result0=ann.predict(iris.getTestSample0FrTestMat());
		System.out.println("�����g�����w��result0="+result0+"��");
		
		float result1=ann.predict(iris.getTestSample1FrTestMat());
		System.out.println("�����g�����w��result1="+result1+"��");

		float result2=ann.predict(iris.getTestSample2FrTestMat());
		System.out.println("�����g�����w��result2="+result2+"��");
		

		//���պ�ǭ�
		
		//�w�����T�֥[1
		int right=0;
		MinMaxLocResult minMaxLocResult;
		float[] answer=iris.getTestingLabels();//for ann�]�i
		//System.out.println("�ռ�"=iris.getTestingDataMat().rows());
		for(int i=0;i<iris.getTestingDataMat().rows();i++){
		
			//System.out.println(iris.getTestingDataMat().row(i).dump());
			float result=ann.predict(iris.getTestingDataMat().row(i));
			if(result==answer[i]){
				right++;
			}else{
				System.out.println("�w�����~!�����g�����w���O"+result+",���T�O="+answer[i]);
			}
			
			
		}
		System.out.println("�����g�������պ�ǭ�="+((float)right/(float)iris.getTestingDataMat().rows())*100+"%");

	}

}
