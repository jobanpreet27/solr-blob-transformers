/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package solr.transformer;

//~--- JDK imports ------------------------------------------------------------

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.apache.solr.handler.dataimport.Context;
import org.apache.solr.handler.dataimport.DataImporter;
import org.apache.solr.handler.dataimport.RegexTransformer;
import org.apache.solr.handler.dataimport.Transformer;


/**
 * Transformer instance which converts a Blob to a String.
 * <p/>
 * Refer to <a href="http://wiki.apache.org/solr/DataImportHandler">http://wiki.apache.org/solr/DataImportHandler</a>
 * for more details.
 * <p/>
 * <b>This API is experimental and subject to change</b>
 *
 */
public class BlobTransformer extends Transformer {
	
	private static final Logger logger = Logger.getLogger(BlobTransformer.class.getName());
	public static final String BLOB = "blob";

    public Object transformRow(Map<String, Object> aRow, Context context) {
      for (Map<String, String> map : context.getAllEntityFields()) {
        String fmt = map.get(BLOB);

        if (fmt == null) continue;

        String column = map.get(DataImporter.COLUMN);
        String srcCol = map.get(RegexTransformer.SRC_COL_NAME);

        if (srcCol == null) srcCol = column;

        Object o = aRow.get(srcCol);

        if (o instanceof List) {
          List inputs  = (List) o;
          List<String> results = new ArrayList<String>();

          for (Object input : inputs)
            results.add(process(input));
            aRow.put(column, results);
        } else if (o != null) aRow.put(column, process(o));
      }
      return aRow;
    }

  private String process(Object value) {

    if (value == null) return null;
    byte[] bdata = (byte[]) value;
    return new String(bdata);
  }
}