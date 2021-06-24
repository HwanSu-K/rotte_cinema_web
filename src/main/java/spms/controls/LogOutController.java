package spms.controls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

//HttpStatus 사용 
@Controller
@SessionAttributes("customer")
public class LogOutController {

	@RequestMapping("/logout.do")
	public String execute(SessionStatus status) throws Exception {
		status.setComplete();
		return "redirect:/main.do";
	}
}
