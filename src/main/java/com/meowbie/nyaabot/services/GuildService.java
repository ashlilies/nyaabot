package com.meowbie.nyaabot.services;

import com.meowbie.nyaabot.Constants;
import com.meowbie.nyaabot.daos.GuildSettingDao;
import com.meowbie.nyaabot.daos.GuildSettingDaoImpl;
import com.meowbie.nyaabot.models.GuildSetting;
import net.dv8tion.jda.api.entities.Guild;

public class GuildService {
    private final GuildSettingDao dao = new GuildSettingDaoImpl();

    /**
     * This method loads a guild's settings, or creates them if not found
     * inside the database.
     * @param guild The guild to load settings from
     * @return Guild settings
     */
    public GuildSetting loadGuild(Guild guild) {
        GuildSetting gs = dao.findGuildSetting(guild.getId());

        if (gs == null) {
            gs = new GuildSetting();
            gs.setGuildId(guild.getId());
            gs.setPrefix(Constants.DEFAULT_PREFIX);
            dao.createGuildSetting(gs);
        }

        return gs;
    }

    public String getGuildPrefix(Guild guild) {
        return loadGuild(guild).getPrefix();
    }

    public void updateGuildPrefix(Guild guild, String newPrefix) {
        GuildSetting gs = loadGuild(guild);
        gs.setPrefix(newPrefix);
        dao.updateGuildSetting(gs);
    }
}
