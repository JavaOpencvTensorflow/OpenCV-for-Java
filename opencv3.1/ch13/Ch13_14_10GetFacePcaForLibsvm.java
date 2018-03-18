package ch13;

import java.io.FileWriter;
import java.text.NumberFormat;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;

public class Ch13_14_10GetFacePcaForLibsvm {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
public static void main(String[] args) {
	
	NumberFormat nf =NumberFormat.getInstance();
	nf.setMaximumFractionDigits(18);
	
	
	Mat allDataMat=new Mat(126,400,CvType.CV_32FC1);//14�Hx9�Ӥ�
	 Mat source=new Mat();
	 Mat Mean = new Mat();
	 Mat Vectors = new Mat();
	
	 String fileNameTrain="C:\\TrainFacePCA.txt";
	 String fileNameTest="C:\\TestFacePCA.txt"; 
		 
	 
	 
	 
	 
	 //�H�y�@14�H,�C�H9�i���P�����v���Τ��P��,�䤤8�i���Ӱ��V�m,�t�@ �i�����ըϥ�!
	 //i:14�H,��j=1;j<=8;j++ ��X�V�m�˥��e20��PCA�S�x��
	 //��j=9;j<=9;j++ ��X����/�w���˥��e20��PCA�S�x��
	 
	 int start=0;
	 //����J�C�H8�i�Ӥ�
	 for(int i=1;i<=14;i++){
		 for(int j=1;j<9;j++){
			 
	source = Imgcodecs.imread("C://opencv3.1//samples//yalefaces//"+i+"-"+j+".png", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
	 // System.out.println(i+"-"+j);
     Mat temp=source.reshape(1, 400);//change image's col & row
		for(int k=0;k<400;k++){
			double[] data=new double[1];
			data=temp.get(k, 0);
			allDataMat.put(start, k, data);
			
		}
		 start++;
	 }
 }
	//����J�C�H��9�i�Ӥ�
	 for(int i=1;i<=14;i++){
		 for(int j=9;j<10;j++){
			 
	source = Imgcodecs.imread("C://opencv3.1//samples//yalefaces//"+i+"-"+j+".png", Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);
    // System.out.println(i+"-"+j);
     Mat temp=source.reshape(1, 400);//change image's col & row
		for(int k=0;k<400;k++){
			double[] data=new double[1];
			data=temp.get(k, 0);
			allDataMat.put(start, k, data);
		}
		 start++;
	 }
 }
	 
	 Core.PCACompute(allDataMat.t(), Mean, Vectors,20);
	 Mat allPcaData=Vectors.t();
		//��0~99�C����train
		 Mat subMatTrainPcaFace=allPcaData.submat(0, 112,0, 20);

		 //��100~110�C����test
		 Mat subMatTestPcaFace=allPcaData.submat(112, 126,0,20);
		 
		 //System.out.println("size="+subMatTestPcaFace.size()+","+subMatTestPcaFace.cols()+","+subMatTestPcaFace.rows());
		 
		 int personId=0;
		 try {
			 FileWriter writer=new FileWriter(fileNameTrain);
				
			 for(int i=0;i<subMatTrainPcaFace.rows();i++){//14*8�ӤH
				 
				 personId=i/8;//�C�H8�i��
				 
				 //System.out.print((i+1)+" ");
				 writer.write((personId+1)+" ");
				 for(int j=0;j<subMatTrainPcaFace.cols();j++){//20�ӯS�x
					 //System.out.print((j+1)+":"+nf.format(subMatTestPcaFace.t().get(j, i)[0])+" ");
					 writer.write((j+1)+":"+nf.format(subMatTrainPcaFace.t().get(j, i)[0])+" ");
			     }
				 //System.out.println("");
				 writer.write("\n");
			 }		 
			 writer.close();
			 
			////////////////////////////////////// 
			 
			 
			 
			 writer=new FileWriter(fileNameTest);
			
			 for(int i=0;i<subMatTestPcaFace.rows();i++){//14�ӤH
				 
				 //System.out.print((i+1)+" ");
				 writer.write((i+1)+" ");
				 for(int j=0;j<subMatTestPcaFace.cols();j++){//20�ӯS�x
					 //System.out.print((j+1)+":"+nf.format(subMatTestPcaFace.t().get(j, i)[0])+" ");
					 writer.write((j+1)+":"+nf.format(subMatTestPcaFace.t().get(j, i)[0])+" ");
			     }
				 //System.out.println("");
				 writer.write("\n");
			 }		 
			 writer.close();
			 
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	 
}


}
