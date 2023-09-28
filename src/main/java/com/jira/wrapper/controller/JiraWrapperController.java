package com.jira.wrapper.controller;

import com.jira.wrapper.dto.*;
import com.jira.wrapper.service.JiraWrapperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class JiraWrapperController {
    @Autowired
    JiraWrapperService jiraWrapperService;

    Logger logger = LoggerFactory.getLogger(JiraWrapperController.class);

    @GetMapping("/creation1")
    public ResponseEntity<JiraCreateIssueResponseDto> CallCreateIssueGet(){
        return new ResponseEntity<>(jiraWrapperService.CallCreateIssue(), HttpStatus.OK);
    }
    @PostMapping("/creation")
    public ResponseEntity<JiraCreateIssueResponseDto> CallCreateIssuePost(){
        return new ResponseEntity<>(jiraWrapperService.CallCreateIssue(), HttpStatus.OK);
    }

    @GetMapping("/issue/{key}-{summary}-{description}-{name}")
    public ResponseEntity<JiraCreateIssueResponseDto> CallCreateIssueWithParams(@PathVariable Map<String, String> pathVariablesMap){
        logger.info("Jira Api Service Called");
        JiraCreateIssueRequestDto jiraCreateIssueRequestDto = new JiraCreateIssueRequestDto();
        FieldDto fieldDto = new FieldDto();
        ProjectDto projectDto = new ProjectDto();
        IssueType issueType = new IssueType();
        projectDto.setKey(pathVariablesMap.get("key"));
        issueType.setName(pathVariablesMap.get("name"));
        fieldDto.setProject(projectDto);
        fieldDto.setSummary(pathVariablesMap.get("summary"));
        fieldDto.setDescription(pathVariablesMap.get("description"));
        fieldDto.setIssuetype(issueType);
        jiraCreateIssueRequestDto.setFields(fieldDto);

        return new ResponseEntity<>(jiraWrapperService.CallCreateIssueWithParams(jiraCreateIssueRequestDto), HttpStatus.OK);
    }

}
