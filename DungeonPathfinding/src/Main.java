import java.util.Scanner;
public class Main {
    
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    
    public static void printHeader(String title) {
        System.out.println("\n" + "=".repeat(80));
        System.out.println(" ".repeat((80 - title.length()) / 2) + title);
        System.out.println("=".repeat(80));
    }
    
    public static void printSubHeader(String title) {
        System.out.println("\n" + "-".repeat(80));
        System.out.println(" " + title);
        System.out.println("-".repeat(80));
    }

    public static void main(String[] args) {
        clearScreen();
        
        System.out.println(" ____  _   _ _   _  ____ _____ ___  _   _   ____   _  _____ _   _   _____ ___ _   _ ____  ");
        System.out.println("|  _ \\| | | | \\ | |/ ___| ____/ _ \\| \\ | | |  _ \\ / \\|_   _| | | | |  ___|_ _| \\ | |  _ \\ ");
        System.out.println("| | | | | | |  \\| | |  _|  _|| | | |  \\| | | |_) / _ \\ | | | |_| | | |_   | ||  \\| | | | |");
        System.out.println("| |_| | |_| | |\\  | |_| | |__| |_| | |\\  | |  __/ ___ \\| | |  _  | |  _|  | || |\\  | |_| |");
        System.out.println("|____/ \\___/|_| \\_|\\____|_____\\___/|_| \\_| |_| /_/   \\_\\_| |_| |_| |_|   |___|_| \\_|____/ \n");

        Scanner scan = new Scanner(System.in);

        System.out.print("\n>>> Masukkan username player: ");
        String user = scan.nextLine().trim();
        Player player = new Player(user);

        clearScreen();

        printHeader("SELAMAT DATANG, " + player.getName().toUpperCase() + "!");
        System.out.println("Mulai petualanganmu di Dungeon yang luas dan misterius.");
        System.out.println("Temukan harta karun, kalahkan monster, dan selamatkan kerajaan!");

        // Struktur data
        RoomBST bst = new RoomBST();
        PlayerStack history = new PlayerStack();
        PlayerQueue logs = new PlayerQueue();
        ItemLinkedList inventory = new ItemLinkedList();
        DirectionStack dirStack = new DirectionStack();

        // Buat banyak ruangan (total 15 ruangan)
        System.out.println("\n[SYSTEM] Membangun dungeon dengan 15 ruangan...");
        
        Room entrance = new Room(8, "Entrance Hall");
        Room hall = new Room(12, "Great Hall");
        Room storage = new Room(5, "Storage Room");
        Room armory = new Room(10, "Armory");
        Room library = new Room(6, "Ancient Library");
        Room kitchen = new Room(3, "Abandoned Kitchen");
        Room barracks = new Room(15, "Guard Barracks");
        Room tunnel = new Room(18, "Dark Tunnel");
        Room cave = new Room(20, "Crystal Cave");
        Room crypt = new Room(7, "Forgotten Crypt");
        Room throne = new Room(25, "Throne Room");
        Room laboratory = new Room(13, "Alchemy Laboratory");
        Room garden = new Room(4, "Poison Garden");
        Room treasury = new Room(30, "Royal Treasury");
        Room boss = new Room(50, "Dragon's Lair");

        // Hubungkan ruangan
        System.out.println("[SYSTEM] Menghubungkan ruangan...");
        
        // Lantai 1: Utama
        entrance.east = hall; hall.west = entrance;
        hall.north = storage; storage.south = hall;
        hall.east = armory; armory.west = hall;
        hall.south = library; library.north = hall;
        
        // Lantai 2: Barat
        storage.west = kitchen; kitchen.east = storage;
        kitchen.north = barracks; barracks.south = kitchen;
        
        // Lantai 3: Timur
        armory.east = tunnel; tunnel.west = armory;
        tunnel.north = cave; cave.south = tunnel;
        tunnel.south = crypt; crypt.north = tunnel;
        cave.east = throne; throne.west = cave;
        
        // Lantai 4: Selatan
        library.south = laboratory; laboratory.north = library;
        laboratory.east = garden; garden.west = laboratory;
        garden.south = treasury; treasury.north = garden;
        treasury.east = boss; boss.west = treasury;
        
        // Tambahkan koneksi bolak-balik
        throne.south = treasury; treasury.west = throne;
        barracks.east = crypt; crypt.west = barracks;

        // Tempatkan item di berbagai ruangan
        System.out.println("[SYSTEM] Menempatkan item dan monster...");
        
        entrance.placeItem("Rusty Key", 15);
        storage.placeItem("Old Map", 25);
        storage.placeItem("Healing Potion", 40);
        armory.placeItem("Iron Sword", 60);
        armory.placeItem("Steel Shield", 75);
        library.placeItem("Ancient Scroll", 100);
        library.placeItem("Magic Tome", 150);
        kitchen.placeItem("Mysterious Meat", 10);
        barracks.placeItem("Guard's Armor", 80);
        tunnel.placeItem("Glowing Crystal", 120);
        cave.placeItem("Diamond", 200);
        cave.placeItem("Crystal Shard", 90);
        crypt.placeItem("Skull Key", 180);
        throne.placeItem("Crown of Kings", 500);
        laboratory.placeItem("Potion of Strength", 200);
        laboratory.placeItem("Poison Antidote", 60);
        garden.placeItem("Poisonous Herb", 30);
        treasury.placeItem("Gold Coins", 300);
        treasury.placeItem("Ruby", 250);
        treasury.placeItem("Sapphire", 250);

        // Tempatkan monster di berbagai ruangan
        kitchen.placeMonster("Rat Swarm", 10);
        barracks.placeMonster("Skeleton Warrior", 30);
        tunnel.placeMonster("Cave Troll", 50);
        cave.placeMonster("Crystal Golem", 70);
        crypt.placeMonster("Wraith", 60);
        crypt.placeMonster("Zombie Horde", 40);
        throne.placeMonster("Dark Knight", 80);
        laboratory.placeMonster("Mad Alchemist", 55);
        garden.placeMonster("Venomous Plant", 35);
        treasury.placeMonster("Golden Guardian", 90);
        boss.placeMonster("Ancient Dragon", 150);
        boss.placeMonster("Dragon's Minion", 40);

        // Masukkan semua ruangan ke BST
        bst.insert(entrance);
        bst.insert(hall);
        bst.insert(storage);
        bst.insert(armory);
        bst.insert(library);
        bst.insert(kitchen);
        bst.insert(barracks);
        bst.insert(tunnel);
        bst.insert(cave);
        bst.insert(crypt);
        bst.insert(throne);
        bst.insert(laboratory);
        bst.insert(garden);
        bst.insert(treasury);
        bst.insert(boss);

        Room current = entrance;
        history.push("Memulai petualangan di " + current.getName());
        dirStack.push(current.getName());
        logs.enqueue(player.getName() + " memulai dari " + current.getName());

        // Event khusus
        boolean hasMap = false;
        boolean hasCrown = false;
        boolean dragonAwakened = false;
        int steps = 0;
        int battlesWon = 0;
        int itemsCollected = 0;
        boolean gameRunning = true;

        printSubHeader("PETUNJUK PERMAINAN");
        System.out.println("1. Gunakan peta untuk menemukan Treasury tanpa terjebak");
        System.out.println("2. Kumpulkan senjata dan armor untuk melawan monster");
        System.out.println("3. Temukan Crown of Kings untuk bonus melawan Dragon");
        System.out.println("4. Ada 15 ruangan yang saling terhubung");
        System.out.println("5. Kumpulkan semua item dan kalahkan semua monster");

        System.out.println("\n[SYSTEM] Game dimulai... Tekan Enter untuk melanjutkan");
        scan.nextLine();
        clearScreen();

        while (gameRunning) {
            steps++;
            
            // Status Bar
            printHeader("LANGKAH KE-" + steps + " | " + current.getName().toUpperCase());
            System.out.printf("%-20s: %-10s", "Player", player.getName());
            System.out.printf(" | %-10s: %3d/100", "HP", player.getHp());
            System.out.printf(" | %-10s: %6d gold\n", "Gold", player.getGold());
            System.out.printf("%-20s: %-10d", "Items", itemsCollected);
            System.out.printf(" | %-10s: %6d", "Monsters", battlesWon);
            System.out.printf("  | %-10s: ID-%02d\n", "Room", current.getId());
            System.out.println("-".repeat(80));

            // Menu Utama
            printSubHeader("MENU UTAMA");
            System.out.println("  [1]  Lihat sekeliling");
            System.out.println("  [2]  Bergerak (u/s/t/b)");
            System.out.println("  [3]  Cari ruangan by ID");
            System.out.println("  [4]  Jelajahi ruangan");
            System.out.println("  [5]  Ambil item");
            System.out.println("  [6]  Serang monster");
            System.out.println("  [7]  Lihat inventory");
            System.out.println("  [8]  History perjalanan");
            System.out.println("  [9]  Log aksi");
            System.out.println(" [10]  Undo langkah");
            System.out.println(" [11]  Gunakan item");
            System.out.println(" [12]  Jual item");
            System.out.println(" [13]  Lihat peta");
            System.out.println(" [14]  Istirahat");
            System.out.println(" [15]  Status game");
            System.out.println(" [16]  Exit game");
            System.out.println("-".repeat(80));
            System.out.print(">>> Pilih aksi (1-16): ");

            int choice = -1;
            try { 
                choice = Integer.parseInt(scan.nextLine()); 
            } catch (Exception ex) { 
                System.out.println("[ERROR] Masukkan angka yang valid!");
                System.out.print("Tekan Enter untuk melanjutkan...");
                scan.nextLine();
                clearScreen();
                continue;
            }
            clearScreen();
            
            switch (choice) {
                case 1:
                    printSubHeader("VIEW - " + current.getName());
                    System.out.println("\nDeskripsi Ruangan:");
                    System.out.println("  Nama  : " + current.getName());
                    System.out.println("  ID    : " + current.getId());
                    System.out.println("\nPintu Keluar:");
                    if (current.north != null) System.out.println("  [U] Utara    : " + current.north.getName());
                    if (current.south != null) System.out.println("  [S] Selatan  : " + current.south.getName());
                    if (current.east != null) System.out.println("  [T] Timur    : " + current.east.getName());
                    if (current.west != null) System.out.println("  [B] Barat    : " + current.west.getName());
                    
                    if (current.north == null && current.south == null && 
                        current.east == null && current.west == null) {
                        System.out.println("  Tidak ada pintu keluar!");
                    }
                    break;

                case 2:
                    printSubHeader("BERGERAK");
                    System.out.println("\nLokasi saat ini: " + current.getName());
                    System.out.print(">>> Masukkan arah (u/s/t/b): ");
                    String dir = scan.nextLine().toLowerCase();
                    Room next = null;
                    String directionName = "";
                    
                    if (dir.equals("u")) { 
                        next = current.north; 
                        directionName = "Utara";
                    }
                    else if (dir.equals("s")) { 
                        next = current.south; 
                        directionName = "Selatan";
                    }
                    else if (dir.equals("t")) { 
                        next = current.east; 
                        directionName = "Timur";
                    }
                    else if (dir.equals("b")) { 
                        next = current.west; 
                        directionName = "Barat";
                    }
                    else {
                        System.out.println("[ERROR] Arah tidak valid! Gunakan n, s, e, atau w.");
                        break;
                    }

                    if (next == null) {
                        System.out.println("[ERROR] Tidak ada jalan ke arah " + directionName + "!");
                        logs.enqueue("Gagal bergerak ke " + directionName + " dari " + current.getName());
                    } else {
                        current = next;
                        history.push("Bergerak ke " + directionName + " menuju " + current.getName());
                        dirStack.push(current.getName());
                        logs.enqueue(player.getName() + " tiba di " + current.getName());
                        
                        System.out.println("[SUCCESS] Berpindah ke " + current.getName());
                        
                        // Event khusus berdasarkan ruangan
                        if (current == treasury && !hasMap) {
                            System.out.println("\n[WARNING] Kamu memasuki Treasury tanpa peta!");
                            System.out.println("[TRAP] Trap aktif! HP berkurang 20.");
                            player.changeHp(-20);
                            logs.enqueue("Terjebak trap di Treasury tanpa peta");
                        }
                        
                        if (current == boss && !dragonAwakened) {
                            System.out.println("\n[EVENT] SUARA MENDERUM: Dragon terbangun dari tidurnya!");
                            dragonAwakened = true;
                            logs.enqueue("Dragon terbangun di Dragon's Lair");
                        }
                        
                        // Event random setiap 5 langkah
                        if (steps % 5 == 0) {
                            int event = (int)(Math.random() * 3);
                            switch(event) {
                                case 0:
                                    int goldFound = 10 + (int)(Math.random() * 20);
                                    player.addGold(goldFound);
                                    System.out.println("[BONUS] Kamu menemukan " + goldFound + " gold di jalan!");
                                    logs.enqueue("Menemukan " + goldFound + " gold secara acak");
                                    break;
                                case 1:
                                    System.out.println("[EVENT] Suara aneh terdengar... tapi tidak terjadi apa-apa.");
                                    break;
                                case 2:
                                    if (player.getHp() < 100) {
                                        player.changeHp(5);
                                        System.out.println("[BONUS] Merasa sedikit lebih segar (+5 HP)");
                                    }
                                    break;
                            }
                        }
                    }
                    break;

                case 3:
                    printSubHeader("CARI RUANGAN");
                    System.out.print("\n>>> Masukkan ID ruangan yang dicari (1-100): ");
                    try {
                        int id = Integer.parseInt(scan.nextLine());
                        if (id < 1 || id > 100) {
                            System.out.println("[ERROR] ID harus antara 1-100");
                            break;
                        }
                        Room found = bst.search(id);
                        if (found == null) {
                            System.out.println("[ERROR] Ruangan dengan ID " + id + " tidak ditemukan.");
                        } else {
                            System.out.println("\n[SUCCESS] Ruangan ditemukan!");
                            System.out.println("  Nama     : " + found.getName());
                            System.out.println("  ID       : " + found.getId());
                            System.out.print("  Terhubung: ");
                            String connections = "";
                            if (found.north != null) connections += "Utara, ";
                            if (found.south != null) connections += "Selatan, ";
                            if (found.east != null) connections += "Timur, ";
                            if (found.west != null) connections += "Barat, ";
                            
                            if (connections.isEmpty()) {
                                System.out.println("Tidak ada koneksi");
                            } else {
                                System.out.println(connections.substring(0, connections.length() - 2));
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("[ERROR] Masukkan angka yang valid!");
                    }
                    break;

                case 4:
                    printSubHeader("JELAJAHI - " + current.getName());
                    System.out.println("\nMencari di " + current.getName() + "...");
                    boolean foundSomething = false;
                    
                    // FIXED: Gunakan getItemName() dan getItemValue() untuk melihat
                    if (current.hasItem()) {
                        System.out.println("[ITEM] Ditemukan: " + current.getItemName() + 
                                        " (Value: " + current.getItemValue() + ")");
                        foundSomething = true;
                    } else {
                        System.out.println("[INFO] Tidak ada item yang terlihat.");
                    }
                    
                    if (current.hasMonster()) {
                        System.out.println("[MONSTER] Terdeteksi: " + current.monsterName() + 
                                        " (Damage: " + current.monsterDamage() + ")");
                        foundSomething = true;
                    } else {
                        System.out.println("[INFO] Tidak ada monster di sini.");
                    }
                    
                    // Peluang menemukan item tersembunyi
                    if (Math.random() < 0.3) {
                        System.out.println("\n[BONUS] Kamu menemukan sesuatu yang tersembunyi!");
                        int hiddenGold = 5 + (int)(Math.random() * 15);
                        player.addGold(hiddenGold);
                        System.out.println("[GOLD] +" + hiddenGold + " gold ditemukan!");
                        logs.enqueue("Menemukan " + hiddenGold + " gold tersembunyi di " + current.getName());
                        foundSomething = true;
                    }
                    
                    if (!foundSomething) {
                        System.out.println("\n[INFO] Ruangan ini kosong.");
                    }
                    break;

                // Di dalam switch case 5 (Ambil item):
                case 5:
                    printSubHeader("AMBIL ITEM");
                    if (current.hasItem()) {
                        // FIXED: Gunakan takeItem() yang mengembalikan ItemInfo
                        Room.ItemInfo itemInfo = current.takeItem();
                        
                        if (itemInfo != null) {
                            String itemName = itemInfo.name;
                            int itemValue = itemInfo.value;
                            
                            // Item khusus
                            if (itemName.equals("Old Map")) {
                                hasMap = true;
                                System.out.println("[SPECIAL] Kamu menemukan Old Map!");
                                System.out.println("[INFO] Sekarang aman memasuki Treasury.");
                            }
                            if (itemName.equals("Crown of Kings")) {
                                hasCrown = true;
                                System.out.println("[SPECIAL] Kamu mendapatkan Crown of Kings!");
                                System.out.println("[INFO] Dragon akan takut padamu!");
                            }
                            
                            inventory.add(new Item(itemName, "Found in " + current.getName(), itemValue));
                            itemsCollected++;
                            logs.enqueue("Mengambil " + itemName + " (value=" + itemValue + ") di " + current.getName());
                            System.out.println("[SUCCESS] Item diambil: " + itemName);
                            System.out.println("[VALUE]  Worth: " + itemValue + " gold");
                        } else {
                            System.out.println("[ERROR] Gagal mengambil item.");
                        }
                    } else {
                        System.out.println("[ERROR] Tidak ada item untuk diambil di ruangan ini.");
                    }
                    break;

                case 6:
                    printSubHeader("PERTEMPURAN");
                    if (current.hasMonster()) {
                        String monster = current.monsterName();
                        int dmg = current.monsterDamage();
                        System.out.println("\n[ENEMY] Melawan: " + monster);
                        System.out.println("[STATS] Damage: " + dmg + " | HP Player: " + player.getHp());
                        
                        // Bonus jika memiliki item tertentu
                        int bonus = 0;
                        if (inventory.hasItem("Iron Sword")) {
                            bonus += 10;
                            System.out.println("[BONUS] Iron Sword (+10 damage)");
                        }
                        if (inventory.hasItem("Steel Shield")) {
                            bonus -= 5;
                            System.out.println("[BONUS] Steel Shield (-5 damage taken)");
                        }
                        if (hasCrown && current == boss) {
                            bonus += 20;
                            System.out.println("[BONUS] Crown of Kings (+20 vs Dragon)");
                        }
                        
                        int actualDmg = Math.max(1, dmg - bonus);
                        System.out.print("\n>>> Serang monster? (y/n): ");
                        String yn = scan.nextLine();
                        
                        if (yn.equalsIgnoreCase("y")) {
                            // Hitung peluang menang
                            double winChance = 0.7;
                            if (player.getHp() < 30) {
                                winChance = 0.4;
                                System.out.println("[WARNING] HP rendah! Peluang menang berkurang.");
                            }
                            if (inventory.hasItem("Potion of Strength")) {
                                winChance = 0.9;
                                System.out.println("[BONUS] Potion of Strength aktif! Peluang menang meningkat.");
                            }
                            
                            System.out.println("\n[MECHANIC] Peluang menang: " + (int)(winChance * 100) + "%");
                            System.out.println("[CALC] Damage yang diterima: " + actualDmg);
                            
                            if (Math.random() < winChance) {
                                current.killMonster();
                                int reward = dmg * 2 + (int)(Math.random() * 20);
                                player.addGold(reward);
                                player.changeHp(-actualDmg);
                                battlesWon++;
                                
                                System.out.println("\n[VICTORY] Kamu mengalahkan " + monster + "!");
                                System.out.println("[REWARD] +" + reward + " gold");
                                System.out.println("[DAMAGE] -" + actualDmg + " HP");
                                System.out.println("[STATS] HP sekarang: " + player.getHp());
                                logs.enqueue("Mengalahkan " + monster + " di " + current.getName());
                                
                                // Jika mengalahkan dragon
                                if (monster.contains("Dragon")) {
                                    System.out.println("\n" + "*".repeat(80));
                                    System.out.println("*" + " ".repeat(58) + "*");
                                    System.out.println("*" + " ".repeat(15) + "VICTORY! Kamu mengalahkan Dragon!" + " ".repeat(16) + "*");
                                    System.out.println("*" + " ".repeat(58) + "*");
                                    System.out.println("*".repeat(80));
                                    gameRunning = false;
                                }
                            } else {
                                int fleeDmg = dmg + 5;
                                player.changeHp(-fleeDmg);
                                System.out.println("\n[DEFEAT] Gagal mengalahkan monster!");
                                System.out.println("[ESCAPE] Kamu kabur dengan luka.");
                                System.out.println("[DAMAGE] -" + fleeDmg + " HP");
                                System.out.println("[STATS] HP sekarang: " + player.getHp());
                                logs.enqueue("Gagal melawan " + monster + " di " + current.getName());
                            }
                        } else {
                            System.out.println("[INFO] Kamu menghindari pertempuran.");
                        }
                    } else {
                        System.out.println("[ERROR] Tidak ada monster untuk dilawan di sini.");
                    }
                    break;

                case 7:
                    printSubHeader("INVENTORY");
                    if (inventory.isEmpty()) {
                        System.out.println("\n[INFO] Inventory kosong.");
                    } else {
                        System.out.println("\n[STATS] Total items: " + inventory.count());
                        System.out.println("[STATS] Total value: " + inventory.totalValue() + " gold");
                        
                        System.out.println("\n[DETAIL] Daftar item:");
                        System.out.println("-".repeat(80));
                        inventory.printAll();
                    }
                    break;

                case 8:
                    printSubHeader("HISTORY PERJALANAN");
                    System.out.println("\n[INFO] Menampilkan " + history.size() + " aksi terakhir:");
                    System.out.println("-".repeat(80));
                    history.printHistory();
                    break;

                case 9:
                    printSubHeader("LOG AKSI");
                    System.out.println("\n[INFO] Menampilkan semua log aksi:");
                    System.out.println("-".repeat(80));
                    logs.printQueue();
                    break;

                case 10:
                    printSubHeader("UNDO LANGKAH");
                    if (history.size() > 1) {
                        String undone = history.pop();
                        System.out.println("\n[UNDO] Membatalkan: " + undone);
                        if (dirStack.size() > 1) {
                            dirStack.pop(); // Hapus current
                            String prevRoom = dirStack.peek();
                            System.out.println("[INFO] Kembali ke: " + (prevRoom != null ? prevRoom : "Awal"));
                        }
                        logs.enqueue("Undo: " + undone);
                    } else {
                        System.out.println("\n[ERROR] Tidak ada langkah untuk di-undo.");
                    }
                    break;

                case 11:
                    printSubHeader("GUNAKAN ITEM");
                    System.out.print("\n>>> Masukkan nama item yang ingin digunakan: ");
                    String useItem = scan.nextLine();
                    if (inventory.hasItem(useItem)) {
                        if (useItem.equals("Healing Potion")) {
                            player.changeHp(50);
                            inventory.remove(useItem);
                            System.out.println("\n[USE] Menggunakan Healing Potion");
                            System.out.println("[EFFECT] +50 HP");
                            System.out.println("[STATS] HP sekarang: " + player.getHp());
                            logs.enqueue("Menggunakan Healing Potion");
                        } else if (useItem.equals("Potion of Strength")) {
                            System.out.println("\n[USE] Menggunakan Potion of Strength");
                            System.out.println("[EFFECT] Damage bonus +10 untuk pertempuran berikutnya.");
                            System.out.println("[INFO] Efek akan aktif saat pertempuran berikutnya.");
                            logs.enqueue("Menggunakan Potion of Strength");
                        } else {
                            System.out.println("\n[ERROR] Item ini tidak bisa digunakan.");
                            System.out.println("[INFO] Item yang bisa digunakan: Healing Potion, Potion of Strength");
                        }
                    } else {
                        System.out.println("\n[ERROR] Item tidak ada di inventory.");
                    }
                    break;

                case 12:
                    printSubHeader("JUAL ITEM");
                    if (inventory.isEmpty()) {
                        System.out.println("\n[ERROR] Inventory kosong, tidak ada item untuk dijual.");
                        break;
                    }
                    
                    System.out.println("\n[DETAIL] Daftar item di inventory:");
                    System.out.println("-".repeat(80));
                    inventory.printAll();
                    
                    System.out.print("\n>>> Masukkan nama item yang ingin dijual: ");
                    String sellItem = scan.nextLine();
                    
                    if (inventory.hasItem(sellItem)) {
                        Item sold = inventory.removeAndGet(sellItem);
                        if (sold != null) {
                            int sellPrice = (int)(sold.getValue() * 0.7);
                            player.addGold(sellPrice);
                            System.out.println("\n[SELL] Menjual: " + sellItem);
                            System.out.println("[VALUE] Harga asli: " + sold.getValue() + " gold");
                            System.out.println("[PRICE] Harga jual: " + sellPrice + " gold (70%)");
                            System.out.println("[STATS] Gold sekarang: " + player.getGold());
                            logs.enqueue("Menjual " + sellItem + " untuk " + sellPrice + " gold");
                        }
                    } else {
                        System.out.println("\n[ERROR] Item tidak ditemukan di inventory.");
                    }
                    break;

                case 13:
                    printSubHeader("PETA DUNGEON");
                    if (hasMap) {
                        System.out.println("\n[LEGEND] Peta Dungeon:");
                        System.out.println("┌─────────────────────────────────────────────────────┐");
                        System.out.println("│  [3] Kitchen ↔ [5] Storage ↔ [8] Entrance           │");
                        System.out.println("│       ↓                ↓                ↓           │");
                        System.out.println("│  [15] Barracks  [12] Great Hall  [10] Armory        │");
                        System.out.println("│       ↓                ↓                ↓           │");
                        System.out.println("│  [7] Crypt ← [18] Dark Tunnel → [20] Crystal Cave   │");
                        System.out.println("│       ↓                ↓                ↓           │");
                        System.out.println("│  [25] Throne Room  [13] Laboratory  [6] Library     │");
                        System.out.println("│       ↓                ↓                ↓           │");
                        System.out.println("│  [30] Treasury ↔ [4] Poison Garden                  │");
                        System.out.println("│       ↓                                             │");
                        System.out.println("│  [50] Dragon's Lair                                 │");
                        System.out.println("└─────────────────────────────────────────────────────┘");
                        System.out.println("\n[TIPS] Cari Crown di Throne Room untuk bonus melawan Dragon!");
                        System.out.println("[TIPS] Hati-hati dengan trap di Treasury tanpa peta!");
                    } else {
                        System.out.println("\n[ERROR] Kamu butuh Old Map untuk melihat peta.");
                        System.out.println("[HINT] Cari Old Map di Storage Room!");
                    }
                    break;

                case 14:
                    printSubHeader("ISTIRAHAT");
                    int heal = 10 + (int)(Math.random() * 20);
                    player.changeHp(heal);
                    System.out.println("\n[ACTION] Beristirahat sejenak...");
                    System.out.println("[HEAL]  Pulih " + heal + " HP");
                    System.out.println("[STATS] HP sekarang: " + player.getHp() + "/100");
                    logs.enqueue("Beristirahat, pulih " + heal + " HP");
                    break;

                case 15:
                    printSubHeader("STATUS GAME");
                    System.out.println("\n[PLAYER] Informasi:");
                    System.out.println("  • Nama          : " + player.getName());
                    System.out.println("  • HP            : " + player.getHp() + "/100");
                    System.out.println("  • Gold          : " + player.getGold());
                    
                    System.out.println("\n[PROGRESS] Statistik:");
                    System.out.println("  • Langkah       : " + steps);
                    System.out.println("  • Items         : " + itemsCollected);
                    System.out.println("  • Monster       : " + battlesWon + " dikalahkan");
                    
                    System.out.println("\n[LOCATION] Saat ini:");
                    System.out.println("  • Ruangan       : " + current.getName());
                    System.out.println("  • ID            : " + current.getId());
                    
                    System.out.println("\n[SPECIAL] Item penting:");
                    System.out.println("  • Map ditemukan : " + (hasMap ? "YA" : "BELUM"));
                    System.out.println("  • Crown ditemuan: " + (hasCrown ? "YA" : "BELUM"));
                    System.out.println("  • Dragon aktif  : " + (dragonAwakened ? "YA" : "BELUM"));
                    
                    System.out.println("\n[INVENTORY] Ringkasan:");
                    System.out.println("  • Total items   : " + inventory.count());
                    System.out.println("  • Total value   : " + inventory.totalValue());
                    break;

                case 16:
                    printSubHeader("KELUAR GAME");
                    System.out.print("\n>>> Yakin ingin keluar? (y/n): ");
                    if (scan.nextLine().equalsIgnoreCase("y")) {
                        System.out.println("\n[SYSTEM] Keluar dari game...");
                        gameRunning = false;
                    }
                    break;

                default:
                    System.out.println("[ERROR] Pilihan tidak valid. Harus 1-16.");
            }

            // Cek kondisi game over
            if (player.getHp() <= 0) {
                clearScreen();
                printHeader("GAME OVER");
                System.out.println("\n[DEATH] HP mu habis!");
                System.out.println("[LOCATION] Petualangan berakhir di " + current.getName());
                System.out.println("\n[STATS] Langkah terakhir: " + steps);
                System.out.println("[STATS] Gold akhir: " + player.getGold());
                gameRunning = false;
            }

            // Cek kemenangan
            if (!boss.hasMonster() && current == boss && battlesWon >= 3) {
                clearScreen();
                printHeader("VICTORY ROYALE!");
                System.out.println("\n[WIN] Kamu mengalahkan Dragon dan menyelamatkan kerajaan!");
                System.out.println("[WIN] Semua monster telah dikalahkan!");
                gameRunning = false;
            }
            
            // Jika masih berjalan, tunggu input untuk melanjutkan
            if (gameRunning) {
                System.out.println("\n" + "=".repeat(80));
                System.out.print(">>> Tekan Enter untuk melanjutkan...");
                scan.nextLine();
                clearScreen();
            }
        }

        // Ringkasan Akhir
        clearScreen();
        printHeader("RINGKASAN AKHIR PETUALANGAN");
        
        System.out.println("\n[PLAYER] Informasi Player:");
        System.out.println("  Nama       : " + player.getName());
        System.out.println("  HP akhir   : " + player.getHp() + "/100");
        System.out.println("  Gold akhir : " + player.getGold() + " gold");
        
        System.out.println("\n[STATS] Statistik Game:");
        System.out.println("  Total langkah       : " + steps);
        System.out.println("  Items terkumpul     : " + itemsCollected);
        System.out.println("  Monster dikalahkan  : " + battlesWon);
        System.out.println("  Map ditemukan       : " + (hasMap ? "YA" : "TIDAK"));
        System.out.println("  Crown ditemukan     : " + (hasCrown ? "YA" : "TIDAK"));
        
        // Hitung skor
        int score = player.getGold() + (itemsCollected * 50) + (battlesWon * 100) + (player.getHp() * 2);
        if (hasCrown) score += 500;
        if (!boss.hasMonster()) score += 1000;
        
        System.out.println("\n[SCORE] Perhitungan Skor:");
        System.out.println("  Gold                : " + player.getGold() + " points");
        System.out.println("  Items (" + itemsCollected + " x 50)   : " + (itemsCollected * 50) + " points");
        System.out.println("  Monster (" + battlesWon + " x 100) : " + (battlesWon * 100) + " points");
        System.out.println("  HP (" + player.getHp() + " x 2)     : " + (player.getHp() * 2) + " points");
        if (hasCrown) System.out.println("  Crown bonus         : 500 points");
        if (!boss.hasMonster()) System.out.println("  Victory bonus       : 1000 points");
        System.out.println("  " + "-".repeat(80));
        System.out.println("  TOTAL SKOR          : " + score + " points");
        
        System.out.println("\n[INVENTORY] Item akhir:");
        System.out.println("-".repeat(80));
        if (inventory.isEmpty()) {
            System.out.println("  (Tidak ada item)");
        } else {
            inventory.sortByValueDesc();
            inventory.printAll();
            System.out.println("  " + "-".repeat(80));
            System.out.println("  Total value: " + inventory.totalValue() + " gold");
        }
        
        System.out.println("\n[HISTORY] 10 aksi terakhir:");
        System.out.println("-".repeat(80));
        logs.printLast(10);
        
        System.out.println("\n" + "=".repeat(80));
        System.out.println("Terima kasih sudah bermain, " + player.getName() + "!");
        System.out.println("=".repeat(80));
        
        scan.close();
    }
}