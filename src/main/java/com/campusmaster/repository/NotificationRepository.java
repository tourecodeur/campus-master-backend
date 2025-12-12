package com.campusmaster.repository;

import com.campusmaster.entity.Notification;
import com.campusmaster.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserAndReadIsFalse(User user);
}
