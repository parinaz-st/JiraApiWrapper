package com.jira.wrapper.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JiraWrapperController {
    @GetMapping("/jira-issue")
    public ResponseEntity<String> CallCreateIssue(){
        return new ResponseEntity<String>("success", HttpStatus.OK);
    }
}
