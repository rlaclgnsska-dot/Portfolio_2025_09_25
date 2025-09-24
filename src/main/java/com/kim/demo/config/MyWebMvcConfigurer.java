package com.kim.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.kim.demo.interceptor.BeforeActionInterceptor;
import com.kim.demo.interceptor.NeedLoginInterceptor;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

	private BeforeActionInterceptor beforeActionInterceptor;

	private NeedLoginInterceptor needLoginInterceptor;

	public MyWebMvcConfigurer(BeforeActionInterceptor beforeActionInterceptor,
			NeedLoginInterceptor needLoginInterceptor) {
		this.beforeActionInterceptor = beforeActionInterceptor;
		this.needLoginInterceptor = needLoginInterceptor;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(beforeActionInterceptor).addPathPatterns("/**").excludePathPatterns("/rosource/**");

		registry.addInterceptor(needLoginInterceptor).addPathPatterns("/usr/article/write")
				.addPathPatterns("/usr/article/doWrite").addPathPatterns("/usr/article/doDelete")
				.addPathPatterns("/usr/article/modify").addPathPatterns("/usr/article/doModify")
				.addPathPatterns("/usr/member/doLogout")
				.addPathPatterns("/usr/trip/doWrite").addPathPatterns("/usr/trip/write")
				.addPathPatterns("/usr/trip/modify").addPathPatterns("/usr/trip/doModify")
				.addPathPatterns("/usr/trip/doDelete").addPathPatterns("/usr/keepTrip/list");
	}

}
