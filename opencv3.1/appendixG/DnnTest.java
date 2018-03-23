package test;
import org.opencv.core.*;
import org.opencv.dnn.*;
import org.opencv.imgproc.*;
import org.opencv.imgcodecs.*;

public class DnnTest {
	static{ System.loadLibrary(Core.NATIVE_LIBRARY_NAME); }
	static String[] PASCIFARnames = new String[]{"airoplane","bicycle","bird","boat","bottle","bus","car","cat","chair","cow", "diningtable","dog","horse","motorbike","person",
            "pottedplant","sheep","sofa","train","tvmonitor"         };
    
	public static void main(String[] args) {        

        Net net = Dnn.readNetFromDarknet("C:\\opencv3.4.1\\samples\\tiny-yolo-voc.cfg", "C:\\opencv3.4.1\\samples\\tiny-yolo-voc.weights");
        if ( net.empty() ) {
            System.out.println("�����J�S�x���O��!");
        }

        String image_file = "C:\\opencv3.4.1\\samples\\004545.jpg";
        Mat im = Imgcodecs.imread(image_file, Imgcodecs.IMREAD_COLOR);
        if( im.empty() ) {
            System.out.println("Reading Image error");
        }

        Mat frame = new Mat();
        Size sz1 = new Size(im.cols(),im.rows());
        Imgproc.resize(im, frame, sz1);

        Mat resized = new Mat();
        //Size sz = new Size(416,416);
        Size blockSize = new Size(416,416);//146person
        Imgproc.resize(im, resized, blockSize);

        float scale = 1.0F / 255.0F;
        Mat inputBlob = Dnn.blobFromImage(im, scale, blockSize, new Scalar(0), false, false);
        net.setInput(inputBlob, "data"); 
        Mat detectionMat = net.forward("detection_out");
        if( detectionMat.empty() ) {
            System.out.println("No result");
        }

        for (int i = 0; i < detectionMat.rows(); i++)
        {           
            int probability_index = 5;
            int size = (int) (detectionMat.cols() * detectionMat.channels());

            float[] data = new float[size];
            detectionMat.get(i, 0, data);
            float confidence = -1;
            int objectClass = -1;
            for (int j=0; j < detectionMat.cols();j++)
            {   
                if (j>=probability_index && confidence<data[j])
                {
                    confidence = data[j];
                    objectClass = j-probability_index;
                }
            }

            if (confidence > 0.5)
            {
                System.out.println("Result Object: "+i);
                for (int j=0; j < detectionMat.cols();j++)
                     System.out.print(" "+j+":"+ data[j]);
                System.out.println("");
                float x = data[0];
                float y = data[1];
                float width = data[2];
                float height = data[3];
                float xLeftBottom = (x - width / 2) * frame.cols();
                float yLeftBottom = (y - height / 2) * frame.rows();
                float xRightTop = (x + width / 2) * frame.cols();
                float yRightTop = (y + height / 2) * frame.rows();

                System.out.println("Class: "+ PASCIFARnames[objectClass]);
                System.out.println("Confidence: "+confidence);
                System.out.println("ROI: "+xLeftBottom+" "+yLeftBottom+" "+xRightTop+" "+yRightTop+"\n");
                //ø�X��
                Imgproc.rectangle(frame, new Point(xLeftBottom, yLeftBottom),
                        new Point(xRightTop,yRightTop),new Scalar(0, 255, 0),3);
                //ø�X���O
                Imgproc.putText(frame, PASCIFARnames[objectClass], new Point(xLeftBottom+10, yLeftBottom+3),3, 0.6,new Scalar(0, 0, 05));
            }
        }

        Imgcodecs.imwrite("C:\\opencv3.4.1\\samples\\004545out.jpg", frame );

    }
}

/**
�ƧѡG�Y�}�oWEB���ε{�ǡAOpenCV�� - * DLL�ݩ�m��Tomcat���B��һ�JRE��BIN�ؿ��U�A�_�h�L�k�[���M�եά����w�C
https://pjreddie.com/darknet/yolo/ download
**/