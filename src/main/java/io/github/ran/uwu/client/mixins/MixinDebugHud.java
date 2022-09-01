package io.github.ran.uwu.client.mixins;

import io.github.ran.uwu.client.Uwuifier;
import io.github.ran.uwu.client.config.UwuConfig;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;
import java.util.List;

@Mixin(value = DebugHud.class, priority = -2)
public abstract class MixinDebugHud {
    @Inject(method = "getRightText", at = @At("RETURN"), cancellable = true)
    private void uwuifyRightText(CallbackInfoReturnable<List<String>> cir) {
        if (UwuConfig.uwuifyMinecraft) {
            List<String> uwuifiedMessages = new ArrayList<>();
            cir.getReturnValue().forEach(message -> uwuifiedMessages.add(Uwuifier.uwuWithoutCuteFace(message)));
            cir.setReturnValue(uwuifiedMessages);
        }
    }

    @Inject(method = "getLeftText", at = @At("RETURN"), cancellable = true)
    private void uwuifyLeftText(CallbackInfoReturnable<List<String>> cir) {
        if (UwuConfig.uwuifyMinecraft) {
            List<String> uwuifiedMessages = new ArrayList<>();
            cir.getReturnValue().forEach(message -> uwuifiedMessages.add(Uwuifier.uwuWithoutCuteFace(message)));
            cir.setReturnValue(uwuifiedMessages);
        }
    }
}
