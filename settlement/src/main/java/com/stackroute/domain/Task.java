package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String taskName;
    private String taskDescription;
    private Boolean taskStatus;
    private String dueDate;
}
