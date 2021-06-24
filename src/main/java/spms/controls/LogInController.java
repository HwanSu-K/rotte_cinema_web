package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	public String loginForm(Map<String, Object> model) throws Exception {
		return "/cinema/page/LoginForm.jsp";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(String email, String password, Map<String, Object> model) throws Exception {
		Customer customer = customerDao.exist(email, password);
		if (customer != null) {
			model.put("customer", customer);
			return "redirect:/main.do";

		} else {
			return "redirect:/login.do";
		}
	}
}
