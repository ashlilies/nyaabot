package com.meowbie.nyaabot.commands;

import com.meowbie.nyaabot.services.GuildService;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BaseCommand extends ListenerAdapter {
    private final String name;

    public BaseCommand(String commandName) {
        name = commandName;
    }
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        super.onMessageReceived(event);

        String message = event.getMessage().getContentRaw();
        GuildService svc = new GuildService();
        String prefix = svc.getGuildPrefix(event.getGuild());

        if (!message.startsWith(prefix + name)) {
            return;
        }
    }
}
