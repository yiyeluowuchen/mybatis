package com.mybatis.mybatis.config.securityConfig;

import com.mybatis.mybatis.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true) // 启用方法安全设置
@EnableWebSecurity
public class MysecurityConfig extends WebSecurityConfigurerAdapter {

//    //覆盖了接口的这个方法，就没有默认界面了，那个默认就是实现了里面的	.httpBasic();
//            @Override
//            protected  void  configure(HttpSecurity http) throws Exception {
//                    /*开启自动配置的登陆功能，如果没有登陆，没有权限会来到登陆界面,
//                     *  这里/aaa是form表单的action，提交到哪，user和pwd是表单中input中的name。这里默认是post提交方式，所以表单一定要写post
//                    */
//                http.formLogin().usernameParameter("user").passwordParameter("pwd").loginPage("/aaa");
//
//                /**
//                 * 这个用来拥有哪些角色的可以访问的路径
//                 */
//                http.authorizeRequests().antMatchers("/","/aaa","/userlogin").permitAll()//所有请求都可以访问，这些是登陆前的操作，必须加进去，下面有一个登陆后才能访问的
//
//                                        .antMatchers("/grade1/**").hasRole("g1")//拥有该角色的才能够访问
//                                        .antMatchers("/grade2/**").hasRole("g2")
//                                        .antMatchers("/grade3/**").hasRole("g3")
//                                        .anyRequest().authenticated();//其余所有请求都必须登陆后才能访问，这个就是我们平常的，不登陆，里面页面你是进不去的
//                //开启自动配置的注销功能，访问/logout 表示注销，清空session，注销成功会返回llogin?logout页面
//                http.logout().logoutSuccessUrl("/");//注销成功后回到首页
//                /**
//                 * 开启记住我功能
//                 * 登陆成功以后，将cookie发给浏览器保存，以后只要通过查看cookie中就可以免登陆
//                 * 下面这个有问题，没解决
//                 */
//               // http.rememberMe().rememberMeParameter("remember");
//
//            }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        //放在缓存中
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("zhangsan")
//                                    .password(new BCryptPasswordEncoder().encode("123456")).roles("g1","g2");
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("lisi")
//                                    .password(new BCryptPasswordEncoder().encode("123456")).roles("g1","g3");
//        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder()).withUser("wangwu").
//                                    password(new BCryptPasswordEncoder().encode("123456")).roles("g2","g3");
//    }




//    //***************************数据库验证--------------
    @Autowired
    private AuthService authService;
    //private AuthService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();   // 使用 BCrypt 加密，密码以明文的方式进行匹配不然会报错
    }

    /**
     * 取出验证信息
     * @return
     */
    @Bean
    public AuthenticationProvider authenticationProvider() {

        System.out.println("验证信息----------------");
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authService);
        authenticationProvider.setPasswordEncoder(passwordEncoder); // 设置密码加密方式
        return authenticationProvider;
    }





    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }





////***************************数据库验证----------
    /**
     * 自定义配置,定义http安全规则
     *
     * 数据库角色需要写 ROLE_ADMIN 对应权限认证的 ADMIN
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("认证请求验证----------------");
        //认证请求
        http.authorizeRequests()
                .antMatchers("/css/**", "/js/**", "/fonts/**", "/index").permitAll() // 都可以访问
                .antMatchers("/u/**").permitAll() // 都可以访问
                .antMatchers("/p/**").hasRole("admin") // 需要相应的角色才能访问(区分大小写)
                .antMatchers("/a/**").hasRole("USER") // 需要相应的角色才能访问
                .and()
                .formLogin();   //基于Form 表单登录验证
				/*.loginPage("/login").failureUrl("/login-error") // 自定义登录界面
				.and().exceptionHandling().accessDeniedPage("/403");  // 处理异常，拒绝访问就重定向到 403 页面
*/
    }

    /**
     * 认证信息管理
     * @param auth
     * @throws Exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

        /**
         * todo 通过数据库进行查询验证----------------
         *  我们需要在 service 层中实现 userDetailsService
         */
        auth.userDetailsService(authService);
        auth.authenticationProvider(authenticationProvider());
    }



}
