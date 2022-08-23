package com.meowbie.nyaabot;

public class Constants {
    public static final String BOT_OWNER_ID = System.getenv("BOT_OWNER");
    public static final String DISCORD_TOKEN = System.getenv("DISCORD_TOKEN");
    public static final String DEFAULT_NOW_PLAYING = "with a cat | !help";
    public static final String DEFAULT_PREFIX = "!";
    public static final int MAX_PREFIX_LENGTH = 10;  // match DB

    // Database constants
    public static final String DB_URL = System.getenv("DB_URL");
    public static final String DB_USERNAME = System.getenv("DB_USER");
    public static final String DB_PASSWORD = System.getenv("DB_PASSWORD");
}
