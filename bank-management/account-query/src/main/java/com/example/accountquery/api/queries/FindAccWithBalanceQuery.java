package com.example.accountquery.api.queries;

import com.example.cqrscore.queries.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class FindAccWithBalanceQuery  extends BaseQuery {
    private BigDecimal lessBalance;
    private BigDecimal highBalance;

}
