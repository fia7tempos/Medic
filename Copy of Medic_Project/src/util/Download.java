package util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Download extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(Download.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uploadPath = "C:/dev64/upload";
		String filename = request.getParameter("filename");
		
		
		response.setContentType("application/octet-stream");
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		
		
		byte[] data = null;
		InputStream is = null;
		OutputStream os = null;
		try {
			File file = new File(uploadPath+"/"+filename);
			data = new byte[1024 * 10];
			if(file.exists()){
				is = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
			}else{
				is = new BufferedInputStream(new FileInputStream("C:/dev64/workspace/Medic_Project/WebContent/img/noimage.gif"));
			}
			os = new BufferedOutputStream(response.getOutputStream());
			while (is.read(data) != -1) { // 끝나면 -1
				os.write(data);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		if (os != null)	try {os.flush();os.close();	} catch (Exception e) {	};
		if (is != null)	try {is.close();} catch (Exception e) {};
	}

}
