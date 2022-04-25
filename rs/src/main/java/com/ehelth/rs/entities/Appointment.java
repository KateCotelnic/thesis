package com.ehelth.rs.entities;


import com.ehelth.rs.entities.enums.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "APPOINTMENTS")
public class Appointment {
    @Id
    @SequenceGenerator(
            name = "ehealth_sequence",
            sequenceName = "ehealth_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ehealth_sequence")
    @Column(name = "appointment_id")
    private long appointmentId;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "doctor_email")
    @NonNull
    private User doctor;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "patient_email")
    @NonNull
    private User patient;

    @ManyToOne(targetEntity = Hospital.class)
    @JoinColumn(name = "hospital_name")
    @NonNull
    private Hospital hospital;

    @Column(name = "date")
    @NonNull
    private LocalDateTime date;

    @Column(name = "duration")
    @NonNull
    private int duration;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    @NonNull
    private Status status;
}
