package com.endava.apijira.model;

import com.endava.apijira.model.issues.objects.LoginInfo;
import com.endava.apijira.model.issues.objects.Session;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties(ignoreUnknown = true)
public class SessionAuth {
    private Session session;
    private LoginInfo loginInfo;
}
