package i.palmer.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


/**
 * 拦截器  全局 主要针对未登陆用户进行检查
 * @author ipalmer
 */
public class SystemInterceptor implements HandlerInterceptor {

	//@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	//@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {

	}

	//@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		/*
		request.setAttribute("ctx", request.getContextPath());
		if(request.getServletPath().startsWith(WebAppContext.LOGIN_URL)){
			return true;
		}
		User user = (User)request.getSession().getAttribute(WebAppContext.USER_PREFIX_IN_SESSION);
		if(user==null){
			response.sendRedirect(request.getContextPath()+WebAppContext.LOGIN_URL);
			return false;
		}
		request.setAttribute("user", user);
		*/
		return true;
	}

}
