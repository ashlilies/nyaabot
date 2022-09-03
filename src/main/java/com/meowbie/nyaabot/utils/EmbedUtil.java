package com.meowbie.nyaabot.utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;

import java.awt.Color;
import java.util.Date;

/**
 * This class provides message embed formatting functionality.
 */
public class EmbedUtil {
    /**
     * Adds a timestamp, and the user image to the footer
     * @param e An EmbedBuilder instance
     * @param u The user who called the command
     */
    public static void formatEmbed(EmbedBuilder e, User u) {
        e.setFooter(new Date().toString(), u.getAvatarUrl());
        e.setColor(Color.PINK);
    }
}
