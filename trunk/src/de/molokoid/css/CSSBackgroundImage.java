package de.molokoid.css;

import org.mt4j.MTApplication;
import org.mt4j.components.TransformSpace;
import org.mt4j.components.visibleComponents.shapes.MTPolygon;
import org.mt4j.util.MT4jSettings;
import org.mt4j.util.math.Tools3D;
import org.mt4j.util.math.Vertex;
import org.mt4j.util.opengl.GLTexture;
import org.mt4j.util.opengl.GLTextureSettings;
import org.mt4j.util.opengl.GLTexture.EXPANSION_FILTER;
import org.mt4j.util.opengl.GLTexture.SHRINKAGE_FILTER;
import org.mt4j.util.opengl.GLTexture.TEXTURE_TARGET;
import org.mt4j.util.opengl.GLTexture.WRAP_MODE;

import processing.core.PImage;

public class CSSBackgroundImage extends MTPolygon {
	public CSSBackgroundImage(MTApplication mtApp, PImage bgImage, boolean tiled) {
		super(new Vertex[]{
				new Vertex(0,0,0 , 0,0),
				new Vertex(mtApp.width,0,0, 1,0),
				new Vertex(mtApp.width,mtApp.height,0, 1,1),
				new Vertex(0,mtApp.height,0, 0,1)}, mtApp);
		
		boolean pot = Tools3D.isPowerOfTwoDimension(bgImage);
		
		
		if (tiled){
			//Generate texture coordinates to repeat the texture over the whole background (works only with OpenGL)
			float u = (float)this.getBounds().getWidthXY(TransformSpace.LOCAL)/(float)bgImage.width;
			float v = (float)this.getBounds().getHeightXY(TransformSpace.LOCAL)/(float)bgImage.height;
			
			Vertex[] backgroundVertices = this.getVerticesLocal();
			backgroundVertices[0].setTexCoordU(0);
			backgroundVertices[0].setTexCoordV(0);
			backgroundVertices[1].setTexCoordU(u);
			backgroundVertices[1].setTexCoordV(0);
			backgroundVertices[2].setTexCoordU(u);
			backgroundVertices[2].setTexCoordV(v);
			backgroundVertices[3].setTexCoordU(0);
			backgroundVertices[3].setTexCoordV(v);
			
//			this.setVertices(getVerticesLocal()); //For performance, just update the texture buffer
			//Update changed texture coordinates for opengl buffer drawing
			if (MT4jSettings.getInstance().isOpenGlMode())
				this.getGeometryInfo().updateTextureBuffer(this.isUseVBOs());
		}
		
		
		if (MT4jSettings.getInstance().isOpenGlMode()){
//			GLTextureParameters tp = new GLTextureParameters();
//			tp.minFilter = GLTextureParameters.LINEAR; //"LINEAR" 
//	//		tp.target = GLConstants.RECTANGULAR;
////			GLTexture tex = new GLTexture(mtApp, mtApp.width, mtApp.height, tp);
////			tex.putImage(bgImage);
//			GLTexture tex = new GLTexture(mtApp, bgImage, tp);
			
			GLTextureSettings g = new GLTextureSettings(TEXTURE_TARGET.TEXTURE_2D, SHRINKAGE_FILTER.BilinearNoMipMaps, EXPANSION_FILTER.Bilinear, WRAP_MODE.REPEAT, WRAP_MODE.REPEAT); 
			GLTexture tex;
			if (pot){
				tex = new GLTexture(mtApp, bgImage, g);
			}else{
				g.target = TEXTURE_TARGET.RECTANGULAR;
				tex = new GLTexture(mtApp, bgImage, g);
			}
			this.setTexture(tex);
		}else{
			this.setTexture(bgImage);
		}
		
//		if (tiled){
//			//Generate texture coordinates to repeat the texture over the whole background (works only with OpenGL)
//			float u = (float)mtApp.width/(float)bgImage.width;
//			float v = (float)mtApp.height/(float)bgImage.height;
//			
//			Vertex[] backgroundVertices = this.getVerticesLocal();
//			backgroundVertices[0].setTexCoordU(0);
//			backgroundVertices[0].setTexCoordV(0);
//			backgroundVertices[1].setTexCoordU(u);
//			backgroundVertices[1].setTexCoordV(0);
//			backgroundVertices[2].setTexCoordU(u);
//			backgroundVertices[2].setTexCoordV(v);
//			backgroundVertices[3].setTexCoordU(0);
//			backgroundVertices[3].setTexCoordV(v);
//			
//			//Update changed texture coordinates for opengl buffer drawing
//			if (MT4jSettings.getInstance().isOpenGlMode())
//				this.getGeometryInfo().updateTextureBuffer(this.isUseVBOs());
//		}
		this.setNoStroke(true);
		this.setPickable(false);
	}
}
