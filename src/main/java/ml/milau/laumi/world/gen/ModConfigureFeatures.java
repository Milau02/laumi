package ml.milau.laumi.world.gen;

import ml.milau.laumi.block.ModBlocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.DarkOakFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.DarkOakTrunkPlacer;

import java.util.OptionalInt;

public class ModConfigureFeatures {

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> SOULTREE =
            register("soultree", Feature.TREE.withConfiguration((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ModBlocks.SOULTREE_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(ModBlocks.SOULTREE_LEAVES.get().getDefaultState()),
                            new DarkOakFoliagePlacer(FeatureSpread.create(0), FeatureSpread.create(0)),
                            new DarkOakTrunkPlacer(6, 2, 1),
                            new ThreeLayerFeature(1, 1, 0, 1, 2, OptionalInt.empty())))
                    .setMaxWaterDepth(Integer.MAX_VALUE).setHeightmap(Heightmap.Type.MOTION_BLOCKING).setIgnoreVines().build()));

    public static final ConfiguredFeature<BaseTreeFeatureConfig, ?> ASTRAL =
            register("astral", Feature.TREE.withConfiguration((
                    new BaseTreeFeatureConfig.Builder(
                            new SimpleBlockStateProvider(ModBlocks.ASTRAL_LOG.get().getDefaultState()),
                            new SimpleBlockStateProvider(ModBlocks.ASTRAL_LEAVES.get().getDefaultState()),
                            new DarkOakFoliagePlacer(FeatureSpread.create(0), FeatureSpread.create(0)),
                            new DarkOakTrunkPlacer(6, 2, 1),
                            new ThreeLayerFeature(1, 1, 0, 1, 2, OptionalInt.empty())))
                    .setMaxWaterDepth(Integer.MAX_VALUE).setHeightmap(Heightmap.Type.MOTION_BLOCKING).setIgnoreVines().build()));



    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, key, configuredFeature);
    }
}
