package ruukas.infinityserver.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandFly extends CommandPlayer
{
    @Override
    public String getName()
    {
        return "fly";
    }
    
    @Override
    public String getUsage( ICommandSender sender )
    {
        return "/fly [player]";
    }
    
    @Override
    public void executeWithTarget( MinecraftServer server, ICommandSender sender, String[] args, EntityPlayerMP target ) throws CommandException
    {
        target.capabilities.allowFlying = !target.capabilities.allowFlying;
        if ( !target.capabilities.allowFlying )
        {
            target.capabilities.isFlying = false;
        }
        target.sendPlayerAbilities();
        sender.sendMessage( new TextComponentString( "Toggled fly mode for " ).appendSibling( target.getDisplayName() ).appendText( "!" ) );
    }
}
