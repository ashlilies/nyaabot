package com.meowbie.nyaabot.commands;

import com.meowbie.nyaabot.Constants;
import com.meowbie.nyaabot.services.GuildService;
import com.meowbie.nyaabot.utils.EmbedUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class HelpCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        MessageChannelUnion channel = event.getChannel();

        GuildService svc = new GuildService();
        String prefix = svc.getGuildPrefix(event.getGuild());

        if (!message.startsWith(prefix + "help")) {
            return;
        }

        channel.sendMessageEmbeds(
                getHelpEmbed(event.getAuthor(),event.getGuild())).queue();
    }

    private MessageEmbed getHelpEmbed(User user, Guild guild) {
        String embedTitle = "Welcome to nyaabot by ashe#0001";
        String embedDesc = "Available commands";
        String pingText = "ping/pong/pung";
        String pingDesc = "Check if I'm alive";
        String calculateText = "calculate [add/sub/mul/div] <first_num> "
                + "<second_num>";
        String calculateDesc = "Perform calculations";
        String userInfoText = "userinfo <name>";
        String userInfoDesc = "Get user info by nickname";
        String meowText = "meow";
        String meowDesc = "Get a random cat GIF";
        String serverText = "server";
        String serverDesc = "Access server owner commands";
        String devText = "dev";
        String devDesc = "Access developer/bot owner commands";

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle(embedTitle);
        builder.setDescription(embedDesc);
        builder.addField(calculateText, calculateDesc, false);
        builder.addField(pingText, pingDesc, false);
        builder.addField(userInfoText, userInfoDesc, false);
        builder.addField(meowText, meowDesc, false);

        if (user.getId().equals(guild.getOwnerId())) {
            builder.addField(serverText, serverDesc, false);
        }

        if (user.getId().equals(Constants.BOT_OWNER_ID)) {
            builder.addField(devText, devDesc, false);
        }

        EmbedUtil.formatEmbed(builder, user);

        return builder.build();
    }
}
