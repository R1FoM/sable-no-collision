package io.github.r1fom.sable_nc;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.logging.LogUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import org.slf4j.Logger;

@Mod(Sable_nc.MODID)
public class Sable_nc {
    public static final String MODID = "sable_nc";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Sable_nc(IEventBus modEventBus, ModContainer modContainer) {
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    @SubscribeEvent
    public void onRegisterCommands(RegisterCommandsEvent event) {
        CommandDispatcher<CommandSourceStack> dispatcher = event.getDispatcher();

        dispatcher.register(Commands.literal("snc")
                .requires(source -> source.hasPermission(2))
                .then(Commands.literal("block_place")
                        .executes(context -> {
                            boolean value = !Config.blockPlace;
                            Config.BLOCK_PLACE.set(value);
                            Config.blockPlace = value;
                            context.getSource().sendSuccess(() -> Component.literal("block_place toggled to " + value), true);
                            return 1;
                        })
                        .then(Commands.argument("value", BoolArgumentType.bool())
                                .executes(context -> {
                                    boolean value = BoolArgumentType.getBool(context, "value");
                                    Config.BLOCK_PLACE.set(value);
                                    Config.blockPlace = value;
                                    context.getSource().sendSuccess(() -> Component.literal("block_place set to " + value), true);
                                    return 1;
                                })
                        )
                )
                .then(Commands.literal("noclip")
                        .executes(context -> {
                            boolean value = !Config.noclip;
                            Config.NOCLIP.set(value);
                            Config.noclip = value;
                            context.getSource().sendSuccess(() -> Component.literal("noclip toggled to " + value), true);
                            return 1;
                        })
                        .then(Commands.argument("value", BoolArgumentType.bool())
                                .executes(context -> {
                                    boolean value = BoolArgumentType.getBool(context, "value");
                                    Config.NOCLIP.set(value);
                                    Config.noclip = value;
                                    context.getSource().sendSuccess(() -> Component.literal("noclip set to " + value), true);
                                    return 1;
                                })
                        )
                )
        );
    }
}
