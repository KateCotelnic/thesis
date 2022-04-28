package com.ehealth.ms.entities.dto;

import lombok.*;
@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {
    private String email;
    private String token;
    private String role;
}
