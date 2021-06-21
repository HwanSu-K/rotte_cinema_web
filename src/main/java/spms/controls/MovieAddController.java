package spms.controls;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spms.dao.MovieDao;
import spms.vo.Movie;

//@RequestParam 적용
@Controller
public class MovieAddController {
	MovieDao movieDao;

	@Autowired
	public MovieAddController setMemberDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping(value = "/cms/movie/add.do", method = RequestMethod.GET)
	public String addForm() throws Exception {
		return "/cms/MovieAddForm.jsp";
	}

	@RequestMapping(value = "/cms/movie/add.do", method = RequestMethod.POST)
	public String add(MultipartHttpServletRequest request, Movie movie) throws Exception {

		MultipartFile file = request.getFile("file");

		String realPath = request.getSession().getServletContext().getRealPath("/images/poster/");
		String fileName = file.getOriginalFilename();
		movie.setPoster(fileName);

		File target = new File(realPath, fileName);

		// 경로 생성
		if (!new File(realPath).exists()) {
			new File(realPath).mkdirs();
		}
		// 파일 복사
		try {
			FileCopyUtils.copy(file.getBytes(), target);
		} catch (Exception e) {
			e.printStackTrace();
		}

		movieDao.insert(movie);

		return "redirect:list.do";
	}
}
