package ruukas.infinityserver.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

public class CommandMore extends Command
{
    
    @Override
    public String getName()
    {
        return "more";
    }
    
    @Override
    public String getUsage( ICommandSender sender )
    {
        return "/more";
    }
    
    @Override
    public void execute( MinecraftServer server, ICommandSender sender, String[] args ) throws CommandException
    {
        if ( sender.getCommandSenderEntity() != null && sender.getCommandSenderEntity() instanceof EntityPlayerMP )
        {
            EntityPlayerMP player = (EntityPlayerMP) sender.getCommandSenderEntity();
            ItemStack stack = player.inventory.getCurrentItem();
            if ( stack.isEmpty() )
            {
                throw new CommandException( "You need to hold something to get more of!", player.getDisplayName() );
            }
            if ( stack.getCount() == stack.getMaxStackSize() )
            {
                throw new CommandException( "You are already holding a full stack!", player.getDisplayName() );
            }
            
            stack.setCount( stack.getMaxStackSize() );
            
            player.inventory.setInventorySlotContents( player.inventory.currentItem, stack );
            
            sender.sendMessage( new TextComponentTranslation( "Gave more!" ) );
        }
    }
    
}
