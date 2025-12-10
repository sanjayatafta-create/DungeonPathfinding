public class Player {
    private String username;
    private int hp;
    private int gold;

    public Player(String username) {
        this.username = username;
        this.hp = 100;
        this.gold = 0;
    }

    public String getName() { 
        return username; 
    }

    public int getHp() { 
        return this.hp; 
    }

    public void changeHp(int delta) { 
        hp += delta; 
        if (hp > 100) hp = 100;  // TAMBAH BATAS MAKSIMAL
        if (hp < 0) hp = 0; 
    }

    public int getGold() { return this.gold; }

    public void addGold(int g) { 
        gold += g; 
        if (gold < 0) gold = 0;  // TAMBAH CEK NEGATIF
    }

    // TAMBAH METHOD BARU
    public void setGold(int g) { 
        this.gold = g; 
    }
}