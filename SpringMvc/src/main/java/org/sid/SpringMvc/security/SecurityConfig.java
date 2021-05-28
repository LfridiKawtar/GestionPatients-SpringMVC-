package org.sid.SpringMvc.security;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private DataSource dataSource;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder passwordEncoder=passwordEncoder();
		System.out.println(passwordEncoder.encode("1234"));
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username as principal, password as credentials, active from users where username=?")
			.authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
			.passwordEncoder(passwordEncoder)
			.rolePrefix("ROLE_");
		/* * auth.inMemoryAuthentication().withUser("Kawtar").password(passwordEncoder.encode("261099")).roles("User");
		 * auth.inMemoryAuthentication().withUser("Kevkava").password(passwordEncoder.encode("1234")).roles("User");
		 * auth.inMemoryAuthentication().withUser("Admin").password(passwordEncoder.encode("admin")).roles("User","ADMIN"); */} 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin().loginPage("/login");
		http.authorizeRequests().antMatchers("/admin/**","/save**/**","/delete**/**","/form**/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/patients**/**").hasRole("USER");
		http.authorizeRequests().antMatchers("/user/**","/login","resources/**","/webjars/**").permitAll();
		http.authorizeRequests().anyRequest().authenticated();
		http.csrf();
		http.exceptionHandling().accessDeniedPage("/notAuthorized");}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();}
}
