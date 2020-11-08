package jml;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

/**
 * Servlet implementation class FileServlet
 */
@WebServlet("/FileServlet")
public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// multipart/form-data
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String fileId = "";
		DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload upload = new ServletFileUpload(factory);
        ServletRequestContext servletRequestContext = new ServletRequestContext(request);
		Map<String,FileItem> map = new HashMap<>();
        if(isMultipart)
		{
			 List<FileItem> fileItems = new ArrayList<>();
			try {
				fileItems = upload.parseRequest(servletRequestContext);
				for (FileItem fileItem : fileItems) {
					map.put(fileItem.getFieldName(), fileItem);
	             }
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
             
			FileItem id = map.get("id");
			FileItem file = map.get("file");
			FileItem seek = map.get("seek");
			FileItem fileName = map.get("fileName");
			String value = id.getString("utf-8");
			String value2 = seek.getString("utf-8");
			String value3 = fileName.getString("utf-8");
			String ext = value3.substring(value3.lastIndexOf("."));
			try {
				FileUtils.fileBranch(file.getInputStream(),"D:/text/"+value+ext,Integer.parseInt(value2));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		doGet(request, response);
	}

}
