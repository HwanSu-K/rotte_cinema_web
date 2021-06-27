package spms.controls;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import spms.dao.CustomerDao;
import spms.vo.Customer;

@Controller
@SessionAttributes("customer")
public class RegistrationController {

	CustomerDao customerDao;

	@Autowired
	public RegistrationController setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}

	@RequestMapping(value = "/registration.do", method = RequestMethod.GET)
	public String loginForm(Map<String, Object> model) throws Exception {
		return "/cinema/page/RegistrationForm.jsp";
	}

	@RequestMapping(value = "/registration.do", method = RequestMethod.POST)
	public String login(Map<String, Object> model) throws Exception {

		return "redirect:/login.do";
		
	}
}
