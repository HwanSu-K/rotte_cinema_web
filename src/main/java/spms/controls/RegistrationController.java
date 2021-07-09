package spms.controls;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import spms.dao.CustomerDao;
import spms.etc.Rand;
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
	public String loginForm(String key, Map<String, Object> model) throws Exception {
		if(key != null && !key.equals(""))
		{
			Customer customer = customerDao.selectOneKey(new Customer().setKey(key)); 
			if(customer != null) {
				customerDao.updateState(new Customer().setKey(key));
				customer.setKey(Rand.code());
				customerDao.updateKey(customer);
				return "redirect:/login.do";
			}
		}
		return "/cinema/page/RegistrationForm.jsp";
	}
	
	@RequestMapping(value = "/loginwarning.do", method = RequestMethod.GET)
	public String warningForm(Map<String, Object> model) throws Exception {

		
		return "/cinema/page/RegistrationWaitForm.jsp";
	}
}
