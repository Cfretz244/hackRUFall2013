package com.gmail.cfretz244;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
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
				URL url = Vanity.class.getClassLoader().getResource("/pic.jpg");
				try {
					BufferedImage img = ImageIO.read(new File(url.toString()));
					img = resizeImage(img);
					
				} catch(IOException e) {
					//do something
				}
				
			}
		} else {
			sender.sendMessage("Wrong command");
		}
		return false;
	}
	
	public BufferedImage resizeImage(BufferedImage img) {
		int height = img.getHeight(), width = img.getWidth(), type = img.getType();
		double ratio = height / (double)width;
		int newHeight = (int)(150 * ratio);
		BufferedImage resizedImage = new BufferedImage(150, newHeight, type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(img, 0, 0, 150, newHeight, null);
		g.dispose();
	 
		return resizedImage;
	}
	
	
}
