package com.vyteamvy.ringsofworlds;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class ModConfig {
    private static final File CONFIG_FILE = new File("config/ringsofworlds.properties");

    // Overworld Rings
    public static int OVERWORLD_RING_1_DURABILITY = 100;
    public static int OVERWORLD_RING_1_COOLDOWN = 20 * 7;

    public static int OVERWORLD_RING_2_DURABILITY = 150;
    public static int OVERWORLD_RING_2_COOLDOWN = 20 * 5;

    public static int OVERWORLD_RING_3_DURABILITY = 200;
    public static int OVERWORLD_RING_3_COOLDOWN = 20 * 3;

    // Nether Rings
    public static int NETHER_RING_1_DURABILITY = 100;
    public static int NETHER_RING_1_COOLDOWN = 20 * 7;

    public static int NETHER_RING_2_DURABILITY = 150;
    public static int NETHER_RING_2_COOLDOWN = 20 * 5;

    public static int NETHER_RING_3_DURABILITY = 200;
    public static int NETHER_RING_3_COOLDOWN = 20 * 3;

    // Ender Rings
    public static int ENDER_RING_1_DURABILITY = 100;
    public static int ENDER_RING_1_COOLDOWN = 20 * 7;

    public static int ENDER_RING_2_DURABILITY = 150;
    public static int ENDER_RING_2_COOLDOWN = 20 * 5;

    public static int ENDER_RING_3_DURABILITY = 200;
    public static int ENDER_RING_3_COOLDOWN = 20 * 3;

    // Ultimate Ring
    public static int ULTIMATE_RING_DURABILITY = 2000;

    public static void loadConfig() {
        Properties properties = new Properties();
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                properties.load(reader);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        OVERWORLD_RING_1_DURABILITY = Integer.parseInt(properties.getProperty("overworld_ring_1_durability", "100"));
        OVERWORLD_RING_1_COOLDOWN = Integer.parseInt(properties.getProperty("overworld_ring_1_cooldown", "200"));

        OVERWORLD_RING_2_DURABILITY = Integer.parseInt(properties.getProperty("overworld_ring_2_durability", "150"));
        OVERWORLD_RING_2_COOLDOWN = Integer.parseInt(properties.getProperty("overworld_ring_2_cooldown", "140"));

        OVERWORLD_RING_3_DURABILITY = Integer.parseInt(properties.getProperty("overworld_ring_3_durability", "200"));
        OVERWORLD_RING_3_COOLDOWN = Integer.parseInt(properties.getProperty("overworld_ring_3_cooldown", "100"));

        NETHER_RING_1_DURABILITY = Integer.parseInt(properties.getProperty("nether_ring_1_durability", "100"));
        NETHER_RING_1_COOLDOWN = Integer.parseInt(properties.getProperty("nether_ring_1_cooldown", "200"));

        NETHER_RING_2_DURABILITY = Integer.parseInt(properties.getProperty("nether_ring_2_durability", "150"));
        NETHER_RING_2_COOLDOWN = Integer.parseInt(properties.getProperty("nether_ring_2_cooldown", "140"));

        NETHER_RING_3_DURABILITY = Integer.parseInt(properties.getProperty("nether_ring_3_durability", "200"));
        NETHER_RING_3_COOLDOWN = Integer.parseInt(properties.getProperty("nether_ring_3_cooldown", "100"));

        ENDER_RING_1_DURABILITY = Integer.parseInt(properties.getProperty("ender_ring_1_durability", "100"));
        ENDER_RING_1_COOLDOWN = Integer.parseInt(properties.getProperty("ender_ring_1_cooldown", "200"));

        ENDER_RING_2_DURABILITY = Integer.parseInt(properties.getProperty("ender_ring_2_durability", "150"));
        ENDER_RING_2_COOLDOWN = Integer.parseInt(properties.getProperty("ender_ring_2_cooldown", "140"));

        ENDER_RING_3_DURABILITY = Integer.parseInt(properties.getProperty("ender_ring_3_durability", "200"));
        ENDER_RING_3_COOLDOWN = Integer.parseInt(properties.getProperty("ender_ring_3_cooldown", "100"));

        ULTIMATE_RING_DURABILITY = Integer.parseInt(properties.getProperty("ultimate_ring_durability", "2000"));
    }

    public static void saveConfig() {
        Properties properties = new Properties();
        properties.setProperty("overworld_ring_1_durability", String.valueOf(OVERWORLD_RING_1_DURABILITY));
        properties.setProperty("overworld_ring_1_cooldown", String.valueOf(OVERWORLD_RING_1_COOLDOWN));
        properties.setProperty("overworld_ring_2_durability", String.valueOf(OVERWORLD_RING_2_DURABILITY));
        properties.setProperty("overworld_ring_2_cooldown", String.valueOf(OVERWORLD_RING_2_COOLDOWN));
        properties.setProperty("overworld_ring_3_durability", String.valueOf(OVERWORLD_RING_3_DURABILITY));
        properties.setProperty("overworld_ring_3_cooldown", String.valueOf(OVERWORLD_RING_3_COOLDOWN));

        properties.setProperty("nether_ring_1_durability", String.valueOf(NETHER_RING_1_DURABILITY));
        properties.setProperty("nether_ring_1_cooldown", String.valueOf(NETHER_RING_1_COOLDOWN));
        properties.setProperty("nether_ring_2_durability", String.valueOf(NETHER_RING_2_DURABILITY));
        properties.setProperty("nether_ring_2_cooldown", String.valueOf(NETHER_RING_2_COOLDOWN));
        properties.setProperty("nether_ring_3_durability", String.valueOf(NETHER_RING_3_DURABILITY));
        properties.setProperty("nether_ring_3_cooldown", String.valueOf(NETHER_RING_3_COOLDOWN));

        properties.setProperty("ender_ring_1_durability", String.valueOf(ENDER_RING_1_DURABILITY));
        properties.setProperty("ender_ring_1_cooldown", String.valueOf(ENDER_RING_1_COOLDOWN));
        properties.setProperty("ender_ring_2_durability", String.valueOf(ENDER_RING_2_DURABILITY));
        properties.setProperty("ender_ring_2_cooldown", String.valueOf(ENDER_RING_2_COOLDOWN));
        properties.setProperty("ender_ring_3_durability", String.valueOf(ENDER_RING_3_DURABILITY));
        properties.setProperty("ender_ring_3_cooldown", String.valueOf(ENDER_RING_3_COOLDOWN));

        properties.setProperty("ultimate_ring_durability", String.valueOf(ULTIMATE_RING_DURABILITY));

        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            properties.store(writer, "Rings of Worlds Configuration");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}