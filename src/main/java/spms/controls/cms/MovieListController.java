package spms.controls.cms;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class MovieListController {
	MovieDao movieDao;

	@Value("${upload.path}")
	private String path;
	
	@Autowired
	public MovieListController setMemberDao(MovieDao movieDao) {
		this.movieDao = movieDao;
		return this;
	}

	@RequestMapping(value = "/cms/movie/add.do", method = RequestMethod.GET)
	public String addGet() throws Exception {
		return "/cms/MovieAddForm.jsp";
	}

	@RequestMapping(value = "/cms/movie/add.do", method = RequestMethod.POST)
	public String addPost(MultipartHttpServletRequest request, Movie movie) throws Exception {

		MultipartFile imageFile = request.getFile("image");

		String realPath = path + "/poster/"; //request.getSession().getServletContext().getRealPath("/images/poster/");
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
	
	@RequestMapping("/cms/movie/list.do")
	public String list(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("type", "all");
		model.put("movies", movieDao.selectList(paramMap));
		return "/cms/MovieListForm.jsp";
	}
	
	@RequestMapping(value = "/cms/movie/update.do", method = RequestMethod.GET)
	public String updateGet(int no, Map<String, Object> model) throws Exception {
		Movie detailInfo = movieDao.selectOne(no);
		model.put("movie", detailInfo);

		return "/cms/MovieUpdateForm.jsp";
	}
	
	@RequestMapping(value = "/cms/movie/update.do", method = RequestMethod.POST)
	public String updatePost(MultipartHttpServletRequest request, Movie movie) throws Exception {

		MultipartFile imageFile = request.getFile("image");
		String realPath = path + "/poster/";
		String fileName = imageFile.getOriginalFilename();

		// 업로드 된 경우에만 객체에 삽입
		if (imageFile.getSize() > 0) {
			movie.setPoster(fileName);

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
		
		movieDao.update(movie);

		return "redirect:list.do";
	}
}
