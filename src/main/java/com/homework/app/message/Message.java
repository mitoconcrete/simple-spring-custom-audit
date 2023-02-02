package com.homework.app.message;

import com.homework.app.common.UserStamped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

// 여러명이 수정할 수 있는 메시지.
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Message extends UserStamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String message;


  public Message(String message) {
    this.message = message;
  }

  public void updateMessage(String message) {
    this.message = message;
  }

  @PrePersist
  public void PrePersist() {
    super.updateCreatedBy();
    super.updateModifiedBy();
  }

  @PreUpdate
  public void PreUpdate() {
    super.updateModifiedBy();
  }
}
