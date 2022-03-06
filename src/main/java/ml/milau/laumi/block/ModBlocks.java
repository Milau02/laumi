package ml.milau.laumi.block;

import ml.milau.laumi.Laumi;
import ml.milau.laumi.block.custom.SoulGate;
import ml.milau.laumi.block.custom.trees.AstralTree;
import ml.milau.laumi.block.custom.trees.SoultreeTree;
import ml.milau.laumi.item.ModItemGroup;
import ml.milau.laumi.item.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.trees.OakTree;
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
    //Garnet Group
    public static final RegistryObject<Block> GARNET_ORE = registerBlock("garnet_ore",
            ()-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f)));
    public static final RegistryObject<Block>  GARNET_CLUSTER = registerBlock("garnet_cluster",
            ()-> new Block(AbstractBlock.Properties.create(Material.ROCK).harvestLevel(2).harvestTool(ToolType.PICKAXE).setRequiresTool().hardnessAndResistance(4f)));

    public static final RegistryObject<Block> SOUL_GATE = registerBlock("soul_gate",
            () -> new SoulGate(AbstractBlock.Properties.create(Material.ROCK, MaterialColor.RED).harvestTool(ToolType.PICKAXE).hardnessAndResistance(0.5f).sound(SoundType.LODESTONE)));

    //Astral Wood
    public static final RegistryObject<Block> ASTRAL_LEAVES = registerBlock("astral_leaves",
            ()-> new Block(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F,2F).tickRandomly().sound(SoundType.PLANT).notSolid()));
    public static final RegistryObject<Block> ASTRAL_SAPLING = registerBlock("astral_sapling",
            ()-> new SaplingBlock(new AstralTree(),AbstractBlock.Properties.from(Blocks.DARK_OAK_SAPLING)));
    public static final RegistryObject<Block> ASTRAL_LOG = registerBlock("astral_log",
            ()-> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> ASTRAL_WOOD = registerBlock("astral_wood",
            ()-> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_ASTRAL_LOG = registerBlock("stripped_astral_log",
            ()-> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_ASTRAL_WOOD = registerBlock("stripped_astral_wood",
            ()-> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> ASTRAL_PLANKS = registerBlock("astral_planks",
            ()-> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> ASTRAL_ROOT = registerBlock("astral_root",
            ()-> new Block(AbstractBlock.Properties.from(Blocks.DIRT)));
    public static final RegistryObject<Block> ASTRAL_WOOD_STAIRS =  registerBlock("astral_stairs",
            ()-> new StairsBlock(()->ASTRAL_PLANKS.get().getDefaultState(),
                    AbstractBlock.Properties.from(Blocks.OAK_STAIRS)));


    //Soul Wood
    public static final RegistryObject<Block> SOULTREE_LEAVES = registerBlock("soultree_leaves",
            ()-> new Block(AbstractBlock.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F,2F).tickRandomly().sound(SoundType.PLANT).notSolid()));
    public static final RegistryObject<Block> SOULTREE_SAPLING = registerBlock("soultree_sapling",
            ()-> new SaplingBlock(new SoultreeTree(),AbstractBlock.Properties.from(Blocks.DARK_OAK_SAPLING)));
    public static final RegistryObject<Block> SOULTREE_LOG = registerBlock("soultree_log",
            ()-> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_LOG)));
    public static final RegistryObject<Block> SOULTREE_WOOD = registerBlock("soultree_wood",
            ()-> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.OAK_WOOD)));
    public static final RegistryObject<Block> STRIPPED_SOULTREE_LOG = registerBlock("stripped_soultree_log",
            ()-> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_LOG)));
    public static final RegistryObject<Block> STRIPPED_SOULTREE_WOOD = registerBlock("stripped_soultree_wood",
            ()-> new RotatedPillarBlock(AbstractBlock.Properties.from(Blocks.STRIPPED_OAK_WOOD)));
    public static final RegistryObject<Block> SOULTREE_PLANKS = registerBlock("soultree_planks",
            ()-> new Block(AbstractBlock.Properties.from(Blocks.OAK_PLANKS)));
    public static final RegistryObject<Block> SOULTREE_ROOT = registerBlock("soultree_root",
            ()-> new Block(AbstractBlock.Properties.from(Blocks.DIRT)));
    public static final RegistryObject<Block> SOULTREE_WOOD_STAIRS =  registerBlock("soultree_stairs",
            ()-> new StairsBlock(()->SOULTREE_PLANKS.get().getDefaultState(),
                    AbstractBlock.Properties.from(Blocks.OAK_STAIRS)));

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
