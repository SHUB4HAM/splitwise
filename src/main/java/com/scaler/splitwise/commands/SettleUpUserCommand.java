package com.scaler.splitwise.commands;

import com.scaler.splitwise.controllers.SettleUpController;
import com.scaler.splitwise.dtos.SettleUpGroupRequestDto;
import com.scaler.splitwise.dtos.SettleUpUserRequestDto;
import com.scaler.splitwise.dtos.SettleUpUserResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SettleUpUserCommand implements Command{
    private SettleUpController settleUpController;

    @Autowired
    public SettleUpUserCommand(SettleUpController settleUpController){
        this.settleUpController = settleUpController;
    }
    @Override
    public boolean matches(String input) {
        List<String> words = List.of(input.split(" "));
        return (words.size() == 2) && words.get(1).equals(CommandKeywords.SettleUpUser);
    }

    @Override
    public void execute(String input) {
        List<String> words = List.of(input.split(" "));
        SettleUpUserRequestDto requestDto = new SettleUpUserRequestDto();
        Long userId = Long.valueOf(words.get(0));
        requestDto.setUserId(userId);

        SettleUpUserResponseDto responseDto = settleUpController.settleUpUser(requestDto);

    }
}
