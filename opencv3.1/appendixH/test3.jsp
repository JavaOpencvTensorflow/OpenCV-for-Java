<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ page import="org.opencv.core.Core,org.opencv.core.CvType,org.opencv.core.Mat,org.opencv.core.Scalar"  %> 
   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
test!<br>
<%

Mat m1 = new Mat(2, 2, CvType.CV_32FC1);
m1.put(0, 0, 1);
m1.put(0, 1, 2);
m1.put(1, 0, 3);
m1.put(1, 1, 4);

out.println("�x�}m1�����="+m1.cols()+"<br>");
out.println("�x�}m1���C��="+m1.rows()+"<br>");
out.println("�x�}m1�Ҧ������Ӽ�="+m1.total()+"<br>");
out.println("�x�}m1��Size="+m1.size()+"<br>");
out.println("�x�}m1(1,1)������="+m1.get(0,0)[0]+"<br>");
out.println("�x�}m1(2,2)������="+m1.get(1,1)[0]+"<br>");
out.println("�x�}m1�Ҧ�����="+m1.dump()+"<br>");

%>
</body>
</html>