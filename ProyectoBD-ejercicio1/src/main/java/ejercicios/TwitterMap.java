package ejercicios;

import org.apache.hadoop.io.IntWritable;
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
 * Created by jessicacotrina on 3/22/17.
 */
public class TwitterMap  extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String rawTweet = value.toString();

        try {
           Status tweetStatus = TwitterObjectFactory.createStatus(rawTweet);
           String tweet = tweetStatus.getText().toUpperCase();


            if (tweet.contains("TRUMP")){
                context.write(new Text("TRUMP"), new IntWritable(1));
            }
         if (tweet.contains("MAGA")){
                context.write(new Text("MAGA"), new IntWritable(1));

            }
            if (tweet.contains("DICTATOR")){
            context.write(new Text("DICTATOR"), new IntWritable(1));
            }
           if (tweet.contains("IMPEACH")){
                context.write(new Text("IMPEACH"), new IntWritable(1));
            }
            if (tweet.contains("DRAIN")){
                context.write(new Text("DRAIN"), new IntWritable(1));
            }
            if (tweet.contains("SWAMP")){
             context.write(new Text("SWAMP"), new IntWritable(1));
            }
            if (tweet.contains("CHANGE")){
               context.write(new Text("CHANGE"), new IntWritable(1));
            }

           // context.write(new Text(tweetStatus.getUser().getScreenName()), new IntWritable(1));
        }
        // Convert to Status
       catch(TwitterException e){
            // ignore bad tweets
            Logger logger = LogManager.getRootLogger();
            logger.trace("Bad Tweet: " + rawTweet);
        }
    }
}
