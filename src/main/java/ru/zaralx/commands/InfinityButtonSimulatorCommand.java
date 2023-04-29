package ru.zaralx.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import ru.zaralx.utils.ButtonInformation;
import ru.zaralx.utils.RebirthButtonInformation;
import ru.zaralx.utils.zModules.TopRebirths;
import ru.zaralx.utils.zModules.configs.buttonsConfig;
import ru.zaralx.utils.zModules.configs.config;
import ru.zaralx.utils.zModules.configs.rebirthsConfig;

import java.util.ArrayList;
import java.util.List;

public class InfinityButtonSimulatorCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) Bukkit.getOfflinePlayer(sender.getName());
        if (args.length == 1) {
            if (args[0].equals("Refresh")) {
                ButtonInformation.removeall();
                ButtonInformation.init();
                RebirthButtonInformation.removeall();
                RebirthButtonInformation.init();

                sender.sendMessage("§aDone!");
            } else if (args[0].equals("PlaceRebirthTop")) {
                config.get().set("Top.Rebirths.X", player.getLocation().getX());
                config.get().set("Top.Rebirths.Y", player.getLocation().getY());
                config.get().set("Top.Rebirths.Z", player.getLocation().getZ());
                config.save();

                TopRebirths.removeall();
                TopRebirths.setup();
                TopRebirths.update();

                sender.sendMessage("§aDone!");
            }
        }
        else if (args.length == 3) {
            if (args[0].equals("addMultiply")) {
                int button_id = buttonsConfig.get().getKeys(false).size();
                buttonsConfig.edit("btn_"+button_id+".X", player.getLocation().getBlockX());
                buttonsConfig.edit("btn_"+button_id+".Y", player.getLocation().getBlockY());
                buttonsConfig.edit("btn_"+button_id+".Z", player.getLocation().getBlockZ());
                buttonsConfig.edit("btn_"+button_id+".Multiply", Double.valueOf(args[1]));
                buttonsConfig.edit("btn_"+button_id+".Price", Double.valueOf(args[2]));

                sender.sendMessage("§aDone!");
            }
            else if (args[0].equals("addRebirth")) {
                int button_id = rebirthsConfig.get().getKeys(false).size();
                rebirthsConfig.edit("btn_"+button_id+".X", player.getLocation().getBlockX());
                rebirthsConfig.edit("btn_"+button_id+".Y", player.getLocation().getBlockY());
                rebirthsConfig.edit("btn_"+button_id+".Z", player.getLocation().getBlockZ());
                rebirthsConfig.edit("btn_"+button_id+".Multiply", Double.valueOf(args[1]));
                rebirthsConfig.edit("btn_"+button_id+".Price", Double.valueOf(args[2]));

                sender.sendMessage("§aDone!");
            }
        } else {
            sender.sendMessage("§cNeed Arguments");
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> arguments = new ArrayList<>();
        if (args.length == 1) {
            arguments.add("addMultiply");
            arguments.add("addRebirth");
            arguments.add("Refresh");
            arguments.add("PlaceRebirthTop");
        }
        else if (args.length == 2) {
            if (args[0].equals("addMultiply")) {
                arguments.add("MULTIPLY");
            }
            else if (args[0].equals("addRebirth")) {
                arguments.add("+REBIRTH");
            }
        }
        else if (args.length == 3) {
            if (args[0].equals("addMultiply") || args[0].equals("addRebirth")) {
                arguments.add("PRICE");
            }
        }

        return arguments;
    }
}
