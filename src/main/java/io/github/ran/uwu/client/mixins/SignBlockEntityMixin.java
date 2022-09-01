package io.github.ran.uwu.client.mixins;

import io.github.ran.uwu.client.Uwuifier;
import io.github.ran.uwu.client.config.UwuConfig;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = SignBlockEntity.class, priority = -2)
public abstract class SignBlockEntityMixin {
    @Shadow protected abstract Text[] getTexts(boolean filtered);

    @Inject(method = "getTextOnRow", at = @At("HEAD"), cancellable = true)
    private void onGetTextOnRow(int row, boolean filtered, CallbackInfoReturnable<Text> cir) {
        cir.setReturnValue(UwuConfig.uwuifySigns ? uwufiedText(this.getTexts(filtered)[row]) : this.getTexts(filtered)[row]);
    }

    @Unique
    private Text uwufiedText(Text text) {
        return Text.literal(Uwuifier.uwuWithoutCuteFace(text.getString())).setStyle(text.getStyle());
    }
}
