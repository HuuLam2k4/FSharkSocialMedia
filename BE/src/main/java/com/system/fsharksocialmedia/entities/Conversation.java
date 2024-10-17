package com.system.fsharksocialmedia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CONVERSATIONS")
public class Conversation {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 300)
    @Nationalized
    @Column(name = "NAME", length = 300)
    private String name;

    @Column(name = "CREATEDAT")
    private Instant createdat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AVATAR")
    private Image avatar;

}