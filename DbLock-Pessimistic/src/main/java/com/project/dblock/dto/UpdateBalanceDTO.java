package com.project.dblock.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateBalanceDTO {

    private Long customerId;
    private Double balance;
    private Integer timeout;

}
