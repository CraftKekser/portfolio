package de.craftkekser.ips.processing;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class PanoramaProcessor {

	public static boolean isSplittable(BufferedImage img) {
		return img.getWidth()/img.getHeight()>=2;
	}

	public static long getMaxSegments(BufferedImage img) {
		return Math.round(Math.floor((double)img.getWidth()/(double)img.getHeight()));
	}

	public static List<BufferedImage> split(BufferedImage img, SplitAlignment alignment) {
		List<BufferedImage> segments = new ArrayList<BufferedImage>();

		long amount = getMaxSegments(img);

		int deltaX = img.getHeight();
		int side = img.getHeight();
		
		System.out.println("Processing: Starting with deltaX=" + deltaX + " alignment=" + alignment.name().toLowerCase());
		
		switch(alignment) {
		case LEFT:
			
			for(int segment = 0; segment < amount; segment++) {
				System.out.println("Processing segment " + (segment+1) + "/" + amount);
				BufferedImage segmentImg = img.getSubimage(segment*deltaX, 0, side, side);
				segments.add(segmentImg);
				System.out.println(" -> Done");
			}

			break;
		case CENTER:

			int startOffsetCenter = (int) (img.getWidth() - (amount*deltaX))/2;
			
			for(int segment = 0; segment < amount; segment++) {
				System.out.println("Processing segment " + (segment+1) + "/" + amount);
				BufferedImage segmentImg = img.getSubimage(segment*deltaX+startOffsetCenter, 0, side, side);
				segments.add(segmentImg);
				System.out.println(" -> Done");
			}
			
			break;
		case RIGHT:

			int startOffsetRight = (int) (img.getWidth() - (amount*deltaX));
			
			for(int segment = 0; segment < amount; segment++) {
				System.out.println("Processing segment " + (segment+1) + "/" + amount);
				BufferedImage segmentImg = img.getSubimage(segment*deltaX+startOffsetRight, 0, side, side);
				segments.add(segmentImg);
				System.out.println(" -> Done");
			}
			
			break;
		default:
			break;
		}
		
		System.out.println("Processing: Done.");
		return segments;
	}
	
	
	
}
