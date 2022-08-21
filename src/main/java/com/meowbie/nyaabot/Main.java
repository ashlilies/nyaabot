package com.meowbie.nyaabot;

import com.meowbie.nyaabot.commands.CalculateCommand;
import com.meowbie.nyaabot.events.HelloEvent;
import com.meowbie.nyaabot.commands.PingCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws LoginException {
        String discordToken = System.getenv("DISCORD_TOKEN");
        String nowPlaying = "Stardew Valley";

        JDA jda = JDABuilder.createDefault(discordToken)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing(nowPlaying))
                .build();

        jda.addEventListener(new HelloEvent());
        jda.addEventListener(new PingCommand());
        jda.addEventListener(new CalculateCommand());
    }
}