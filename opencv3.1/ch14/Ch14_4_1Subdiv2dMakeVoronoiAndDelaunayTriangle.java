package ch14;

import java.util.ArrayList;
import java.util.List;

import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfFloat6;
import org.opencv.core.MatOfInt;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.imgproc.Subdiv2D;
import org.opencv.core.Point;
import org.opencv.core.Rect;

public class Ch14_4_1Subdiv2dMakeVoronoiAndDelaunayTriangle {
	 static{System.loadLibrary(Core.NATIVE_LIBRARY_NAME);}

	    public static void main(String[] args) {      
	    	
	    	//�w�qdelaunay���C��
	    	Scalar delaunay_color=new Scalar(255, 255, 255);
	    	
	    	//�o�̶}�l,�ǳƿ�J�H������100��0-600�H���ƪ����͡C�ϥ�java�e��List����100�ӦbSubdiv2D ��Point2f data
	    	MatOfPoint2f obj=new MatOfPoint2f();
	    	List<Point> allPoints=new ArrayList<Point>();
	       	for(int j=0;j<100;j++){
	    		int x=(int)(Math.random()*599)+1;
	    		int y=(int)(Math.random()*599)+1;
	    		allPoints.add(new Point(x,y));
	    	}
	  
	        //�ŧiSubdiv2D��Ҥ�,�èϥδ��J�\��,�N100�I�������Subdiv2D
	    	Rect rect=new Rect(0,0,600, 600);
	    	Subdiv2D subdiv=new Subdiv2D(rect);
	    	obj.fromList(allPoints);
	    	subdiv.insert(obj);
	   
	    	//�qSubdiv2D��Ҥƥh���Voronoi��,�ϥ�getVoronoiFacetList method
	    	//facetLists�O�����I�C��Voronoi�ϰ�C���AfacetCenters�N��Voronoi�ϰ쪺���I�C��C
	    	//IDX���n�Ĩ���Voronoi�ϰ쪺ID�C��C��MatOfInt���c.
	    	MatOfInt idx=new MatOfInt();
	    	List<MatOfPoint2f> facetList=new ArrayList<MatOfPoint2f>();
	    	MatOfPoint2f facetCenters=new MatOfPoint2f();
	    	   	
	    	subdiv.getVoronoiFacetList(idx, facetList, facetCenters);
	    	//System.out.println(facetList.size()+","+idx.dump()+","+facetCenters.dump()); 
	    	
	    	//�}�lø�sVoronoi�Ϫ��I�۵��X�C�H���I��ø�ϥHcircle�����I���
	    	Mat img1 = Mat.zeros(600, 600, CvType.CV_8UC3);
	    	Mat img2 = Mat.zeros(600, 600, CvType.CV_8UC3);
	    	for(int j=0;j<100;j++){
	    		Point tmpPoint=allPoints.get(j);
	    		
	    		Imgproc.circle(img1, tmpPoint,4, new Scalar(0,0,255), -1);
	    		Imgproc.circle(img2, tmpPoint,4, new Scalar(0,0,255), -1);
	    	}
	    	
	    	//�}�lø�sVoronoi��,�h��κ��u����
	    	Point before=new Point();
	    	for(int i=0;i<facetList.size();i++){
	    		
	    		MatOfPoint2f tmpMatOfPoint2f=facetList.get(i);
	    		//for(int k=0;k<tmpMatOfPoint2f.)
	    		System.out.println(tmpMatOfPoint2f.dump());
	    		//System.out.println(tmpMatOfPoint2f.rows());
	    		for(int k=0;k<tmpMatOfPoint2f.rows();k++){
	    			double dataX=tmpMatOfPoint2f.get(k, 0)[0];
	    			double dataY=tmpMatOfPoint2f.get(k, 0)[1];
	    			//System.out.println("x-"+dataX);
	    			//System.out.println("y-"+dataY);
	    			
	    			Point currentPoint=new Point(dataX,dataY);
	    			if(k>0){
	    			Imgproc.line(img1, before, currentPoint, new Scalar(64,255,128));
	    			}
	    			before=currentPoint;
	    			
	    		}
	    	}
	    	
	    	  Imgcodecs.imwrite("C://opencv3.1//samples//Subdiv2dMakeVoronoi.jpg", img1);
	    	
	    	  
	    	//ø�XDelaunay�T�������A�T���Ϊ��T�ӳ��I�i�@��Vec6f�o��b�@�_,�N�U�I�s����1�ӤT���ΡC  
	      	MatOfFloat6 triangleList = new MatOfFloat6();
	  	    subdiv.getTriangleList(triangleList);
	      	//System.out.println(triangleList.dump());
	      	
	  	    for(int j=0;j<triangleList.rows();j++){
	  	    	
	  	    	double dataU=triangleList.get(j, 0)[0];
	  			double dataV=triangleList.get(j, 0)[1];
	  			double dataW=triangleList.get(j, 0)[2];
	  			double dataX=triangleList.get(j, 0)[3];
	  			double dataY=triangleList.get(j, 0)[4];
	  			double dataZ=triangleList.get(j, 0)[5];
	  			Point p1=new Point(dataU,dataV);
	  			Point p2=new Point(dataW,dataX);
	  			Point p3=new Point(dataY,dataZ);
	  			
	  			Imgproc.line(img2, p1, p2, new Scalar(64,255,128));
	  			Imgproc.line(img2, p2, p3, new Scalar(64,255,128));
	  			Imgproc.line(img2, p3, p1, new Scalar(64,255,128));
	  	    //System.out.println(dataU+","+dataV+","+dataW+","+dataX+","+dataY+","+dataZ);	
	  	    }
	  	  Imgcodecs.imwrite("C://opencv3.1//samples//Subdiv2dMakeTriangle.jpg", img2);
	    	
	    }

}
