package com.volmit.efficienthomes;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.volmit.volume.bukkit.VolumePlugin;
import com.volmit.volume.bukkit.pawn.Start;
import com.volmit.volume.bukkit.pawn.Stop;
import com.volmit.volume.bukkit.service.IService;
import com.volmit.volume.bukkit.util.data.PawnClusterPort;
import com.volmit.volume.bukkit.util.data.YAMLClusterPort;
import com.volmit.volume.lang.collections.GMap;

public class HomeSVC implements IService
{
	private File df;
	private GMap<Player, HomePlayer> g;

	@Start
	public void start()
	{
		df = VolumePlugin.vpi.getDataFolder("player-data");
		g = new GMap<Player, HomePlayer>();
	}

	@Stop
	public void stop()
	{

	}

	public HomePlayer get(Player p)
	{
		if(g.containsKey(p))
		{
			return g.get(p);
		}
		try
		{
			return loadPlayer(p);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public void savePlayer(Player p) throws Exception
	{
		if(!g.containsKey(p))
		{
			HomePlayer hp = new HomePlayer();
			g.put(p, hp);
		}
		HomePlayer hp = g.get(p);
		File f = new File(df, p.getUniqueId().toString() + ".yml");
		FileConfiguration fc = new YAMLClusterPort().fromCluster(new PawnClusterPort(hp).toCluster());
		fc.save(f);
	}

	public HomePlayer loadPlayer(Player p) throws Exception
	{
		HomePlayer hp = new HomePlayer();
		File f = new File(df, p.getUniqueId().toString() + ".yml");
		if(f.exists())
		{
			FileConfiguration fc = new YamlConfiguration();
			fc.load(f);
			new PawnClusterPort(hp).fromCluster(new YAMLClusterPort().toCluster(fc));
			g.put(p, hp);
		}
		savePlayer(p);
		return hp;
	}
}
