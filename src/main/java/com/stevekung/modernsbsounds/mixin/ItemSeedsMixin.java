package com.stevekung.modernsbsounds.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

@Mixin(ItemSeeds.class)
public class ItemSeedsMixin
{
    @Shadow
    private Block crops;

    @Inject(method = "onItemUse", at = @At(value = "INVOKE", target = "net/minecraft/world/World.setBlockState(Lnet/minecraft/util/BlockPos;Lnet/minecraft/block/state/IBlockState;)Z"))
    private void addPlaceSound(ItemStack stack, EntityPlayer player, World world, BlockPos pos, EnumFacing side, float hitX, float hitY, float hitZ, CallbackInfoReturnable info)
    {
        world.playSound(pos.getX(), pos.getY(), pos.getZ(), this.crops.stepSound.getPlaceSound(), (this.crops.stepSound.getVolume() + 1.0F) / 2.0F, this.crops.stepSound.getFrequency() * 0.8F, false);
    }
}