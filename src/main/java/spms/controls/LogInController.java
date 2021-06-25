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
public class LogInController {

	CustomerDao customerDao;

	@Autowired
	public LogInController setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginForm(@CookieValue(value = "email", required = false) String email, Map<String, Object> model) throws Exception {
		if (email != null) {
			model.put("email", email);
			model.put("saveEmailState", "checked");
		}
		return "/cinema/page/LoginForm.jsp";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(String email, String password, String saveEmail, HttpServletResponse response, Map<String, Object> model) throws Exception {
		System.out.println(saveEmail);
		Cookie cookie = null;
		if (saveEmail != null) {
			cookie = new Cookie("email", email);
			cookie.setMaxAge(60 * 60 * 24 * 365); // 365일 동안 쿠키 보관
		} else {
			cookie = new Cookie("email", "xxxx");
			cookie.setMaxAge(0);
		}
		response.addCookie(cookie);
		Customer customer = customerDao.exist(email, password);
		if (customer != null) {
			model.put("customer", customer);
			return "redirect:/main.do";

		} else {
			return "redirect:/login.do";
		}
	}
}
