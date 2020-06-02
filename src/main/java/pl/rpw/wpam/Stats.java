package pl.rpw.wpam;

public class Stats {
    private String email;
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

    public Stats(String email, String name, int level, int damage, int health, int energy, int healing, int armor, int crit, int stun, int evade, int points) {
        this.email = email;
        this.name = name;
        this.level = level;
        this.damage = damage;
        this.health = health;
        this.energy = energy;
        this.healing = healing;
        this.armor = armor;
        this.crit = crit;
        this.stun = stun;
        this.evade = evade;
        this.points = points;
    }

    public String getEmail() {
        return email;
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
