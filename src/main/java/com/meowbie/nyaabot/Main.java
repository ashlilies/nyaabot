package com.meowbie.nyaabot;

import com.meowbie.nyaabot.commands.*;
import com.meowbie.nyaabot.events.HelloEvent;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {
        String nowPlaying = "with a cat";

        if (Constants.DISCORD_TOKEN == null) {
            System.err.println("Invalid DISCORD_TOKEN environment variable");
            System.exit(1);
        }

        JDA jda = JDABuilder.createDefault(Constants.DISCORD_TOKEN)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing(nowPlaying))
                .build();

        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new HelpCommand());
        jda.addEventListener(new PingCommand());
        jda.addEventListener(new CalculateCommand());
        jda.addEventListener(new UserInfoCommand());
        jda.addEventListener(new MeowCommand());
        jda.addEventListener(new DeveloperCommand());
    }
}