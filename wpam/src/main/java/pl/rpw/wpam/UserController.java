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
    @PutMapping("/login")
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

        System.out.println("fetched size " + fetched.size());

        for (User a: fetched) {
            System.out.println(a.getEmail());
            System.out.println(a.getPassword());
        }

        if (fetched.size() != 0)
            return false;

        jdbcTemplate.update("INSERT INTO USERS VALUES (?, ?)", user.getEmail(), user.getPassword());
        return true;
    }

    @CrossOrigin
    @PutMapping("/save")
    public boolean save(@RequestBody SaveRequest request) {
        List<User> fetchedUsers = jdbcTemplate.query(
                "SELECT * FROM USERS WHERE EMAIL = ?", new Object[]{request.getEmail()}, new UserRowMapper()
        );

        if (fetchedUsers.size() != 1)
            return false;

        if (fetchedUsers.get(0).getEmail().equals(request.getEmail()) && fetchedUsers.get(0).getPassword().equals(request.getPassword())) {
            List<Texture> fetched = jdbcTemplate.query(
                    "SELECT * FROM TEXTURES WHERE EMAIL = ?", new Object[]{request.getEmail()}, new TextureRowMapper()
            );

            System.out.println("fetched size " + fetched.size());

            for (Texture a: fetched) {
                System.out.println(a.getEmail());
                System.out.println(a.getTexture());
            }

            jdbcTemplate.update("INSERT INTO TEXTURES VALUES (?, ?)", request.getEmail(), request.getTexture());
            return true;
        } else {
            return false;
        }
    }

    @CrossOrigin
    @PutMapping("/load")
    public String load(@RequestBody User request) {
        List<User> fetchedUsers = jdbcTemplate.query(
                "SELECT * FROM USERS WHERE EMAIL = ?", new Object[]{request.getEmail()}, new UserRowMapper()
        );

        if (fetchedUsers.size() != 1)
            return "FAILURE";

        if (fetchedUsers.get(0).getEmail().equals(request.getEmail()) && fetchedUsers.get(0).getPassword().equals(request.getPassword())) {
            List<Texture> fetched = jdbcTemplate.query(
                    "SELECT * FROM TEXTURES WHERE EMAIL = ?", new Object[]{request.getEmail()}, new TextureRowMapper()
            );

            System.out.println("fetched size " + fetched.size());

            for (Texture a: fetched) {
                System.out.println(a.getEmail());
                System.out.println(a.getTexture());
            }

            return fetched.get(0).getTexture();
        } else {
            return "UNAUTHORISED";
        }
    }
}
