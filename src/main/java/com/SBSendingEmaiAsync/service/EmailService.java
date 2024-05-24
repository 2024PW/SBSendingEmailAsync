package com.SBSendingEmaiAsync.service;

import com.SBSendingEmaiAsync.model.Email;

import java.util.concurrent.CompletableFuture;

public interface EmailService {
    CompletableFuture<Boolean> sendEmail(Email email);
}
