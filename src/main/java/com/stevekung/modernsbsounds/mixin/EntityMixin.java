package com.stevekung.modernsbsounds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.stevekung.modernsbsounds.ModernSkyblockSounds;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.BlockPos;

@Mixin(Entity.class)
public class EntityMixin
{
    @Redirect(method = "playStepSound", at = @At(value = "FIELD", target = "net/minecraft/block/Block.stepSound:Lnet/minecraft/block/Block$SoundType;", ordinal = 0), require = 1)
    private Block.SoundType setNewStepSound(Block block, BlockPos pos, Block blockIn)
    {
        IBlockState state = ((Entity)(Object)this).worldObj.getBlockState(pos);

        if (ModernSkyblockSounds.isNetherBricksSlab(state))
        {
            return ModernSkyblockSounds.NETHER_BRICKS;
        }
        return block.stepSound;
    }
}