package io.github.r1fom.sable_nc;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

@EventBusSubscriber(modid = Sable_nc.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ClientEvents {

    public static final KeyMapping TOGGLE_BLOCK_PLACE = new KeyMapping(
            "key.sable_nc.toggle_block_place",
            InputConstants.UNKNOWN.getValue(),
            "key.categories.sable_nc"
    );

    public static final KeyMapping TOGGLE_NOCLIP = new KeyMapping(
            "key.sable_nc.toggle_noclip",
            InputConstants.UNKNOWN.getValue(),
            "key.categories.sable_nc"
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(TOGGLE_BLOCK_PLACE);
        event.register(TOGGLE_NOCLIP);
    }

    @EventBusSubscriber(modid = Sable_nc.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.GAME)
    public static class ClientGameEvents {
        @SubscribeEvent
        public static void onClientTick(ClientTickEvent.Post event) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null) {
                while (TOGGLE_BLOCK_PLACE.consumeClick()) {
                    mc.getConnection().sendCommand("snc block_place");
                }
                while (TOGGLE_NOCLIP.consumeClick()) {
                    mc.getConnection().sendCommand("snc noclip");
                }
            }
        }
    }
}
