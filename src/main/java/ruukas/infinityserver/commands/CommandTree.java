package ruukas.infinityserver.commands;

import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.RayTraceResult.Type;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import ruukas.infinityserver.libs.util.RayTraceUtil;

public class CommandTree extends Command
{
    
    @Override
    public String getName()
    {
        return "tree";
    }
    
    @Override
    public String getUsage( ICommandSender sender )
    {
        return "/tree";
    }
    
    @Override
    public void execute( MinecraftServer server, ICommandSender sender, String[] args ) throws CommandException
    {
        if ( sender.getCommandSenderEntity() != null && sender.getCommandSenderEntity() instanceof EntityPlayer )
        {
            RayTraceResult ray = RayTraceUtil.getObjectAimedAt( (EntityPlayer) sender.getCommandSenderEntity(), sender.getEntityWorld(), 3.0d, 0 );
            if ( ray.typeOfHit == Type.BLOCK )
            {               
                WorldGenBigTree gen = new WorldGenBigTree( false );
                
                if(gen.generate( sender.getEntityWorld(), sender.getEntityWorld().rand, ray.getBlockPos().up() )){
                    sender.sendMessage( new TextComponentTranslation( "Generated tree!", sender.getEntityWorld().getBlockState( ray.getBlockPos() ).getBlock().getLocalizedName() ) );
                    return;
                }
                sender.sendMessage( new TextComponentTranslation( "Couldn't generate tree there!", sender.getEntityWorld().getBlockState( ray.getBlockPos() ).getBlock().getLocalizedName() ) );
                return;
            }
        }
        throw new CommandException( "No block found!" );
    }
}
