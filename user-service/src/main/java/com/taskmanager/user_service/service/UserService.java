package com.taskmanager.user_service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.taskmanager.user_service.model.User;
import com.taskmanager.user_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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
        return userRepository.findByUsername(username);
    }
}
