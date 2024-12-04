package org.example.cropmonitoringsystem.dto.impl;

import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.cropmonitoringsystem.customObj.StaffResponse;
import org.example.cropmonitoringsystem.dto.SuperDTO;
import org.example.cropmonitoringsystem.enums.Gender;
import org.example.cropmonitoringsystem.enums.Role;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StaffDTO implements SuperDTO, StaffResponse {
    @Id
    private String staffId;

    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "First name must contain only alphabets")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Last name must contain only alphabets")
    private String lastName;

    @NotNull(message = "Designation cannot be null")
    private String designation;

    @NotNull(message = "Gender cannot be null")
    private Gender gender;

    @PastOrPresent(message = "Joined date must be in the past or today")
    private LocalDate joinedDate;

    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;

    @NotBlank(message = "Address line 1 cannot be blank")
    @Pattern(regexp = "^[A-Za-z0-9 ,.-/()]+$", message = "Address line contains invalid characters")
    private String addressLine1;

    @Pattern(regexp = "^[A-Za-z0-9 ,.-/()]*$", message = "Address line contains invalid characters")
    private String addressLine2;

    @Pattern(regexp = "^[A-Za-z0-9 ,.-/()]*$", message = "Address line contains invalid characters")
    private String addressLine3;

    @Pattern(regexp = "^[A-Za-z0-9 ,.-/()]*$", message = "Address line contains invalid characters")
    private String addressLine4;

    @Pattern(regexp = "^[A-Za-z0-9 ,.-/()]*$", message = "Address line contains invalid characters")
    private String addressLine5;

    @NotBlank(message = "Contact number cannot be blank")
    @Pattern(regexp = "^\\+?[0-9]{7,15}$", message = "Contact number must be valid and contain 7-15 digits")
    private String contactNo;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email must be valid")
    private String email;

    @NotNull(message = "Role cannot be null")
    private Role role;
}
