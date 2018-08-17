package ruukas.infinityserver.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandSpawn extends CommandPlayer
{
    
    @Override
    public String getName()
    {
        return "spawn";
    }
    
    @Override
    public String getUsage( ICommandSender sender )
    {
        return "/spawn";
    }
    
    @Override
    public void executeWithTarget( MinecraftServer server, ICommandSender sender, String[] args, EntityPlayerMP target ) throws CommandException
    {
        target.moveToBlockPosAndAngles( target.getEntityWorld().getSpawnPoint(), 0.0F, 0.0F );
        sender.sendMessage( new TextComponentString( "Teleported to spawn!" ) );
    }
}
