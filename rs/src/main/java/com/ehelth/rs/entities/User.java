package com.ehelth.rs.entities;

import com.ehelth.rs.entities.dto.DoctorDTO;
import com.ehelth.rs.entities.dto.DoctorDetailsDTO;
import com.ehelth.rs.entities.dto.UserCredentialsDTO;
import com.ehelth.rs.entities.dto.UserDetailsDTO;
import com.ehelth.rs.entities.enums.Classification;
import com.ehelth.rs.entities.enums.Grade;
import com.ehelth.rs.entities.enums.Role;
import com.ehelth.rs.entities.enums.Speciality;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @Column(name = "age")
    private int age;

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
    private List<FreeTime> freeTimes;

    @OneToMany(mappedBy = "doctor",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comment> commentsForDoctors;

    @OneToMany(mappedBy = "patient",cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Comment> commentsForUser;

    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    private List<Hospital> hospitals;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Appointment> appointmentsForDoctor;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<Appointment> appointmentsForUsers;

    public UserCredentialsDTO toUserCredentialsDTO(){
        return UserCredentialsDTO.builder()
                .email(this.email)
                .password(this.password)
                .role(this.role.toString())
                .isEnable(this.isEnable)
                .build();
    }

    public DoctorDTO toDoctorDTO(){
        return DoctorDTO.builder()
                .email(email)
                .classification(classification.toString())
                .description(description)
                .firstName(firstName)
                .middleName(middleName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .speciality(speciality.toString())
                .price(price + "")
                .photo(photo)
                .grade(grade.toString())
                .experience(experience + "")
                .rating(rating + "")
                .hospitals(hospitals.stream().map(Hospital::getHospitalName).collect(Collectors.toList()))
                .build();
    }

    public DoctorDetailsDTO toDoctorDetailsDTO(){
        return DoctorDetailsDTO.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .phoneNumber(phoneNumber)
                .speciality(speciality.toString())
                .price(price + "")
                .photo(photo)
                .grade(grade.toString())
                .experience(experience + "")
                .description(description)
                .classification(classification.toString())
                .rating(rating + "")
                .hospitals(hospitals.stream().map(Hospital::getHospitalName).collect(Collectors.toList()))
                .comments(commentsForDoctors.stream().map(Comment::toCommentDTO).collect(Collectors.toList()))
                .appointmentsDoctor(appointmentsForDoctor.stream().map(Appointment::toAppointmentDoctorDTO).collect(Collectors.toList()))
                .freeTime(freeTimes.stream().map(FreeTime::toFreeTimeForDoctorDTO).collect(Collectors.toList()))
                .build();
    }

    public UserDetailsDTO toUserDetailsDTO(){
        return UserDetailsDTO.builder()
                .firstName(firstName)
                .lastName(lastName)
                .middleName(middleName)
                .age(age + "")
                .phoneNumber(phoneNumber)
                .photo(photo)
                .appointments(appointmentsForUsers.stream().map(Appointment::toAppointmentPatientDTO).collect(Collectors.toList()))
                .build();
    }
}
