package com.sreekanth.mailGuardian.handler;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public class MailBoxDoesntExist extends RuntimeException {

}


