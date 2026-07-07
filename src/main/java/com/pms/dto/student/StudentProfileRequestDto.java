package com.pms.dto.student;

import java.util.List;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentProfileRequestDto {
    @NotBlank(message = "Roll No is Required")
    private String rollNo;

    @NotBlank(message = "Department is Required")
    private String department;

    @NotNull(message = "CGPA is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "CGPA must be greater than 0")
    @DecimalMax(value = "10.0", inclusive = true, message = "CGPA must be less than 10")
    private Double cgpa;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number ,must be 10 digits")
    private String phoneNo;

    @NotNull(message = "Graduation year is")
    private Integer graduationYear;

    private List<String> skills;
}
