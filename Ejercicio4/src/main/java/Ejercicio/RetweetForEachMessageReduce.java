package Ejercicio;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by jessicacotrina on 3/27/17.
 */
public class RetweetForEachMessageReduce extends Reducer<LongWritable, LongWritable, LongWritable, Text> {

    //private IntWritable result = new IntWritable();

    @Override
    protected void reduce(LongWritable key, Iterable<LongWritable> values, Context context)
            throws IOException, InterruptedException {

        String result = "";

        for (LongWritable value : values) {

            result += value.toString() + " ";

        }

        context.write(key, new Text(result));

    }
}
