package com.jira.wrapper.controller;

import com.jira.wrapper.dto.JiraCreateIssueResponseDto;
import com.jira.wrapper.service.JiraWrapperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JiraWrapperController {
    @Autowired
    JiraWrapperService jiraWrapperService;
    @GetMapping("/jira-issue")
    public ResponseEntity<JiraCreateIssueResponseDto> CallCreateIssue(){
        return new ResponseEntity<JiraCreateIssueResponseDto>(jiraWrapperService.CallCreateIssue(), HttpStatus.OK);
    }
}
