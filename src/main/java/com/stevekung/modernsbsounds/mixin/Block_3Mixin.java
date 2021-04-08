package com.stevekung.modernsbsounds.mixin;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.block.Block;

@Mixin(targets = "net.minecraft.block.Block$3")
public class Block_3Mixin extends Block.SoundType
{
    Block_3Mixin()
    {
        super(null, 0, 0);
    }

    @Override
    public String getStepSound()
    {
        return "step.stone";
    }
}