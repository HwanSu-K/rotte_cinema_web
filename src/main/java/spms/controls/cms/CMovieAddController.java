package spms.controls.cms;

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
public class CMovieAddController {
	MovieDao movieDao;

	@Autowired
	public CMovieAddController setMemberDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping(value = "/cms/movie/add.do", method = RequestMethod.GET)
	public String addForm() throws Exception {
		return "/cms/MovieAddForm.jsp";
	}

	@RequestMapping(value = "/cms/movie/add.do", method = RequestMethod.POST)
	public String add(MultipartHttpServletRequest request, Movie movie) throws Exception {

		MultipartFile imageFile = request.getFile("image");

		String realPath = request.getSession().getServletContext().getRealPath("/images/poster/");
		String fileName = imageFile.getOriginalFilename();
		movie.setPoster(fileName);
		
		// 업로드 된 경우에만 객체에 삽입
		if (imageFile.getSize() > 0) {	

			File target = new File(realPath, fileName);

			// 경로 생성
			if (!new File(realPath).exists()) {
				new File(realPath).mkdirs();
			}
			// 파일 복사
			try {
				FileCopyUtils.copy(imageFile.getBytes(), target);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		movieDao.insert(movie);

		return "redirect:list.do";
	}
}
