package ruukas.infinityserver.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandClearinventory extends CommandPlayer
{
    
    @Override
    public String getName()
    {
        return "clearinventory";
    }
    
    @Override
    public String getUsage( ICommandSender sender )
    {
        return "/clearinventory";
    }
    
    @Override
    public void executeWithTarget( MinecraftServer server, ICommandSender sender, String[] args, EntityPlayerMP target ) throws CommandException
    {
        target.inventory.clear();
        sender.sendMessage( new TextComponentString( "Cleared inventory of " ).appendSibling( target.getDisplayName() ).appendText( "." ) );
    }
}
