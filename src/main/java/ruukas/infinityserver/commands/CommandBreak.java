package ruukas.infinityserver.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextComponentTranslation;
import ruukas.infinityserver.libs.util.RayTraceUtil;

public class CommandBreak extends Command
{
    
    @Override
    public String getName()
    {
        return "break";
    }
    
    @Override
    public String getUsage( ICommandSender sender )
    {
        return "/break";
    }
    
    @Override
    public void execute( MinecraftServer server, ICommandSender sender, String[] args ) throws CommandException
    {
        if ( sender.getCommandSenderEntity() != null && sender.getCommandSenderEntity() instanceof EntityPlayer )
        {
            RayTraceResult ray = RayTraceUtil.getObjectAimedAt( (EntityPlayer) sender.getCommandSenderEntity(), sender.getEntityWorld(), 3.0d, 0 );
            if ( ray.typeOfHit == Type.BLOCK )
            {
                sender.sendMessage( new TextComponentTranslation( "Broke %s", sender.getEntityWorld().getBlockState( ray.getBlockPos() ).getBlock().getLocalizedName() ) );
                sender.getEntityWorld().setBlockToAir( ray.getBlockPos() );
                return;
            }
        }
        throw new CommandException( "No block found" );
    }
}
