package ru.zaralx.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import ru.zaralx.utils.ButtonInfortmation;
import ru.zaralx.utils.zModules.configs.buttonsConfig;

import java.util.ArrayList;
import java.util.List;

public class InfinityButtonSimulatorCommand implements CommandExecutor, TabCompleter {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) Bukkit.getOfflinePlayer(sender.getName());
        if (args.length == 1) {
            if (args[0].equals("Refresh")) {
                ButtonInfortmation.removeall();
                ButtonInfortmation.init();

                sender.sendMessage("§aDone!");
            }
        }
        if (args.length == 3) {
            if (args[0].equals("set")) {
                int button_id = buttonsConfig.get().getKeys(false).size();
                buttonsConfig.edit("btn_"+button_id+".X", player.getLocation().getBlockX());
                buttonsConfig.edit("btn_"+button_id+".Y", player.getLocation().getBlockY());
                buttonsConfig.edit("btn_"+button_id+".Z", player.getLocation().getBlockZ());
                buttonsConfig.edit("btn_"+button_id+".Multiply", Double.valueOf(args[1]));
                buttonsConfig.edit("btn_"+button_id+".Price", Double.valueOf(args[2]));

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
            arguments.add("set");
            arguments.add("Refresh");
        }
        else if (args.length == 2) {
            if (args[0].equals("set")) {
                arguments.add("MULTIPLY");
            }
        }
        else if (args.length == 3) {
            if (args[0].equals("set")) {
                arguments.add("PRICE");
            }
        }

        return arguments;
    }
}
