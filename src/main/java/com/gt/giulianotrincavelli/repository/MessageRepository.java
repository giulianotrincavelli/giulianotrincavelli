package com.gt.giulianotrincavelli.repository;

import com.gt.giulianotrincavelli.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findByKey(final String key);
}