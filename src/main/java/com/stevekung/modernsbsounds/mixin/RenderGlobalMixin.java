package com.stevekung.modernsbsounds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.stevekung.modernsbsounds.ModernSkyblockSounds;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.audio.SoundHandler;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;

@Mixin(RenderGlobal.class)
public abstract class RenderGlobalMixin
{
    @Redirect(method = "playAuxSFX", at = @At(value = "INVOKE", target = "net/minecraft/client/audio/SoundHandler.playSound(Lnet/minecraft/client/audio/ISound;)V"))
    private void playSound(SoundHandler soundHandler, ISound sound, EntityPlayer player, int sfxType, BlockPos blockPosIn, int meta)
    {
        Block block = Block.getBlockById(meta & 4095);
        IBlockState state = block.getStateFromMeta(meta >> 12 & 255);

        if (ModernSkyblockSounds.isNetherBricksSlab(state))
        {
            soundHandler.playSound(new PositionedSoundRecord(new ResourceLocation(ModernSkyblockSounds.NETHER_BRICKS.getBreakSound()), (ModernSkyblockSounds.NETHER_BRICKS.getVolume() + 1.0F) / 2.0F, ModernSkyblockSounds.NETHER_BRICKS.getFrequency() * 0.8F, blockPosIn.getX() + 0.5F, blockPosIn.getY() + 0.5F, blockPosIn.getZ() + 0.5F));
        }
        else
        {
            soundHandler.playSound(sound);
        }
    }
}