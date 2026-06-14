package io.github.r1fom.sable_nc;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

@EventBusSubscriber(modid = Sable_nc.MODID, bus = EventBusSubscriber.Bus.MOD)
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue BLOCK_PLACE = BUILDER
            .comment("Allows placing blocks even if sub-level is in the blocks")
            .define("blockPlace", false);

    public static final ModConfigSpec.BooleanValue NOCLIP = BUILDER
            .comment("Allows player to fly through sub-level")
            .define("noclip", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    public static boolean blockPlace;
    public static boolean noclip;

    @SubscribeEvent
    static void onLoad(final ModConfigEvent event) {
        blockPlace = BLOCK_PLACE.get();
        noclip = NOCLIP.get();
    }
}
