package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.Expense;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class SettleUpUserResponseDto {
    private List<Expense> expenses; // transactions(dummy expenses) if made user will actually settle up.
}
