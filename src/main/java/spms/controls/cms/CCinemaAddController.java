package spms.controls.cms;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spms.dao.CinemaDao;
import spms.vo.Cinema;

//@RequestParam 적용
@Controller
public class CCinemaAddController {
	CinemaDao cinemaDao;

	@Autowired
	public CCinemaAddController setMemberDao(CinemaDao cinemaDao) {
		this.cinemaDao = cinemaDao;
		return this;
	}

	@RequestMapping(value = "/cms/cinema/add.do", method = RequestMethod.GET)
	public String addForm(Map<String, Object> model) throws Exception {
		model.put("locals", cinemaDao.selectListLocal());

		return "/cms/CinemaAddForm.jsp";
	}

	@RequestMapping(value = "/cms/cinema/add.do", method = RequestMethod.POST)
	public String add(Cinema cinema) throws Exception {

		cinemaDao.insert(cinema);

		return "redirect:list.do";
	}
}
