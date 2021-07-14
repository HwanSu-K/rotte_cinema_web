package spms.controls.cms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spms.dao.CinemaDao;
import spms.vo.Cinema;

//@RequestParam 적용
@Controller
public class CinemaController {
	CinemaDao cinemaDao;

	@Autowired
	public CinemaController setMemberDao(CinemaDao cinemaDao) {
		this.cinemaDao = cinemaDao;
		return this;
	}

	@RequestMapping(value = "/cms/cinema/add.do", method = RequestMethod.GET)
	public String addGet(Map<String, Object> model) throws Exception {
		model.put("locals", cinemaDao.selectListLocal());

		return "/cms/CinemaAddForm.jsp";
	}

	@RequestMapping(value = "/cms/cinema/add.do", method = RequestMethod.POST)
	public String addPost(Cinema cinema) throws Exception {

		cinemaDao.insert(cinema);

		return "redirect:list.do";
	}
	
	@RequestMapping("/cms/cinema/list.do")
	public String list(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", "all");
		model.put("cinemas", cinemaDao.selectListDefault(paramMap));
		return "/cms/CinemaListForm.jsp";
	}
	
	@RequestMapping(value = "/cms/cinema/update.do", method = RequestMethod.GET)
	public String updateGet(int no, Map<String, Object> model) throws Exception {
		Cinema detailInfo = cinemaDao.selectOneDefault(no);
		model.put("cinema", detailInfo);
		model.put("locals", cinemaDao.selectListLocal());
		return "/cms/CinemaUpdateForm.jsp";
	}

	@RequestMapping(value = "/cms/cinema/update.do", method = RequestMethod.POST)
	public String updatePost(Cinema cinema) throws Exception {
		
		cinemaDao.update(cinema);

		return "redirect:list.do";
	}
}
