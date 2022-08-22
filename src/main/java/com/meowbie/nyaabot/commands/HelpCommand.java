package com.meowbie.nyaabot.commands;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class HelpCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        MessageChannelUnion channel = event.getChannel();

        if (message.startsWith("!help")) {
            String reply = "Welcome to nyaabot by ashe#0001.\n"
                     + "Available commands:\n"
                     + "``!ping/pong/pung``"
                     + "``!calculate``";
            channel.sendMessage(reply).queue();
        }
    }
}
