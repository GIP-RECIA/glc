/*
 * Copyright (C) 2023 GIP-RECIA, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package fr.recia.glc;

import fr.recia.glc.security.cas.AjaxAuthenticationFailureHandler;
import fr.recia.glc.security.cas.AjaxAuthenticationSuccessHandler;
import fr.recia.glc.security.cas.AjaxLogoutSuccessHandler;
import fr.recia.glc.security.cas.CustomSessionFixationProtectionStrategy;
import fr.recia.glc.security.cas.CustomSingleSignOutFilter;
import fr.recia.glc.security.cas.RememberCasAuthenticationEntryPoint;
import fr.recia.glc.security.cas.RememberCasAuthenticationProvider;
import fr.recia.glc.security.cas.RememberWebAuthenticationDetailsSource;
import fr.recia.glc.services.beans.ServiceUrlHelper;
import fr.recia.glc.web.filter.CsrfCookieGeneratorFilter;
import lombok.extern.slf4j.Slf4j;
import org.apereo.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import jakarta.servlet.Filter;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  @Value("${server.servlet.context-path}")
  private String contextPath;
  public static final String PROTECTED_PATH = "/protected/";

  @Value("${security-configuration.cas.service}")
  private String casService;
  @Value("${security-configuration.cas.id-key-provider}")
  private String casIdKeyProvider;
  @Value("${security-configuration.cas.url.login}")
  private String casUrlLogin;
  @Value("${security-configuration.cas.url.logout}")
  private String casUrlLogout;
  @Value("${security-configuration.cas.url.prefix}")
  private String casUrlPrefix;

  @Value("${security-configuration.cors.enable}")
  private boolean corsEnable;
  @Value("${security-configuration.cors.allow-credentials}")
  private Boolean corsAllowCredentials;
  @Value("${security-configuration.cors.allowed-origins}")
  private List<String> corsAllowedOrigins;
  @Value("${security-configuration.cors.exposed-headers}")
  private List<String> corsExposedHeaders;
  @Value("${security-configuration.cors.allowed-headers}")
  private List<String> corsAllowedHeaders;
  @Value("${security-configuration.cors.allowed-methods}")
  private List<String> corsAllowedMethods;

  @Inject
  private AjaxAuthenticationSuccessHandler ajaxAuthenticationSuccessHandler;
  @Inject
  private AjaxAuthenticationFailureHandler ajaxAuthenticationFailureHandler;
  @Inject
  private AjaxLogoutSuccessHandler ajaxLogoutSuccessHandler;
  @Inject
  private AuthenticationUserDetailsService<CasAssertionAuthenticationToken> userDetailsService;

  // CAS

  @Bean
  public ServiceProperties serviceProperties() {
    ServiceProperties sp = new ServiceProperties();
    sp.setService(casService);
    sp.setSendRenew(false);
    sp.setAuthenticateAllArtifacts(true);

    return sp;
  }

  @Bean
  public ServiceUrlHelper serviceUrlHelper() {
    String ctxPath = contextPath;
    if (!ctxPath.startsWith("/")) ctxPath = "/" + ctxPath;
    final String protocol = "https://";
    final List<String> domainNames = corsAllowedOrigins;
    ServiceUrlHelper serviceUrlHelper = new ServiceUrlHelper(ctxPath, domainNames, protocol, "/view/item/");
    log.info("ServiceUrlHelper is configured with properties : {}", serviceUrlHelper);

    return serviceUrlHelper;
  }

  @Bean
  String getCasTargetUrlParameter() {
    return "spring-security-redirect";
  }

  @Bean
  protected AuthenticationManager authenticationManager() {
    return new ProviderManager(Collections.singletonList(casAuthenticationProvider()));
  }

  @Bean
  public SessionAuthenticationStrategy sessionStrategy() {
    SessionFixationProtectionStrategy sessionStrategy = new CustomSessionFixationProtectionStrategy(
      serviceUrlHelper(), serviceProperties(), getCasTargetUrlParameter()
    );
    sessionStrategy.setMigrateSessionAttributes(false);

    return sessionStrategy;
  }

  @Bean
  public SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler() {
    SimpleUrlAuthenticationSuccessHandler authenticationSuccessHandler = new SimpleUrlAuthenticationSuccessHandler();
    authenticationSuccessHandler.setDefaultTargetUrl("/");
    authenticationSuccessHandler.setTargetUrlParameter(getCasTargetUrlParameter());

    return authenticationSuccessHandler;
  }

  @Bean
  public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
    return new Cas20ServiceTicketValidator(casUrlPrefix);
  }

  @Bean
  public RememberCasAuthenticationProvider casAuthenticationProvider() {
    RememberCasAuthenticationProvider casAuthenticationProvider = new RememberCasAuthenticationProvider();
    casAuthenticationProvider.setAuthenticationUserDetailsService(userDetailsService);
    casAuthenticationProvider.setServiceProperties(serviceProperties());
    casAuthenticationProvider.setTicketValidator(cas20ServiceTicketValidator());
    casAuthenticationProvider.setKey(casIdKeyProvider);

    return casAuthenticationProvider;
  }

  @Bean
  public RememberCasAuthenticationEntryPoint casAuthenticationEntryPoint() {
    RememberCasAuthenticationEntryPoint casAuthenticationEntryPoint = new RememberCasAuthenticationEntryPoint();
    casAuthenticationEntryPoint.setLoginUrl(casUrlLogin);
    casAuthenticationEntryPoint.setServiceProperties(serviceProperties());
    casAuthenticationEntryPoint.setUrlHelper(serviceUrlHelper());
    casAuthenticationEntryPoint.setPathLogin("/app/login");

    return casAuthenticationEntryPoint;
  }

  @Bean
  public CasAuthenticationFilter casAuthenticationFilter() {
    CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
    casAuthenticationFilter.setFilterProcessesUrl("/" + casService);
    casAuthenticationFilter.setAuthenticationManager(authenticationManager());
    casAuthenticationFilter.setAuthenticationDetailsSource(new RememberWebAuthenticationDetailsSource(
      serviceUrlHelper(), serviceProperties(), getCasTargetUrlParameter()
    ));
    casAuthenticationFilter.setSessionAuthenticationStrategy(sessionStrategy());
    casAuthenticationFilter.setAuthenticationFailureHandler(ajaxAuthenticationFailureHandler);
    casAuthenticationFilter.setAuthenticationSuccessHandler(ajaxAuthenticationSuccessHandler);

    return casAuthenticationFilter;
  }

  @Bean
  public CustomSingleSignOutFilter singleSignOutFilter() {
    CustomSingleSignOutFilter singleSignOutFilter = new CustomSingleSignOutFilter();
    singleSignOutFilter.setCasServerUrlPrefix(casUrlPrefix);

    return singleSignOutFilter;
  }

  // Spring

  @Bean
  CorsConfigurationSource corsConfigurationSource() {
    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

    if (corsEnable) {
      if (log.isWarnEnabled()) log.warn("CORS ABILITATI! CORS est autorisé");

      final CorsConfiguration configuration = new CorsConfiguration();

      configuration.setAllowCredentials(corsAllowCredentials);
      configuration.setAllowedOrigins(corsAllowedOrigins);
      configuration.setExposedHeaders(corsExposedHeaders);
      configuration.setAllowedHeaders(corsAllowedHeaders);
      configuration.setAllowedMethods(corsAllowedMethods);

      source.registerCorsConfiguration("/**", configuration);
    }

    return source;
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    final RequestMatcher pathMatcher = new AntPathRequestMatcher("/api/**");
    final RequestMatcher inverseMatcher = new NegatedRequestMatcher(pathMatcher);

    return web -> web.ignoring().requestMatchers(inverseMatcher);
  }

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().configurationSource(corsConfigurationSource());
    http.sessionManagement().sessionFixation().newSession();

    http
      .addFilterAfter(new CsrfCookieGeneratorFilter(), CsrfFilter.class).exceptionHandling()
      .authenticationEntryPoint(casAuthenticationEntryPoint());

    http
      .addFilterBefore(casAuthenticationFilter(), BasicAuthenticationFilter.class)
      .addFilterBefore(singleSignOutFilter(), CasAuthenticationFilter.class);

    http.headers().frameOptions().disable();

    http
      .authorizeHttpRequests(authz -> authz
        .requestMatchers(HttpMethod.OPTIONS).permitAll()
        .requestMatchers(
          "/health-check",
          "/app/**/*.{js, ts, html}",
          "/api/**"
        ).permitAll()
        .requestMatchers(
          "/app/**",
          PROTECTED_PATH + "**"
        ).authenticated()
        .anyRequest().denyAll()
      );

    http
      .logout()
      .logoutUrl("/api/logout")
      .logoutSuccessHandler(ajaxLogoutSuccessHandler)
      .invalidateHttpSession(true)
      .deleteCookies("JSESSIONID")
      .permitAll();

    return http.build();
  }

  @Bean
  public ErrorPageFilter errorPageFilter() {
    return new ErrorPageFilter();
  }

  @Bean
  public FilterRegistrationBean<Filter> disableSpringBootErrorFilter() {
    /*
     * The ErrorPageFilter (Spring) makes extra calls to HttpServletResponse.flushBuffer(),
     * and this behavior produces many warnings in the portal logs during portlet requests.
     */
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(errorPageFilter());
    filterRegistrationBean.setEnabled(false);

    return filterRegistrationBean;
  }

}
