package com.meowbie.nyaabot.events;

import com.meowbie.nyaabot.models.GuildSetting;
import com.meowbie.nyaabot.services.GuildService;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class PingEvent extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        JDA jda = event.getJDA();
        String botId = jda.getSelfUser().getId();
        String message = event.getMessage().getContentRaw();
        MessageChannelUnion channel = event.getChannel();

        String ping = "<@" + botId + ">";

        if (!message.startsWith(ping)) {
            return;
        }

        Guild guild = event.getGuild();
        GuildService svc = new GuildService();
        GuildSetting gs = svc.loadGuild(guild);
        String reply = "My prefix in this server is **"
                + gs.getPrefix() + "**.\n"
                + "Type `" + gs.getPrefix() + "help` for a list of commands!";
        channel.sendMessage(reply).queue();
    }
}
