package com.ehelth.rs.entities;

import com.ehelth.rs.entities.dto.UserCredentialsDTO;
import com.ehelth.rs.entities.enums.Classification;
import com.ehelth.rs.entities.enums.Grade;
import com.ehelth.rs.entities.enums.Role;
import com.ehelth.rs.entities.enums.Speciality;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @NonNull
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    @NonNull
    private Role role;

    @Column(name = "is_enable")
    @NonNull
    private boolean isEnable;

    @Column(name = "first_name")
    @NonNull
    private String firstName;

    @Column(name = "last_name")
    @NonNull
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "phone_number")
    @NonNull
    private String phoneNumber;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "speciality")
    private Speciality speciality;

    @Column(name = "price")
    private int price;

    @Column(name = "photo")
    private String photo;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "grade")
    private Grade grade;

    @Column(name = "experience")
    private int experience;

    @Column(name = "description")
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "classification")
    private Classification classification;

    @Column(name = "rating")
    private double rating;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comment> commentsForUser;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comment> commentsByUser;

    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    private List<Hospital> hospitals;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Appointment> appointmentsForUser;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Appointment> appointmentsByUser;

    public UserCredentialsDTO toUserCredentialsDTO(){
        return UserCredentialsDTO.builder()
                .email(this.email)
                .password(this.password)
                .role(this.role.toString())
                .isEnable(this.isEnable)
                .build();
    }
}
