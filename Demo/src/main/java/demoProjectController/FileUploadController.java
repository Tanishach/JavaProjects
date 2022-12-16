package demoProjectController;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Controller
public class FileUploadController {
	
	@RequestMapping("/fileFormUpload")
	public String showUploadForm() {
		return "fileForm";
	}
	
	@RequestMapping(value = "/uploaded", method =RequestMethod.POST)
	public String fileUpload(@RequestParam("profile") CommonsMultipartFile file,  HttpSession s, Model m) {
		System.out.println("done");
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		byte data[] =file.getBytes();
		String path = s.getServletContext().getRealPath("/") + "WEB-INF" + File.separator + "webjars" + File.separator + "images" + File.separator + 
				file.getOriginalFilename();
		System.out.println(path);
		try {
			FileOutputStream fos = new FileOutputStream(path);
			fos.write(data);
			fos.close();
			System.out.println("file uploaded successfully");
			m.addAttribute("msg", "uploaded successfully");
			m.addAttribute("filename", file.getOriginalFilename());
		} catch (IOException e){
			e.printStackTrace();
			System.out.println("Uploading errors");
			m.addAttribute("msg", "uploading error");
			
		}
		
		return "successUpload";
	}
}