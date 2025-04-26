package com.abanoub.criteria;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class FilterQuery {
    public String attributeName;
    public Object attributeValue;
    public Operator op;
    // private String type;
}
