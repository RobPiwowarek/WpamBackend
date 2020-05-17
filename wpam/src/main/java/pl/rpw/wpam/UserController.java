package pl.rpw.wpam;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final JdbcTemplate jdbcTemplate;

    public UserController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @CrossOrigin
    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name, Model model) {
        return "greeting";
    }

    @CrossOrigin
    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        List<User> fetched = jdbcTemplate.query(
                "SELECT * FROM USERS WHERE EMAIL = ?", new Object[]{user.getEmail()}, new UserRowMapper()
        );

        if (fetched.size() != 1)
            return false;

        if (fetched.get(0).getEmail().equals(user.getEmail()) && fetched.get(0).getPassword().equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
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
}
