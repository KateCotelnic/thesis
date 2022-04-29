package com.ehelth.rs.entities;


import com.ehelth.rs.entities.dto.HospitalDTO;
import com.ehelth.rs.entities.enums.Area;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "HOSPITALS")
public class Hospital {
    @Id
    @Column(name = "hospital_name")
    private String hospitalName;

    @Column(name = "is_enable")
    @NonNull
    private boolean isEnable;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "city_area")
    @NonNull
    private Area cityArea;

    @Column(name = "photo")
    private String photo;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "website")
    private String website;

    @Column(name = "address")
    @NonNull
    private String address;

    @ManyToMany
    @JoinTable(name = "hospitals_doctors", joinColumns = @JoinColumn(name = "hospital_name"), inverseJoinColumns = @JoinColumn(name = "doctor_email"))
    private List<User> doctors;

    public HospitalDTO toHospitalDTO(){
        return HospitalDTO.builder()
                .hospitalName(hospitalName)
                .phoneNumber(phoneNumber)
                .address(address)
                .photo(photo)
                .cityArea(cityArea.toString())
                .website(website)
                .build();
    }
}
