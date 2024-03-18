package com.example.demo.intercepter;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.annotation.NonAuth;
import com.example.demo.model.Users;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginIntercepter implements HandlerInterceptor  {

    	@Autowired
	    private Users user;

		public LoginIntercepter() {
		      
		}
	    @Override
	    public boolean preHandle(
	                    HttpServletRequest request,
	                    HttpServletResponse response,
	                    Object handler) throws Exception {

	        // リクエストがマッピングできていない場合はfalse
	        if( handler instanceof HandlerMethod ) {

	            // @NonAuthが付与されているかどうかをチェックする
	            HandlerMethod hm = (HandlerMethod) handler;
	            Method method = hm.getMethod();
	            NonAuth annotation = AnnotationUtils.findAnnotation(method, NonAuth.class);
	            if (annotation != null) {
	                return true;
	            }

	            // ユーザ認証
	            try {
	                if(request.getSession().getAttribute("userId") == null) {
	                    response.sendRedirect("/");
	                    return false;
	                }
	            }catch(NullPointerException e){
	                response.sendRedirect("/");
	                return false;
	            }
	            return true;
	        }
	        response.sendRedirect("/");
	        return false;
	    }


}
