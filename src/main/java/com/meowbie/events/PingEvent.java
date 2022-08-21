package com.meowbie.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingEvent extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();
        var channel = event.getChannel();

        if (messageSent.startsWith("!ping")) {
            channel.sendMessage("Pong!").queue();
        } else if (messageSent.startsWith("!pong")) {
            channel.sendMessage("Pung!").queue();
        } else if (messageSent.startsWith("!pung")) {
            channel.sendMessage("Ping!").queue();
        }
    }
}
