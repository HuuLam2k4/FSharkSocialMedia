package com.system.fsharksocialmedia.repositories;

import com.system.fsharksocialmedia.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {
}