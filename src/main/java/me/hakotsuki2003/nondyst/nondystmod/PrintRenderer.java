package me.hakotsuki2003.nondyst.nondystmod;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class PrintRenderer extends MobRenderer<PrintEntity, PrintModel<PrintEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(Nondystmod.MODID, "textures/entity/printsshell.png");
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Nondystmod.MODID, "print"), "main");

    public PrintRenderer(EntityRendererProvider.Context context) {
        super(context, new PrintModel<>(context.bakeLayer(LAYER_LOCATION)), 0.5F);
    }

    @Override
    public ResourceLocation getTextureLocation(PrintEntity entity) {
        return TEXTURE;
    }
}
