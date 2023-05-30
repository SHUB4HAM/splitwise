package com.scaler.splitwise.commands;

import org.springframework.stereotype.Component;

@Component
public interface Command {
    boolean matches(String input);
    void execute(String input);
}
