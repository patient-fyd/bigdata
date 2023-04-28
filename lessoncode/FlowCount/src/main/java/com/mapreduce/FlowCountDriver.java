package com.mapreduce;

import com.entity.FlowBean;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;

import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

import static org.apache.hadoop.mapreduce.lib.input.FileInputFormat.setInputPaths;

/**
 * @Author: Fourteen-Y
 * @Description:
 * @Date: 2023/4/28 10:43
 */
public class FlowCountDriver {
    /**
     * 主方法
     * @param args
     * @throws IOException
     * @throws InterruptedException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, InterruptedException,ClassNotFoundException {
        //创建配置对象
        Configuration config = new Configuration();

        //初始化作业对象
        Job job = Job.getInstance(config);

        // 1、设置当前作业的驱动类
        job.setJarByClass(FlowCountDriver.class);

        // 2、设置Mapper类
        job.setMapperClass(FlowCountMapper.class);

        // 3、设置Reducer类
        job.setReducerClass(FlowCountReduce.class);

        // 4、设置Mapper类的输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowBean.class);

        // 5、设置Reducer类的输出类型
        job.setOutputKeyClass(org.apache.hadoop.io.Text.class);
        job.setOutputValueClass(FlowBean.class);

        // 6、设置作业的输入路径

        FileInputFormat.setInputPaths(job, new org.apache.hadoop.fs.Path("C:\\Users\\33275\\Documents\\input\\inputflow.txt"));

        // 7、设置作业的输出路径
        FileOutputFormat.setOutputPath(job, new Path("C:\\Users\\33275\\Documents\\output\\outputflow"));

        // 8、提交作业
        boolean result = job.waitForCompletion(true);

        // 9、退出程序
        System.exit(result ? 0 : 1);


    }
}
