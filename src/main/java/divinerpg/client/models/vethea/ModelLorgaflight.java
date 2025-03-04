package divinerpg.client.models.vethea;

import com.google.common.collect.ImmutableList;

import net.minecraft.client.renderer.entity.model.SegmentedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelLorgaflight<T extends Entity> extends SegmentedModel<T>
{
    //fields
    ModelRenderer head;
    ModelRenderer body;
    ModelRenderer rightarm;
    ModelRenderer leftarm;
    ModelRenderer body2;
    ModelRenderer body3;
    ModelRenderer body4;
    ModelRenderer body5;
    ModelRenderer body6;
    ModelRenderer body7;
    ModelRenderer body8;

    public ModelLorgaflight()
    {
        texWidth = 64;
        texHeight = 32;

        head = new ModelRenderer(this, 0, 0);
        head.addBox(-4F, -8F, -4F, 8, 8, 8);
        head.setPos(0F, -3F, 0F);
        head.setTexSize(64, 32);
        head.mirror = true;
        head.y = 1;
        setRotation(head, 0F, 0F, 0F);
        body = new ModelRenderer(this, 21, 16);
        body.addBox(-1F, 3F, 5F, 10, 4, 4);
        body.setPos(-4F, 7F, 0F);
        body.setTexSize(64, 32);
        body.mirror = true;
        body.y = 1;
        setRotation(body, 0.6981317F, 0F, 0F);
        rightarm = new ModelRenderer(this, 40, 16);
        rightarm.addBox(-3F, -2F, -2F, 2, 12, 4);
        rightarm.setPos(-5F, 1F, 3F);
        rightarm.setTexSize(64, 32);
        rightarm.mirror = true;
        rightarm.y = 1;
        setRotation(rightarm, 0F, 0F, 1.047198F);
        leftarm = new ModelRenderer(this, 40, 16);
        leftarm.addBox(-1F, -2F, -2F, 2, 12, 4);
        leftarm.setPos(7F, -1F, 3F);
        leftarm.setTexSize(64, 32);
        leftarm.mirror = true;
        leftarm.y = 1;
        setRotation(leftarm, 0F, 0F, -1.047198F);
        body2 = new ModelRenderer(this, 26, 0);
        body2.addBox(-1F, 0F, -2F, 2, 2, 2);
        body2.setPos(2F, -3F, 1F);
        body2.setTexSize(64, 32);
        body2.mirror = true;
        body2.y = 1;
        setRotation(body2, 0.6981317F, 0F, 0F);
        body2.mirror = false;
        body3 = new ModelRenderer(this, 55, 0);
        body3.addBox(-1F, 0F, -2F, 2, 12, 2);
        body3.setPos(0F, -4F, 1F);
        body3.setTexSize(64, 32);
        body3.mirror = true;
        body3.y = 1;
        setRotation(body3, 0.6981317F, 0F, 0F);
        body4 = new ModelRenderer(this, 26, 0);
        body4.addBox(-1F, 0F, -2F, 2, 2, 2);
        body4.setPos(-2F, -3F, 1F);
        body4.setTexSize(64, 32);
        body4.mirror = true;
        body4.y = 1;
        setRotation(body4, 0.6981317F, 0F, 0F);
        body5 = new ModelRenderer(this, 36, 0);
        body5.addBox(-1F, 0F, 1F, 5, 2, 2);
        body5.setPos(-5F, 1F, 1F);
        body5.setTexSize(64, 32);
        body5.mirror = true;
        body5.y = 1;
        setRotation(body5, 0.6981317F, 0F, 0F);
        body6 = new ModelRenderer(this, 36, 0);
        body6.addBox(-1F, 0F, 1F, 5, 2, 2);
        body6.setPos(2F, 1F, 1F);
        body6.setTexSize(64, 32);
        body6.mirror = true;
        body6.y = 1;
        setRotation(body6, 0.6981317F, 0F, 0F);
        body7 = new ModelRenderer(this, 26, 0);
        body7.addBox(-1F, 0F, 4F, 2, 2, 2);
        body7.setPos(-2F, 5F, 1F);
        body7.setTexSize(64, 32);
        body7.mirror = true;
        body7.y = 1;
        setRotation(body7, 0.6981317F, 0F, 0F);
        body8 = new ModelRenderer(this, 26, 0);
        body8.addBox(-1F, 0F, 4F, 2, 2, 2);
        body8.setPos(2F, 5F, 1F);
        body8.setTexSize(64, 32);
        body8.mirror = true;
        body8.y = 1;
        setRotation(body8, 0.6981317F, 0F, 0F);
        body8.mirror = false;
    }

    @Override
    public Iterable<ModelRenderer> parts() {
        return ImmutableList.of(head, body, rightarm, leftarm, body2, body3, body4, body5, body6, body7, body8);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z)
    {
        model.xRot = x;
        model.yRot = y;
        model.zRot = z;
    }

    @Override
	public void setupAnim(T entityIn, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }
}