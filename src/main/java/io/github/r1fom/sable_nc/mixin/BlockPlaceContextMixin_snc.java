package io.github.r1fom.sable_nc.mixin;

import io.github.r1fom.sable_nc.Config;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.item.context.UseOnContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = BlockPlaceContext.class, priority = 500)
public abstract class BlockPlaceContextMixin_snc extends UseOnContext {

    @Shadow
    protected boolean replaceClicked;

    public BlockPlaceContextMixin_snc(Player pPlayer, net.minecraft.world.InteractionHand pHand, net.minecraft.world.phys.BlockHitResult pHitResult) {
        super(pPlayer, pHand, pHitResult);
    }

    @Inject(method = "canPlace", at = @At("HEAD"), cancellable = true)
    private void snc$forcePlace(CallbackInfoReturnable<Boolean> cir) {
        Player player = this.getPlayer();
        if (Config.blockPlace && player != null && player.hasPermissions(2)) {
            boolean vanillaCanPlace = this.replaceClicked || this.getLevel().getBlockState(this.getClickedPos()).canBeReplaced((BlockPlaceContext) (Object) this);
            cir.setReturnValue(vanillaCanPlace);
        }
    }
}
