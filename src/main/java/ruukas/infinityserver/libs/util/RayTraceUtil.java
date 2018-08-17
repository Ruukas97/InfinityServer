package ruukas.infinityserver.libs.util;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RayTraceUtil
{
    /**
     * Gets the block or object that is being moused over.
     */
    @Nullable
    public static RayTraceResult getObjectAimedAt( EntityLivingBase entity, World world, double interactionLimit, float partialTicks )
    {
        RayTraceResult objectMouseOver = null;
        Entity pointedEntity;
        
        if ( entity != null && world != null )
        {
            boolean isCreative = (entity instanceof EntityPlayer && !((EntityPlayer) entity).isCreative());
            double playerReach = (double) entity.getEntityAttribute( EntityPlayer.REACH_DISTANCE ).getAttributeValue() - (isCreative ? 0.5d : 0.0d);
            objectMouseOver = entityRayTrace( entity, playerReach, partialTicks );
            Vec3d eyeVector = entity.getPositionEyes( partialTicks );
            boolean interactionLimitWithinReach = false;
            double distanceToBlock = playerReach;
            
            if ( isCreative )
            {
                distanceToBlock = Math.max( 6.0D, playerReach );
                playerReach = distanceToBlock;
            }
            else if ( playerReach > interactionLimit )
            {
                interactionLimitWithinReach = true;
            }
            
            if ( objectMouseOver != null )
            {
                distanceToBlock = objectMouseOver.hitVec.distanceTo( eyeVector );
            }
            
            Vec3d lookVector = entity.getLook( 1.0F );
            Vec3d reachVector = eyeVector.addVector( lookVector.x * playerReach, lookVector.y * playerReach, lookVector.z * playerReach );
            pointedEntity = null;
            Vec3d entityHitVector = null;
            List<Entity> entitiesInVectorBox = world.getEntitiesInAABBexcluding( entity, entity.getEntityBoundingBox().expand( lookVector.x * playerReach, lookVector.y * playerReach, lookVector.z * playerReach ).grow( 1.0D, 1.0D, 1.0D ), Predicates.and( EntitySelectors.NOT_SPECTATING, new Predicate<Entity>() {
                public boolean apply( @Nullable Entity entity )
                {
                    return entity != null && entity.canBeCollidedWith();
                }
            } ) );
            double distanceToEntity = distanceToBlock;
            
            for ( int j = 0 ; j < entitiesInVectorBox.size() ; ++j )
            {
                Entity boxEntity = entitiesInVectorBox.get( j );
                AxisAlignedBB entityCollisionBox = boxEntity.getEntityBoundingBox().grow( (double) boxEntity.getCollisionBorderSize() );
                RayTraceResult raytraceresult = entityCollisionBox.calculateIntercept( eyeVector, reachVector );
                
                if ( entityCollisionBox.contains( eyeVector ) )
                {
                    if ( distanceToEntity >= 0.0D )
                    {
                        pointedEntity = boxEntity;
                        entityHitVector = raytraceresult == null ? eyeVector : raytraceresult.hitVec;
                        distanceToEntity = 0.0D;
                    }
                }
                else if ( raytraceresult != null )
                {
                    double distanceHit = eyeVector.distanceTo( raytraceresult.hitVec );
                    
                    if ( distanceHit < distanceToEntity || distanceToEntity == 0.0D )
                    {
                        if ( boxEntity.getLowestRidingEntity() == entity.getLowestRidingEntity() && !boxEntity.canRiderInteract() )
                        {
                            if ( distanceToEntity == 0.0D )
                            {
                                pointedEntity = boxEntity;
                                entityHitVector = raytraceresult.hitVec;
                            }
                        }
                        else
                        {
                            pointedEntity = boxEntity;
                            entityHitVector = raytraceresult.hitVec;
                            distanceToEntity = distanceHit;
                        }
                    }
                }
            }
            
            if ( pointedEntity != null && interactionLimitWithinReach && eyeVector.distanceTo( entityHitVector ) > interactionLimit )
            {
                pointedEntity = null;
                objectMouseOver = new RayTraceResult( RayTraceResult.Type.MISS, entityHitVector, (EnumFacing) null, new BlockPos( entityHitVector ) );
            }
            
            if ( pointedEntity != null && (distanceToEntity < distanceToBlock || objectMouseOver == null) )
            {
                objectMouseOver = new RayTraceResult( pointedEntity, entityHitVector );
            }
        }
        return objectMouseOver;
    }
    
    @Nullable
    public static RayTraceResult entityRayTrace( Entity entity, double blockReachDistance, float partialTicks )
    {
        Vec3d vec3d = entity.getPositionEyes( partialTicks );
        Vec3d vec3d1 = entity.getLook( partialTicks );
        Vec3d vec3d2 = vec3d.addVector( vec3d1.x * blockReachDistance, vec3d1.y * blockReachDistance, vec3d1.z * blockReachDistance );
        return entity.world.rayTraceBlocks( vec3d, vec3d2, false, false, true );
    }
}
