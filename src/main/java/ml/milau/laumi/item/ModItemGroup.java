package ml.milau.laumi.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroup {
    //item group is abstract class, must define the override
    public static final ItemGroup LAUMI_GROUP = new ItemGroup("laumiModTab") {
        @Override
        public ItemStack createIcon() {
            return new ItemStack(ModItems.GARNET.get());
        }
    };
}
