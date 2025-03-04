package divinerpg.items.arcana;

import divinerpg.*;
import divinerpg.capability.*;
import divinerpg.items.base.*;
import divinerpg.util.*;
import net.minecraft.client.util.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.text.*;
import net.minecraft.world.*;

import javax.annotation.*;
import java.util.*;

public class ItemArcanaPotion extends ItemMod {

    protected int amountToAdd;

    public ItemArcanaPotion(String name, int amountToAdd) {
    	super(name, new Item.Properties().tab(DivineRPG.tabs.utilities));
        this.amountToAdd = amountToAdd;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(LocalizeUtils.arcanaRegen(amountToAdd));
    }


    @Override
    public ItemStack finishUsingItem(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        PlayerEntity player = (PlayerEntity) entityLiving;
        if (player != null) {
            if (!player.isCreative()) {
                stack.shrink(1);
            }
            Arcana arcana = player.getCapability(ArcanaCapability.CAPABILITY_ARCANA).orElse(null);
            arcana.fill(player, amountToAdd);
        }

        return stack;
    }

    @Override
    public UseAction getUseAnimation(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getUseDuration(ItemStack par1ItemStack) {
        return 12;
    }

    @Override
    public ActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        player.startUsingItem(hand);

        Arcana arcana = player.getCapability(ArcanaCapability.CAPABILITY_ARCANA).orElse(null);

        ActionResultType result = arcana.getArcana() < arcana.getMaxArcana() ? ActionResultType.SUCCESS :
                ActionResultType.FAIL;

        return new ActionResult<>(result, player.getItemInHand(hand));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}