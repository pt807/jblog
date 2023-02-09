package com.douzone.jblog.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.douzone.jblog.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1. handler 종류 확인
		if (!(handler instanceof HandlerMethod)) {
			// DefaultServletHandler가 처리하는 경우(정적 자원, /assets/**) 통과시켜준다.
			return true;
		}
		// 2. Handler Method(컨트롤러) 인 경우 casting 해준다.
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 3. 클라이언트가 요청한 url 에 해당하는 Method(컨트롤러)에 @Auth 달려 있는지 확인하기
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

		// 5. Type이나 Method에 @Auth 가 없는 경우
		// 즉 인증이 필요 없는 요청
		if (auth == null) {
			// 패스시켜준다.
			return true;
		}

		// 6. @Auth가 붙어 있기 때문에 인증(Authenfication) 여부 확인
		HttpSession session = request.getSession();
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		// 권한이 없다면 로그인 페이지로 안내 한다.
		if (authUser == null) {
			response.sendRedirect(request.getContextPath() + "/user/login");
			return false;
		}
		// split 이후에 [숫자]를 사용하면 해당하는 순서의 값만 가져온다.
		String id = request.getRequestURI().split("/")[3];
		String authId = authUser.getId();
		
		if (id.equals(authId) == false) {
			response.sendRedirect(request.getContextPath());
			return false;
		}

		return true;
	}

}
