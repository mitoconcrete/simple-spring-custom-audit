package com.homework.app.common;

import javax.persistence.MappedSuperclass;
import javax.servlet.http.HttpServletRequest;
import lombok.Getter;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Getter
@MappedSuperclass
public class UserStamped<T> {

  private String createdBy;
  private String modifiedBy;

  public void updateCreatedBy() {
    createdBy = getCurrentUserName();
  }

  public void updateModifiedBy() {
    modifiedBy = getCurrentUserName();
  }

  private String getCurrentUserName() {
    HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    return (String) request.getAttribute("username");
  }
}
