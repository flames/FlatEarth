
package de.procrafter.flames.bukkit.flatearth;

import java.io.File;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.config.Configuration;

public class FlatEarth extends JavaPlugin {
	
	private final FlatearthPlayerListener playerListener = new FlatearthPlayerListener(this);
	
	  public static int maxflatradius;
	  public static int maxfreeradius;
	  public static int CheckRadius;
	
	  public void onDisable() {
		  //
	  }
	  public void onEnable() {
		  PluginDescriptionFile pdfFile = this.getDescription();
		  System.out.println( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
		  
		  registerEvents();
		  
		  Configuration configuration = new Configuration(new File(this.getDataFolder(), "configuration.yml"));
		  configuration.load();
		  maxflatradius = configuration.getInt("maxflatradius", 250);
		  maxfreeradius = configuration.getInt("maxfreeradius", 50);
		  CheckRadius = configuration.getInt("radius", 8);
      }
	  
	  private void registerEvents() 
	    {
		  	PluginManager pm = getServer().getPluginManager(); 
			pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Priority.Monitor, this);
		}
	  
	  public boolean onCommand(CommandSender sender, Command command, String commandLabel, String[] args) {
	        String[] trimmedArgs = args;
	        String commandName = command.getName().toLowerCase();

	        if (commandName.equals("flatearth")) {
	            return performFlatearth(sender, trimmedArgs);
	        } else if (commandName.equals("freespace")) {
	            return performFreespace(sender, trimmedArgs);
	        }
	        return false;
	  }
	  
	  private boolean performFlatearth(CommandSender sender, String[] split) {
	        if (!sender.isOp()) {
	            sender.sendMessage("You do not have permission to flat bedrock");
	            return false;
	        }
	        if (split.length > 1) {
	            return false;
	        }

	        int radius = 8;

	        if (split.length >= 1) {
	            try {
	                radius = Integer.parseInt(split[0]);
	                if ( radius > maxflatradius) {
	                	sender.sendMessage("You specified too big radius of " + radius + ", radius was automatically limited to " + maxflatradius);
	                	radius = maxflatradius;
	                }
	            } catch (NumberFormatException ex) {
	                sender.sendMessage(ChatColor.RED + "'" + split[0] + "' is not a number!");
	                return false;
	            }
	        }

	        Player player = (Player)sender;
	        World world = player.getWorld();
	        
	        int x = player.getLocation().getBlockX();
            int z = player.getLocation().getBlockZ();
            
            if (world.getEnvironment() == World.Environment.NETHER) {
	            for (int i = x - radius; i < x + radius + 1; i++) {
	              for (int j = z - radius - 1; j < z + radius; j++) {
	            	  if (world.isChunkLoaded(world.getChunkAt(world.getBlockAt(i, 0, j)))) {
		            	  if (world.getBlockAt(i, 0, j).getTypeId() != 7) world.getBlockAt(i, 0, j).setTypeId(7);
		            		
		                  if (world.getBlockAt(i, 1, j).getTypeId() == 7) world.getBlockAt(i, 1, j).setTypeId(1);
		                  if (world.getBlockAt(i, 2, j).getTypeId() == 7) world.getBlockAt(i, 2, j).setTypeId(1);
		                  if (world.getBlockAt(i, 3, j).getTypeId() == 7) world.getBlockAt(i, 3, j).setTypeId(1);
		                  if (world.getBlockAt(i, 4, j).getTypeId() == 7) world.getBlockAt(i, 4, j).setTypeId(1);
		                  
		            	  if (world.getBlockAt(i, 127, j).getTypeId() != 7) world.getBlockAt(i, 127, j).setTypeId(7);
		            		
		                  if (world.getBlockAt(i, 126, j).getTypeId() == 7) world.getBlockAt(i, 126, j).setTypeId(87);
		                  if (world.getBlockAt(i, 125, j).getTypeId() == 7) world.getBlockAt(i, 125, j).setTypeId(87);
		                  if (world.getBlockAt(i, 124, j).getTypeId() == 7) world.getBlockAt(i, 124, j).setTypeId(87);
		                  if (world.getBlockAt(i, 123, j).getTypeId() == 7) world.getBlockAt(i, 123, j).setTypeId(87);
	            	  } //else { sender.sendMessage("skipped x" + i + " z" + j); }
	              }
	            }
	            sender.sendMessage("Flattened bedrock in radius " + radius);
	            return true;
            } else if (world.getEnvironment() == World.Environment.NORMAL) {
	            for (int i = x - radius; i < x + radius + 1; i++) {
	              for (int j = z - radius - 1; j < z + radius; j++) {
	            	  if (world.isChunkLoaded(world.getChunkAt(world.getBlockAt(i, 0, j)))) {
		            	  if (world.getBlockAt(i, 0, j).getTypeId() != 7) world.getBlockAt(i, 0, j).setTypeId(7);
		
		                  if (world.getBlockAt(i, 1, j).getTypeId() == 7) world.getBlockAt(i, 1, j).setTypeId(1);
		                  if (world.getBlockAt(i, 2, j).getTypeId() == 7) world.getBlockAt(i, 2, j).setTypeId(1);
		                  if (world.getBlockAt(i, 3, j).getTypeId() == 7) world.getBlockAt(i, 3, j).setTypeId(1);
		                  if (world.getBlockAt(i, 4, j).getTypeId() == 7) world.getBlockAt(i, 4, j).setTypeId(1);
	            	  } //else { sender.sendMessage("skipped x" + i + " z" + j); }
	              }
	            }
	            sender.sendMessage("Flattened bedrock in radius " + radius);
	            return true;
            } else {
            	sender.sendMessage("This Environment is not supported yet");
            	return false;
            }
	  }
	  
	  private boolean performFreespace(CommandSender sender, String[] split) {
	        if (!sender.isOp()) {
	            sender.sendMessage("You do not have permission to remove blocks");
	            return false;
	        }
	        if (split.length > 1) {
	            return false;
	        }

	        int radius = 8;

	        if (split.length >= 1) {
	            try {
	                radius = Integer.parseInt(split[0]);
	                if ( radius > maxfreeradius) {
	                	sender.sendMessage("You specified too big radius of " + radius + ", radius was automatically limited to " + maxfreeradius);
	                	radius = maxfreeradius;
	                }
	            } catch (NumberFormatException ex) {
	                sender.sendMessage(ChatColor.RED + "'" + split[0] + "' is not a number!");
	                return false;
	            }
	        }

	        Player player = (Player)sender;
	        World world = player.getWorld();
	        
	        int x = player.getLocation().getBlockX();
            int z = player.getLocation().getBlockZ();
            
            for (int i = x - radius; i < x + radius + 1; i++) {
              for (int j = z - radius - 1; j < z + radius; j++) {
            	  if (world.isChunkLoaded(world.getChunkAt(world.getBlockAt(i, 0, j)))) {
	            	  for (int y = 0; y< 127; y++) {
	            		  if (world.getBlockAt(i, y, j).getTypeId() != 7) world.getBlockAt(i, y, j).setTypeId(0);
	            	  }
            	  } //else { sender.sendMessage("skipped x" + i + " z" + j); }
              }
            }
            sender.sendMessage("Removed blocks in radius " + radius);
            return true;
	  }
}
