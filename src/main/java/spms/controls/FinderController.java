package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spms.dao.CustomerDao;

@Controller
public class FinderController {
	CustomerDao customerDao;

	@Autowired
	public FinderController setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}

	@RequestMapping(value = "/finder.do", method = RequestMethod.GET)
	public String execute(String type, Map<String, Object> model) throws Exception {

		if (type.equals("email")) {
			return "/cinema/page/FinderIdForm.jsp";
		} else {
			return "/cinema/page/FinderPwForm.jsp";
		}

	}

	@RequestMapping(value = "/password.do", method = RequestMethod.GET)
	public String passwordPage(String key, Map<String, Object> model) throws Exception {
		System.out.println(key);
		return "/cinema/page/PassWordForm.jsp";
	}
}
