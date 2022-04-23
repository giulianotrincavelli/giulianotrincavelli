package com.gt.giulianotrincavelli.repository;

import com.gt.giulianotrincavelli.model.Contact;
import com.gt.giulianotrincavelli.model.MessageGroup;
import com.gt.giulianotrincavelli.model.ReadyMessageGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReadyMessageGroupRepository extends JpaRepository<ReadyMessageGroup, Long> {
    Optional<ReadyMessageGroup> findByContactAndMessageGroup(final Contact contact, final MessageGroup messageGroup);
}