package com.ehelth.rs.entities;


import com.ehelth.rs.entities.dto.CommentDTO;
import com.ehelth.rs.entities.dto.NewCommentDTO;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "COMMENTS")
public class Comment {
    @Id
    @SequenceGenerator(
            name = "ehealth_sequence",
            sequenceName = "ehealth_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ehealth_sequence")
    @Column(name = "comment_id")
    private long commentId;

    @Column(name = "body")
    private String body;

    @Column(name = "rating")
    @NonNull
    private int rating;

    @Column(name = "date")
    @NonNull
    private LocalDate date;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "patient_email")
    @NonNull
    private User patient;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "doctor_email")
    @NonNull
    private User doctor;

    public CommentDTO toCommentDTO(){
        return CommentDTO.builder()
                .id(commentId + "")
                .body(body)
                .date(date.toString())
                .rating(rating + "")
                .firstNamePatient(patient.getFirstName())
                .lastNamePatient(patient.getLastName())
                .middleNamePatient(patient.getMiddleName())
                .photoPatient(patient.getPhoto())
                .build();
    }

    public NewCommentDTO toNewCommentDTO(){
        return NewCommentDTO.builder()
                .commentId(commentId + "")
                .body(body)
                .rating(rating + "")
                .date(date.toString())
                .patientEmail(patient.getEmail())
                .doctorEmail(doctor.getEmail())
                .build();
    }
}
