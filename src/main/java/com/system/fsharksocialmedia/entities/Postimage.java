package com.system.fsharksocialmedia.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "POSTIMAGES")
public class Postimage {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSTID")
    private Post postid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IMAGE")
    private Image image;

}