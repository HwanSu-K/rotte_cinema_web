package spms.controls;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ErrorController {

	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handleError404(HttpServletRequest request, Exception e) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/cinema/error/404Form.jsp");
		mav.addObject("message", "404오류");
		mav.addObject("url", request.getRequestURL());
		return mav;
	}

	/* common 메소드는 Exception 타입으로 처리하는 모든 예외를 처리하도록 설정 */
	@ExceptionHandler(Exception.class)
	public ModelAndView common(Exception e, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("/cinema/error/ErrorForm.jsp");
		mav.addObject("exception", e); // 예외를 뷰에 넘깁니다.
		mav.addObject("url", request.getRequestURL());
		return mav;
	}
}
