package ruukas.infinityserver.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;

public class CommandShow extends Command
{
    
    @Override
    public String getName()
    {
        return "show";
    }
    
    @Override
    public String getUsage( ICommandSender sender )
    {
        return "/show";
    }
    
    @Override
    public void execute( MinecraftServer server, ICommandSender sender, String[] args ) throws CommandException
    {
        if ( sender.getCommandSenderEntity() != null && sender.getCommandSenderEntity() instanceof EntityPlayer )
        {
            EntityPlayer player = (EntityPlayer) sender.getCommandSenderEntity();
            ItemStack stack = player.inventory.getCurrentItem();
            server.getPlayerList().sendMessage( sender.getDisplayName().appendText( " shows their " ).appendSibling( stack.isEmpty() ? new TextComponentString( "fist!" ) : player.inventory.getCurrentItem().getTextComponent().appendText( "!" ) ) );
        }
        
    }
    
}
