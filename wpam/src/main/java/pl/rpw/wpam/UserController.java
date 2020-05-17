package pl.rpw.wpam;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        User fetched = jdbcTemplate.queryForObject(
                "SELECT * FROM USERS WHERE EMAIL = :email", new Object[]{user.getEmail()}, new UserRowMapper()
        );

        if (fetched == null) {
            return false;
        } else {
            if (fetched.getEmail().equals(user.getEmail()) && fetched.getPassword().equals(user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }

    @CrossOrigin
    @PutMapping("/register")
    public boolean register(@RequestBody User user) {
        User fetched = jdbcTemplate.queryForObject(
                "SELECT * FROM USERS WHERE EMAIL = :email", new Object[]{user.getEmail()}, new UserRowMapper()
        );

        if (fetched == null) {
            jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?)", user.getEmail(), user.getPassword());
            return true;
        } else {
            return false;
        }
    }
}
