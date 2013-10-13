package com.gmail.cfretz244;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.BlockState;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;

public final class Vanity extends JavaPlugin {
	
	@Override
	public void onEnable() {
		//Do something awesome
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("vanity")) {
			if(sender instanceof Player) {
				Player player = (Player)sender;
				World world = player.getWorld();
				int width = 150, height = 150;
				Location loc;
				loc = player.getLocation();
//				if(args[0].equals("here")) {
//					loc = player.getLocation();
//				} else if(args[0].equals("there")) {
//					loc = player.getTargetBlock(null, 200).getLocation();
//				} else {
//					loc = player.getLocation();
//					double x = loc.getX();
//					double y = loc.getY();
//					double z = loc.getZ();
//					loc = new Location(world, x - 1, y, z - 1);
//				}
				width = Integer.parseInt(args[0]);
				height = Integer.parseInt(args[1]);
				BufferedImage img;
				if(args.length == 3) {
					width = Integer.parseInt(args[1]);
					height = Integer.parseInt(args[2]);
				}
				try {
					URL url = new URL("https://scontent-a-lga.xx.fbcdn.net/hphotos-ash3/1234554_10151663409294037_1614676655_n.jpg");
					img = ImageIO.read(url);
				} catch(IOException e) {
					player.sendMessage("Terribly sorry, but an error has occured. Perhaps the image does not exist?");
					return false;
				} catch(Exception e) {
					player.sendMessage("Terribly sorry, but an error has occurred");
					return false;
				}
				img = resizeImage(img, width, height);
				int[][] woolColors = processImage(img);
				writeBlocks(woolColors, loc);
			}
		} else {
			sender.sendMessage("Wrong command");
		}
		return false;
	}
	
	public BufferedImage resizeImage(BufferedImage img, int newWidth, int newHeight) {
		int type = img.getType();
		BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(img, 0, 0, newWidth, newHeight, null);
		g.dispose();
		return resizedImage;
	}
	
	public int[][] processImage(BufferedImage img) {
		int[][] pixelData = new int[img.getWidth()][img.getHeight()];
		for(int i = 0; i < img.getWidth(); i++) {
			for(int k = 0; k < img.getHeight(); k++) {
				Color currentPixel = new Color(img.getRGB(i, k));
				int r = currentPixel.getRed();
				int g = currentPixel.getGreen();
				int b = currentPixel.getBlue();
				if((r >= 190 && r <= 260) && (g >= 190 && g <= 260) && (b >= 190 && b <= 260)) {
					pixelData[i][k] = 0;
				} else if((r >= 130 && r <= 200) && (g >= 130 && g <= 200) && (b >= 130 && b <= 200)) {
					pixelData[i][k] = 1;
				} else if((r >= 30 && r <= 100) && (g >= 30 && g <= 100) && (b >= 30 && b <= 100)) {
					pixelData[i][k] = 2;
				} else if((r >= 0 && r <= 70) && (g >= 0 && g <= 70) && (b >= 0 && b <= 70)) {
					pixelData[i][k] = 3;
				} else if((r >= 125 && r <= 195) && (g >= 20 && g <= 90) && (b >= 20 && b <= 90)) {
					pixelData[i][k] = 4;
				} else if((r >= 185 && r <= 255) && (g >= 90 && g <= 160) && (b >= 25 && b <= 95)) {
					pixelData[i][k] = 5;
				} else if((r >= 155 && r <= 225) && (g >= 145 && g <= 215) && (b >= 5 && b <= 75)) {
					pixelData[i][k] = 6;
				} else if((r >= 30 && r <= 100) && (g >= 140 && g <= 210) && (b >= 25 && b <= 95)) {
					pixelData[i][k] = 7;
				} else if((r >= 25 && r <= 95) && (g >= 45 && g <= 115) && (b >= 0 && b <= 70)) {
					pixelData[i][k] = 8;
				} else if((r >= 55 && r <= 125) && (g >= 90 && g <= 160) && (b >= 160 && b <= 230)) {
					pixelData[i][k] = 9;
				} else if((r >= 15 && r <= 85) && (g >= 80 && g <= 150) && (b >= 105 && b <= 175)) {
					pixelData[i][k] = 10;
				} else if((r >= 10 && r <= 80) && (g >= 20 && g <= 90) && (b >= 100 && b <= 170)) {
					pixelData[i][k] = 11;
				} else if((r >= 85 && r <= 155) && (g >= 25 && g <= 95) && (b >= 145 && b <= 215)) {
					pixelData[i][k] = 12;
				} else if((r >= 140 && r <= 210) && (g >= 35 && g <= 105) && (b >= 150 && b <= 220)) {
					pixelData[i][k] = 13;
				} else if((r >= 175 && r <= 245) && (g >= 105 && g <= 175) && (b >= 125 && b <= 195)) {
					pixelData[i][k] = 14;
				} else if((r >= 40 && r <= 110) && (g >= 15 && g <= 85) && (b >= 0 && b <= 70)) {
					pixelData[i][k] = 15;
				} else {
					pixelData[i][k] = -1;
				}
			}
		}
		
		return pixelData;
	}
	
	public void writeBlocks(int[][] woolColors, Location loc) {
		Location currentLoc = new Location(loc.getWorld(), loc.getX(), loc.getY() + woolColors[0].length, loc.getZ());
		for(int i = 0; i < woolColors.length; i++) {
			for(int k = 0; k < woolColors[i].length; k++) {
				currentLoc = new Location(currentLoc.getWorld(), currentLoc.getX(), currentLoc.getY(), currentLoc.getZ() + 1);
				BlockState blockState = currentLoc.getBlock().getState();
				blockState.setType(Material.WOOL);
				byte color = 0000;
				int woolColor = woolColors[i][k];
				if(woolColor == 0) {
					color = 0;
				} else if(woolColor == 1) {
					color = 8;
				} else if(woolColor == 2) {
					color = 7;
				} else if(woolColor == 3) {
					color = 15;
				} else if(woolColor == 4) {
					color = 14;
				} else if(woolColor == 5) {
					color = 1;
				} else if(woolColor == 6) {
					color = 4;
				} else if(woolColor == 7) {
					color = 5;
				} else if(woolColor == 8) {
					color = 13;
				} else if(woolColor == 9) {
					color = 3;
				} else if(woolColor == 10) {
					color = 9;
				} else if(woolColor == 11) {
					color = 11;
				} else if(woolColor == 12) {
					color = 10;
				} else if(woolColor == 13) {
					color = 2;
				} else if(woolColor == 14) {
					color = 6;
				} else if(woolColor == 15) {
					color = 12;
				} else {
					color = 8;
				}
				MaterialData data = blockState.getData();
				data.setData(color);
				blockState.setData(data);
				blockState.update(true);
			}
			currentLoc = new Location(currentLoc.getWorld(), currentLoc.getX(), currentLoc.getY() - 1, currentLoc.getZ() - woolColors[0].length);
		}
	}
	
	
}
