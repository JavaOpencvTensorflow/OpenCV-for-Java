package ch13;

import java.util.ArrayList;
import java.util.List;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.TermCriteria;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.utils.Converters;

public class Ch13_12_6KmeanClassificationForOCR {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }

	public static void main(String[] args) {
		OcrDatabase ocr=new OcrDatabase();
		
		 Mat labels=new Mat();
		 TermCriteria criteria = new TermCriteria(TermCriteria.COUNT, 10, 1);
		 Core.kmeans(ocr.getTrainingDataMat(), 10, labels, criteria, 3, Core.KMEANS_PP_CENTERS);
		 // 100-13/100,��Core.KMEANS_RANDOM_CENTERS�]�@��
		 System.out.println("labels="+labels.dump());
	     
		 
		 // �S�N�q,�]���u��10�Ӽ˥�,��10��
		 /*
		  
		 labels=new Mat();
		 Core.kmeans(ocr.getTestingDataMat(), 10, labels, criteria, 3, Core.KMEANS_PP_CENTERS);
		 System.out.println("labels="+labels.dump());
		*/
	}
}
