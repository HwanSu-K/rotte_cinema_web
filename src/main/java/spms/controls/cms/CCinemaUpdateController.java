package spms.controls.cms;

import java.io.File;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spms.dao.CinemaDao;
import spms.vo.Cinema;
import spms.vo.Movie;

//@RequestParam 적용
@Controller
public class CCinemaUpdateController {
	CinemaDao cinemaDao;

	@Autowired
	public CCinemaUpdateController setMemberDao(CinemaDao cinemaDao) {
		this.cinemaDao = cinemaDao;
		return this;
	}

	@RequestMapping(value = "/cms/cinema/update.do", method = RequestMethod.GET)
	public String updateForm(int no, Map<String, Object> model) throws Exception {
		Cinema detailInfo = cinemaDao.selectOneDefault(no);
		model.put("cinema", detailInfo);
		model.put("locals", cinemaDao.selectListLocal());
		return "/cms/CinemaUpdateForm.jsp";
	}

	@RequestMapping(value = "/cms/cinema/update.do", method = RequestMethod.POST)
	public String update(Cinema cinema) throws Exception {

		
		cinemaDao.update(cinema);

		return "redirect:list.do";
	}
}
