package com.ehelth.rs.entities;

import com.ehelth.rs.entities.dto.FreeTimeDTO;
import com.ehelth.rs.entities.dto.FreeTimeForDoctorDTO;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "FREE_TIME")
public class FreeTime {
    @Id
    @SequenceGenerator(
            name = "ehealth_sequence",
            sequenceName = "ehealth_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ehealth_sequence")
    @Column(name = "freetime_id")
    private long freetimeId;

    @Column(name = "cron_expression")
    @NonNull
    private String cronExpression;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "doctor_email")
    @NonNull
    private User doctor;

    public FreeTimeDTO toFreeTimeDTO() {
        return FreeTimeDTO.builder()
                .id(freetimeId + "")
                .doctorEmail(doctor.getEmail())
                .cronExpression(cronExpression)
                .build();
    }

    public FreeTimeForDoctorDTO toFreeTimeForDoctorDTO() {
        return FreeTimeForDoctorDTO.builder()
                .id(freetimeId + "")
                .cronExpression(cronExpression)
                .build();
    }
}
