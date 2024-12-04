package com.njdge.fastbuilder.profile.command;

import com.njdge.fastbuilder.FastBuilder;
import com.njdge.fastbuilder.arena.Arena;
import com.njdge.fastbuilder.profile.PlayerProfile;
import com.njdge.fastbuilder.profile.ProfileState;
import com.njdge.fastbuilder.utils.CC;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;

public class SwitchCommand implements CommandExecutor {
    private final FastBuilder plugin;
    public SwitchCommand(FastBuilder plugin) {
        this.plugin = plugin;
        PluginCommand cmd = plugin.getCommand("switch");
        cmd.setExecutor(this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (args.length == 0) {
            player.sendMessage(CC.RED + "Usage: /switch <mode>");
            return false;
        }

        if (args.length == 1) {
            String mode = args[0];
            Arena arena = plugin.getArenaManager().getArenaIgnoreCase(mode);
            if (arena == null) {
                player.sendMessage(CC.RED + "Invalid arena");
                return false;
            }
            PlayerProfile profile = plugin.getProfileManager().get(player.getUniqueId());
            if (profile.getState().equals(ProfileState.EDITING)){
                profile.setState(ProfileState.PLAYING);
            }
            plugin.getArenaManager().leave(player);
            profile.setArena(arena);
            plugin.getArenaManager().join(player, profile.getArena());
            profile.reset();

            profile.giveItems();

        }




        return false;
    }
}

