package com.gt.giulianotrincavelli.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "ready_message_group")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReadyMessageGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id", nullable = false)
    private Contact contact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "message_group_id", nullable = false)
    private MessageGroup messageGroup;
}
