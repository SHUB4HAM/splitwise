package com.scaler.splitwise.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class ExpenseUser extends BaseModel{
    @ManyToOne
    private Expense expense;
    @ManyToOne
    private User user;
    private int amount;
    @Enumerated(value = EnumType.ORDINAL)
    private ExpenseUserType expenseUserType;

}
