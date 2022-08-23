package com.meowbie.nyaabot.daos;

import com.meowbie.nyaabot.models.GuildSetting;

public interface GuildSettingDao {
    GuildSetting findGuildSetting(String guildId);
    int createGuildSetting(GuildSetting guildSetting);
    int updateGuildSetting(GuildSetting guildSetting);
}

