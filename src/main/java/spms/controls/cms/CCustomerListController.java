package spms.controls.cms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import spms.dao.CustomerDao;

//@RequestParam 적용
@Controller
public class CCustomerListController {
	CustomerDao customerDao;

	@Autowired
	public CCustomerListController setBannerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
		return this;
	}

	@RequestMapping("/cms/customer/list.do")
	public String execute(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		//paramMap.put("view", "all");
		model.put("customers", customerDao.selectList(paramMap));
		return "/cms/CustomerListForm.jsp";
	}
}
