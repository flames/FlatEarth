package de.procrafter.flames.bukkit.flatearth;

import org.bukkit.World;
import org.bukkit.entity.Player;
import de.procrafter.flames.bukkit.flatearth.FlatEarth;
import org.bukkit.Location; 
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerListener; 
import org.bukkit.event.player.PlayerMoveEvent; 

public class FlatearthPlayerListener extends PlayerListener {
	
	private FlatEarth plugin = null;
	public FlatearthPlayerListener(FlatEarth instance) {
		plugin = instance;
	}
	
	//public void onPlayerChangeBlock(PlayerMoveEvent event) {
	public void onPlayerMove(PlayerMoveEvent event) {
	          Player player = event.getPlayer();
	          World world = player.getWorld();

	          int x = player.getLocation().getBlockX();
	          int z = player.getLocation().getBlockZ();
	          
	          if (world.getEnvironment() == World.Environment.NETHER) {
		            for (int i = x - FlatEarth.CheckRadius; i < x + FlatEarth.CheckRadius + 1; i++) {
		              for (int j = z - FlatEarth.CheckRadius - 1; j < z + FlatEarth.CheckRadius; j++) {
		            	  //if (world.isChunkLoaded(world.getChunkAt(world.getBlockAt(i, 0, j)))) {
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
			                  
			                  //for (int y = 0; y< 127; y++) {
			            		  //if (world.getBlockAt(i, y, j).getTypeId() != 7) world.getBlockAt(i, y, j).setTypeId(0);
			            	  //}
		            	  //}
		              }
		            }
	          } else if (world.getEnvironment() == World.Environment.NORMAL) {
		            for (int i = x - FlatEarth.CheckRadius; i < x + FlatEarth.CheckRadius + 1; i++) {
		              for (int j = z - FlatEarth.CheckRadius - 1; j < z + FlatEarth.CheckRadius; j++) {
		            	  //if (world.isChunkLoaded(world.getChunkAt(world.getBlockAt(i, 0, j)))) {
			            	  if (world.getBlockAt(i, 0, j).getTypeId() != 7) world.getBlockAt(i, 0, j).setTypeId(7);
			
			                  if (world.getBlockAt(i, 1, j).getTypeId() == 7) world.getBlockAt(i, 1, j).setTypeId(1);
			                  if (world.getBlockAt(i, 2, j).getTypeId() == 7) world.getBlockAt(i, 2, j).setTypeId(1);
			                  if (world.getBlockAt(i, 3, j).getTypeId() == 7) world.getBlockAt(i, 3, j).setTypeId(1);
			                  if (world.getBlockAt(i, 4, j).getTypeId() == 7) world.getBlockAt(i, 4, j).setTypeId(1);
			                  
			                  //for (int y = 0; y< 127; y++) {
			            		  //if (world.getBlockAt(i, y, j).getTypeId() != 7) world.getBlockAt(i, y, j).setTypeId(0);
			            	  //}
		            	  //}
		              }
		            }
	          }
		  }

}
