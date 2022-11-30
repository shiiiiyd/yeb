package com.xxxx.yeb.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xxxx.yeb.pojo.Admin;
import com.xxxx.yeb.pojo.RespBean;
import com.xxxx.yeb.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.ConcurrentSessionFilter;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Security配置类
 *
 * @author cv工程师
 * @since 1.0.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private IAdminService adminService;
	@Autowired
	private RestFulAccessDeniedHandler restFulAccessDeniedHandler;
	@Autowired
	private RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;
	@Autowired
	private CustomFilter customFilter;
	@Autowired
	private CustomUrlAccessDecisionManager customUrlAccessDecisionManager;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers(
				"/login",
				"/logout",
				"/css/**",
				"/js/**",
				"/index.html",
				"/img/**",
				"/fonts/**",
				"/favicon.ico",
				"/doc.html",
				"/webjars/**",
				"/swagger-resources/**",
				"/v2/api-docs/**",
				"/captcha",
				"/user/**",
				"/ws/ep/**",
				"/ws/chat/**"
		);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//使用JWT，不需要csrf
		http.csrf().disable()
				//不需要session
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				//所有请求都必须被认证
				.anyRequest()
				.authenticated()
				//动态权限配置
				.withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
					@Override
					public <O extends FilterSecurityInterceptor> O postProcess(O object) {
						object.setSecurityMetadataSource(customFilter);
						object.setAccessDecisionManager(customUrlAccessDecisionManager);
						return object;
					}
				})
				.and()
				//禁用缓存
				.headers()
				.cacheControl();
		//JWT登录拦截器
		http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		//自定义异常返回结果
		http.exceptionHandling()
				.accessDeniedHandler(restFulAccessDeniedHandler)
				.authenticationEntryPoint(restfulAuthenticationEntryPoint);
		http.authorizeRequests()
				.and().formLogin().permitAll();
		http.addFilterAt(new ConcurrentSessionFilter(sessionRegistry(), event -> {
			HttpServletResponse resp = event.getResponse();
			resp.setContentType("application/json;charset=utf-8");
			resp.setStatus(401);
			PrintWriter out = resp.getWriter();
			out.write(new ObjectMapper().writeValueAsString(RespBean.error("您已在另一台设备登录，本次登录已下线!")));
			out.flush();
			out.close();
		}), ConcurrentSessionFilter.class);
	}

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		return username -> {
			Admin admin = adminService.getAdminByUserName(username);
			if (null == admin) {
				throw new UsernameNotFoundException("用户名或密码错误！");
			}
			admin.setRoles(adminService.getRoles(admin.getId()));
			return admin;
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
		return new JwtAuthenticationTokenFilter();
	}

	@Bean
	SessionRegistryImpl sessionRegistry() {
		return new SessionRegistryImpl();
	}

}