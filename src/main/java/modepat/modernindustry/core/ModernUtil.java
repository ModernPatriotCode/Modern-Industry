package modepat.modernindustry.core;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Nullable;

public class ModernUtil {
	
	/**
	 * 					Gives a randomized offset and scaled velocity to coordinates
	 * @param xPos		X-Position
	 * @param yPos		Y-Position
	 * @param zPos		Z-Position
	 * @param maxOffset	Maximum allowed Coordinate-Distance from Block-Center
	 * @return			Array of doubles with length 6, 0-2 Offset coordinate, 3-5 velocity values
	 */
	public static double[] getRandomizedOffsetAndScaledVelocityForBlock(double xPos, double yPos, double zPos, double maxOffset) {
		double[] offset = getRandomizedOffsetForBlock(xPos, yPos, zPos, maxOffset);
		double[] velocity = getScaledVelocityForBlock(xPos, yPos, zPos, offset[0], offset[1], offset[2], 1);
		return new double[] {offset[0], offset[1], offset[2], velocity[0], velocity[1], velocity[2]};
	}
	
	public static double[]  getRandomizedOffsetForBlock(double xPos, double yPos, double zPos, double maxOffset) {
		return new double[] {(xPos+0.5+((Math.random()-0.5)*maxOffset*2)), (yPos+0.5+((Math.random()-0.5)*maxOffset*2)), (zPos+0.5+((Math.random()-0.5)*maxOffset*2))};
	}
	
	public static double[] getScaledVelocityForBlock(double xPos, double yPos, double zPos, double xPosDis, double yPosDis, double zPosDis, double scale) {
		double xDif = xPosDis - xPos;
		double yDif = yPosDis - yPos;
		double zDif = zPosDis - zPos;
		double distance = Math.sqrt(Math.pow(xDif, 2) + Math.pow(yDif, 2) + Math.pow(zDif, 2));
		return new double[] {((xDif/distance)/scale), ((yDif/distance)/scale), ((zDif/distance)/scale)};
	}
	
	@Nullable
	public static int[] convertIntegers(List<Integer> integers) {
	    int[] ret = new int[integers.size()];
	    Iterator<Integer> iterator = integers.iterator();
	    for (int i = 0; i < ret.length; i++)
	    {
	        ret[i] = iterator.next().intValue();
	    }
	    return ret;
	}

}
