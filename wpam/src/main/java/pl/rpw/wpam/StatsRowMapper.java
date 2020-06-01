package pl.rpw.wpam;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StatsRowMapper implements RowMapper<Stats> {
    @Override
    public Stats mapRow(ResultSet resultSet, int i) throws SQLException {
        return new Stats(
                resultSet.getString("EMAIL"),
                resultSet.getString("NAME"),
                resultSet.getInt("LEVEL"),
                resultSet.getInt("DAMAGE"),
                resultSet.getInt("HEALTH"),
                resultSet.getInt("ENERGY"),
                resultSet.getInt("HEALING"),
                resultSet.getInt("ARMOR"),
                resultSet.getInt("CRIT"),
                resultSet.getInt("STUN"),
                resultSet.getInt("EVADE"),
                resultSet.getInt("POINTS")
        );
    }
}
