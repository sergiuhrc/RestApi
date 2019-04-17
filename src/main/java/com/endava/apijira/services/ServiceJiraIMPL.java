package com.endava.apijira.services;

import com.endava.apijira.model.SessionAuth;
import com.endava.apijira.model.issues.Issue;
import com.endava.apijira.model.issues.objects.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;


@Service
public class ServiceJiraIMPL implements ServiceJiraAuth,ServiceJiraCrud{
    @Autowired
    RestTemplate restTemplate;
    @Value("${r.addres}")
    public String REST_SERVICE_URI;

    @Value("${s.username}")
    private String username;

    @Value("${s.password}")
    private String password;
    String issuePath ="api/2/issue/";

    static Session session = new Session();
    SessionAuth sessionAuth = new SessionAuth();

    @Override
    public ResponseEntity<?> auth() {
        String uri = REST_SERVICE_URI + "auth/1/session";
        HttpHeaders httpHeaders = new HttpHeaders();
        Map<String, String> map = new HashMap<>();
        map.put("username", username);
        map.put("password", password);
        HttpEntity<?> httpEntity = new HttpEntity<>(map, httpHeaders);
        sessionAuth = restTemplate.postForObject(uri, httpEntity, SessionAuth.class);
        System.out.println(sessionAuth);
        session.setName(sessionAuth.getSession().getName());
        session.setValue(sessionAuth.getSession().getValue());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getIssue(String id) {
        String uri = REST_SERVICE_URI + issuePath + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("cookie", session.getName() + "=" + session.getValue());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<Issue> issue = restTemplate.exchange(uri, HttpMethod.GET, httpEntity, Issue.class);
        System.out.println("Issue: " + issue.getBody());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public void createIssue(Issue issue) {
        String uri = REST_SERVICE_URI + issuePath;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("cookie", session.getName() + "=" + session.getValue());
        HttpEntity<Issue> httpEntity = new HttpEntity<>(issue, httpHeaders);
        restTemplate.postForEntity(uri, httpEntity, Issue.class);
    }

    @Override
    public void deleteIssue(String id) {
        String uri = REST_SERVICE_URI + issuePath + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("cookie", session.getName() + "=" + session.getValue());
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<String> issue = restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);

    }

    @Override
    public void updateTask(String id, Issue issue) {
        String uri = REST_SERVICE_URI + issuePath + id;
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("cookie", session.getName() + "=" + session.getValue());
        HttpEntity<Issue> httpEntity = new HttpEntity<>(issue, httpHeaders);
        ResponseEntity<Issue> result = restTemplate.exchange(uri, HttpMethod.PUT, httpEntity, Issue.class);
    }
}
