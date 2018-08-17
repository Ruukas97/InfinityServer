package ruukas.infinityserver.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.SyntaxErrorException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;

public class CommandAfk extends CommandPlayer
{

    @Override
    public String getName()
    {
        return "afk";
    }

    @Override
    public String getUsage( ICommandSender sender )
    {
        return "/afk";
    }

    @Override
    public void executeWithTarget( MinecraftServer server, ICommandSender sender, String[] args, EntityPlayerMP target ) throws CommandException
    {
        if(sender.getCommandSenderEntity() == target){
            //TODO Command: afk
        }
        
        else{
            throw new SyntaxErrorException("You can only change afk status of yourself");
        }
    }
}
