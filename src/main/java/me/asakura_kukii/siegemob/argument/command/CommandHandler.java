package me.asakura_kukii.siegemob.argument.command;

import me.asakura_kukii.siegecore.argument.PArgument;
import me.asakura_kukii.siegecore.argument.PSender;
import me.asakura_kukii.siegemob.mob.PMob;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class CommandHandler {

    public static boolean onCommand(PSender sender, PArgument argument) {
        sender.nextLine();
        sender.log("Issued:");
        sender.raw(">> " + argument.colorize());

        String s = argument.nextString();
        if (!argument.success) {
            sender.error("Missing sub-argument");
            return false;
        }

        switch (s) {
            case "test":
                Player p = ((Player) sender.sender);
                PMob.test.spawn(p.getLocation());
                return true;
            case "info":
                return onInfo(sender, argument);
            default:
                sender.error("Invalid sub-argument");
                return false;
        }
    }

    public static boolean onInfo(PSender sender, PArgument argument) {
        sender.info("Standby!");
        return true;
    }
}
