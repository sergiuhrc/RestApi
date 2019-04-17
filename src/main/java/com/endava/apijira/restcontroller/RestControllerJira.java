package com.endava.apijira.restcontroller;


import com.endava.apijira.model.issues.Issue;
import com.endava.apijira.services.ServiceJiraIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestControllerJira {

    //    @PostMapping("/auth")
//    public HttpHeaders authenticate(){
//
//
//
//
//
//    }
    @Autowired
    ServiceJiraIMPL serviceJira;
    Object a;

    @PostMapping(name = "/auth")
    public void authToJira() {
        serviceJira.auth();
//        System.err(object);
    }



    @GetMapping("/getIssue/{id}")
    public ResponseEntity<?> getIssue(@PathVariable String id) {

        return serviceJira.getIssue(id);
    }

    @PostMapping(value = "/createIssue")
    public void createIssue(@RequestBody Issue issue) {
        serviceJira.createIssue(issue);
    }

    @PutMapping("/updateIssue/{id}")
    public void updateIssue(@PathVariable String id, @RequestBody Issue issue) {

        serviceJira.updateTask(id, issue);
    }

    @DeleteMapping("/deleteIssue/{id}")
    public void deleteIssue(@PathVariable String id) {

        serviceJira.deleteIssue(id);
    }
}
