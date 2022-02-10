package ml.milau.laumi.block.custom;

import ml.milau.laumi.event.SoulGateHandler;
import ml.milau.laumi.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.Property;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.GameType;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;

public class SoulGate extends Block {
    public static final Property<Boolean> IS_ASTRAL = BooleanProperty.create("astral");
    public SoulGate(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(IS_ASTRAL,false));
    }
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block,BlockState> builder){
        builder.add(IS_ASTRAL);
        super.fillStateContainer(builder);
    }
    @SuppressWarnings("deprecation")
    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(!worldIn.isRemote()){
            if(handIn == Hand.MAIN_HAND){
                if(player.getHeldItem(Hand.MAIN_HAND).getItem() == ModItems.SOULSTONE.get()){
                    becomeAstral(worldIn,pos,player);
                    player.getHeldItem(Hand.MAIN_HAND).damageItem(16, player, player1 -> player.sendBreakAnimation(player.getActiveHand()));
                }
            }
        }
        return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
    }
    private void becomeAstral(World worldIn, BlockPos pos, PlayerEntity player){
        worldIn.setBlockState(pos,this.getDefaultState().with(IS_ASTRAL,true));
        playerEthereal(player);
        waitHandler(10,worldIn,pos,player);
    }
    public void waitHandler(int seconds,World worldIn, BlockPos pos, PlayerEntity player){
        SoulGateHandler handles = new SoulGateHandler();
        MinecraftForge.EVENT_BUS.register(handles);
        handles.beginCount(seconds,worldIn,pos,player,this.getDefaultState());
    }
    public static void playerEthereal(PlayerEntity player){
        player.setGameType(GameType.SPECTATOR);
    }
    public static void endAstral(World worldIn, BlockPos pos, PlayerEntity player, BlockState bState){
        worldIn.setBlockState(pos,bState.with(IS_ASTRAL,false));
        player.setGameType(GameType.SURVIVAL);
        player.attemptTeleport(pos.getX() + 0.5,pos.getY() + 1,pos.getZ() + 0.5,true);
    }
}
