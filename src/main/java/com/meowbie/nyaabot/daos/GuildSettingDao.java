package com.meowbie.nyaabot.daos;

import com.meowbie.nyaabot.models.GuildSetting;

public interface GuildSettingDao {
    GuildSetting findGuildSetting(String guildId);
    void createGuildSetting(GuildSetting guildSetting);
    void updateGuildSetting(GuildSetting guildSetting);
}

