package com.meowbie.nyaabot;

import com.meowbie.nyaabot.services.GuildService;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

/**
 * Handles miscellaneous stuff, such as guild joins.
 */
public class Listener extends ListenerAdapter {
    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        GuildService svc = new GuildService();
        svc.loadGuild(event.getGuild());
    }
}
