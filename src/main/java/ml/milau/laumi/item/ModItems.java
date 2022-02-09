package ml.milau.laumi.item;

import ml.milau.laumi.Laumi;
import ml.milau.laumi.item.custom.SoulStone;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
    //List that keeps tracks of all items blocks etc, so forge can register it
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Laumi.MOD_ID);

    //registering a new item and creating it at the same time using a "supplier" that returns a new Item
    //List of items being added to mod
    public static final RegistryObject<Item> GARNET = ITEMS.register("garnet",
            () -> new Item(new Item.Properties().group(ModItemGroup.LAUMI_GROUP)));
    public static final RegistryObject<Item> SOULSTONE = ITEMS.register("soulstone",
            () -> new SoulStone(new Item.Properties().group(ModItemGroup.LAUMI_GROUP).maxDamage(64)));
    //End of items being added to mod

    //Register method for ModItems and deferred register will be registered in our event bus, in Laumi class
    public static void register(IEventBus eventBus){
        ITEMS.register(eventBus);
    }
}
