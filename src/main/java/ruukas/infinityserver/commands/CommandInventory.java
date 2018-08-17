package ruukas.infinityserver.commands;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import ruukas.infinityserver.libs.inventory.InventoryPlayerWrapper;

public class CommandInventory extends CommandPlayer
{
    
    @Override
    public String getName()
    {
        return "inventory";
    }
    
    @Override
    public String getUsage( ICommandSender sender )
    {
        return "/inventory [player]";
    }
    
    @Override
    public List<String> getAliases()
    {
        ArrayList<String> list = new ArrayList<>();
        list.add( "inv" );
        return list;
    }
    
    @Override
    public void executeWithTarget( MinecraftServer server, ICommandSender sender, String[] args, EntityPlayerMP target ) throws CommandException
    {
        if ( sender.getCommandSenderEntity() != null && sender.getCommandSenderEntity() instanceof EntityPlayerMP )
        {
            ((EntityPlayerMP) sender.getCommandSenderEntity()).displayGUIChest( new InventoryPlayerWrapper( target.inventory ) );
            //sender.sendMessage( new TextComponentTranslation( "commands.inventory.success" ) );
        }
        else throw new CommandException( "\"/inventory\" can only be used by a player." );
    }
    
}
