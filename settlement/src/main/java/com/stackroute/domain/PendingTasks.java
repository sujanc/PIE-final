package com.stackroute.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Document(collection = "pending_tasks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PendingTasks {
    @Id
    private int pendingTasksId;
    private List<Task> taskList;
    private int portingRequestId;
    private String newInsurerName;
    @NotBlank
    private String insuredName;
    @NotBlank
    private String insurerName;
}
