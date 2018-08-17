package ruukas.infinityserver;

import org.apache.logging.log4j.Logger;

import net.minecraft.command.CommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import ruukas.infinityserver.commands.CommandBreak;
import ruukas.infinityserver.commands.CommandClearinventory;
import ruukas.infinityserver.commands.CommandFly;
import ruukas.infinityserver.commands.CommandHeal;
import ruukas.infinityserver.commands.CommandInventory;
import ruukas.infinityserver.commands.CommandMore;
import ruukas.infinityserver.commands.CommandShow;
import ruukas.infinityserver.commands.CommandSpawn;
import ruukas.infinityserver.commands.CommandTree;

@Mod( modid = InfinityServer.MODID, name = InfinityServer.NAME, version = InfinityServer.VERSION, acceptableRemoteVersions = "*" )
public class InfinityServer
{
    public static final String MODID = "infinityserver";
    public static final String NAME = "Infinity Server";
    public static final String VERSION = "0.1";
    
    private static Logger logger;
    
    @EventHandler
    public void preInit( FMLPreInitializationEvent e )
    {
        logger = e.getModLog();
    }
    
    @EventHandler
    public void init( FMLInitializationEvent e )
    {
        logger.info( "Hello" );
    }
    
    @EventHandler()
    public void serverStarting( FMLServerStartingEvent e )
    {
        if ( e.getServer().getCommandManager() instanceof CommandHandler )
        {
            CommandHandler cmdHandler = (CommandHandler) e.getServer().getCommandManager();
            cmdHandler.registerCommand( new CommandBreak() );
            cmdHandler.registerCommand( new CommandClearinventory() );
            cmdHandler.registerCommand( new CommandFly() );
            cmdHandler.registerCommand( new CommandHeal() );
            cmdHandler.registerCommand( new CommandInventory() );
            cmdHandler.registerCommand( new CommandMore() );
            cmdHandler.registerCommand( new CommandShow() );
            cmdHandler.registerCommand( new CommandSpawn() );
            cmdHandler.registerCommand( new CommandTree() );
        }
    }
    
}
