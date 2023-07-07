package fr.recia.glc.web.rest;

import fr.recia.glc.security.cas.CustomUserDetails;
import fr.recia.glc.security.cas.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
@Slf4j
public class AccountController {

  @GetMapping(value = "/signin")
  public ResponseEntity<CustomUserDetails> signIn() {
    CustomUserDetails userDetails = SecurityUtils.getCurrentUserDetails();
    log.debug("UserDetails: {}", userDetails);
    if (userDetails == null)
      return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

    return new ResponseEntity<>(userDetails, HttpStatus.OK);
  }

}
