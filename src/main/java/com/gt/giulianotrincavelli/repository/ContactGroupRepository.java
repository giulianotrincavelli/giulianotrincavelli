package com.gt.giulianotrincavelli.repository;

import com.gt.giulianotrincavelli.model.ContactGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactGroupRepository extends JpaRepository<ContactGroup, Long> {
}