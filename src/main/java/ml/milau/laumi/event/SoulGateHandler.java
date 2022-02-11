package ml.milau.laumi.event;


import ml.milau.laumi.block.custom.SoulGate;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SoulGateHandler {
    World theWorld;
    BlockPos thePos;
    PlayerEntity thePlayer;
    BlockState theBState;
    int ticks = 0;
    int maxticks = 1;
    boolean count = false;

    @SubscribeEvent
    public void onTick(final TickEvent.ServerTickEvent event){
        if(ticks >= maxticks){
            this.count = false;
            this.ticks = 0;
            SoulGate.endAstral(theWorld,thePos,thePlayer,theBState);
        }
        if(this.count){
            this.ticks++;
        }
    }
    public void beginCount(int seconds, World worldIn, BlockPos pos, PlayerEntity player, BlockState bState) {
        this.maxticks = (seconds * 20 * 2);
        this.theWorld = worldIn;
        this.thePos = pos;
        this.thePlayer = player;
        this.theBState = bState;
        this.count = true;
    }
}
