package com.ehelth.rs.entities.dto;

import com.ehelth.rs.entities.Hospital;
import com.ehelth.rs.entities.enums.Area;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HospitalDTO {
    private String hospitalName;
    private String cityArea;
    private String photo;
    private String phoneNumber;
    private String website;
    private String address;

    public Hospital toHospital(){
        return Hospital.builder()
                .hospitalName(hospitalName)
                .cityArea(Area.valueOf(cityArea))
                .photo(photo)
                .phoneNumber(phoneNumber)
                .website(website)
                .address(address)
                .doctors(new ArrayList<>())
                .build();
    }
}
