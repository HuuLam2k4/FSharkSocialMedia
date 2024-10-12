package com.system.fsharksocialmedia.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
@Table(name = "PLACES")
public class Place {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 500)
    @Nationalized
    @Column(name = "NAMEPLACE", length = 500)
    private String nameplace;

    @Size(max = 100)
    @Column(name = "LONGITUDE", length = 100)
    private String longitude;

    @Size(max = 100)
    @Column(name = "LATITUDE", length = 100)
    private String latitude;

    @Size(max = 1000)
    @Column(name = "URLMAP", length = 1000)
    private String urlmap;

    @Size(max = 1000)
    @Nationalized
    @Column(name = "ADDRESS", length = 1000)
    private String address;

    @Size(max = 1000)
    @Nationalized
    @Column(name = "DESCRIPTION", length = 1000)
    private String description;

}