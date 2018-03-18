
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

/**
 * Servlet implementation class showImg
 */
@WebServlet("/doOpencvBlur")
public class doOpencvBlur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public doOpencvBlur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			int ksize=Integer.parseInt(request.getParameter("ksize"));
			System.out.println("ksize="+ksize);
			Mat source = Imgcodecs.imread("C://opencv3.1//samples//lena.jpg");
			Mat destination=new Mat(source.rows(),source.cols(),source.type());
	        
			 Imgproc.GaussianBlur(source, destination,new Size(ksize,ksize),0,0);
			MatOfByte buffer=new MatOfByte();
			Imgcodecs.imencode(".jpg", destination, buffer);
			byte data1[]=buffer.toArray();
			response.setContentType("image/jpeg"); //�]�m��^���������
			OutputStream toClient=response.getOutputStream(); //�o��V�Ȥ�ݿ�X�G�i��ƾڪ���H
			toClient.write(data1); //��X�ƾ�
			toClient.close();
			}
			catch(IOException e) //���~�B�z
			{
			 PrintWriter toClient = response.getWriter(); //�o��V�Ȥ�ݿ�X�奻����H
			 response.setContentType("text/html;charset=big5");
			 toClient.write("�L�k���}�Ϥ�!");
			 toClient.close();
			}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
