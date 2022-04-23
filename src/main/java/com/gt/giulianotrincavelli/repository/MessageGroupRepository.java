package com.gt.giulianotrincavelli.repository;

import com.gt.giulianotrincavelli.model.Contact;
import com.gt.giulianotrincavelli.model.Group;
import com.gt.giulianotrincavelli.model.Message;
import com.gt.giulianotrincavelli.model.MessageGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageGroupRepository extends JpaRepository<MessageGroup, Long> {
    Optional<MessageGroup> findByContactAndGroupAndMessage(final Contact contact, final Group group, final Message message);
    Optional<MessageGroup> findByMessage(final Message message);

}