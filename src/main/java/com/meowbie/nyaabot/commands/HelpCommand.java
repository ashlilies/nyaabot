package com.meowbie.nyaabot.commands;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.StringJoiner;

public class HelpCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        MessageChannelUnion channel = event.getChannel();

        if (!message.startsWith("!help")) {
            return;
        }

        String welcomeText = "Welcome to nyaabot by ashe#0001.\n"
                + "Available commands:";
        String pingText = "``!ping/pong/pung``";
        String calculateText = "``!calculate`` - perform calculations";
        String userInfoText = "``!userinfo`` - get user info by nickname";
        String meowText = "``!meow`` - get a random cat GIF";

        StringJoiner sj = new StringJoiner("\n");
        sj.add(welcomeText);
        sj.add(pingText);
        sj.add(calculateText);
        sj.add(userInfoText);
        sj.add(meowText);
        String reply = sj.toString();
        channel.sendMessage(reply).queue();
    }
}
