package com.endava.apijira.model.issues.objects;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class Fields {

    @JsonProperty("labels")
    String[] labels;
    Assignee assignee;
    IssueType issuetype;
    Project project;
    String summary;
    String description;


}

