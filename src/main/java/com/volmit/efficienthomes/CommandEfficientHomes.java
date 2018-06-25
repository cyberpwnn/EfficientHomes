package com.volmit.efficienthomes;

import com.volmit.volume.bukkit.U;
import com.volmit.volume.bukkit.command.Command;
import com.volmit.volume.bukkit.command.PawnCommand;
import com.volmit.volume.bukkit.command.VolumeSender;

public class CommandEfficientHomes extends PawnCommand
{
	@Command
	public CommandSetHome cmdSetHome;

	public CommandEfficientHomes()
	{
		super("efficienthomes", "ef", "efh", "eh");
	}

	public boolean handle(VolumeSender s, String[] a)
	{
		HomePlayer hp = U.getService(HomeSVC.class).get(s.player());

		for(String i : hp.getHomes().k())
		{
			s.sendMessage(i + " - " + hp.getHomes().get(i).getWorld().getName());
		}
		return true;
	}

}
