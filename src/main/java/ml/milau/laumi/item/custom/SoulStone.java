package ml.milau.laumi.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Objects;

public class SoulStone extends Item {
    ArrayList<Block> transmuteOrder = new ArrayList<Block>();
    public SoulStone(Properties properties) {
        super(properties);
        transmuteOrder.add(Blocks.DIRT);
        transmuteOrder.add(Blocks.COBBLESTONE);
        transmuteOrder.add(Blocks.STONE);
        transmuteOrder.add(Blocks.ANDESITE);
        transmuteOrder.add(Blocks.GRANITE);
        transmuteOrder.add(Blocks.DIORITE);
        transmuteOrder.add(Blocks.GRAVEL);
        transmuteOrder.add(Blocks.SAND);
    }
    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World theWorld = context.getWorld();
        if(!theWorld.isRemote){
            //Initialize certain variables to be used, make sure player is not null
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState blockClicked = theWorld.getBlockState(context.getPos());
            //Method that does the advanced action
            transmuteBlock(blockClicked, context, playerEntity);

            stack.damageItem(1, playerEntity,player -> player.sendBreakAnimation(context.getHand()));
        }
        return super.onItemUseFirst(stack, context);
    }

    private void transmuteBlock(BlockState blockClicked, ItemUseContext context, PlayerEntity playerEntity) {
        Block transmuteInto = blockChoice(blockClicked);
        if(transmuteInto == Blocks.AIR){ //the base return from block Choice, when it's not in the transmute order
            return;
        }
        else{
            replaceBlock(context.getWorld(),context.getPos(),transmuteInto);
        }
    }
    private Block blockChoice(BlockState blockClicked){
        Block b = blockClicked.getBlock();
        if(b == Blocks.SAND){
            return Blocks.DIRT;
        }
        else if(transmuteOrder.contains(b)){
           int currentIdx = transmuteOrder.indexOf(b);
           return transmuteOrder.get(currentIdx + 1);
        }
        return Blocks.AIR;
    }

    public static void replaceBlock(World theWorld, BlockPos pos, Block toReplace){
        BlockState bState = toReplace.getDefaultState();
        theWorld.setBlockState(pos,bState);
    }
}