package com.stevekung.modernsbsounds;

import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModernSkyblockSounds.MOD_ID, name = ModernSkyblockSounds.NAME, version = ModernSkyblockSounds.VERSION, clientSideOnly = true)
public class ModernSkyblockSounds
{
    protected static final String NAME = "ModernSkyblockSounds";
    protected static final String MOD_ID = "modernsbsounds";
    public static final String MOD_ASSETS = MOD_ID + ":";
    private static final int MAJOR_VERSION = 1;
    private static final int MINOR_VERSION = 0;
    private static final int BUILD_VERSION = 0;
    protected static final String VERSION = ModernSkyblockSounds.MAJOR_VERSION + "." + ModernSkyblockSounds.MINOR_VERSION + "." + ModernSkyblockSounds.BUILD_VERSION;

    private static final Block.SoundType SOUL_SAND = new ModSoundType("soul_sand", 1.0F, 1.0F);
    public static final Block.SoundType NETHER_BRICKS = new ModSoundType("nether_bricks", 1.0F, 1.0F);
    private static final Block.SoundType NETHERRACK = new ModSoundType("netherrack", 1.0F, 1.0F);
    private static final Block.SoundType NETHER_ORE = new ModSoundType("nether_ore", 1.0F, 1.0F);
    private static final Block.SoundType CROP = new ModSoundType("crop", 1.0F, 1.0F)
    {
        @Override
        public String getStepSound()
        {
            return "step.grass";
        }

        @Override
        public String getPlaceSound()
        {
            return MOD_ASSETS + "item." + this.soundName + ".plant";
        }
    };
    private static final Block.SoundType NETHER_WART = new ModSoundType("nether_wart", 1.0F, 1.0F)
    {
        @Override
        public String getStepSound()
        {
            return "step.stone";
        }

        @Override
        public String getPlaceSound()
        {
            return MOD_ASSETS + "item." + this.soundName + ".plant";
        }
    };

    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
        ModernSkyblockSounds.init(event.getModMetadata());
    }

    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        Blocks.hopper.setStepSound(Block.soundTypeMetal);
        Blocks.noteblock.setStepSound(Block.soundTypeWood);
        Blocks.jukebox.setStepSound(Block.soundTypeWood);
        Blocks.nether_brick.setStepSound(NETHER_BRICKS);
        Blocks.nether_brick_fence.setStepSound(NETHER_BRICKS);
        Blocks.nether_brick_stairs.setStepSound(NETHER_BRICKS);
        Blocks.netherrack.setStepSound(NETHERRACK);
        Blocks.quartz_ore.setStepSound(NETHER_ORE);
        Blocks.carrots.setStepSound(CROP);
        Blocks.wheat.setStepSound(CROP);
        Blocks.potatoes.setStepSound(CROP);
        Blocks.nether_wart.setStepSound(NETHER_WART);
        Blocks.soul_sand.setStepSound(SOUL_SAND);
    }

    public static boolean isNetherBricksSlab(IBlockState state)
    {
        return state.getBlock() == Blocks.stone_slab && state.getValue(BlockStoneSlab.VARIANT) == BlockStoneSlab.EnumType.NETHERBRICK || state.getBlock() == Blocks.double_stone_slab && state.getValue(BlockStoneSlab.VARIANT) == BlockStoneSlab.EnumType.NETHERBRICK;
    }

    private static void init(ModMetadata info)
    {
        info.autogenerated = false;
        info.modId = ModernSkyblockSounds.MOD_ID;
        info.name = ModernSkyblockSounds.NAME;
        info.version = ModernSkyblockSounds.VERSION;
        info.description = "Adds new sounds from modern version of the Minecraft!";
        info.authorList = Arrays.asList("SteveKunG");
    }

    static class ModSoundType extends Block.SoundType
    {
        ModSoundType(String name, float volume, float frequency)
        {
            super(name, volume, frequency);
        }

        @Override
        public String getBreakSound()
        {
            return MOD_ASSETS + "block." + this.soundName + ".break";
        }

        @Override
        public String getStepSound()
        {
            return MOD_ASSETS + "block." + this.soundName + ".step";
        }
    }
}