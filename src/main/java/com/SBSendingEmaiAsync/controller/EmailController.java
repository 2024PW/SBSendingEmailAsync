package com.SBSendingEmaiAsync.controller;

import com.SBSendingEmaiAsync.model.Email;
import com.SBSendingEmaiAsync.model.MessageResponse;
import com.SBSendingEmaiAsync.service.EmailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@Slf4j
@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Value("${message.success}")
    private String messageSuccess;

    @Value("${message.failure}")
    private String messageFailure;

    @PostMapping("/sendEmailAsync")
    @CrossOrigin(origins = "*")
    @Tag(name = "Spring Boot Sending Email Asynchronously Web API Method")
    public ResponseEntity<MessageResponse> sendEmailAsync(@Valid @RequestBody Email email) {
        try{
            // int i = 1/0;
            CompletableFuture<Boolean> emailResult =  emailService.sendEmail(email);
            // CompletableFuture<Boolean> email2 =  emailService.sendEmail(email);
            // CompletableFuture<Boolean> email3 =  emailService.sendEmail(email);
            Boolean result = emailResult.get();
            if (result) {
                return new ResponseEntity<>(new MessageResponse("messageSuccess"), HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(new MessageResponse(messageFailure), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        catch (Exception exception) {
            return new ResponseEntity<>(new MessageResponse(messageFailure), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
