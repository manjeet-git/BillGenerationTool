package com.sboot.security;

import static com.sboot.security.ApplicationUserPermission.CUSTOMER_READ;
import static com.sboot.security.ApplicationUserPermission.CUSTOMER_WRITE;
import static com.sboot.security.ApplicationUserPermission.SHOAPKEEPER_READ;
import static com.sboot.security.ApplicationUserPermission.SHOAPKEEPER_WRITE;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sboot.security.auth.AuthenticationUserService;
import com.sboot.security.jwt.JwtConfig;
import com.sboot.security.jwt.JwtTokenVerifierFilter;
import com.sboot.security.jwt.JwtUsernameAndPasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


	PasswordEncoder passwordEncoder;
	AuthenticationUserService userService;
	JwtConfig jwtConfig;
	SecretKey secretKey;
	
	
	

	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, 
			                         AuthenticationUserService userService,
			                         JwtConfig jwtConfig,
			                         SecretKey secretKey) {
		this.passwordEncoder = passwordEncoder;
		this.userService = userService;
		this.jwtConfig = jwtConfig;
		this.secretKey = secretKey;
	}



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http
		.csrf().disable()
				  .sessionManagement() 
				      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				  .and() 
				  .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(),jwtConfig,secretKey))
				  .addFilterAfter(new JwtTokenVerifierFilter(jwtConfig,secretKey), JwtUsernameAndPasswordAuthenticationFilter.class)
				 
		.authorizeRequests()
		.antMatchers("/","index","/css/*","/js/*").permitAll()
		
		//Below disable codes are used for Role based Authentication
	    //.antMatchers("/api/**").hasRole(ADMIN.name())
		
		//Below codes are for the Permission based Authentication
		.antMatchers(HttpMethod.GET,"/api/cutomer/customer-by-Customer_id/{customerId}").hasAuthority(CUSTOMER_READ.getPermission())
		.antMatchers(HttpMethod.GET,"/api/cutomer/all-customers").hasAuthority(CUSTOMER_READ.getPermission())
		.antMatchers(HttpMethod.GET,"/api/bill/all-bills").hasAuthority(SHOAPKEEPER_READ.getPermission())
		.antMatchers(HttpMethod.GET,"/api/bill/bill-by-bill_id/{billId}").hasAuthority(SHOAPKEEPER_READ.getPermission())
		.antMatchers(HttpMethod.POST,"/api/customer/save-customer").hasAuthority(CUSTOMER_WRITE.getPermission())
		.antMatchers(HttpMethod.POST,"/api/bill/save-bill").hasAuthority(SHOAPKEEPER_WRITE.getPermission())
		.anyRequest()
		.authenticated();
		
		//Below commentted all the codes in the method has been only used without jwt 
		//.and()
		
		//Codes for the login Event when occures
		/*.formLogin()
		   .loginPage("/login").permitAll();*/
		  // .passwordParameter("password")
		  // .usernameParameter("username")
		   //.defaultSuccessUrl("/logoutform")
		   /*
			 *.defaultSuccessUrl("/logoutform",true)
				 .failureUrl("/error")
				 */
		//.and()
		
		//Logout related Events when occures
		/*.logout()
		  .logoutUrl("/logout")
		  .clearAuthentication(true)
		  .invalidateHttpSession(true)
		  .deleteCookies("JSESSIONID","remember-me")
		  .logoutSuccessUrl("/login");*/
		
		
		// This is for making the remember-me token for long time validity
		//default time 2 weeks
		/*
		 * .and()
		 *  .rememberMe()
		 *  .rememberMeParameter("remember-me");
		 *  .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21))
		 * .key("BillGenerationTools");
		 */
		
		
		// for the Basic Authentication
		//.httpBasic();
		

		
	}
	
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userService);
		
		return provider;
	}
	
	//Below codes for Inmemory users

	/*
	 * @Override
	 * 
	 * @Bean protected UserDetailsService userDetailsService() {
	 * 
	 * UserDetails manjeet=User.builder() .username("Manjeet")
	 * .password(passwordEncoder.encode("subram")) //.roles(CUSTOMER.name())
	 * .authorities(CUSTOMER.getGrantedAuthorities()) .build();
	 * 
	 * UserDetails ajeet=User.builder() .username("Ajeet")
	 * .password(passwordEncoder.encode("subram123")) //.roles(SHOAPKEEPER.name())
	 * .authorities(SHOAPKEEPER.getGrantedAuthorities()) .build();
	 * 
	 * UserDetails ranjeet=User.builder() .username("Ranjeet")
	 * .password(passwordEncoder.encode("subram1234")) //.roles(ADMIN.name())
	 * .authorities(ADMIN.getGrantedAuthorities()) .build();
	 * 
	 * 
	 * return new InMemoryUserDetailsManager(manjeet,ajeet,ranjeet);
	 * 
	 * }
	 */
	
	

	
	
}
