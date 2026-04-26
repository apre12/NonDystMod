package me.hakotsuki2003.nondyst.nondystmod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class PrintModel<T extends Entity> extends EntityModel<T> {
    private final ModelPart root;

    public PrintModel(ModelPart root) {
        this.root = root;
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition group = partdefinition.addOrReplaceChild("group", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        group.addOrReplaceChild("leg1", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -6.0F, -4.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        group.addOrReplaceChild("leg2", CubeListBuilder.create().texOffs(0, 6).addBox(2.0F, -6.0F, -4.0F, 2.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        
        // Body base
        group.addOrReplaceChild("body_base", CubeListBuilder.create().texOffs(8, 0).addBox(-8.0F, -23.3F, -3.0F, 16.0F, 0.3F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        group.addOrReplaceChild("body_main", CubeListBuilder.create().texOffs(0, 0).addBox(-7.6F, -23.0F, -2.7F, 15.4F, 4.1F, 10.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        group.addOrReplaceChild("body_back", CubeListBuilder.create().texOffs(0, 11).addBox(-8.0F, -23.0F, 8.0F, 16.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        
        // Layers
        group.addOrReplaceChild("layer1", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -19.0F, -3.0F, 16.0F, 0.4F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        group.addOrReplaceChild("layer2", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -20.0F, -3.0F, 16.0F, 0.4F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        group.addOrReplaceChild("layer3", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -21.0F, -3.0F, 16.0F, 0.4F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        group.addOrReplaceChild("layer4", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -22.0F, -3.0F, 16.0F, 0.4F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        group.addOrReplaceChild("layer5", CubeListBuilder.create().texOffs(0, 0).addBox(-8.0F, -23.0F, -3.0F, 16.0F, 0.4F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
