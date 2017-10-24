package com.control.production;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.file.manager.ImgCompress;
import com.service.interfaces.ProductionServiceInterface;
import com.service.production.ProductionService;
import com.until.errorcode.MAGICCODE;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Servlet implementation class FileUpLoad
 */
@WebServlet("/FileUpload")
public class FileUpload extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
//	static Logger logger = LogManager.getLogger();

	// 后期做单独的存储管理
	// private

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FileUpload()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// TODO Auto-generated method stub

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("deprecation")
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String filePath = "/srv/www/htdocs/shop/images/";
		request.setCharacterEncoding("utf-8"); // 设置编码
		HttpSession session = request.getSession();
//		String userID = (String) session.getAttribute("userID");
		String keyID = "ccc65483";
		String productID = request.getParameter("uuid");
		String token = "ccc65483";
		List<String> fileNameList = new ArrayList<String>();
		
		String uploadType = request.getParameter("upload");
        filePath = filePath + keyID + "/" + productID;

		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// 命名应该为filePath+从session中获取的作者ID，+/从session中获取的当前作品ID
		// String realPath =

		// 获取文件要上传到的路径
		// String path = request.getRealPath("/upload");
		File folder = new File(filePath);
		if (!folder.exists())
		{
			folder.mkdirs();
		}

		factory.setRepository(new File(filePath));
		// 设置 缓存的大小
		factory.setSizeThreshold(1024 * 1024);

		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);

		try
		{	
			// 可以上传多个文件
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);

//			if(logger.isDebugEnabled()){
//	        	logger.debug("productID:"+productID);
//	        	logger.debug("filePath:"+filePath);
//	        }
			
			for (FileItem item : list)
			{

				// 获取表单的属性名
				String name = item.getFieldName();

				if (item.isFormField())
				{
					String value = item.getString();
					request.setAttribute(name, value);
//					if(logger.isDebugEnabled()){
//			        	logger.debug("FormField fieldName:"+name);
//			        	logger.debug("FormField value:"+value);
//			        }
				} else
				{
					String value = item.getName();
					int start = value.lastIndexOf("\\");
					String filename = value.substring(start + 1);
					request.setAttribute(name, filename);
					
					OutputStream out = new FileOutputStream(new File(filePath, filename));
					String picFile = filePath +"/";
					picFile += filename;
					fileNameList.add(picFile);
					InputStream in = item.getInputStream();
					
//					if(logger.isDebugEnabled()){
//			        	logger.debug("notFormField fieldName:"+name);
//			        	logger.debug("notFormField value:"+value);
//			        	logger.debug("notFormField filename:"+filename);
//			        }

					int length = 0;
					byte[] buf = new byte[1024];

					while ((length = in.read(buf)) != -1)
					{

						out.write(buf, 0, length);

					}

					in.close();
					out.close();
//					i++;
					
					
					if(!("idcard".equals(uploadType)))
					{
		
						int result = compress(filePath,filename);
						if(0!=result)
						{
							//do something
						}
					}
					
				}

			}
			
			ProductionServiceInterface prodcutionService = new ProductionService();
			int result = prodcutionService.createProductionPic(productID, fileNameList, "", keyID);
			if(MAGICCODE.OK != result)
			{
			    
			}
			

		} catch (FileUploadException e)
		{
//			logger.error(e);;
		} catch (Exception e1)
		{
//			logger.error(e1);;
		} catch (Throwable throwable)
		{
//			logger.error(throwable);;
		}

	}
	
	public int compress(String filePath,String filename)
	{
		//压缩文件路径
		String compressSmallPath = filePath + "/smallcompress";
		File compressSmallFolder = new File(compressSmallPath);
		if (!compressSmallFolder.exists())
		{
			compressSmallFolder.mkdirs();
		}
		
		String compressMidPath = filePath + "/midcompress";
		File compressMidFolder = new File(compressMidPath);
		if (!compressMidFolder.exists())
		{
			compressMidFolder.mkdirs();
		}
		
		//压缩
		String oldName = filePath + "/" +filename;
		String newSmallName = compressSmallPath + "/" +filename;
		String newMidName = compressMidPath + "/" +filename;
		ImgCompress small;
		try
		{
			small = new ImgCompress(oldName,newSmallName);
			small.resizeFix(100, 100);
			
			ImgCompress mid = new ImgCompress(oldName,newMidName);
			mid.resizeFix(380, 380);
		} catch (IOException e)
		{
//			logger.error(e);;
			return 1;
		} catch (Throwable throwable)
		{
//			logger.error(throwable);;
			return 1;
		}
		return 0;

	}
	
}
