
import java.io.*;
import java.text.*;
import java.util.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class showImg
 */
@WebServlet("/showImg")
public class showImg extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showImg() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			FileInputStream hFile = new FileInputStream("C:\\opencv3.1\\samples\\lena.jpg"); //�Hbyte�y���覡���}��� d:\1.gif
			int i=hFile.available(); //�o����j�p
			byte data[]=new byte[i];
			hFile.read(data); //Ū�ƾ�
			hFile.close();
			response.setContentType("image/jpeg"); //�]�m��^���������
			OutputStream toClient=response.getOutputStream(); //�o��V�Ȥ�ݿ�X�G�i��ƾڪ���H
			toClient.write(data); //��X�ƾ�
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
