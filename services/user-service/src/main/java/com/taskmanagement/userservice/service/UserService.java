package com.taskmanagement.userservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanagement.userservice.model.User;
import com.taskmanagement.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  @Autowired
  private ObjectMapper objectMapper;

  public User createUser(User user) {
    final String event = "user-created";
    Map<String, Object> message = new HashMap<>();
    User savedUser = null;

    try {
      savedUser = userRepository.save(user);

      message.put("event-type", "user-created");
      message.put("userId", savedUser.getId());
      message.put("user", savedUser);

      kafkaTemplate.send("user-created", objectMapper.writeValueAsString(message));
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return savedUser;
  }

  public User getUserByUsername(String username) {
    // Optional<T> uses when there is possibility of no result
    Optional<User> requestTask = userRepository.findByUsername(username);
    return requestTask.orElse(null);
  }

  public User getUserById(long id) {
    // Optional<T> uses when there is possibility of no result
    Optional<User> requestTask = userRepository.findById(id);
    return requestTask.orElse(null);
  }

  public User updateUser(User user) {
    return userRepository.save(user);
  }

  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

}
