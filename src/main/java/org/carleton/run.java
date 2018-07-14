package org.carleton;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.api.common.JobExecutionResult;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.source.SourceFunction;
import org.apache.flink.streaming.connectors.twitter.TwitterSource;
import org.apache.flink.util.Collector;
import java.util.Properties;

public class run {

    public static final String ANSI_BLUE = "\u001B[34m";

    public static void main(String[] args)  throws Exception {

        StreamExecutionEnvironment envrionment = StreamExecutionEnvironment.getExecutionEnvironment();

        Properties props = new Properties();
        props.setProperty(TwitterSource.CONSUMER_KEY, keys.CONSUMER_KEY);
        props.setProperty(TwitterSource.CONSUMER_SECRET, keys.CONSUMER_SECRET);
        props.setProperty(TwitterSource.TOKEN, keys.TOKEN);
        props.setProperty(TwitterSource.TOKEN_SECRET, keys.TOKEN_SECRET);
        DataStream<String> streamSource = envrionment.addSource(new TwitterSource(props));
        DataStreamSource<String> twitterSource = envrionment.addSource((SourceFunction<String>) new TwitterSource(props));

//      twitterSource.print();

        DataStream<Tuple2<String, Integer>> tweets = streamSource
                .flatMap(new MyTokanizer())
                .keyBy(0);

        tweets.map(new MapFunction<Tuple2<String,Integer>, Object>() {
            @Override
            public Object map(Tuple2<String, Integer> stringIntegerTuple2) {
//                System.out.println((String) stringIntegerTuple2.getField(0));
                String data = stringIntegerTuple2.getField(0);
                if(data.contains("movie"))
                { System.out.println(ANSI_BLUE + "Alert !!!! " + data  + "\n"); }
                return null;
            }
        });

       JobExecutionResult jobExecutionResult = envrionment.execute(" Started the execution ");
    }// main

    public static class MyTokanizer implements FlatMapFunction<String, Tuple2<String, Integer>> {
        private static final long serialVersionUID = 1L;
        private transient ObjectMapper jsonParser;
        /**
         * Select the language from the incoming JSON text.
         */
        @Override
        public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
            if (jsonParser == null) {
                jsonParser = new ObjectMapper();
            }
            JsonNode jsonNode = jsonParser.readValue(value, JsonNode.class);
            boolean isEnglish = jsonNode.has("user") && jsonNode.get("user").has("lang") && jsonNode.get("user").get("lang").asText().equals("en");
            boolean hasText = jsonNode.has("text");
            if (isEnglish && hasText) {
                // message of tweet
                String textTweet = jsonNode.get("text").asText();
                out.collect(new Tuple2<>(textTweet, 1));
            }
        }
    }
} //class



