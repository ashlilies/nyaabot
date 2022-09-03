package com.meowbie.nyaabot.commands;

import com.meowbie.nyaabot.services.GuildService;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.Color;
import java.util.Date;
import java.util.List;

public class UserInfoCommand extends ListenerAdapter {
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        String[] messageContent = message.split(" ");
        MessageChannelUnion channel = event.getChannel();

        GuildService svc = new GuildService();
        String prefix = svc.getGuildPrefix(event.getGuild());

        if (!message.startsWith(prefix + "userinfo")) {
            return;
        }

        if (messageContent.length != 2) {
            String helpMessage = "Usage: ``userinfo <name>``";
            channel.sendMessage(helpMessage).queue();
            return;
        }

        String username = messageContent[1];
        List<Member> members = event.getGuild()
                .getMembersByEffectiveName(username, true);

        if (members.size() != 1) {
            if (members.size() == 0) {
                channel.sendMessage("No user found...").queue();
            } else {
                channel.sendMessage("More than 1 user found. "
                        + "Be more specific?").queue();
            }
            return;
        }

        Member member = members.get(0);
        User user = member.getUser();
        Date date = new Date();

        User requester = event.getAuthor();

        String footerText = date + " by " + requester.getName();

        EmbedBuilder avatarEmbed = new EmbedBuilder();
        avatarEmbed.setTitle(user.getName() + "#" + user.getDiscriminator());
        avatarEmbed.setColor(Color.GREEN);
        avatarEmbed.addField("Nickname", user.getName(), false);
        avatarEmbed.addField("Online Status",
                member.getOnlineStatus().toString(), false);
        avatarEmbed.addField("Avatar", "", false);
        avatarEmbed.setImage(user.getAvatarUrl());
        avatarEmbed.setDescription(user.getId());
        avatarEmbed.setFooter(footerText, requester.getAvatarUrl());

        channel.sendMessageEmbeds(avatarEmbed.build()).queue();
    }
}
