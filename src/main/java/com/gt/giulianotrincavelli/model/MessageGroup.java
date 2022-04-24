package com.gt.giulianotrincavelli.model;

import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "message_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MessageGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", nullable = false)
    private Group group;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "message_id", nullable = false)
    private Message message;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "messageGroup")
    private Set<ReadyMessageGroup> readyMessageGroups = new HashSet<>();
}
