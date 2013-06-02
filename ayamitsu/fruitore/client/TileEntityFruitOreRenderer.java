package ayamitsu.fruitore.client;

import org.lwjgl.opengl.GL11;

import ayamitsu.fruitore.block.TileEntityFruitOre;
import ayamitsu.fruitore.object.FruitOreObject;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class TileEntityFruitOreRenderer extends TileEntitySpecialRenderer {

	public TileEntityFruitOreRenderer() {
	}

	@Override
	public void renderTileEntityAt(TileEntity tileentity, double d0, double d1, double d2, float f) {
		this.renderFruitOre((TileEntityFruitOre)tileentity, d0, d1, d2, f);
	}

	public void renderFruitOre(TileEntityFruitOre fruitOre, double x, double y, double z, float f) {
		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glTranslatef((float)x + 0.5F, (float)y + 0.5F, (float)z + 0.5F);


		FruitOreObject object = FruitOreObject.fruitsList[fruitOre.getFruitId()];

		if (object != null && fruitOre.getBlockMetadata() >= object.getHarvetableLevel()) {
			ItemStack renderItem = object.getRenderItem(fruitOre.getFruitMeta(), fruitOre.worldObj.rand);

			if (renderItem != null) {
				EntityItem entityItem = new EntityItem(fruitOre.worldObj, 0.0D, 0.0D, 0.0D, renderItem);
				entityItem.hoverStart = 0;

				GL11.glPushMatrix();
				RenderItem.renderInFrame = true;
				RenderManager.instance.renderEntityWithPosYaw(entityItem, 0.0D, 0.0D, 0.0D, 0.0F, 0.0F);
				RenderItem.renderInFrame = false;
				GL11.glPopMatrix();
			}
		}


		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL11.GL_BLEND);

		GL11.glPushMatrix();

		GL11.glTranslatef(0.0F, -0.5F, 0.0F);

		Tessellator tes = Tessellator.instance;

		GL11.glNormal3f(0.0F, 1.0F, 0.0F);

		tes.startDrawing(GL11.GL_TRIANGLE_FAN);
		tes.setColorRGBA_F(0.4F, 0.4F, 0.4F, 0.6F);

		tes.addVertex(0.0F, 1.0F, 0.0F);
		tes.addVertex(0.0F, 0.9F, 0.30F);
		tes.addVertex(0.25F, 0.9F, 0.15F);
		tes.addVertex(0.25F, 0.9F, -0.15F);
		tes.addVertex(-0.0F, 0.9F, -0.30F);
		tes.addVertex(-0.25F, 0.9F, -0.15F);
		tes.addVertex(-0.25F, 0.9F, 0.15F);
		tes.addVertex(0.0F, 0.9F, 0.30F);

		tes.draw();

		tes.startDrawing(GL11.GL_QUAD_STRIP);

		tes.addVertex(0.0F, 0.9F, 0.30F);
		tes.addVertex(0.00F, 0.70F, 0.50F);
		tes.addVertex(0.25F, 0.9F, 0.15F);
		tes.addVertex(0.433F, 0.70F, 0.25F);
		tes.addVertex(0.25F, 0.9F, -0.15F);
		tes.addVertex(0.433F, 0.70F, -0.25F);
		tes.addVertex(-0.0F, 0.9F, -0.30F);
		tes.addVertex(0.00F, 0.70F, -0.50F);
		tes.addVertex(-0.25F, 0.9F, -0.15F);
		tes.addVertex(-0.433F, 0.70F, -0.25F);
		tes.addVertex(-0.25F, 0.9F, 0.15F);
		tes.addVertex(-0.433F, 0.70F, 0.25F);
		tes.addVertex(0.0F, 0.9F, 0.30F);
		tes.addVertex(0.00F, 0.70F, 0.50F);

		tes.draw();

		tes.startDrawing(GL11.GL_QUAD_STRIP);

		tes.addVertex(0.00F, 0.70F, 0.50F);
		tes.addVertex(0.00F, 0.20F, 0.40F);
		tes.addVertex(0.433F, 0.70F, 0.25F);
		tes.addVertex(0.3464F, 0.20F, 0.20F);
		tes.addVertex(0.433F, 0.70F, -0.25F);
		tes.addVertex(0.3464F, 0.20F, -0.20F);
		tes.addVertex(0.00F, 0.70F, -0.50F);
		tes.addVertex(0.00F, 0.20F, -0.40F);
		tes.addVertex(-0.433F, 0.70F, -0.25F);
		tes.addVertex(-0.3464F, 0.20F, -0.20F);
		tes.addVertex(-0.433F, 0.70F, 0.25F);
		tes.addVertex(-0.3464F, 0.20F, 0.20F);
		tes.addVertex(0.00F, 0.70F, 0.50F);
		tes.addVertex(0.00F, 0.20F, 0.40F);

		tes.draw();

		tes.startDrawing(GL11.GL_QUAD_STRIP);

		tes.addVertex(0.00F, 0.20F, 0.40F);
		tes.addVertex(0.00F, 0.09F, 0.225F);
		tes.addVertex(0.3464F, 0.20F, 0.20F);
		tes.addVertex(0.1949F, 0.09F, 0.1125F);
		tes.addVertex(0.3464F, 0.20F, -0.20F);
		tes.addVertex(0.1949F, 0.09F, -0.1125F);
		tes.addVertex(0.00F, 0.20F, -0.40F);
		tes.addVertex(0.00F, 0.09F, -0.225F);
		tes.addVertex(-0.3464F, 0.20F, -0.20F);
		tes.addVertex(-0.1949F, 0.09F, -0.1125F);
		tes.addVertex(-0.3464F, 0.20F, 0.20F);
		tes.addVertex(-0.1949F, 0.09F, 0.1125F);
		tes.addVertex(0.00F, 0.20F, 0.40F);
		tes.addVertex(0.00F, 0.09F, 0.225F);

		tes.draw();

		tes.startDrawing(GL11.GL_TRIANGLE_FAN);

		tes.addVertex(0.0F, 0.0F, 0.0F);
		tes.addVertex(0.00F, 0.09F, 0.225F);
		tes.addVertex(-0.1949F, 0.09F, 0.1125F);
		tes.addVertex(-0.1949F, 0.09F, -0.1125F);
		tes.addVertex(0.00F, 0.09F, -0.225F);
		tes.addVertex(0.1949F, 0.09F, -0.1125F);
		tes.addVertex(0.1949F, 0.09F, 0.1125F);
		tes.addVertex(0.00F, 0.09F, 0.225F);

		tes.draw();

		/*tes.startDrawing(GL11.GL_TRIANGLE_FAN);
		tes.setColorRGBA_F(0.4F, 0.4F, 0.4F, 0.6F);

		tes.addVertex(0.0F, 1.0F, 0.0F);
		tes.addVertex(0.0F, 0.9F, 0.30F);
		tes.addVertex(0.25F, 0.9F, 0.15F);
		tes.addVertex(0.25F, 0.9F, -0.15F);
		tes.addVertex(-0.0F, 0.9F, -0.30F);
		tes.addVertex(-0.25F, 0.9F, -0.15F);
		tes.addVertex(-0.25F, 0.9F, 0.15F);
		tes.addVertex(0.0F, 0.9F, 0.30F);

		tes.draw();

		tes.startDrawing(GL11.GL_QUAD_STRIP);

		tes.addVertex(0.0F, 0.9F, 0.30F);
		tes.addVertex(0.00F, 0.70F, 0.50F);
		tes.addVertex(0.25F, 0.9F, 0.15F);
		tes.addVertex(0.433F, 0.70F, 0.25F);
		tes.addVertex(0.25F, 0.9F, -0.15F);
		tes.addVertex(0.433F, 0.70F, -0.25F);
		tes.addVertex(-0.0F, 0.9F, -0.30F);
		tes.addVertex(0.00F, 0.70F, -0.50F);
		tes.addVertex(-0.25F, 0.9F, -0.15F);
		tes.addVertex(-0.433F, 0.70F, -0.25F);
		tes.addVertex(-0.25F, 0.9F, 0.15F);
		tes.addVertex(-0.433F, 0.70F, 0.25F);
		tes.addVertex(0.0F, 0.9F, 0.30F);
		tes.addVertex(0.00F, 0.70F, 0.50F);

		tes.draw();

		tes.startDrawing(GL11.GL_QUAD_STRIP);

		tes.addVertex(0.00F, 0.70F, 0.50F);
		tes.addVertex(0.00F, 0.20F, 0.40F);
		tes.addVertex(0.433F, 0.70F, 0.25F);
		tes.addVertex(0.3464F, 0.20F, 0.20F);
		tes.addVertex(0.433F, 0.70F, -0.25F);
		tes.addVertex(0.3464F, 0.20F, -0.20F);
		tes.addVertex(0.00F, 0.70F, -0.50F);
		tes.addVertex(0.00F, 0.20F, -0.40F);
		tes.addVertex(-0.433F, 0.70F, -0.25F);
		tes.addVertex(-0.3464F, 0.20F, -0.20F);
		tes.addVertex(-0.433F, 0.70F, 0.25F);
		tes.addVertex(-0.3464F, 0.20F, 0.20F);
		tes.addVertex(0.00F, 0.70F, 0.50F);
		tes.addVertex(0.00F, 0.20F, 0.40F);

		tes.draw();

		tes.startDrawing(GL11.GL_QUAD_STRIP);

		tes.addVertex(0.00F, 0.20F, 0.40F);
		tes.addVertex(0.00F, 0.09F, 0.225F);
		tes.addVertex(0.3464F, 0.20F, 0.20F);
		tes.addVertex(0.1949F, 0.09F, 0.1125F);
		tes.addVertex(0.3464F, 0.20F, -0.20F);
		tes.addVertex(0.1949F, 0.09F, -0.1125F);
		tes.addVertex(0.00F, 0.20F, -0.40F);
		tes.addVertex(0.00F, 0.09F, -0.225F);
		tes.addVertex(-0.3464F, 0.20F, -0.20F);
		tes.addVertex(-0.1949F, 0.09F, -0.1125F);
		tes.addVertex(-0.3464F, 0.20F, 0.20F);
		tes.addVertex(-0.1949F, 0.09F, 0.1125F);
		tes.addVertex(0.00F, 0.20F, 0.40F);
		tes.addVertex(0.00F, 0.09F, 0.225F);

		tes.draw();

		tes.startDrawing(GL11.GL_TRIANGLE_FAN);

		tes.addVertex(0.0F, 0.0F, 0.0F);
		tes.addVertex(0.00F, 0.09F, 0.225F);
		tes.addVertex(-0.1949F, 0.09F, 0.1125F);
		tes.addVertex(-0.1949F, 0.09F, -0.1125F);
		tes.addVertex(0.00F, 0.09F, -0.225F);
		tes.addVertex(0.1949F, 0.09F, -0.1125F);
		tes.addVertex(0.1949F, 0.09F, 0.1125F);
		tes.addVertex(0.00F, 0.09F, 0.225F);

		tes.draw();*/

		/*tes.startDrawing(GL11.GL_TRIANGLE_FAN);
		tes.setColorRGBA_F(0.4F, 0.4F, 0.4F, 0.6F);

		tes.addVertexWithUV(0.0F, 1.0F, 0.0F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.0F, 0.9F, 0.30F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.25F, 0.9F, 0.15F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.25F, 0.9F, -0.15F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.0F, 0.9F, -0.30F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.25F, 0.9F, -0.15F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.25F, 0.9F, 0.15F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.0F, 0.9F, 0.30F, 0.0F, 0.0F);

		tes.draw();

		tes.startDrawing(GL11.GL_QUAD_STRIP);

		tes.addVertexWithUV(0.0F, 0.9F, 0.30F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.70F, 0.50F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.25F, 0.9F, 0.15F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.433F, 0.70F, 0.25F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.25F, 0.9F, -0.15F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.433F, 0.70F, -0.25F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.0F, 0.9F, -0.30F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.70F, -0.50F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.25F, 0.9F, -0.15F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.433F, 0.70F, -0.25F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.25F, 0.9F, 0.15F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.433F, 0.70F, 0.25F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.0F, 0.9F, 0.30F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.70F, 0.50F, 0.0F, 0.0F);

		tes.draw();

		tes.startDrawing(GL11.GL_QUAD_STRIP);

		tes.addVertexWithUV(0.00F, 0.70F, 0.50F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.20F, 0.40F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.433F, 0.70F, 0.25F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.3464F, 0.20F, 0.20F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.433F, 0.70F, -0.25F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.3464F, 0.20F, -0.20F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.70F, -0.50F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.20F, -0.40F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.433F, 0.70F, -0.25F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.3464F, 0.20F, -0.20F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.433F, 0.70F, 0.25F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.3464F, 0.20F, 0.20F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.70F, 0.50F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.20F, 0.40F, 0.0F, 0.0F);

		tes.draw();

		tes.startDrawing(GL11.GL_QUAD_STRIP);

		tes.addVertexWithUV(0.00F, 0.20F, 0.40F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.09F, 0.225F, 0.0F, 0.0F);

		tes.addVertexWithUV(0.3464F, 0.20F, 0.20F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.1949F, 0.09F, 0.1125F, 0.0F, 0.0F);

		tes.addVertexWithUV(0.3464F, 0.20F, -0.20F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.1949F, 0.09F, -0.1125F, 0.0F, 0.0F);

		tes.addVertexWithUV(0.00F, 0.20F, -0.40F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.09F, -0.225F, 0.0F, 0.0F);

		tes.addVertexWithUV(-0.3464F, 0.20F, -0.20F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.1949F, 0.09F, -0.1125F, 0.0F, 0.0F);

		tes.addVertexWithUV(-0.3464F, 0.20F, 0.20F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.1949F, 0.09F, 0.1125F, 0.0F, 0.0F);

		tes.addVertexWithUV(0.00F, 0.20F, 0.40F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.09F, 0.225F, 0.0F, 0.0F);

		tes.draw();

		tes.startDrawing(GL11.GL_TRIANGLE_FAN);

		tes.addVertexWithUV(0.0F, 0.0F, 0.0F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.09F, 0.225F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.1949F, 0.09F, 0.1125F, 0.0F, 0.0F);
		tes.addVertexWithUV(-0.1949F, 0.09F, -0.1125F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.09F, -0.225F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.1949F, 0.09F, -0.1125F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.1949F, 0.09F, 0.1125F, 0.0F, 0.0F);
		tes.addVertexWithUV(0.00F, 0.09F, 0.225F, 0.0F, 0.0F);

		tes.draw();*/

		/*
		Tessellator tes = Tessellator.instance;


		// top
		GL11.glNormal3f(0F, -1.0F, 0F);
		tes.startDrawing(GL11.GL_TRIANGLE_FAN);
		tes.setColorRGBA_F(0.2F, 0.2F, 0.6F, 0.5F);

		tes.addVertexWithUV(0D, 0.5D, 0D, 0.5D, 0.5D);

		tes.addVertexWithUV(  0.5D, 0.25D, 0D, 0.5D, 0.5D);
		tes.addVertexWithUV( 0.25D, 0.25D, -0.4330127018922193233819D, 1.0D, 0.0D);
		tes.addVertexWithUV(-0.25D, 0.25D, -0.4330127018922193233819D, 0.0D, 0.0D);
		tes.addVertexWithUV( -0.5D, 0.25D, 0D, 0.5D, 0.5D);
		tes.addVertexWithUV(-0.25D, 0.25D, 0.4330127018922193233819D, 0.0D, 1.0D);
		tes.addVertexWithUV( 0.25D, 0.25D, 0.4330127018922193233819D, 1.0D, 1.0D);
		tes.addVertexWithUV(  0.5D, 0.25D, 0D, 0.5D, 0.5D);

		tes.draw();

		// bottom
		GL11.glNormal3f(0F, 1.0F, 0F);
		tes.startDrawing(GL11.GL_TRIANGLE_FAN);

		tes.addVertexWithUV(0D, -0.5D, 0D, 0.5D, 0.5D);

		tes.addVertexWithUV(  0.5D, -0.25D, 0D, 0.5D, 0.5D);
		tes.addVertexWithUV( 0.25D, -0.25D, 0.4330127018922193233819D, 1.0D, 1.0D);
		tes.addVertexWithUV(-0.25D, -0.25D, 0.4330127018922193233819D, 0.0D, 1.0D);
		tes.addVertexWithUV( -0.5D, -0.25D, 0D, 0.5D, 0.5D);
		tes.addVertexWithUV(-0.25D, -0.25D, -0.4330127018922193233819D, 0.0D, 0.0D);
		tes.addVertexWithUV( 0.25D, -0.25D, -0.4330127018922193233819D, 1.0D, 0.0D);
		tes.addVertexWithUV(  0.5D, -0.25D, 0D, 0.5D, 0.5D);

		tes.draw();

		// side
		GL11.glNormal3f(0F, -1.0F, 0F);
		tes.startDrawing(GL11.GL_QUAD_STRIP);

		tes.addVertexWithUV(  0.5D, 0.25, 0D, 0D, 0D);
		tes.addVertexWithUV(  0.5D, -0.25, 0D, 0D, 1D);
		tes.addVertexWithUV( 0.25D, 0.25, -0.4330127018922193233819D, 1D, 0D);
		tes.addVertexWithUV( 0.25D, -0.25, -0.4330127018922193233819D, 1D, 1D);
		tes.addVertexWithUV(-0.25D, 0.25, -0.4330127018922193233819D, 0D, 0D);
		tes.addVertexWithUV(-0.25D, -0.25, -0.4330127018922193233819D, 0D, 1D);
		tes.addVertexWithUV( -0.5D, 0.25, 0D, 1D, 0D);
		tes.addVertexWithUV( -0.5D, -0.25, 0D, 1D, 1D);
		tes.addVertexWithUV(-0.25D, 0.25, 0.4330127018922193233819D, 0D, 0D);
		tes.addVertexWithUV(-0.25D, -0.25, 0.4330127018922193233819D, 0D, 1D);
		tes.addVertexWithUV( 0.25D, 0.25, 0.4330127018922193233819D, 1D, 0D);
		tes.addVertexWithUV( 0.25D, -0.25, 0.4330127018922193233819D, 1D, 1D);
		tes.addVertexWithUV(  0.5D, 0.25, 0D, 0D, 0D);
		tes.addVertexWithUV(  0.5D, -0.25, 0D, 0D, 1D);

		tes.draw();
		*/



		GL11.glPopMatrix();

		GL11.glPopMatrix();

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}

}
