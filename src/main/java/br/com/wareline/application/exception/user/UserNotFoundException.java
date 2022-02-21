package br.com.wareline.application.exception.user;

import java.io.Serializable;

public class UserNotFoundException extends RuntimeException implements Serializable {

  public UserNotFoundException(String message) {
    super(message);
  }
}
