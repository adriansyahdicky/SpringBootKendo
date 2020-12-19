package com.apotik.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDTO {
    @NotBlank(message = "First Name Tidak Boleh Kosong")
    private String firstName;
    @NotBlank(message = "Last Name Tidak Boleh Kosong")
    private String lastName;
    @NotBlank(message = "Email Tidak Boleh Kosong")
    @Email(message = "Email Tidak Valid")
    private String email;
    @NotBlank(message = "Password Tidak Boleh Kosong")
    private String password;
    @NotNull(message = "Role Belum Dipilih")
    private long role;
}
