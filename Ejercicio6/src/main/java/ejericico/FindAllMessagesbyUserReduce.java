package ejericico;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * Created by jessicacotrina on 3/27/17.
 */
public class FindAllMessagesbyUserReduce  extends Reducer<Text, LongWritable, Text, Text> {
    private IntWritable count = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context)
            throws IOException, InterruptedException {

        String result = "";

        int sum = 0;
        for (LongWritable val : values) {
            sum += 1;
            result += val.toString() + " ";
       }
        count.set(sum);
        String final_result = count.toString() + " " + result;
        context.write(key, new Text(final_result));
    }
}
