package com.sainttx.chancetotrigger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

/**
 * Created by Matthew on 10/21/2015.
 */
public class ChancePlugin extends JavaPlugin {

    private Random random = new Random(); // used to determine if execute should run

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            return false;
        }

        double percentChance;
        String execute;

        try {
            percentChance = Double.parseDouble(args[0]);
        } catch (NumberFormatException ex) {
            return false;
        }

        if (Double.compare(percentChance, random.nextDouble() * 100) < 0) {
            getLogger().info("/" + command.getLabel() + " " + Double.toString(percentChance) + " failed");
            return true;
        }

        StringBuilder executeBuilder = new StringBuilder();
        for (int i = 1 ; i < args.length ; i++) {
            executeBuilder.append(args[i]);
            executeBuilder.append(" ");
        }
        execute = executeBuilder.toString();
        getLogger().info("/" + command.getLabel() + " " + Double.toString(percentChance) + " success: " + execute);
        getServer().dispatchCommand(getServer().getConsoleSender(), execute);
        return true;
    }
}
