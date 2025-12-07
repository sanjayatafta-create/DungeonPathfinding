public class Monster {
    private String name;
    private int hp;

    public Monster(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public String getName() { 
        return name; 
    }
    public int getHp() { 
        return hp; 
    }

    public void damage(int d) { 
        hp -= d; 
    }
}