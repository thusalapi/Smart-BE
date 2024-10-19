package org.trash.smartbe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.trash.smartbe.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}