package ejercicio;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.io.IOException;

/**
 * Created by jessicacotrina on 3/26/17.
 */
public class TweetUniqueScreenNameMap  extends Mapper<LongWritable, Text, Text, NullWritable> {
    private Text word = new Text();

    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String rawTweet = value.toString();

        try {
            Status tweetStatus = TwitterObjectFactory.createStatus(rawTweet);
            String name = tweetStatus.getUser().getName();

            word.set(name);



            context.write(word,NullWritable.get());

        }
        // Convert to Status
        catch(TwitterException e){
            // ignore bad tweets
            Logger logger = LogManager.getRootLogger();
            logger.trace("Bad Tweet: " + rawTweet);
        }

    }
}
