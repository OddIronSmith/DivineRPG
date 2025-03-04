package divinerpg.client.models.iceika;

import com.google.common.collect.*;
import net.minecraft.client.renderer.entity.model.*;
import net.minecraft.client.renderer.model.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraftforge.api.distmarker.*;

@OnlyIn(Dist.CLIENT)
public class ModelFractite<T extends Entity> extends SegmentedModel<T> {
    ModelRenderer frostBody;
    ModelRenderer shard1;
    ModelRenderer shard2;
    ModelRenderer shard7;
    ModelRenderer shard8;
    ModelRenderer shard4;
    ModelRenderer shard3;
    ModelRenderer shard5;
    ModelRenderer shard6;

    public ModelFractite()
    {
        texWidth = 64;
        texHeight = 32;

        frostBody = new ModelRenderer(this, 0, 11);
        frostBody.addBox(0F, 0F, 0F, 8, 8, 6);
        frostBody.setPos(-4F, 5F, -4F);
        frostBody.setTexSize(64, 32);
        frostBody.mirror = true;
        setRotation(frostBody, 0F, 0F, 0F);
        shard1 = new ModelRenderer(this, 0, 0);
        shard1.addBox(8F, 0F, 0F, 9, 1, 2);
        shard1.setPos(1F, 8F, -2F);
        shard1.setTexSize(64, 32);
        shard1.mirror = true;
        setRotation(shard1, 0F, 0F, -0.7853982F);
        shard2 = new ModelRenderer(this, 0, 0);
        shard2.addBox(-19F, 0F, 0F, 9, 1, 2);
        shard2.setPos(0F, 9F, -2F);
        shard2.setTexSize(64, 32);
        shard2.mirror = true;
        setRotation(shard2, 0F, 0F, -0.7853982F);
        shard7 = new ModelRenderer(this, 0, 0);
        shard7.addBox(10F, 0F, 0F, 10, 1, 2);
        shard7.setPos(0F, 9F, -2F);
        shard7.setTexSize(64, 32);
        shard7.mirror = true;
        setRotation(shard7, 0F, 0F, 0.7853982F);
        shard8 = new ModelRenderer(this, 0, 0);
        shard8.addBox(-19F, 0F, 0F, 10, 1, 2);
        shard8.setPos(-1F, 9F, -2F);
        shard8.setTexSize(64, 32);
        shard8.mirror = true;
        setRotation(shard8, 0F, 0F, 0.7853982F);
        shard4 = new ModelRenderer(this, 0, 0);
        shard4.addBox(-6F, -8F, 0F, 15, 1, 2);
        shard4.setPos(0F, 8F, -2F);
        shard4.setTexSize(64, 32);
        shard4.mirror = true;
        setRotation(shard4, 0F, 0F, 1.570796F);
        shard3 = new ModelRenderer(this, 0, 0);
        shard3.addBox(-7F, 7F, 0F, 15, 1, 2);
        shard3.setPos(0F, 9F, -2F);
        shard3.setTexSize(64, 32);
        shard3.mirror = true;
        setRotation(shard3, 0F, 0F, 1.570796F);
        shard5 = new ModelRenderer(this, 0, 0);
        shard5.addBox(-8F, -7F, 0F, 14, 1, 2);
        shard5.setPos(1F, 9F, -2F);
        shard5.setTexSize(64, 32);
        shard5.mirror = true;
        setRotation(shard5, 0F, 0F, 0F);
        shard6 = new ModelRenderer(this, 0, 0);
        shard6.addBox(-7F, 7F, 0F, 14, 1, 2);
        shard6.setPos(0F, 9F, -2F);
        shard6.setTexSize(64, 32);
        shard6.mirror = true;
        setRotation(shard6, 0F, 0F, 0F);
    }

    @Override
    public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.shard1.xRot = MathHelper.cos(ageInTicks * 0.1F) * (float)Math.PI;
        this.shard2.xRot = MathHelper.cos(ageInTicks * 0.1F) * (float)Math.PI;
        this.shard3.xRot = MathHelper.cos(ageInTicks * 0.1F) * (float)Math.PI;
        this.shard4.xRot = MathHelper.cos(ageInTicks * 0.1F) * (float)Math.PI;
        this.shard5.xRot = MathHelper.cos(ageInTicks * 0.1F) * (float)Math.PI;
        this.shard6.xRot = MathHelper.cos(ageInTicks * 0.1F) * (float)Math.PI;
        this.shard7.xRot = MathHelper.cos(ageInTicks * 0.1F) * (float)Math.PI;
        this.shard8.xRot = MathHelper.cos(ageInTicks * 0.1F) * (float)Math.PI;
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(frostBody, shard1, shard2, shard7, shard8, shard4, shard3, shard5, shard6);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }


}
