package com.jira.wrapper.service;

import com.jira.wrapper.dto.JiraCreateIssueRequestDto;
import com.jira.wrapper.dto.JiraCreateIssueResponseDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Component
public class JiraClient {
    @Value("${jira.service.url}")
    private String url;
    @Value("${jira.service.username}")
    private String username;
    @Value("${jira.service.password}")
    private String password;
    @Autowired
    RestTemplate restTemplateClient;

    public JiraCreateIssueResponseDto callJiraApi(JiraCreateIssueRequestDto requestDto){
        try{
            HttpHeaders headers = initHeaders();
            HttpEntity<JiraCreateIssueRequestDto> httpEntity = new HttpEntity<>(requestDto, headers);
            Object result= restTemplateClient.postForObject(url, httpEntity, Object.class);
//            JiraCreateIssueResponseDto jiraCreateIssueResponseDto = new JiraCreateIssueResponseDto();
//            jiraCreateIssueResponseDto.setId("");
//            jiraCreateIssueResponseDto.setKey("");
//            jiraCreateIssueResponseDto.setSelf("");
            return (JiraCreateIssueResponseDto) result;
        }
        catch (Exception ex){
            ex.printStackTrace();
            throw ex;
        }
    }

    //Basic AUthentication ---- TO DO
    private HttpHeaders initHeaders() {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            Base64 base64 = new Base64();
            String authHeader = "Basic " + new String(base64.encode(auth.getBytes()));
            set("Authorization", authHeader);
        }};
    }




}
