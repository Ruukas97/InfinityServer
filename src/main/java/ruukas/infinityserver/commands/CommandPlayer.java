package ruukas.infinityserver.commands;

import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

/**
 * A base command class for commands with the format /command [player]
 *
 */
public abstract class CommandPlayer extends Command
{
    /**
     * Whether or not to target command sender if no argument was given.
     */
    private boolean targetSelf = true;
    //TODO require playerSender
    //TODO takes args
    
    public CommandPlayer(boolean targetSelf) {
        this.targetSelf = targetSelf;
    }
    
    public CommandPlayer() {
    }
    
    public void setTargetSelf( boolean targetSelf )
    {
        this.targetSelf = targetSelf;
    }
    
    public boolean getTargetSelf()
    {
        return this.targetSelf;
    }
    
    @Override
    public void execute( MinecraftServer server, ICommandSender sender, String[] args ) throws CommandException
    {
        if ( args.length == 0 && sender.getCommandSenderEntity() instanceof EntityPlayerMP )
        {
            executeWithTarget( server, sender, args, (EntityPlayerMP) sender.getCommandSenderEntity() );
        }
        
        else if ( args.length == 1 )
        {
            executeWithTarget( server, sender, args, getPlayer( server, sender, args[0] ) );
        }
        
        else
        {
            throw new WrongUsageException( getUsage( sender ) );
        }
    }
    
    public abstract void executeWithTarget( MinecraftServer server, ICommandSender sender, String[] args, EntityPlayerMP target ) throws CommandException;
    
    @Override
    public List<String> getTabCompletions( MinecraftServer server, ICommandSender sender, String[] args, BlockPos targetPos )
    {
        return getListOfStringsMatchingLastWord( args, server.getOnlinePlayerNames() );
    }
    
    @Override
    public boolean isUsernameIndex( String[] args, int index )
    {
        return index == 0;
    }
}
