package com.jira.wrapper.service;

import com.jira.wrapper.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class JiraWrapperService {

    @Value("${jira.input.key}")
    private String key;
    @Value("${jira.input.summary}")
    private String summary;
    @Value("${jira.input.description}")
    private String description;
    @Value("${jira.input.name}")
    private String name;

    @Autowired
     JiraClient jiraClient;
    public JiraCreateIssueResponseDto CallCreateIssue(){
        JiraCreateIssueRequestDto jiraCreateIssueRequestDto = populateJiraRequest();
        return jiraClient.callJiraApi(jiraCreateIssueRequestDto);
    }

    private JiraCreateIssueRequestDto populateJiraRequest(){
        JiraCreateIssueRequestDto jiraCreateIssueRequestDto = new JiraCreateIssueRequestDto();
        FieldDto fieldDto = new FieldDto();
        ProjectDto projectDto = new ProjectDto();
        IssueType issueType = new IssueType();
        projectDto.setKey(key);
        issueType.setName(name);
        fieldDto.setProject(projectDto);
        fieldDto.setSummary(summary);
        fieldDto.setDescription(description);
        fieldDto.setIssuetype(issueType);
        jiraCreateIssueRequestDto.setFields(fieldDto);
        return  jiraCreateIssueRequestDto;
    }
    public JiraCreateIssueResponseDto CallCreateIssueWithParams(JiraCreateIssueRequestDto requestDto){
        return jiraClient.callJiraApi(requestDto);
    }
}
