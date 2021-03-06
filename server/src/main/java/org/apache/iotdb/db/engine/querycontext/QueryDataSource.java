/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.iotdb.db.engine.querycontext;

import org.apache.iotdb.db.engine.storagegroup.TsFileResource;
import org.apache.iotdb.tsfile.read.common.Path;

import java.util.List;
import org.apache.iotdb.tsfile.read.filter.TimeFilter;
import org.apache.iotdb.tsfile.read.filter.basic.Filter;
import org.apache.iotdb.tsfile.read.filter.operator.AndFilter;

public class QueryDataSource {
  private Path seriesPath;
  private List<TsFileResource> seqResources;
  private List<TsFileResource> unseqResources;

  /**
   * data older than currentTime - dataTTL should be ignored.
   */
  private long dataTTL = Long.MAX_VALUE;

  public QueryDataSource(Path seriesPath, List<TsFileResource> seqResources, List<TsFileResource> unseqResources) {
    this.seriesPath = seriesPath;
    this.seqResources = seqResources;
    this.unseqResources = unseqResources;
  }

  public Path getSeriesPath() {
    return seriesPath;
  }

  public List<TsFileResource> getSeqResources() {
    return seqResources;
  }

  public List<TsFileResource> getUnseqResources() {
    return unseqResources;
  }

  public long getDataTTL() {
    return dataTTL;
  }

  public void setDataTTL(long dataTTL) {
    this.dataTTL = dataTTL;
  }

  /**
   *
   * @return an updated time filter concerning TTL
   */
  public Filter updateTimeFilter(Filter timeFilter) {
    if (dataTTL != Long.MAX_VALUE) {
      if (timeFilter != null) {
        timeFilter = new AndFilter(timeFilter, TimeFilter.gtEq(System.currentTimeMillis() -
            dataTTL));
      } else {
        timeFilter = TimeFilter.gtEq(System.currentTimeMillis() - dataTTL);
      }
    }
    return timeFilter;
  }
}
