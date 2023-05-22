package fr.recia.glc.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@RestController
public class HealthCheckController {

  @GetMapping(value = "/health-check")
  @ResponseStatus(HttpStatus.OK)
  public void healthCheck(HttpServletRequest request, HttpServletResponse response) {
    if (log.isDebugEnabled()) log.debug("Health check. HTTP 200: OK.");
  }

}
