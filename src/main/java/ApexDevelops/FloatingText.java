package ApexDevelops;

import java.io.File;
import java.util.*;

import cn.nukkit.Player;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerRespawnEvent;
import cn.nukkit.utils.TextFormat;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.Server;
import cn.nukkit.event.Listener;
import cn.nukkit.level.particle.FloatingTextParticle;
import cn.nukkit.level.particle.Particle;
import cn.nukkit.level.Level;
import cn.nukkit.level.Position;
import cn.nukkit.plugin.PluginManager;
import cn.nukkit.plugin.Plugin;
import cn.nukkit.math.Vector3;
import cn.nukkit.utils.Config;
			
public class FloatingText extends PluginBase implements Listener {
	
    public Config config;
    
    @Override
    public void onEnable() {
    	this.getLogger().info(TextFormat.GREEN + "FloatingText enabled!");
	getServer().getPluginManager().registerEvents(this, this);
	Config config = new Config(new File(this.getDataFolder(), "config.yml"));
	this.saveDefaultConfig();
	this.saveResource("config.yml", true);
    }
	
    @Override
    public void onLoad() {
        this.getLogger().info(TextFormat.DARK_GREEN + "FloatingText loaded!");
    }
    
    @Override
    public void onDisable() {
    	this.getLogger().info(TextFormat.DARK_RED + "FloatingText disabled!");
    }
	
    public void onJoin(PlayerJoinEvent e) {
	    Player player = e.getPlayer();
	    Position pos = e.getPlayer().getLevelBlock();
	    int x1 = config.getInt("Xs");
	    int y1 = config.getInt("Ys");
	    int z1 = config.getInt("Zs");
	    String line1 = config.getString("LINE1");
	    String line2 = config.getString("LINE2");
	    String line3 = config.getString("LINE3");
	    String line4 = config.getString("LINE4");
	    String line5 = config.getString("LINE5");
	    int online = Server.getInstance().getOnlinePlayers().size();
	    int maxonline = this.getServer().getMaxPlayers();
	    String playername = player.getName();                                                  
 	    String rs = TextFormat.RESET + "\n";
	    String allline = line1 + rs + line2 + rs + line3 + rs + line4 + rs + line5;
	    allline = allline.replace("{ONLINE}", String.valueOf(online));
	    allline = allline.replace("{MAXONLINE}", String.valueOf(maxonline));
	    allline = allline.replace("{PLAYERNAME}", String.valueOf(playername));
	    FloatingTextParticle particle = new FloatingTextParticle(new Vector3(x1, y1, z1), allline);
	    e.getPlayer().getLevel().addParticle(particle);
    }
}
