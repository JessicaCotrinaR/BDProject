package ejercicio;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by jessicacotrina on 3/26/17.
 */
public   class TweetUniqueScreenNameReduce extends Reducer<Text, NullWritable, Text, NullWritable> {

    @Override
    protected void reduce(Text key, Iterable<NullWritable> values,Context context)
            throws IOException, InterruptedException {
        context.write(key, NullWritable.get());



    }
}
