package hbase.com;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.hbase.mapreduce.TableReducer;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;

import java.io.IOException;

public class hbaseMR {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf = HBaseConfiguration.create();
        Job job = new Job();
        job.setJarByClass(hbaseMR.class);

        Scan scan = new Scan();
        scan.setCaching(1000);

        TableMapReduceUtil.initTableMapperJob("students",scan, MyMapper.class, Text.class, Text.class, job);
        TableMapReduceUtil.initTableReducerJob("ageAgrRes",MyReducer.class,job);

        job.waitForCompletion(true);

    }
}

class MyMapper extends TableMapper<Text, Text>{

    @Override
    protected void map(ImmutableBytesWritable key,Result value,Context context)
            throws IOException,InterruptedException{
//        super.map(key, value, context);

        Text k=new Text(Bytes.toString(key.get()));

        Text v=new Text(Bytes.toString(value.getValue(Bytes.toBytes("basicInfo"),Bytes.toBytes("age"))));

        context.write(v,k);

        }
}

class MyReducer extends TableReducer<Text, Text, ImmutableBytesWritable>{

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        Put put = new Put(Bytes.toBytes(key.toString()));

        for(Text value : values){

            put.add(Bytes.toBytes("f1"),Bytes.toBytes(value.toString()), Bytes.toBytes(value.toString()));

        }

        context.write(null,put);

    }
}