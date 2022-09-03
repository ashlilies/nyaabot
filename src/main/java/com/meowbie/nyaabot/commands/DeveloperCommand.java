package com.meowbie.nyaabot.commands;

import com.meowbie.nyaabot.Constants;
import com.meowbie.nyaabot.services.GuildService;
import com.meowbie.nyaabot.utils.EmbedUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.*;

import java.awt.Color;

/**
 * This command provides miscellaneous administrative tools for the bot owner.
 */
public class DeveloperCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String[] messageContent = message.split(" ");
        MessageChannelUnion channel = event.getChannel();
        User user = event.getAuthor();

        GuildService svc = new GuildService();
        String prefix = svc.getGuildPrefix(event.getGuild());

        if (!user.getId().equals(Constants.BOT_OWNER_ID)) {
            return;
        }

        if (!(message.startsWith(prefix + "dev"))) {
            return;
        }

        if (messageContent.length == 1) {
            sendHelpText(event, channel);
            return;
        }

        switch (messageContent[1]) {
        case "shutdown":
            channel.sendMessage("Shutting down bot...").complete();
            shutDownBot(user.getId());
            break;
        case "status":
            if (messageContent.length == 2) {
                sendHelpText(event, channel);
                return;
            }

            StringJoiner sj = new StringJoiner(" ");
            for (int i = 2; i < messageContent.length; i++) {
                sj.add(messageContent[i]);
            }
            String status = sj.toString();

            if (status.length() > 128) {
                channel.sendMessage("Status can't be longer than 128 "
                        + "characters!").queue();
                return;
            }

            setStatus(event, status);
            channel.sendMessage("Successfully updated status to " + status)
                    .queue();
            break;
        case "servers":
            sendAllGuilds(event, channel);
            break;
        default:
            channel.sendMessage("I didn't understand that! Try again?")
                    .queue();
            sendHelpText(event, channel);
            break;
        }
    }

    private void shutDownBot(String userId) {
        System.out.println("Shutting down bot as requested by userId "
                + userId);
        System.exit(0);
    }

    private void setStatus(MessageReceivedEvent e, String status) {
        JDA jda = e.getJDA();
        jda.getPresence().setActivity(Activity.playing(status));
    }

    private void sendAllGuilds(MessageReceivedEvent e,
                               MessageChannelUnion responseChannel) {
        JDA jda = e.getJDA();
        List<Guild> guilds = jda.getGuilds();
        List<Guild> sortedGuilds = new ArrayList<>(guilds);
        sortedGuilds.sort((a, b) ->
                b.loadMembers().get().size() - a.loadMembers().get().size());

        String embedTitle = "I am in " + sortedGuilds.size() + " servers!";
        String embedDesc = "These are the servers";

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(embedTitle);
        eb.setDescription(embedDesc);

        for (Guild guild : sortedGuilds) {
            String guildDesc = guild.loadMembers().get().size()
                    + " members (" + guild.getId() + ")";

            eb.addField(guild.getName(), guildDesc, false);
        }

        EmbedUtil.formatEmbed(eb, e.getAuthor());
        responseChannel.sendMessageEmbeds(eb.build()).queue();
    }

    private void sendHelpText(MessageReceivedEvent e,
                              MessageChannelUnion responseChannel) {
        String embedTitle = "Developer commands available to you";
        String embedDesc = "All developer commands start with *dev*";
        String shutdownText = "shutdown";
        String shutdownDesc = "Shuts down the bot immediately";
        String statusText = "status <newStatus>";
        String statusDesc = "Set my playing activity";
        String serversText = "servers";
        String serversDesc = "Get all servers I'm in";

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.PINK);
        eb.setTitle(embedTitle);
        eb.setDescription(embedDesc);
        eb.addField(shutdownText, shutdownDesc, false);
        eb.addField(statusText, statusDesc, false);
        eb.addField(serversText, serversDesc, false);
        eb.setFooter(new Date().toString(), e.getAuthor().getAvatarUrl());
        responseChannel.sendMessageEmbeds(eb.build()).queue();
    }
}
