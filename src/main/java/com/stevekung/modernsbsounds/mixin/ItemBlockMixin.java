package com.stevekung.modernsbsounds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.stevekung.modernsbsounds.ModernSkyblockSounds;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

@Mixin(ItemBlock.class)
public abstract class ItemBlockMixin
{
    @Redirect(method = "onItemUse", at = @At(value = "INVOKE", target = "net/minecraft/world/World.playSoundEffect(DDDLjava/lang/String;FF)V"))
    private void playSoundEffect(World world, double x, double y, double z, String soundName, float volume, float pitch, ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (ModernSkyblockSounds.isNetherBricksSlab(worldIn.getBlockState(pos)))
        {
            world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, ModernSkyblockSounds.NETHER_BRICKS.getPlaceSound(), (ModernSkyblockSounds.NETHER_BRICKS.getVolume() + 1.0F) / 2.0F, ModernSkyblockSounds.NETHER_BRICKS.getFrequency() * 0.8F);
        }
        else
        {
            world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, ((ItemBlock)(Object)this).block.stepSound.getPlaceSound(), (((ItemBlock)(Object)this).block.stepSound.getVolume() + 1.0F) / 2.0F, ((ItemBlock)(Object)this).block.stepSound.getFrequency() * 0.8F);
        }
    }
}