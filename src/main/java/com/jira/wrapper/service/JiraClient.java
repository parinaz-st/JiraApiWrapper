package com.jira.wrapper.service;

import com.jira.wrapper.dto.JiraCreateIssueRequestDto;
import com.jira.wrapper.dto.JiraCreateIssueResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class JiraClient {
    @Value("jira.service.url")
    private String url;
    @Value("jira.service.username")
    private String username;
    @Value("jira.service.password")
    private String pasword;
    @Autowired
    RestTemplate restTemplateClient;

    public JiraCreateIssueResponseDto callJiraApi(JiraCreateIssueRequestDto requestDto){
        try{
            return restTemplateClient.postForObject(url, requestDto, JiraCreateIssueResponseDto.class);
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
    }

    //Basic AUthentication ---- TO DO


}
