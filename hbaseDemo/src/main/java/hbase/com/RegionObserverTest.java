package hbase.com;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.List;

public class RegionObserverTest extends BaseRegionObserver {

//    private static byte[] fixed_rowkey = Bytes.toBytes("Tom");
    private static byte[] fixed_rowkey = Bytes.toBytes("Jerry");

    @Override
    public void preGetOp(ObserverContext<RegionCoprocessorEnvironment> c,Get get, List<Cell> result) throws IOException {

        if(Bytes.equals(get.getRow(), fixed_rowkey)){

            KeyValue kv = new KeyValue(get.getRow(), Bytes.toBytes("time"), Bytes.toBytes("time")
                    ,Bytes.toBytes(System.currentTimeMillis()));

            result.add(kv);
        }

    }



}
