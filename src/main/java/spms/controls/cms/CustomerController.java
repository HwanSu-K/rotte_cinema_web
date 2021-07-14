package spms.controls.cms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.CustomerDao;

//@RequestParam 적용
@Controller
public class CustomerController {
	CustomerDao customerDao;

	@Autowired
	public CustomerController setBannerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}

	@RequestMapping("/cms/customer/list.do")
	public String execute(Map<String, Object> model) throws Exception {
	
		model.put("customers", customerDao.selectList());
		return "/cms/CustomerListForm.jsp";
	}
}
