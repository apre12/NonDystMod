package me.hakotsuki2003.nondyst.nondystmod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ExternalSilicoShellRenderer extends MobRenderer<ExternalSilicoShellEntity, ExternalSilicoShellModel<ExternalSilicoShellEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Nondystmod.MODID, "textures/entity/printsshell.png");

    public ExternalSilicoShellRenderer(EntityRendererProvider.Context context) {
        super(context, new ExternalSilicoShellModel<>(context.bakeLayer(ExternalSilicoShellModel.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(ExternalSilicoShellEntity entity) {
        return TEXTURE;
    }
}
