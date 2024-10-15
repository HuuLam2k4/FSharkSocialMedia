package com.system.fsharksocialmedia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "POSTS")
public class Post {
    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNAME")
    private User username;

    @Size(max = 200)
    @Nationalized
    @Column(name = "CONTENT", length = 200)
    private String content;

    @Column(name = "CREATEDATE")
    private Instant createdate;

}