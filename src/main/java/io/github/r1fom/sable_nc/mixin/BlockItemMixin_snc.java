package io.github.r1fom.sable_nc.mixin;

import io.github.r1fom.sable_nc.Config;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockItem.class)
public abstract class BlockItemMixin_snc {

    @Inject(method = "canPlace", at = @At("HEAD"), cancellable = true)
    private void snc$ignoreEntities(BlockPlaceContext pContext, BlockState pState, CallbackInfoReturnable<Boolean> cir) {
        if (Config.blockPlace && pContext.getPlayer() != null && pContext.getPlayer().hasPermissions(2)) {
            if (pState.canSurvive(pContext.getLevel(), pContext.getClickedPos())) {
                cir.setReturnValue(true);
            }
        }
    }
}
