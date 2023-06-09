package com.scaler.splitwise.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CommandExecutor {
    List<Command> commands;

    @Autowired
    public CommandExecutor(
            SettleUpUserCommand settleUpUserCommand,
            RegisterUserCommand registerUserCommand,
            UpdateProfileCommand updateProfileCommand
            ){
        commands = new ArrayList<>();
        commands.add(settleUpUserCommand);
        commands.add(registerUserCommand);
        commands.add(updateProfileCommand);
    }

    public void addCommand(Command command){
        this.commands.add(command);
    }

    public void removeCommand(Command command){
        this.commands.remove(command);
    }

    public void execute(String input){
        for(Command command: commands){
            if(command.matches(input)){
                command.execute(input);
                break;
            }
        }
    }
}
