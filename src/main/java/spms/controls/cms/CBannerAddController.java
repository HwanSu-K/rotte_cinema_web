package spms.controls.cms;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spms.dao.BannerDao;
import spms.vo.Banner;

//@RequestParam 적용
@Controller
public class CBannerAddController {
	BannerDao bannerDao;

	@Autowired
	public CBannerAddController setMemberDao(BannerDao bannerDao) {
		this.bannerDao = bannerDao;
		return this;
	}

	@RequestMapping(value = "/cms/banner/add.do", method = RequestMethod.GET)
	public String addForm() throws Exception {
		return "/cms/BannerAddForm.jsp";
	}

	@RequestMapping(value = "/cms/banner/add.do", method = RequestMethod.POST)
	public String add(MultipartHttpServletRequest request, Banner banner) throws Exception {

		MultipartFile imageFile = request.getFile("image");
		MultipartFile videoFile = request.getFile("video");

		String realPath = request.getSession().getServletContext().getRealPath("/images/banner/");
		String imageFileName = imageFile.getOriginalFilename();
		String videoFileName = videoFile.getOriginalFilename();

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
}
