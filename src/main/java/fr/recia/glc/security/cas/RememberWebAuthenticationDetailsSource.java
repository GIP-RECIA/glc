package fr.recia.glc.security.cas;

import fr.recia.glc.services.beans.ServiceUrlHelper;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.authentication.ServiceAuthenticationDetails;

import jakarta.servlet.http.HttpServletRequest;

public class RememberWebAuthenticationDetailsSource
  implements AuthenticationDetailsSource<HttpServletRequest, ServiceAuthenticationDetails> {

  private ServiceUrlHelper urlHelper;
  private ServiceProperties serviceProperties;
  private String casTargetUrlParam;

  public RememberWebAuthenticationDetailsSource(
    @NotNull ServiceUrlHelper urlHelper, @NotNull ServiceProperties serviceProperties, @NotNull String casTargetUrlParam
  ) {
    this.urlHelper = urlHelper;
    this.serviceProperties = serviceProperties;
    this.casTargetUrlParam = casTargetUrlParam;
  }

  public RememberWebAuthenticationDetails buildDetails(HttpServletRequest request) {
    return new RememberWebAuthenticationDetails(request, urlHelper, serviceProperties, casTargetUrlParam);
  }

}
