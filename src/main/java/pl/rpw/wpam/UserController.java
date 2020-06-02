package pl.rpw.wpam;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @CrossOrigin
    @PutMapping("/login")
    public boolean login(@RequestBody User user) {
        return authorize(user.getEmail(), user.getPassword());
    }

    @CrossOrigin
    @PutMapping("/register")
    public boolean register(@RequestBody User user) {
        List<User> fetched = jdbcTemplate.query(
                "SELECT * FROM USERS WHERE EMAIL = ?", new Object[]{user.getEmail()}, new UserRowMapper()
        );

        if (fetched.size() != 0)
            return false;

        jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?)", user.getEmail(), user.getPassword());
        return true;
    }

    @CrossOrigin
    @PutMapping("/save")
    public boolean save(@RequestBody SaveRequest request) {
        if (authorize(request.getEmail(), request.getPassword())) {
            List<Texture> fetched = jdbcTemplate.query(
                    "SELECT * FROM TEXTURES WHERE EMAIL = ?", new Object[]{request.getEmail()}, new TextureRowMapper()
            );

            if (fetched.size() == 0) {
                jdbcTemplate.update("INSERT INTO TEXTURES VALUES (?, ?)", request.getEmail(), request.getTexture());
            } else {
                jdbcTemplate.update("UPDATE TEXTURES SET email=?, texture=?", request.getEmail(), request.getTexture());
            }

            List<Stats> fetchedStats = jdbcTemplate.query(
                    "SELECT * FROM STATS WHERE EMAIL = ?", new Object[]{request.getEmail()}, new StatsRowMapper()
            );

            if (fetchedStats.size() == 0) {
                jdbcTemplate.update("INSERT INTO STATS VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", request.getEmail(), "Name...", 1, 1, 1, 1, 1, 0, 0, 0, 0, 10);
            }

            return true;
        } else {
            return false;
        }
    }

    @CrossOrigin
    @PutMapping("/load")
    public String load(@RequestBody User request) {
        if (authorize(request.getEmail(), request.getPassword())) {
            List<Texture> fetched = jdbcTemplate.query(
                    "SELECT * FROM TEXTURES WHERE EMAIL = ?", new Object[]{request.getEmail()}, new TextureRowMapper()
            );

            return fetched.get(0).getTexture();
        } else {
            return "UNAUTHORISED";
        }
    }

    @CrossOrigin
    @PutMapping("/loadStats")
    public Stats loadStats(@RequestBody User request) {
        if (authorize(request.getEmail(), request.getPassword())) {
            List<Stats> fetched = jdbcTemplate.query(
                    "SELECT * FROM STATS WHERE EMAIL = ?", new Object[]{request.getEmail()}, new StatsRowMapper()
            );

            return fetched.get(0);
        } else {
            throw new IllegalStateException("UNAUTHORISED");
        }
    }

    @CrossOrigin
    @PutMapping("/saveStats")
    public boolean saveStats(@RequestBody StatsSaveRequest request) {
        if (authorize(request.getEmail(), request.getPassword())) {
            jdbcTemplate.update("UPDATE STATS SET name=?, level=?, damage=?, health=?, energy=?, healing=?, armor=?, crit=?, stun=?, evade=?, points=? where email=?",
                    request.getName(),
                    request.getLevel(),
                    request.getDamage(),
                    request.getHealth(),
                    request.getEnergy(),
                    request.getHealing(),
                    request.getArmor(),
                    request.getCrit(),
                    request.getStun(),
                    request.getEvade(),
                    request.getPoints(),
                    request.getEmail()
                    );
            return true;
        } else {
            return false;
        }
    }

    private boolean authorize(String email, String password) {
        List<User> fetchedUsers = jdbcTemplate.query(
                "SELECT * FROM USERS WHERE EMAIL = ?", new Object[]{email}, new UserRowMapper()
        );

        if (fetchedUsers.size() != 1)
            return false;

        return fetchedUsers.get(0).getEmail().equals(email) && fetchedUsers.get(0).getPassword().equals(password);
    }
}
