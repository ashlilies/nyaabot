package com.meowbie.events;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class HelloEvent extends ListenerAdapter {
    public void onMessageReceived(MessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();
        if (messageSent.equalsIgnoreCase("hello")) {
            event.getChannel().sendMessage("Hi!").queue();
        }
    }
}
