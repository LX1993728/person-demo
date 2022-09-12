package com.example.demo.entities;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "person")
public class PersonEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    private Integer gender;

    @Column(nullable = false)
   @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birth;

    private Boolean disable = false;
}
