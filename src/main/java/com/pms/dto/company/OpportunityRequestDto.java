package com.pms.dto.company;

import com.pms.enums.OpportunityType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OpportunityRequestDto {
    @NotBlank(message = "Title is required")
    private String title ;
    @NotBlank(message = "Description is required")
    private String description ;
    @NotNull(message = "Oppurtunity type is required")
    private OpportunityType type ;
    @Positive(message = "Salary/Stipend must be positive")
    private BigDecimal salaryStipend ;
    @DecimalMin(value = "0.0" , message = "CGPA must be at least 0")
    @DecimalMax(value = "10.0" , message = "CGPA cannot exceed 10")
    private Double requiredCGPA ;
    @Future(message = "Deadline must be a future date")
    @NotNull(message = "Deadline is required")
    private LocalDate deadline ;
}
