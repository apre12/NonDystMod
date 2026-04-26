package me.hakotsuki2003.nondyst.nondystmod;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PrintRenderer extends MobRenderer<PrintEntity, Modelprint<PrintEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Nondystmod.MODID, "textures/entity/printsshell.png");

    public PrintRenderer(EntityRendererProvider.Context context) {
        super(context, new Modelprint<>(context.bakeLayer(Modelprint.LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PrintEntity entity) {
        return TEXTURE;
    }
}
