package demoProjectController;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import demoProjectEntity.StudentEntity;

@Controller
public class FormController {
	
	@RequestMapping("/complexForm")
	public String showForm() {
		return "form";
	}
	
	@RequestMapping(path="/handle", method = RequestMethod.POST)
	public String formHandler(@ModelAttribute("student") StudentEntity student, BindingResult result) {
		
		if(result.hasErrors()) {
			return "form";
		}
		
		System.out.println(student);
		System.out.println(student.getAddress());
		return "success";
	}
}
