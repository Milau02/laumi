package ml.milau.laumi.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
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
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {

        if(Screen.hasShiftDown()){
            tooltip.add(new TranslationTextComponent("tooltip.laumi.soulstone_shift"));
        }
        else{
            tooltip.add(new TranslationTextComponent("tooltip.laumi.soulstone"));
        }
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World theWorld = context.getWorld();
        if(!theWorld.isRemote){
            //Initialize certain variables to be used, make sure player is not null
            PlayerEntity playerEntity = Objects.requireNonNull(context.getPlayer());
            BlockState blockClicked = theWorld.getBlockState(context.getPos());
            //Method that does the advanced action
            if(transmuteBlock(blockClicked, context, playerEntity)){
                stack.damageItem(1, playerEntity,player -> player.sendBreakAnimation(context.getHand()));
            }
        }
        return super.onItemUseFirst(stack, context);
    }

    private boolean transmuteBlock(BlockState blockClicked, ItemUseContext context, PlayerEntity playerEntity) {
        Block transmuteInto = blockChoice(blockClicked);
        if(transmuteInto == Blocks.AIR){ //the base return from block Choice, when it's not in the transmute order
            return false;
        }
        else{
            replaceBlock(context.getWorld(),context.getPos(),transmuteInto);
            return true;
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