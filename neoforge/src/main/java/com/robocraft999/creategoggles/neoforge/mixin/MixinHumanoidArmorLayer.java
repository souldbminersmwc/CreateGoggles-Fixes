package com.robocraft999.creategoggles.neoforge.mixin;

import com.robocraft999.creategoggles.registry.CGTrimPatterns;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ArmorFeatureRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.trim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArmorFeatureRenderer.class)
public abstract class MixinArmorFeatureRenderer<T, M extends BipedEntityModel<T>, A extends BipedEntityModel<T>> {

    @Inject(
        method = "renderTrim(Lnet/minecraft/item/ArmorMaterial;Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/item/trim/ArmorTrim;Lnet/minecraft/client/render/entity/model/BipedEntityModel;Z)V",
        at = @At("HEAD"),
        cancellable = true
    )
    private void onRenderTrim(ArmorMaterial material,
                              MatrixStack matrices,
                              VertexConsumerProvider vcp,
                              int light,
                              ArmorTrim trim,
                              BipedEntityModel<T> model,
                              boolean slim,
                              CallbackInfo ci) {
        if (trim != null && trim.material().is(CGTrimPatterns.GOGGLE_MATERIAL)) {
            ci.cancel();
        }
    }
}
