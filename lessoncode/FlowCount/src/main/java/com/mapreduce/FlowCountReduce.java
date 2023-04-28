package com.mapreduce;

import com.entity.FlowBean;
import org.apache.hadoop.mapreduce.Reducer;

import javax.xml.soap.Text;

/**
 * @Author: Fourteen-Y
 * @Description:
 * @Date: 2023/4/28 10:42
 */

public class FlowCountReduce extends Reducer<Text, FlowBean, Text, FlowBean> {
    /**
     * 重写reduce方法
     * @param key2
     * @param value2s
     * @param context
     * @throws java.io.IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key2, Iterable<FlowBean> value2s, Context context) throws java.io.IOException, InterruptedException {
        // 1. 遍历集合，将上行流量，下行流量分别累加
        long upFlow = 0;
        long downFlow = 0;
        for (FlowBean value2 : value2s) {
            upFlow += value2.getUpFlow();
            downFlow += value2.getDownFlow();
        }
        System.out.println("upload------->" + upFlow);
        // 2. 封装对象
        FlowBean value3 = new FlowBean(upFlow, downFlow);
        // 3. 写出
        context.write(key2, value3);
    }
}