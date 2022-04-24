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
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.CascadeType;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "groups")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "description")
    private String description;

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "group")
    private Set<ContactGroup> contacts = new HashSet<>();

    @Builder.Default
    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, mappedBy = "group")
    private Set<MessageGroup> messageGroups = new HashSet<>();
}
