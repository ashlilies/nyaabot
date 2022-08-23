package com.meowbie.nyaabot.commands;

import com.meowbie.nyaabot.services.GuildService;
import com.meowbie.nyaabot.utils.EmbedUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class ServerCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String[] messageContent = message.split(" ");
        MessageChannelUnion channel = event.getChannel();

        GuildService svc = new GuildService();
        String prefix = svc.getGuildPrefix(event.getGuild());

        // Ignore all messages not from server owner
        if (!event.getAuthor().getId().equals(event.getGuild().getOwnerId())) {
            return;
        }

        if (!message.startsWith(prefix + "server")) {
            return;
        }

        if (messageContent.length == 1) {
            sendHelpText(event, channel);
            return;
        }

        switch (messageContent[1]) {
        case "prefix":
            if (messageContent.length != 3) {
                sendHelpText(event, channel);
                return;
            }

            String newPrefix = messageContent[2];
            svc.updateGuildPrefix(event.getGuild(), newPrefix);
            channel.sendMessage("Successfully updated server prefix to **"
                            + newPrefix + "**")
                    .queue();
            break;
        default:
            sendHelpText(event, channel);
            break;
        }
    }

    private void sendHelpText(MessageReceivedEvent e,
                              MessageChannelUnion responseChannel) {
        String embedTitle = "Server management commands";
        String embedDesc = "Only available to the server owner.\n"
                + "All server management commands start with *server*.";
        String prefixText = "prefix <newPrefix>";
        String prefixDesc = "Set the bot's prefix for this server.\n"
                + "Ping the bot to check the prefix.";

        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle(embedTitle);
        eb.setDescription(embedDesc);
        eb.addField(prefixText, prefixDesc, false);
        EmbedUtil.formatEmbed(eb, e.getAuthor());
        responseChannel.sendMessageEmbeds(eb.build()).queue();
    }
}
