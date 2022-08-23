package com.meowbie.nyaabot.commands;

import com.meowbie.nyaabot.services.GuildService;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class PingCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String messageSent = event.getMessage().getContentRaw();
        MessageChannelUnion channel = event.getChannel();

        GuildService svc = new GuildService();
        String prefix = svc.getGuildPrefix(event.getGuild());

        if (messageSent.startsWith(prefix + "ping")) {
            channel.sendMessage("Pong!").queue();
        } else if (messageSent.startsWith(prefix + "pong")) {
            channel.sendMessage("Pung!").queue();
        } else if (messageSent.startsWith(prefix + "pung")) {
            channel.sendMessage("Ping!").queue();
        }
    }
}
