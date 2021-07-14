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

import spms.dao.BannerDao;
import spms.dao.MovieDao;
import spms.vo.Banner;

//@RequestParam 적용
@Controller
public class BannerController {
	BannerDao bannerDao;

	@Value("${upload.path}")
	private String path;
	
	
	@Autowired
	public BannerController setBannerDao(BannerDao bannerDao) {
		this.bannerDao = bannerDao;
		return this;
	}
	
	@RequestMapping(value = "/cms/banner/add.do", method = RequestMethod.GET)
	public String addGet() throws Exception {
		return "/cms/BannerAddForm.jsp";
	}

	@RequestMapping(value = "/cms/banner/add.do", method = RequestMethod.POST)
	public String addPost(MultipartHttpServletRequest request, Banner banner) throws Exception {

		MultipartFile imageFile = request.getFile("image");
		MultipartFile videoFile = request.getFile("video");

		String realPath = path + "/banner"; //request.getSession().getServletContext().getRealPath("/images/banner/");
		String imageFileName = imageFile.getOriginalFilename();
		String videoFileName = videoFile.getOriginalFilename();
		System.out.println(realPath);
		banner.setImagePath(imageFileName);
		banner.setVideoPath(videoFileName);
		
		// 업로드 된 경우에만 객체에 삽입
		if (imageFile.getSize() > 0) {

			File target = new File(realPath, imageFileName);
			
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

		// 업로드 된 경우에만 객체에 삽입
		if (videoFile.getSize() > 0) {

			File target = new File(realPath, videoFileName);

			// 경로 생성
			if (!new File(realPath).exists()) {
				new File(realPath).mkdirs();
			}
			// 파일 복사
			try {
				FileCopyUtils.copy(videoFile.getBytes(), target);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		bannerDao.insert(banner);

		
		return "redirect:list.do";
	}

	@RequestMapping("/cms/banner/list.do")
	public String list(Map<String, Object> model) throws Exception {
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("view", "all");
		model.put("banners", bannerDao.selectList(paramMap));
		return "/cms/BannerListForm.jsp";
	}
	
	@RequestMapping(value = "/cms/banner/update.do", method = RequestMethod.GET)
	public String updateGet(int no, Map<String, Object> model) throws Exception {
		Banner detailInfo = bannerDao.selectOne(no);
		model.put("banner", detailInfo);

		return "/cms/BannerUpdateForm.jsp";
	}
	
	@RequestMapping(value = "/cms/banner/update.do", method = RequestMethod.POST)
	public String updatePost(MultipartHttpServletRequest request, Banner banner) throws Exception {

		MultipartFile imageFile = request.getFile("image");
		MultipartFile videoFile = request.getFile("video");

		String realPath = path + "/banner";
		String imageFileName = imageFile.getOriginalFilename();
		String videoFileName = videoFile.getOriginalFilename();

		// 업로드 된 경우에만 객체에 삽입
		if (imageFile.getSize() > 0) {
			banner.setImagePath(imageFileName);

			File target = new File(realPath, imageFileName);
			
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

		// 업로드 된 경우에만 객체에 삽입
		if (videoFile.getSize() > 0) {
			banner.setVideoPath(videoFileName);

			File target = new File(realPath, videoFileName);

			// 경로 생성
			if (!new File(realPath).exists()) {
				new File(realPath).mkdirs();
			}
			// 파일 복사
			try {
				FileCopyUtils.copy(videoFile.getBytes(), target);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		bannerDao.update(banner);

		return "redirect:list.do";
	}
}
