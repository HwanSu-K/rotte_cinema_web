package spms.controls.cms;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spms.dao.LocalDao;
import spms.vo.Cinema;
import spms.vo.Local;

//@RequestParam 적용
@Controller
public class LocalController {
	LocalDao localDao;

	@Autowired
	public LocalController setMemberDao(LocalDao localDao) {
		this.localDao = localDao;
		return this;
	}

	@RequestMapping(value = "/cms/local/add.do", method = RequestMethod.GET)
	public String addGet(Map<String, Object> model) throws Exception {
		model.put("locals", localDao.selectList());

		return "/cms/LocalAddForm.jsp";
	}

	@RequestMapping(value = "/cms/local/add.do", method = RequestMethod.POST)
	public String addPost(Local local) throws Exception {

		localDao.insert(local);

		return "redirect:list.do";
	}
	
	@RequestMapping("/cms/local/list.do")
	public String list(Map<String, Object> model) throws Exception {

		model.put("locals", localDao.selectList());
		return "/cms/LocalListForm.jsp";
	}
	
	@RequestMapping(value = "/cms/local/update.do", method = RequestMethod.GET)
	public String updateGet(int no, Map<String, Object> model) throws Exception {
		Local detailInfo = localDao.selectOne(no);
		model.put("local", detailInfo);
		return "/cms/LocalUpdateForm.jsp";
	}

	@RequestMapping(value = "/cms/local/update.do", method = RequestMethod.POST)
	public String updatePost(Local local) throws Exception {
		
		localDao.update(local);

		return "redirect:list.do";
	}
}
