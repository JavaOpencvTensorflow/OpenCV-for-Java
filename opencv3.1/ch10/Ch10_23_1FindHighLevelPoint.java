package ch10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.imgcodecs.Imgcodecs;

public class Ch10_23_1FindHighLevelPoint {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	public static void main(String[] args) {
		List <Integer> allY=new ArrayList<Integer>(); 
		
		Mat source=Imgcodecs.imread("C://opencv3.1//samples//datachart.png");
		 
		 for (int j=0;j<source.rows();j++){
        	 for(int i=0;i<source.cols();i++){
        		 double[] temp=source.get(j,i);
        		 if(temp[0]==0 && temp[1]==0 && temp[2]==255){
        			 //System.out.println(temp[0]+","+temp[1]+","+temp[2]);
        			 //System.out.println("x="+i+",y="+j);
        			 allY.add(j);
        		 }
        	 }
        }
		 int minY=Collections.min(allY);
		 
		 //System.out.println("x="+minY);
		 //���]�w��0����m��Y�O328,5����m��Y�O290
		 float currentHighLevel=(float)(328-minY)*5/(328-290);
		 System.out.println("�̰��I��Y�y�ЬO"+minY+",�ƭȬO="+currentHighLevel);
		 
	}

}
