package com.meowbie.nyaabot.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.Date;
import java.util.StringJoiner;

/**
 * This command provides miscellaneous administrative tools for the bot owner.
 */
public class DeveloperCommand extends ListenerAdapter {
    private static final String BOT_OWNER_ID = System.getenv("BOT_OWNER");

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String[] messageContent = message.split(" ");
        MessageChannelUnion channel = event.getChannel();
        User user = event.getAuthor();

        if (!user.getId().equals(BOT_OWNER_ID)) {
            return;
        }

        if (!(message.startsWith("!dev"))) {
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

    private void sendHelpText(MessageReceivedEvent e,
                              MessageChannel responseChannel) {
        String titleText = "Developer commands available to you:";
        String shutdownText = "``shutdown``";
        String shutdownDesc = "Shuts down the bot immediately";
        String statusText = "``status <newStatus>``";
        String statusDesc = "Set my playing activity";

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.PINK);
        eb.setTitle(titleText);
        eb.addField(shutdownText, shutdownDesc, false);
        eb.addField(statusText, statusDesc, false);
        eb.setFooter(new Date().toString(), e.getAuthor().getAvatarUrl());
        responseChannel.sendMessageEmbeds(eb.build()).queue();
    }
}
