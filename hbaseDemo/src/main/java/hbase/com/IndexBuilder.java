package hbase.com;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.MultiTableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.mapreduce.TableMapper;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class IndexBuilder {

    String tableName = null;
    String familyName = null;

    private Map<ImmutableBytesWritable,String> indexTables = new HashMap<ImmutableBytesWritable,String>();

    static class MyMapper extends TableMapper<ImmutableBytesWritable, Put>{

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {

            Configuration conf = context.getConfiguration();

            String tableName = conf.get("tableName");
            String familyName = conf.get("familyName");
            String[] qulifiers = conf.getStrings("qulifiers");

            // 根据 qulifiers 定义 对应不同的HBASE字段 的索引表名称：
            for(String q : qulifiers){

                indexTables.put(q,)


            }

        }

        @Override
        protected void map(ImmutableBytesWritable key, Result value, Context context) throws IOException, InterruptedException {

        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {

        Configuration conf =  new HBaseConfiguration();
        String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();


        // 获取对应的 参数值：
        if(otherArgs.length < 3){
            System.exit(-1);
        }

        String tableName = otherArgs[0];
        String familyName = otherArgs[1];
        conf.set("tableName", tableName);
        conf.set("familyName", familyName);

        String[] qulifiers = new String[otherArgs.length - 2];

        for(int i = 0; i < qulifiers.length; i++){

            qulifiers[i] = otherArgs[i+2];

        }

        conf.setStrings("qulifiers", qulifiers);

        Job job = new Job(conf, tableName);

        job.setJarByClass(IndexBuilder.class);
        job.setMapperClass(MyMapper.class);
        job.setNumReduceTasks(0);
        // 表格格式输入
        job.setInputFormatClass(TableInputFormat.class);
        // 多张表输出格式
        job.setOutputFormatClass(MultiTableOutputFormat.class);

        Scan scan = new Scan();
        scan.setCaching(50000);

        TableMapReduceUtil.initTableMapperJob(tableName, scan, MyMapper.class, ImmutableBytesWritable.class, Put.class, job);

        job.waitForCompletion(true);


    }
}
