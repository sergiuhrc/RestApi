package com.endava.apijira.services;

import com.endava.apijira.model.issues.Issue;
import org.springframework.http.ResponseEntity;

public interface ServiceJiraCrud {
    void createIssue(Issue issue);
    void deleteIssue(String id);
    ResponseEntity<?> getIssue(String id);
    void updateTask(String id, Issue issue);
}
