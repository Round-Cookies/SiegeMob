package me.asakura_kukii.siegemob;

import me.asakura_kukii.siegecore.argument.PArgument;
import me.asakura_kukii.siegecore.argument.PSender;
import me.asakura_kukii.siegecore.io.PType;
import me.asakura_kukii.siegecore.util.format.FormatHandler;
import me.asakura_kukii.siegemob.argument.command.CommandHandler;
import me.asakura_kukii.siegemob.argument.tab.TabHandler;
import me.asakura_kukii.siegemob.mob.PAction;
import me.asakura_kukii.siegemob.mob.PActiveMob;
import me.asakura_kukii.siegemob.mob.PJoint;
import me.asakura_kukii.siegemob.mob.PMob;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SiegeMob extends JavaPlugin {
    public static String pluginColorCode = "&4";

    public static Server server = null;
    public static String pluginName;
    public static String pluginPrefix;
    public static String consolePluginPrefix;
    public static JavaPlugin pluginInstance = null;
    public static File pluginFolder = null;
    public static HashMap<JavaPlugin, BukkitTask> updaterRegister = new HashMap<>();

    public static void registerEvent() {
    }

    public static void registerType() {
        PType.putPType(pluginInstance, "joint", PJoint.class);
        PType.putPType(pluginInstance, "action", PAction.class);
        PType.putPType(pluginInstance, "mob", PMob.class);
    }

    @Override
    public void onEnable() {
        server = getServer();
        pluginName = getName();
        pluginPrefix = FormatHandler.format("&8[" + pluginColorCode + pluginName + "&8] &f");
        consolePluginPrefix = "[" + pluginName + "]->>";
        info("Enabling " + pluginName);
        pluginInstance = this;
        pluginFolder = getDataFolder();
        if (!pluginFolder.exists() && pluginFolder.mkdirs()) warn("Creating plugin folder [" + pluginName + "]");

        registerEvent();
        registerType();

        updater();
        info(pluginName + " enabled");
    }

    public void onDisable() {
        info("Disabling " + pluginName);
        info(pluginName + " disabled");
    }

    public static void updater() {
        if (updaterRegister.containsKey(pluginInstance)) {
            updaterRegister.get(pluginInstance).cancel();
            updaterRegister.remove(pluginInstance);
        }
        updaterRegister.put(pluginInstance, new BukkitRunnable() {
            @Override
            public void run() {
                for (PActiveMob pAM : PActiveMob.livingMobMap.values()) {
                    pAM.update();
                }
            }
        }.runTaskTimer(pluginInstance , 0, 1));
    }

    public static void info(String s) {
        String[] msgList = s.split("\n");
        for (String msg : msgList) {
            if (msg.trim().length() >= 200) {
                raw(FormatHandler.ANSI_GREEN + consolePluginPrefix + msg.trim().substring(0, 199) + "..." + FormatHandler.ANSI_WHITE);
            } else {
                raw(FormatHandler.ANSI_GREEN + consolePluginPrefix + msg.trim() + FormatHandler.ANSI_WHITE);
            }
        }
    }

    public static void log(String s) {
        String[] msgList = s.split("\n");
        for (String msg : msgList) {
            if (msg.trim().length() >= 200) {
                raw(FormatHandler.ANSI_WHITE + consolePluginPrefix + msg.trim().substring(0, 199) + "..." + FormatHandler.ANSI_WHITE);
            } else {
                raw(FormatHandler.ANSI_WHITE + consolePluginPrefix + msg.trim() + FormatHandler.ANSI_WHITE);
            }
        }
    }

    public static void warn(String s) {
        String[] msgList = s.split("\n");
        for (String msg : msgList) {
            if (msg.trim().length() >= 200) {
                raw(FormatHandler.ANSI_YELLOW + consolePluginPrefix + msg.trim().substring(0, 199) + "..." + FormatHandler.ANSI_WHITE);
            } else {
                raw(FormatHandler.ANSI_YELLOW + consolePluginPrefix + msg.trim() + FormatHandler.ANSI_WHITE);
            }
        }
    }

    public static void error(String s) {
        String[] msgList = s.split("\n");
        for (String msg : msgList) {
            if (msg.trim().length() >= 200) {
                raw(FormatHandler.ANSI_RED + consolePluginPrefix + msg.trim().substring(0, 199) + "..." + FormatHandler.ANSI_WHITE);
            } else {
                raw(FormatHandler.ANSI_RED + consolePluginPrefix + msg.trim() + FormatHandler.ANSI_WHITE);
            }
        }
    }

    public static void raw(String s) {
        server.getConsoleSender().sendMessage(s);
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        List<String> sL = new ArrayList<>();
        if (args.length > 0) {
            PArgument argument = new PArgument(label, args);
            PSender sender = new PSender(pluginName, pluginPrefix, commandSender);
            sL = TabHandler.onTab(sender, argument);
            return sL;
        }
        return sL;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase(pluginName)) {
            PArgument argument = new PArgument(label, args);
            PSender sender = new PSender(pluginName, pluginPrefix, commandSender);
            return CommandHandler.onCommand(sender, argument);
        }
        return true;
    }
}