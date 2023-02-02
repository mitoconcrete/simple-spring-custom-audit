package com.homework.app.message;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@SpringBootTest
class MessageRepositoryTest {

  @Autowired
  MessageRepository messageRepository;

  @Test
  @DisplayName("create Message Test")
  void createTest() {
    String createUsername = "json";
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setAttribute("username", createUsername);
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    Message m = new Message("message");
    Message savedMessage = messageRepository.save(m);

    Message message = messageRepository.findById(savedMessage.getId()).get();

    assert message.getCreatedBy().equals(createUsername) && message.getModifiedBy()
        .equals(createUsername);
  }

  @Test
  @DisplayName("update Message Test")
  void modifyTest() {
    String createUsername = "json";
    String modifyUsername = "sam";
    MockHttpServletRequest request = new MockHttpServletRequest();
    request.setAttribute("username", createUsername);
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

    Message m = new Message("message");
    Message createdMessage = messageRepository.save(m);

    request.setAttribute("username", modifyUsername);
    RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    createdMessage.updateMessage("messageModify");
    Message updateMessage = messageRepository.save(createdMessage);

    Assertions.assertEquals(createdMessage.getCreatedBy(), createUsername);
    Assertions.assertEquals(createdMessage.getModifiedBy(), createUsername);
    Assertions.assertEquals(updateMessage.getCreatedBy(), createUsername);
    Assertions.assertEquals(updateMessage.getModifiedBy(), modifyUsername);
  }
}