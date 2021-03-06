package ml.milau.laumi.util;

import ml.milau.laumi.Laumi;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class LaumiTags {

    public static class Blocks{

        private static Tags.IOptionalNamedTag<Block> createTag(String name){
            return BlockTags.createOptional(new ResourceLocation(Laumi.MOD_ID,name));
        }

        private static Tags.IOptionalNamedTag<Block> createForgeTag(String name){
            return BlockTags.createOptional(new ResourceLocation("forge",name));
        }

    }

    public static class Items{

        public static final Tags.IOptionalNamedTag<Item> ASTRAL_LOG = createTag("astral_logs");
        public static final Tags.IOptionalNamedTag<Item> SOULTREE_LOG = createTag("soultree_logs");
        
        private static Tags.IOptionalNamedTag<Item> createTag(String name){
            return ItemTags.createOptional(new ResourceLocation(Laumi.MOD_ID,name));
        }

        private static Tags.IOptionalNamedTag<Item> createForgeTag(String name){
            return ItemTags.createOptional(new ResourceLocation("forge",name));
        }

    }
}
