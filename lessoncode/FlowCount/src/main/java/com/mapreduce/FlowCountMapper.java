package com.mapreduce;

import com.entity.FlowBean;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * @Author: Fourteen-Y
 * @Description:
 * @Date: 2023/4/28 10:34
 */
public class FlowCountMapper extends Mapper<LongWritable, Text, Text,FlowBean> {
    /**
     * 重写map方法
     * @param key1
     * @param value1
     * @param context
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key1, Text value1, Context context) throws java.io.IOException, InterruptedException {
        // 1. 获取一行数据
        String line = value1.toString();
        // 2. 切割数据
        String[] fields = line.split("\t");
        // 3. 封装对象
        String phoneNum = fields[1];
        long upFlow = Long.parseLong(fields[fields.length - 3]);
        long downFlow = Long.parseLong(fields[fields.length - 2]);
        // 4. 写出
        context.write(new Text(phoneNum), new FlowBean(upFlow, downFlow));
    }
}
