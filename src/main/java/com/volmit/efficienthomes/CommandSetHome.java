package com.volmit.efficienthomes;

import com.volmit.volume.bukkit.U;
import com.volmit.volume.bukkit.command.PawnCommand;
import com.volmit.volume.bukkit.command.VolumeSender;

public class CommandSetHome extends PawnCommand
{

	public CommandSetHome()
	{
		super("sethome", "sh", "efsh", "set");
	}

	public boolean handle(VolumeSender s, String[] a)
	{
		if(a.length == 1)
		{
			U.getService(HomeSVC.class).get(s.player()).addHome(a[0], s.player().getLocation());
			s.sendMessage("Home \"" + a[0] + "\" set.");
		}
		else
		{
			s.sendMessage("STICK TO THE FUCKIN' PLAN");
		}
		return true;
	}

}
