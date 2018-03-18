package ch13;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.ml.Boost;
import org.opencv.ml.DTrees;
import org.opencv.ml.LogisticRegression;
import org.opencv.ml.Ml;


public class Ch13_9_3BoostForOcr {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	
	public static void main(String[] args) {
		Mat trainingLabelsMat=new Mat(20,1,CvType.CV_32SC1);//CV_32FC1�|Exception in thread "main" java.lang.Exception: unknown exception
		Mat trainingDataMat=new Mat(20,144,CvType.CV_32FC1);
		
		float[] trainingLabels={0,0,0,0,0,0,0,0,0,0,1,1,1,1,1,1,1,1,1,1};//[20]
		
		
		Mat source;
		//assign training Mat
		//���յo�{Mat �����Ӥj�|�X�{OpenCV Error: Bad argument (check training parameters. Invalid training classifier) in cv::ml::LogisticRegressionImpl
		//���`�G�C�Ӥ���/10
		for(int i=0;i<20;i++){//���10��0��10��1
			if (i<10){
				source = Imgcodecs.imread("C://opencv3.1//samples//ocr//0"+i+".jpg",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
			}else{
				source = Imgcodecs.imread("C://opencv3.1//samples//ocr//"+i+".jpg",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
			}
			//assign ocr's image data into Mat
			Mat temp=source.reshape(1, 144);//change image's col & row
			for(int j=0;j<144;j++){
				double[] data=new double[1];
				data=temp.get(j, 0);
				trainingDataMat.put(i, j, data);
			}
			
			//assign label data
			trainingLabelsMat.put(i, 0, trainingLabels[i]);
		}
		
		
		Boost boost=Boost.create();
		boost.setBoostType(Boost.DISCRETE);
		boost.setWeakCount(3);
		boost.setMinSampleCount(4);
		boost.setMaxCategories(4);
		boost.setMaxDepth(2);		
//		System.out.println(trainingDataMat.size());
//		System.out.println(trainingLabelsMat.size());
		//System.out.println(trainingDataMat.row(18).dump());
		//System.out.println(trainingLabelsMat.dump());
		boolean r=boost.train(trainingDataMat, Ml.ROW_SAMPLE, trainingLabelsMat);
		
		System.out.println("�O�_���V�m���\="+r);
		
		//�H�K��2�մ���
		Mat results = new Mat();
		int flags = 0;
		Mat zero= handleForML(Imgcodecs.imread("C://opencv3.1//samples//ocr//number0.jpg",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE));
		Mat one= handleForML(Imgcodecs.imread("C://opencv3.1//samples//ocr//number1.jpg",Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE));	
		
		
		boost.predict(zero, results, flags);
			System.out.println("Boost�w��result0="+results.get(0, 0)[0]+",��ڬO0");
			
			boost.predict(one, results, flags);
			System.out.println("Boost�w��result1="+results.get(0, 0)[0]+",��ڬO1");
	
	}
	//���յo�{Mat �����Ӥj�|�X�{OpenCV Error: Bad argument (check training parameters. Invalid training classifier) in cv::ml::LogisticRegressionImpl
    //���`�G�C�Ӥ���/10
	public static Mat handleForML(Mat src){
		Mat handle=new Mat(1,144,CvType.CV_32FC1);
		Mat tempSample=src.reshape(1,144);
		for(int j=0;j<144;j++){
			double[] data=new double[1];
			data=tempSample.get(j, 0);
			handle.put(0, j, data);
		}
		
		return handle;
	}
}
