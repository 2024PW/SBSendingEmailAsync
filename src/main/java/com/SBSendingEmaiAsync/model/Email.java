package com.SBSendingEmaiAsync.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @NotEmpty(message = "Email receiver list cannot be empty.")
    private List<@NotBlank(message = "Email receiver list cannot be empty.") String> to;

    @NotNull(message = "Bcc cannot be null. But it can be empty.")
    private String bcc;

    @NotBlank(message = "Email subject cannot be blank.")
    private String subject;

    @NotBlank(message = "Email content cannot be blank.")
    private String content;
}
