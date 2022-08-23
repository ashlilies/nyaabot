package com.meowbie.nyaabot.models;

import com.meowbie.nyaabot.Constants;

public class GuildSetting {
    private int id;
    private String guildId;
    private String prefix;

    public GuildSetting() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGuildId() {
        return guildId;
    }

    public void setGuildId(String guildId) {
        this.guildId = guildId;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) throws RuntimeException {
        if (prefix.length() > Constants.MAX_PREFIX_LENGTH) {
            throw new RuntimeException("Prefix length is too long!");
        }

        this.prefix = prefix;
    }
}
