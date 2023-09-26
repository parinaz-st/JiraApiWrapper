package com.jira.wrapper.service;

import com.jira.wrapper.dto.JiraCreateIssueRequestDto;
import com.jira.wrapper.dto.JiraCreateIssueResponseDto;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedHashMap;

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
        Logger logger = LoggerFactory.getLogger(JiraCreateIssueResponseDto.class);
        try{
            JiraCreateIssueResponseDto jiraCreateIssueResponseDto = new JiraCreateIssueResponseDto();
            HttpHeaders headers = initHeaders();
            HttpEntity<JiraCreateIssueRequestDto> httpEntity = new HttpEntity<>(requestDto, headers);
            Object result= restTemplateClient.postForObject(url, httpEntity, Object.class);

            jiraCreateIssueResponseDto.setId(((LinkedHashMap) result).get("id").toString());
            jiraCreateIssueResponseDto.setKey(((LinkedHashMap) result).get("key").toString());
            jiraCreateIssueResponseDto.setSelf(((LinkedHashMap) result).get("self").toString());
            return jiraCreateIssueResponseDto;
        }
        catch (Exception ex){
            ex.printStackTrace();
            logger.error(ex.getMessage());
            throw ex;
        }
    }
    private HttpHeaders initHeaders() {
        return new HttpHeaders() {{
            String auth = username + ":" + password;
            Base64 base64 = new Base64();
            String authHeader = "Basic " + new String(base64.encode(auth.getBytes()));
            set("Authorization", authHeader);
        }};
    }




}
