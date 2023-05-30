package com.scaler.splitwise.services;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.ExpenseUser;
import com.scaler.splitwise.models.Group;
import com.scaler.splitwise.models.User;
import com.scaler.splitwise.repositories.ExpenseRepository;
import com.scaler.splitwise.repositories.ExpenseUserRepository;
import com.scaler.splitwise.repositories.GroupRepository;
import com.scaler.splitwise.repositories.UserRepository;
import com.scaler.splitwise.strategies.SettleUpStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SettleUpService {
    private ExpenseRepository expenseRepository;
    private GroupRepository groupRepository;
    private SettleUpStrategy settleUpStrategy;
    private UserRepository userRepository;
    private ExpenseUserRepository expenseUserRepository;

    @Autowired
    public SettleUpService(
            ExpenseRepository expenseRepository,
            GroupRepository groupRepository,
            SettleUpStrategy settleUpStrategy,
            UserRepository userRepository,
            ExpenseUserRepository expenseUserRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.groupRepository = groupRepository;
        this.settleUpStrategy = settleUpStrategy;
        this.userRepository = userRepository;
        this.expenseUserRepository = expenseUserRepository;
    }

    public List<Expense> settleUpUser(Long userId){
        /*
         1. Get all expenses in which user is part of
         2. Iterate through all the expenses and find out all people involved in
            those expenses and who owes how much extra/less.
         3. Using Min and Max heap approach, find all the transactions that have to be made
         4. Return transactions involving the user
        */
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException();
        }
        User user = userOptional.get();
        List<ExpenseUser> expenseUsers = expenseUserRepository.findAllByUser(user);

        Set<Expense> expenses = new HashSet<>();
        for(ExpenseUser expenseUser: expenseUsers){
            expenses.add(expenseUser.getExpense());
        }
        List<Expense> expensesToSettle = settleUpStrategy.settleUp(expenses.stream().toList());

        List<Expense> expensesToReturn = new ArrayList<>();
        for(Expense expense: expensesToSettle){
            for(ExpenseUser expenseUser: expense.getExpenseUsers()){
                if (expenseUser.getUser().equals(user)){
                    expensesToReturn.add(expense);
                    break;
                }
            }
        }

        return expensesToReturn;
    }
    public List<Expense> settleUpGroup(Long groupId){
        /*
         1. Get all expenses in which user is part of
         2. Iterate through all the expenses and find out all people involved in
            those expenses and who owes how much extra/less.
         3. Using Min and Max heap approach, find all the transactions that have to be made
         4. Return all transactions
        */
        Optional<Group> groupOptional = groupRepository.findById(groupId);
        if(groupOptional.isEmpty()){
            throw new RuntimeException();
        }

        List<Expense> expenses = expenseRepository.findAllByGroup(groupOptional.get());

        List<Expense> expensesToSettle = settleUpStrategy.settleUp(expenses);

        return expensesToSettle;
    }
}
