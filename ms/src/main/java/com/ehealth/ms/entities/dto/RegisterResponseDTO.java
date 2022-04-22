package com.ehealth.ms.entities.dto;

import lombok.*;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponseDTO {
    private String email;
    private String token;
}
