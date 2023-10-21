package com.joaosilveira.springcoredemo;

import org.springframework.stereotype.Component;

@Component
public class CircketCoach implements Coach{
    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }
}
