package me.hakotsuki2003.nondyst.nondystmod;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ExternalSilicoShellModel<T extends ExternalSilicoShellEntity> extends EntityModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
			new ResourceLocation(Nondystmod.MODID, "external_silico_shell"), "main");
	private final ModelPart bone3;
	private final ModelPart bone;
	private final ModelPart bone2;
	private final ModelPart bone4;
	private final ModelPart bb_main;

	private int shellColor = 0xFFFFFF;

	public ExternalSilicoShellModel(ModelPart root) {
		this.bone3 = root.getChild("bone3");
		this.bone = root.getChild("bone");
		this.bone2 = root.getChild("bone2");
		this.bone4 = root.getChild("bone4");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bone3 = partdefinition.addOrReplaceChild("bone3",
				CubeListBuilder.create().texOffs(24, 22).addBox(-5.0F, -1.0F, -0.5F, 3.0F, 1.75F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.1F, 19.0F, -3.4F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r1 = bone3
				.addOrReplaceChild("cube_r1",
						CubeListBuilder.create().texOffs(0, 0).addBox(-0.1606F, -1.0F, 2.7336F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.9635F, 0.0F));

		PartDefinition cube_r2 = bone3
				.addOrReplaceChild("cube_r2",
						CubeListBuilder.create().texOffs(0, 3).addBox(1.7336F, -1.0F, 0.8394F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.7489F, 0.0F));

		PartDefinition cube_r3 = bone3
				.addOrReplaceChild("cube_r3",
						CubeListBuilder.create().texOffs(0, 6).addBox(0.9749F, -1.0F, 1.9749F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r4 = bone3
				.addOrReplaceChild("cube_r4",
						CubeListBuilder.create().texOffs(0, 9).addBox(-1.5F, -1.0F, 3.0F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r5 = bone3
				.addOrReplaceChild("cube_r5",
						CubeListBuilder.create().texOffs(0, 22).addBox(-2.8394F, -1.0F, 2.7336F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r6 = bone3
				.addOrReplaceChild("cube_r6",
						CubeListBuilder.create().texOffs(8, 22).addBox(-3.9749F, -1.0F, 1.9749F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r7 = bone3
				.addOrReplaceChild("cube_r7",
						CubeListBuilder.create().texOffs(16, 22).addBox(-4.7336F, -1.0F, 0.8394F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition bone = partdefinition.addOrReplaceChild("bone",
				CubeListBuilder.create().texOffs(24, 28).addBox(-5.0F, -1.0F, -0.5F, 3.0F, 1.75F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-4.9F, 19.0F, -3.4F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r8 = bone
				.addOrReplaceChild("cube_r8",
						CubeListBuilder.create().texOffs(8, 25).addBox(-0.1606F, -1.0F, 2.7336F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.9635F, 0.0F));

		PartDefinition cube_r9 = bone
				.addOrReplaceChild("cube_r9",
						CubeListBuilder.create().texOffs(0, 25).addBox(1.7336F, -1.0F, 0.8394F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.7489F, 0.0F));

		PartDefinition cube_r10 = bone
				.addOrReplaceChild("cube_r10",
						CubeListBuilder.create().texOffs(16, 25).addBox(0.9749F, -1.0F, 1.9749F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r11 = bone
				.addOrReplaceChild("cube_r11",
						CubeListBuilder.create().texOffs(24, 25).addBox(-1.5F, -1.0F, 3.0F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r12 = bone
				.addOrReplaceChild("cube_r12",
						CubeListBuilder.create().texOffs(0, 28).addBox(-2.8394F, -1.0F, 2.7336F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r13 = bone
				.addOrReplaceChild("cube_r13",
						CubeListBuilder.create().texOffs(8, 28).addBox(-3.9749F, -1.0F, 1.9749F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r14 = bone
				.addOrReplaceChild("cube_r14",
						CubeListBuilder.create().texOffs(16, 28).addBox(-4.7336F, -1.0F, 0.8394F, 3.0F, 1.75F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition bone2 = partdefinition.addOrReplaceChild("bone2",
				CubeListBuilder.create().texOffs(36, 6).addBox(-5.0F, -0.9F, -0.5F, 3.0F, 1.65F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(-5.0F, 19.0F, 5.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r15 = bone2
				.addOrReplaceChild("cube_r15",
						CubeListBuilder.create().texOffs(0, 34).addBox(-0.1606F, -0.9F, 2.7336F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.9635F, 0.0F));

		PartDefinition cube_r16 = bone2
				.addOrReplaceChild("cube_r16",
						CubeListBuilder.create().texOffs(8, 34).addBox(1.7336F, -0.9F, 0.8394F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.7489F, 0.0F));

		PartDefinition cube_r17 = bone2
				.addOrReplaceChild("cube_r17",
						CubeListBuilder.create().texOffs(16, 34).addBox(0.9749F, -0.9F, 1.9749F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r18 = bone2
				.addOrReplaceChild("cube_r18",
						CubeListBuilder.create().texOffs(24, 34).addBox(-1.5F, -0.9F, 3.0F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r19 = bone2
				.addOrReplaceChild("cube_r19",
						CubeListBuilder.create().texOffs(32, 34).addBox(-2.8394F, -0.9F, 2.7336F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r20 = bone2
				.addOrReplaceChild("cube_r20",
						CubeListBuilder.create().texOffs(36, 0).addBox(-3.9749F, -0.9F, 1.9749F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r21 = bone2
				.addOrReplaceChild("cube_r21",
						CubeListBuilder.create().texOffs(36, 3).addBox(-4.7336F, -0.9F, 0.8394F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition bone4 = partdefinition.addOrReplaceChild("bone4",
				CubeListBuilder.create().texOffs(32, 31).addBox(-5.0F, -0.9F, -0.5F, 3.0F, 1.65F, 1.0F,
						new CubeDeformation(0.0F)),
				PartPose.offsetAndRotation(5.0F, 19.0F, 5.5F, 0.0F, 0.0F, -1.5708F));

		PartDefinition cube_r22 = bone4
				.addOrReplaceChild("cube_r22",
						CubeListBuilder.create().texOffs(0, 31).addBox(-0.1606F, -0.9F, 2.7336F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.9635F, 0.0F));

		PartDefinition cube_r23 = bone4
				.addOrReplaceChild("cube_r23",
						CubeListBuilder.create().texOffs(8, 31).addBox(1.7336F, -0.9F, 0.8394F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.7489F, 0.0F));

		PartDefinition cube_r24 = bone4
				.addOrReplaceChild("cube_r24",
						CubeListBuilder.create().texOffs(16, 31).addBox(0.9749F, -0.9F, 1.9749F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r25 = bone4
				.addOrReplaceChild("cube_r25",
						CubeListBuilder.create().texOffs(24, 31).addBox(-1.5F, -0.9F, 3.0F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition cube_r26 = bone4
				.addOrReplaceChild("cube_r26",
						CubeListBuilder.create().texOffs(32, 22).addBox(-2.8394F, -0.9F, 2.7336F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -1.1781F, 0.0F));

		PartDefinition cube_r27 = bone4
				.addOrReplaceChild("cube_r27",
						CubeListBuilder.create().texOffs(32, 25).addBox(-3.9749F, -0.9F, 1.9749F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		PartDefinition cube_r28 = bone4
				.addOrReplaceChild("cube_r28",
						CubeListBuilder.create().texOffs(32, 28).addBox(-4.7336F, -0.9F, 0.8394F, 3.0F, 1.65F, 1.0F,
								new CubeDeformation(0.0F)),
						PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0)
				.addBox(-6.0F, -11.5F, -5.0F, 12.0F, 10.0F, 12.0F, new CubeDeformation(0.0F)),
				PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch) {
		this.shellColor = entity.getShellColor();
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay,
			float red, float green, float blue, float alpha) {
		float r = (float)(shellColor >> 16 & 255) / 255.0F;
		float g = (float)(shellColor >> 8 & 255) / 255.0F;
		float b = (float)(shellColor & 255) / 255.0F;

		bone3.render(poseStack, vertexConsumer, packedLight, packedOverlay, r * red, g * green, b * blue, alpha);
		bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, r * red, g * green, b * blue, alpha);
		bone2.render(poseStack, vertexConsumer, packedLight, packedOverlay, r * red, g * green, b * blue, alpha);
		bone4.render(poseStack, vertexConsumer, packedLight, packedOverlay, r * red, g * green, b * blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, r * red, g * green, b * blue, alpha);
	}
}
