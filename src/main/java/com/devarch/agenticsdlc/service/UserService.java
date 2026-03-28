package com.devarch.agenticsdlc.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

import com.devarch.agenticsdlc.exception.UserNotFoundException;
import com.devarch.agenticsdlc.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Service for managing Users.
 * Uses an in-memory store for demo purposes.
 */
@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    private final Map<Long, User> store = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public UserService() {
        // Pre-populate with a demo user
        User demoUser = new User(idGenerator.getAndIncrement(), "Demo User", "demo@example.com");
        store.put(demoUser.getId(), demoUser);
        log.info("Initialized UserService with demo user: ID {}", demoUser.getId());
    }

    /**
     * Finds a user by ID.
     * @param id the user ID
     * @return the user
     * @throws UserNotFoundException if not found
     */
    public User findById(Long id) {
        log.debug("Finding user by id: {}", id);
        User user = store.get(id);
        if (user == null) {
            throw new UserNotFoundException(id);
        }
        return user;
    }
}
