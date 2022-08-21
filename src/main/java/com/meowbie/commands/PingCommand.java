package com.meowbie.commands;

import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();
        MessageChannelUnion channel = event.getChannel();

        if (messageSent.startsWith("!ping")) {
            channel.sendMessage("Pong!").queue();
        } else if (messageSent.startsWith("!pong")) {
            channel.sendMessage("Pung!").queue();
        } else if (messageSent.startsWith("!pung")) {
            channel.sendMessage("Ping!").queue();
        }
    }
}
