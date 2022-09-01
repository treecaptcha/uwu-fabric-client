package io.github.ran.uwu.client.mixins;

import io.github.ran.uwu.client.Uwuifier;
import io.github.ran.uwu.client.config.UwuConfig;
import net.minecraft.client.font.TextRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(value = TextRenderer.class, priority = -2)
public abstract class MixinTextRenderer {
    @ModifyVariable(method = "drawInternal(Ljava/lang/String;FFIZLnet/minecraft/util/math/Matrix4f;Lnet/minecraft/client/render/VertexConsumerProvider;ZIIZ)I", at = @At("HEAD"), argsOnly = true, ordinal = 0)
    private String uwuifiedString(String string) {
        return (UwuConfig.uwuifyFontRenderer && UwuConfig.uwuifyMinecraft) ? Uwuifier.uwuWithoutCuteFace(string) : string;
    }
}
