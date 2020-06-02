package pl.rpw.wpam;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TextureRowMapper implements RowMapper<Texture> {
    @Override
    public Texture mapRow(ResultSet resultSet, int i) throws SQLException {
        Texture texture = new Texture();

        texture.setEmail(resultSet.getString("EMAIL"));
        texture.setTexture(resultSet.getString("TEXTURE"));

        return texture;
    }
}
