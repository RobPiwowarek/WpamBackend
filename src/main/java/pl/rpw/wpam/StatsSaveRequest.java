package pl.rpw.wpam;

public class StatsSaveRequest {
    private String email;
    private String password;
    private String name;
    private int level;
    private int damage;
    private int health;
    private int energy;
    private int healing;
    private int armor;
    private int crit;
    private int stun;
    private int evade;
    private int points;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getDamage() {
        return damage;
    }

    public int getHealth() {
        return health;
    }

    public int getEnergy() {
        return energy;
    }

    public int getHealing() {
        return healing;
    }

    public int getArmor() {
        return armor;
    }

    public int getCrit() {
        return crit;
    }

    public int getStun() {
        return stun;
    }

    public int getEvade() {
        return evade;
    }

    public int getPoints() {
        return points;
    }
}
