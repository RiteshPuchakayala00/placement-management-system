package com.pms.dto.coordinator;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CoordinatorProfileRequestDto {
    @NotBlank(message = "Designation is required")
    private String designation ;
}
