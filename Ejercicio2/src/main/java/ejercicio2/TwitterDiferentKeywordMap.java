package ejercicio2;

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
 * Created by jessicacotrina on 3/26/17.
 */
public  class TwitterDiferentKeywordMap extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String rawTweet = value.toString();

        String[] stopword = {"a", "an", "and", "are", "as", "at", "be", "by", "for", "from", "has", "be", "in", "is", "it", "its",
                "of", "on", "that", "the", "to", "was", "were", "will", "with"};

        try {
            Status tweetStatus = TwitterObjectFactory.createStatus(String.valueOf(rawTweet));
            String tweet = tweetStatus.getText().toLowerCase();
            String tweet_array[] = tweet.split(" ");
            for (String p: tweet_array) {
                for (String word : stopword) {
                    if (p.contains(word)) {
                        context.write(new Text(word), new IntWritable(1));

                    }
                }
            }
        } catch (TwitterException e1) {
        Logger logger = LogManager.getRootLogger();
        logger.trace("Bad Tweet: " + stopword);
        }


    }


}

