package com.volmit.efficienthomes;

import org.bukkit.Location;

import com.volmit.volume.bukkit.pawn.IPawn;
import com.volmit.volume.bukkit.pawn.Node;
import com.volmit.volume.bukkit.util.data.StringUtil;
import com.volmit.volume.lang.collections.GList;
import com.volmit.volume.lang.collections.GMap;

public class HomePlayer implements IPawn
{
	@Node("homes")
	public GList<String> homes = new GList<>();

	public GMap<String, Location> getHomes()
	{
		GMap<String, Location> l = new GMap<>();

		for(String i : homes)
		{
			l.put(i.split("--")[0], StringUtil.locationFromString(i.split("--")[1]));
		}

		return l;
	}

	public void addHome(String name, Location l)
	{
		homes.add(name + "--" + StringUtil.locationToString(l));
	}
}
