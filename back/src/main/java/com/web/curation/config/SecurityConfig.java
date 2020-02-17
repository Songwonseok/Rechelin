package com.web.curation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.web.curation.security.CustomUserDetailsService;
import com.web.curation.security.JwtAuthenticationEntryPoint;
import com.web.curation.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity( //蹂댁븞 �뼱�끂�뀒�씠�뀡
    securedEnabled = true, //@Secured 媛� 遺숈� �겢�옒�뒪�굹 �씤�꽣�럹�씠�뒪�쓽 硫붿냼�뱶 �븸�꽭�뒪 �젣�븳
    jsr250Enabled = true, //@RolesAllowed
    prePostEnabled = true //@PreAuthorize
)

//WebSecurityConfigurerAdapter : extends, customize by customize �젣怨�
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    
    //user, role �씤利앹쓣 �쐞�빐 users detail 媛��졇�삤湲�
    //loadUserByUsername�쑝濡� UserDetail 媛앹껜瑜� 諛섑솚�븯�뒗 userDetailService濡� 援ъ꽦�릺�뼱�엳�쓬
    @Autowired
    CustomUserDetailsService customUserDetailService;  

    //蹂댁븞 resource�뿉 �씤利앸릺吏� �븡�� �젒洹� 諛쒖깮�떆 401 �뿉�윭 return
    //spring security�쓽 AuthenticationEntryPoint interface瑜� implements
    @Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    //(紐⑤뱺 �슂泥��쓽 Authorization header�쓽)�넗�겙�씠 �븘�슂�븳 �븘�꽣
    //filter�뒗 RequestMatcher(�빐�떦 �븘�꽣�뿉 ���븳 Url, Method �꽕�젙 遺�遺�) �씤�꽣�럹�씠�뒪 臾댁“嫄� 諛쏆쓬
    //�넗�겙怨� 愿��젴�맂 users detail 媛��졇�샂(SecurityContext)
    @Bean
    public JwtAuthenticationFilter JwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception{
        authenticationManagerBuilder //�씤利� 媛앹껜 �깮�꽦 �젣怨� 
                //�뒪�봽留� �떆�걧由ы떚 �씤利앹슜.
                //userDao瑜� �넻�빐 �쁺�냽�꽦�쑝濡� ���옣�맂 �씤利앹젙蹂대�� 寃��깋�븳 �썑 議댁옱�븯吏� �븡�뒗�떎硫� exception 諛섑솚
                //�엳�떎硫� UserDetails 媛앹껜 諛섑솚
                .userDetailsService(customUserDetailService) 
                .passwordEncoder(passwordEncoder()); //�뙣�뒪�썙�뱶 �븫�샇�솕 援ы쁽泥�
    }

    //AuthenticationManager : �씤利� 怨듦툒�옄瑜� �쐞�븳 而⑦뀒�씠�꼫
    //�씤利앹쓣 �떆�룄�빐�꽌 �꽦怨듯븯硫� authentication 媛앹껜 諛섑솚
    //�떎�뙣 �떆 exception 諛섑솚
    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors() //Cross Origin Resource Sharing
                    .and()
                .csrf()
                    .disable() //rest api�씠誘�濡� csrf 蹂댁븞�씠 �븘�슂 �뾾�쑝誘�濡� disable 泥섎━
                .exceptionHandling() //�삁�쇅泥섎━
                    .authenticationEntryPoint(unauthorizedHandler) //�쟾�떖 �삁�쇅 �옟湲�
                    .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // jwt token�쑝濡� �씤利앺븯誘�濡� �꽭�뀡�� �븘�슂�뾾�쑝誘�濡� �깮�꽦�븞�븿
                    .and()
                .authorizeRequests() //�떎�쓬 由ы�섏뒪�듃�뿉 ���븳 �궗�슜沅뚰븳 泥댄겕
                    .antMatchers("/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                        .permitAll() // �쐞 寃쎈줈�뒗 �늻援щ굹 �젒洹� 媛��뒫
                    .antMatchers("/auth/**") //auth 濡� �떆�옉�븯�뒗 寃쎈줈 �늻援щ굹 �젒洹쇨��뒫
                        .permitAll()
                    .antMatchers("/account/changePW","/main/**,/signUpForm/**","/account/selectName/**", "/account/selectEmail/**", "/account/selectId/**"
                    		, "/account/naverlogin/**", "/access_token/**")
                    
                        .permitAll() // �쐞 寃쎈줈 �늻援щ굹 �젒洹쇨��뒫
                    .anyRequest()
                    	.permitAll();
//                   	.authenticated();//洹� �쇅 �굹癒몄� �슂泥��� 紐⑤몢 �씤利앸맂 �쉶�썝留� �젒洹쇨��뒫

        // Add our custom JWT security filter
        http.addFilterBefore(JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }
}