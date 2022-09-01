package io.github.ran.uwu.client.mixins;

import io.github.ran.uwu.client.Uwuifier;
import io.github.ran.uwu.client.config.UwuConfig;
import net.minecraft.scoreboard.ScoreboardPlayerScore;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ScoreboardPlayerScore.class)
public abstract class ScoreboardPlayerScoreMixin {
    @Shadow @Final private String playerName;

    @Inject(method = "getPlayerName", at = @At("HEAD"), cancellable = true)
    private void getDisplayName(CallbackInfoReturnable<String> cir) {
        cir.setReturnValue(UwuConfig.uwuifyMinecraft ? Uwuifier.uwuWithoutCuteFace(this.playerName) : this.playerName);
    }
}
