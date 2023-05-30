package com.scaler.splitwise.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Expense extends BaseModel{
    private String description;
    private int amount;
    @ManyToOne
    private User createdBy;
    @Enumerated(value = EnumType.ORDINAL)
    private ExpenseType expenseType;
    @ManyToOne
    private Group group;
    @OneToMany(mappedBy = "expense")
    private List<ExpenseUser> expenseUsers;
}
