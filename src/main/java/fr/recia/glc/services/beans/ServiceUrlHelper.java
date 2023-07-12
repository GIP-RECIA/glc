package fr.recia.glc.services.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by jgribonvald on 03/06/16.
 */
@Data
@AllArgsConstructor
@Slf4j
public class ServiceUrlHelper {

  @NonNull
  private String contextPath;

  @NonNull
  private List<String> authorizedDomainNames;

  @NonNull
  private String protocol = "https://";

  @NonNull
  private String itemUri;

  /**
   * Used for conf display only
   */
  private String getItemUrl() {
    return protocol + authorizedDomainNames.get(0) + contextPath + itemUri + "ID";
  }

  public String getRootAppUrl(final HttpServletRequest request) {
    final String contextPath = !request.getContextPath().isEmpty() ? request.getContextPath() + "/" : "/";
    return getRootDomainUrl(request) + contextPath;
  }

  public String getRootDomainUrl(final HttpServletRequest request) {
    final String url = request.getRequestURL().toString();
    final String uri = request.getRequestURI();
    return url.substring(0, url.length() - uri.length());
  }

  @Override
  public String toString() {
    return "ServiceUrlHelper{" +
      "contextPath='" + contextPath + '\'' +
      ", authorizedDomainNames='" + authorizedDomainNames.toString() + '\'' +
      ", protocol='" + protocol + '\'' +
      ", itemUri='" + itemUri + '\'' +
      ", example of itemUrl generated ='" + getItemUrl() + '\'' +
      '}';
  }

}
