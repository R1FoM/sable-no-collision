package io.github.r1fom.sable_nc.mixin;

import io.github.r1fom.sable_nc.Config;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin_snc {
    @Shadow public boolean noPhysics;

    @Inject(method = "move", at = @At("HEAD"))
    private void snc$applyNoclip(MoverType pType, Vec3 pPos, CallbackInfo ci) {
        if (Config.noclip && ((Object) this) instanceof Player player) {
            if (player.hasPermissions(2)) {
                this.noPhysics = true;
                player.getAbilities().mayfly = true;
                player.getAbilities().flying = true;
            }
        }
    }
}
