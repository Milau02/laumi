package ml.milau.laumi.block;

import ml.milau.laumi.Laumi;
import ml.milau.laumi.block.custom.SoulGate;
import ml.milau.laumi.item.ModItemGroup;
import ml.milau.laumi.item.ModItems;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS
            = DeferredRegister.create(ForgeRegistries.BLOCKS, Laumi.MOD_ID);
    //List of blocks being added to mod
    public static final RegistryObject<Block> GARNET_ORE = registerBlock("garnet_ore",
            ()-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f)));
    public static final RegistryObject<Block>  GARNET_CLUSTER = registerBlock("garnet_cluster",
            ()-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f)));
    public static final RegistryObject<Block> SOUL_GATE = registerBlock("soul_gate",
            () -> new SoulGate(AbstractBlock.Properties.create(Material.SAND, MaterialColor.BLUE).harvestTool(ToolType.SHOVEL).hardnessAndResistance(0.5f).sound(SoundType.SOUL_SAND)));
    //End of blocks being added to mod


    //helper methods to register a block and then also register it as a block-item
    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn);
        return toReturn;
    }
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block){
        ModItems.ITEMS.register(name,() -> new BlockItem(block.get(),
                new Item.Properties().group(ModItemGroup.LAUMI_GROUP)));
    }

    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
