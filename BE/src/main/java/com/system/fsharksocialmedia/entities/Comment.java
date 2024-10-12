package com.system.fsharksocialmedia.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 500)
    @Nationalized
    @Column(name = "CONTENT", length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNAME")
    private User username;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE")
    private Image image;

}