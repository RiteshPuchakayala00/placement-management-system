package com.pms.dto.company;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProfileRequestDto {
    @NotBlank(message = "Company Name is Required")
    private String companyName ;
    @NotBlank(message = "website is required")
    private String website ;
}
