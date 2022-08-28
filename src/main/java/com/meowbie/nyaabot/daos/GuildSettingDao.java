package com.meowbie.nyaabot.daos;

import com.meowbie.nyaabot.models.GuildSetting;

public interface GuildSettingDao {
    GuildSetting find(String guildId);
    void create(GuildSetting guildSetting);
    void save(GuildSetting guildSetting);
}

