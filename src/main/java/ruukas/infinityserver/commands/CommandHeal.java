package ruukas.infinityserver.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.FoodStats;
import net.minecraft.util.text.TextComponentString;

public class CommandHeal extends CommandPlayer
{
    
    @Override
    public String getName()
    {
        return "heal";
    }
    
    @Override
    public String getUsage( ICommandSender sender )
    {
        return "/heal [player]";
    }
    
    @Override
    public void executeWithTarget( MinecraftServer server, ICommandSender sender, String[] args, EntityPlayerMP target ) throws CommandException
    {
        if ( target.isDead )
        {
            throw new CommandException( "Can't heal dead target.", target.getDisplayName() );
        }
        target.setHealth( target.getMaxHealth() );
        FoodStats food = target.getFoodStats();
        food.addStats( 20, 1f );
        target.clearActivePotions();
        // TODO treat command instead, and this would only heal
        sender.sendMessage( new TextComponentString( "Healed " ).appendSibling( target.getDisplayName() ).appendText( "!" ) );
    }
    
}
