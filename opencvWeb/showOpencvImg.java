
import java.awt.image.BufferedImage;
import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;

/**
 * Servlet implementation class showImg
 */
@WebServlet("/showOpencvImg")
public class showOpencvImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showOpencvImg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
			//System.load(arg0);
			
			Mat source = Imgcodecs.imread("C://opencv3.1//samples//lena.jpg");
			MatOfByte buffer=new MatOfByte();
			Imgcodecs.imencode(".jpg", source, buffer);//��v���s�X�åB��m��w�İO����
			byte data1[]=buffer.toArray();//��}�C
			response.setContentType("image/jpeg"); //�]�m��^���������
			OutputStream toClient=response.getOutputStream(); //�o��V�Ȥ�ݿ�X�G�i��ƾڪ���H
			toClient.write(data1); //��X�ƾ�.web���@�k
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
