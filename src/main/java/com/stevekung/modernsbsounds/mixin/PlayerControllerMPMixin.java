package com.stevekung.modernsbsounds.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.stevekung.modernsbsounds.ModernSkyblockSounds;

import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

@Mixin(PlayerControllerMP.class)
public abstract class PlayerControllerMPMixin
{
    @Shadow
    @Final
    private Minecraft mc;

    @Redirect(method = "onPlayerDamageBlock", at = @At(value = "INVOKE", target = "net/minecraft/client/audio/SoundHandler.playSound(Lnet/minecraft/client/audio/ISound;)V"))
    private void onPlayerDamageBlock(SoundHandler soundHandler, ISound sound, BlockPos posBlock, EnumFacing directionFacing)
    {
        if (ModernSkyblockSounds.isNetherBricksSlab(this.mc.theWorld.getBlockState(posBlock)))
        {
            soundHandler.playSound(new PositionedSoundRecord(new ResourceLocation(ModernSkyblockSounds.NETHER_BRICKS.getStepSound()), (ModernSkyblockSounds.NETHER_BRICKS.getVolume() + 1.0F) / 8.0F, ModernSkyblockSounds.NETHER_BRICKS.getFrequency() * 0.5F, posBlock.getX() + 0.5F, posBlock.getY() + 0.5F, posBlock.getZ() + 0.5F));
        }
        else
        {
            soundHandler.playSound(sound);
        }
    }
}