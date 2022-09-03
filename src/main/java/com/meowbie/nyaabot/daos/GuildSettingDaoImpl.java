package com.meowbie.nyaabot.daos;

import com.meowbie.nyaabot.models.GuildSetting;

import java.sql.*;

public class GuildSettingDaoImpl extends BaseDao implements GuildSettingDao {
    private static final String FIND_QUERY =
            "SELECT * FROM guild_setting WHERE guild_id = ?";
    private static final String CREATE_QUERY =
            "INSERT INTO guild_setting(guild_id, prefix) VALUES (?, ?)";
    private static final String UPDATE_QUERY =
            "UPDATE guild_setting SET guild_id = ?, prefix = ? WHERE id = ?";

    @Override
    public GuildSetting find(String guildId) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_QUERY)) {
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
        }
    }

    /**
     * Creates a new guild in the database
     *
     * @param guildSetting The GuildSetting object to be inserted
     */
    @Override
    public void create(GuildSetting guildSetting) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     CREATE_QUERY,
                     Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, guildSetting.getGuildId());
            stmt.setString(2, guildSetting.getPrefix());

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                guildSetting.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Updates a guild's settings
     *
     * @param guildSetting The GuildSetting object with new details
     */
    @Override
    public void save(GuildSetting guildSetting) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_QUERY)) {
            stmt.setString(1, guildSetting.getGuildId());
            stmt.setString(2, guildSetting.getPrefix());
            stmt.setInt(3, guildSetting.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
