package com.example.accountquery.api.queries;

import com.example.cqrscore.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccByIdQuery extends BaseQuery {
    private String id;
}
