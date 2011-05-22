package de.procrafter.flames.flatearth;

import ru.tehkode.permissions.bukkit.*;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.anjocaido.groupmanager.GroupManager;

import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class FlatEarthPermissions {

	private enum PermissionHandler {
		PERMISSIONSEX, PERMISSIONS, GROUPMANAGER, NONE
	}
	private static PermissionHandler handler;
	private static Plugin permissionPlugin;

	public static void initialize(Server server) {
		Plugin permissionsEx = server.getPluginManager().getPlugin("PermissionsEx");
		Plugin groupManager = server.getPluginManager().getPlugin("GroupManager");
		Plugin permissions = server.getPluginManager().getPlugin("Permissions");

		if (permissionsEx != null) {
			permissionPlugin = permissionsEx;
			handler = PermissionHandler.PERMISSIONSEX;
			String version = permissionsEx.getDescription().getVersion();
			FlatEarthLogger.info("Permissions enabled using: PermissionsEx v" + version);
		} else if (groupManager != null) {
			permissionPlugin = groupManager;
			handler = PermissionHandler.GROUPMANAGER;
			String version = groupManager.getDescription().getVersion();
			FlatEarthLogger.info("Permissions enabled using: GroupManager v" + version);
		} else if (permissions != null) {
			permissionPlugin = permissions;
			handler = PermissionHandler.PERMISSIONS;
			String version = permissions.getDescription().getVersion();
			FlatEarthLogger.info("Permissions enabled using: Permissions v" + version);
		} else {
			handler = PermissionHandler.NONE;
			FlatEarthLogger.warning("A permission plugin isn't loaded.");
		}
	}

	public static boolean permission(Player player, String permission, boolean defaultPerm) {
		switch (handler) {
		case PERMISSIONSEX:
			return ((PermissionsEx) permissionPlugin).getPermissionManager().has(player, permission);
		case PERMISSIONS:
			return ((Permissions) permissionPlugin).getHandler().has(player, permission);
		case GROUPMANAGER:
			return ((GroupManager) permissionPlugin).getWorldsHolder().getWorldPermissions(player).has(player, permission);
		case NONE:
			return defaultPerm;
		default:
			return defaultPerm;
		}
	}

	public static boolean isAdmin(Player player) {
		return permission(player, "flatearth.admin", player.isOp());
	}

	public static boolean flatearth(Player player) {
		return permission(player, "flatearth.flatearth", true);
	}

	public static boolean freespace(Player player) {
		return permission(player, "flatearth.freespace", true);
	}

	public static boolean onmove(Player player) {
		return permission(player, "flatearth.onmove", true);
	}
}
