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
	public String execute(Map<String, Object> model) throws Exception {

		return "/cinema/page/FinderForm.jsp";
	}
}
