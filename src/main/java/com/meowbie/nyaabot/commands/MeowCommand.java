package com.meowbie.nyaabot.commands;

import com.meowbie.nyaabot.services.GuildService;
import com.meowbie.nyaabot.utils.ColorUtil;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.Random;

/**
 * This command returns an image of a random cat.
 */
public class MeowCommand extends ListenerAdapter {
    private static final String RANDOM_CAT_URL = "https://cataas.com/cat/cute";
    private static final String[] RANDOM_TEXT = {"Meow!", "Nya...", "Purr..."};

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String message = event.getMessage().getContentRaw();
        MessageChannelUnion channel = event.getChannel();
        User requester = event.getAuthor();

        GuildService svc = new GuildService();
        String prefix = svc.getGuildPrefix(event.getGuild());

        if (!message.startsWith(prefix + "meow")) {
            return;
        }

        // Generate random text
        Random rand = new Random();
        int textIdx = rand.nextInt(RANDOM_TEXT.length);

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(ColorUtil.getRandomColor());
        eb.setImage(RANDOM_CAT_URL);
        eb.setTitle(RANDOM_TEXT[textIdx]);
        eb.setFooter(new Date().toString(), requester.getAvatarUrl());
        channel.sendMessageEmbeds(eb.build()).queue();
    }
}
