package com.stevekung.modernsbsounds.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import com.stevekung.modernsbsounds.ModernSkyblockSounds;

import net.minecraft.block.BlockSlab;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

@Mixin(ItemSlab.class)
public abstract class ItemSlabMixin
{
    @Shadow
    @Final
    private BlockSlab doubleSlab;

    @Redirect(method = "onItemUse", at = @At(value = "INVOKE", target = "net/minecraft/world/World.playSoundEffect(DDDLjava/lang/String;FF)V"))
    private void playSoundEffect1(World world, double x, double y, double z, String soundName, float volume, float pitch, ItemStack stack, EntityPlayer playerIn, World worldIn, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ)
    {
        if (ModernSkyblockSounds.isNetherBricksSlab(worldIn.getBlockState(pos)))
        {
            world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, ModernSkyblockSounds.NETHER_BRICKS.getPlaceSound(), (ModernSkyblockSounds.NETHER_BRICKS.getVolume() + 1.0F) / 2.0F, ModernSkyblockSounds.NETHER_BRICKS.getFrequency() * 0.8F);
        }
        else
        {
            world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, this.doubleSlab.stepSound.getPlaceSound(), (this.doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, this.doubleSlab.stepSound.getFrequency() * 0.8F);
        }
    }

    @Redirect(method = "tryPlace", at = @At(value = "INVOKE", target = "net/minecraft/world/World.playSoundEffect(DDDLjava/lang/String;FF)V"))
    private void playSoundEffect2(World world, double x, double y, double z, String soundName, float volume, float pitch, ItemStack stack, World worldIn, BlockPos pos, Object variantInStack)
    {
        if (ModernSkyblockSounds.isNetherBricksSlab(worldIn.getBlockState(pos)))
        {
            world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, ModernSkyblockSounds.NETHER_BRICKS.getPlaceSound(), (ModernSkyblockSounds.NETHER_BRICKS.getVolume() + 1.0F) / 2.0F, ModernSkyblockSounds.NETHER_BRICKS.getFrequency() * 0.8F);
        }
        else
        {
            world.playSoundEffect(pos.getX() + 0.5F, pos.getY() + 0.5F, pos.getZ() + 0.5F, this.doubleSlab.stepSound.getPlaceSound(), (this.doubleSlab.stepSound.getVolume() + 1.0F) / 2.0F, this.doubleSlab.stepSound.getFrequency() * 0.8F);
        }
    }
}