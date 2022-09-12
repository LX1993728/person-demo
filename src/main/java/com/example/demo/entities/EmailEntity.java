package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "email")
public class EmailEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String receiver;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    private Timestamp  sendTime;

}
