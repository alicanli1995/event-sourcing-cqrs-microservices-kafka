package com.example.accountquery.api.queries;

import com.example.cqrscore.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FindAccByHolderIdQuery extends BaseQuery {
    private String accountHolder;
}
