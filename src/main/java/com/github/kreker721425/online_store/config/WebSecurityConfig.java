package com.github.kreker721425.online_store.config;

import javax.sql.DataSource;

/*@Configuration
@EnableWebSecurity
@EnableOAuth2Sso*/
public class WebSecurityConfig /*extends WebSecurityConfigurerAdapter*/  {

    /*@Autowired
    private final DataSource dataSource;

    public WebSecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }*/

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login", "/js/**", "/error/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout().logoutSuccessUrl("/").permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PrincipalExtractor principalExtractor(PersonRepository repository) {
        return map -> {
            Person person = new Person();
            person.setName((String) map.get("name"));
            person.setEmail((String) map.get("email"));
            return repository.save(person);
        };
    }*/

    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password from person where username=?")
                .authoritiesByUsernameQuery("select p.username from person p");
    }*/
}
