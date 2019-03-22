package com.stackroute.pie.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "searchFrom")
@Data
public class SearchPDM {
    @Id
    public String searchValue;
    int count = 0;
}
