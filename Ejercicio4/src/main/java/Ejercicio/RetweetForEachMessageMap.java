package Ejercicio;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterObjectFactory;

import java.io.IOException;

/**
 * Created by jessicacotrina on 3/27/17.
 */
public class RetweetForEachMessageMap extends Mapper<LongWritable, Text, LongWritable, LongWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String rawTweet = value.toString();

        try {
            Status tweetStatus = TwitterObjectFactory.createStatus(rawTweet);
//            long tweet = tweetStatus.getId();
//            long retweet = tweetStatus.getRetweetedStatus().getId();

            boolean a = tweetStatus.isRetweet();
            if (a){
                context.write(new LongWritable(tweetStatus.getRetweetedStatus().getId()), new LongWritable(tweetStatus.getId()));
            }
        } catch (TwitterException e1) {
            Logger logger = LogManager.getRootLogger();
            logger.trace("Bad Tweet: " + rawTweet);
        }


    }
}
