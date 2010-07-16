package de.molokoid.util;

import processing.core.PImage;

public class ImageProcessor {
	
	@Deprecated
	public static PImage combineImages(PImage input, float wdth, float hght) {
		int width = (int)wdth;
		int height = (int)hght;
		
		PImage newImage= new PImage(width, height);
		int[] newPixels = new int[width*height];
		int idx = 0;
		
		for (int lines = 0; lines < height; lines++) {
			for (int rows = 0; rows < width; rows++) {
				newPixels[idx] = input.pixels[rows % input.width + (lines % input.height * input.width)];

				
				
				idx++;
			}
		}
		
		
		
		newImage.pixels = newPixels;
		return newImage;
	}
	
	
}
