package com.meowbie.nyaabot.daos;

import com.meowbie.nyaabot.models.GuildSetting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GuildSettingDaoImpl extends BaseDao implements GuildSettingDao {
    private static final String FIND_GUILD_SETTING =
            "SELECT * FROM guild_setting WHERE guild_id = ?";
    private static final String CREATE_GUILD_SETTING =
            "INSERT INTO guild_setting(guild_id, prefix) VALUES (?, ?)";
    private static final String UPDATE_GUILD_SETTING =
            "UPDATE guild_setting SET guild_id = ?, prefix = ? WHERE id = ?";

    @Override
    public GuildSetting findGuildSetting(String guildId) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(FIND_GUILD_SETTING);
            stmt.setString(1, guildId);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                GuildSetting guildSetting = new GuildSetting();
                guildSetting.setId(rs.getInt("id"));
                guildSetting.setGuildId(rs.getString("guild_id"));
                guildSetting.setPrefix(rs.getString("prefix"));

                return guildSetting;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    /**
     * Creates a new guild in the database
     * @param guildSetting The GuildSetting object to be inserted
     * @return Number of affected rows
     */
    @Override
    public int createGuildSetting(GuildSetting guildSetting) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(CREATE_GUILD_SETTING);
            stmt.setString(1, guildSetting.getGuildId());
            stmt.setString(2, guildSetting.getPrefix());

            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                guildSetting.setId(rs.getInt(1));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    /**
     * Updates a guild's settings
     * @param guildSetting The GuildSetting object with new details
     * @return Number of affected rows
     */
    @Override
    public int updateGuildSetting(GuildSetting guildSetting) {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement(UPDATE_GUILD_SETTING);
            stmt.setString(1, guildSetting.getGuildId());
            stmt.setString(2, guildSetting.getPrefix());
            stmt.setInt(3, guildSetting.getId());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }
}
