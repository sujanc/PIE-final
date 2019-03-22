package com.stackroute.pie.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "FormFormats")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormFormat {
    @Id
    private int formId;
    private String formName;
    private List<String> fields;
}
