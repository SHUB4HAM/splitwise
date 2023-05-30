package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.SettleUpGroupRequestDto;
import com.scaler.splitwise.dtos.SettleUpGroupResponseDto;
import com.scaler.splitwise.dtos.SettleUpUserRequestDto;
import com.scaler.splitwise.dtos.SettleUpUserResponseDto;
import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.services.SettleUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class SettleUpController {
    private SettleUpService settleUpService;

    @Autowired
    public SettleUpController(SettleUpService settleUpService) {
        this.settleUpService = settleUpService;
    }

    /*
            Returns list of transactions if they are made by the user, it will settleup or
            made pending amount to be paid to the friends to be zero.

         */
    public SettleUpUserResponseDto settleUpUser(SettleUpUserRequestDto requestDto){
        SettleUpUserResponseDto responseDto = new SettleUpUserResponseDto();
        List<Expense> expenses = settleUpService.settleUpUser(requestDto.getUserId());
        responseDto.setExpenses(expenses);
        return responseDto;
    }


    public SettleUpGroupResponseDto settleUpGroup(SettleUpGroupRequestDto requestDto){

        return null;
    }
}
