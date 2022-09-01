package io.github.ran.uwu.client.mixins;

import io.github.ran.uwu.client.Uwuifier;
import io.github.ran.uwu.client.config.UwuConfig;
import net.minecraft.scoreboard.ScoreboardObjective;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScoreboardObjective.class)
public abstract class ScoreboardObjectiveMixin {
    @Shadow private Text displayName;

    @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true)
    private void getDisplayName(CallbackInfoReturnable<Text> cir) {
        cir.setReturnValue(UwuConfig.uwuifyMinecraft ? uwufiedText(this.displayName) : this.displayName);
    }

    @Unique
    private Text uwufiedText(Text text) {
        return Text.literal(Uwuifier.uwuWithoutCuteFace(text.getString())).setStyle(text.getStyle());
    }
}
