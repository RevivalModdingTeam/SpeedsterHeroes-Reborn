package com.speedstersreborn.util.helper;

import net.minecraft.util.math.BlockPos;

public class MathHelper {
	
    /** Distance between X and Z coordinates of given positions **/
    public static double distanceToBlockPos2D(BlockPos pos1, BlockPos pos2) {
    	return Math.sqrt(sqr(Math.abs(pos1.getX() - pos2.getX())) + sqr(Math.abs(pos1.getZ() - pos2.getZ())));
    }
    
    /** Distance between X,Y and Z coordinates of given positions **/
	public static double getDistanceToBlockPos3D(BlockPos pos1, BlockPos pos2)
	{
		return Math.sqrt(sqr(Math.sqrt(sqr(Math.abs(pos1.getX() - pos2.getX())) + sqr(Math.abs(pos1.getZ() - pos2.getZ())))) + sqr(Math.abs(pos1.getY() - pos2.getY())));
	}
    
    public static double sqr(double d) {
    	return d*d;
    }
}
