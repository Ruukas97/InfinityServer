package ruukas.infinityserver.events;

import java.util.Map;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent.ServerConnectionFromClientEvent;
import net.minecraftforge.fml.common.network.handshake.NetworkDispatcher;
import ruukas.infinityserver.InfinityServer;

@Mod.EventBusSubscriber( modid = InfinityServer.MODID )
public class InfinityEventHandler
{
    public static String[] bannedMods = {};
    @SubscribeEvent
    public static void onPlayerConnection(ServerConnectionFromClientEvent e){

        NetworkDispatcher dispatcher = e.getManager().channel().attr( NetworkDispatcher.FML_DISPATCHER ).get();
        Map<String, String> map = dispatcher.getModList();
        
        for(String v : map.keySet()){
            for(String m : bannedMods){
                if (v.equals( m )){
                    dispatcher.rejectHandshake( "Illegal mod installed!" );
                }
            }
        }
    }
}
