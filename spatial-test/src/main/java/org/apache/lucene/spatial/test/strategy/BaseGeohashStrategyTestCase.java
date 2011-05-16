package org.apache.lucene.spatial.test.strategy;

import java.io.IOException;

import org.apache.lucene.spatial.base.context.SpatialContext;
import org.apache.lucene.spatial.base.prefix.GeohashSpatialPrefixGrid;
import org.apache.lucene.spatial.strategy.SimpleSpatialFieldInfo;
import org.apache.lucene.spatial.strategy.prefix.DynamicPrefixStrategy;
import org.apache.lucene.spatial.test.SpatialMatchConcern;
import org.apache.lucene.spatial.test.StrategyTestCase;
import org.junit.Test;


public abstract class BaseGeohashStrategyTestCase extends StrategyTestCase<SimpleSpatialFieldInfo> {

  protected abstract SpatialContext getSpatialContext();


  @Test
  public void testGeohashStrategy() throws IOException {

    SimpleSpatialFieldInfo finfo = new SimpleSpatialFieldInfo( "geohash" );

    int maxLength = GeohashSpatialPrefixGrid.getMaxLevelsPossible();
    GeohashSpatialPrefixGrid grs = new GeohashSpatialPrefixGrid(
        getSpatialContext(), maxLength );
    DynamicPrefixStrategy s = new DynamicPrefixStrategy( grs );

    // SimpleIO
    executeQueries( s, grs.getShapeIO(), finfo,
        SpatialMatchConcern.FILTER,
        DATA_WORLD_CITIES_POINTS,
        QTEST_Cities_IsWithin_BBox );
  }
}