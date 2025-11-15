package com.example.flink;

import org.apache.flink.api.common.eventtime.WatermarkStrategy;
import org.apache.flink.api.common.restartstrategy.RestartStrategies;
import org.apache.flink.api.common.time.Time;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.time.Duration;
import java.util.Arrays;

/**
 * Minimal Apache Flink streaming job.
 *
 * It reads a small in-memory collection, transforms it, and prints to stdout.
 * This is enough to verify your toolchain is set up and Flink runs locally.
 */
public class BasicJob {
    public static void main(String[] args) throws Exception {
        // Set up the streaming execution environment
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // Some conservative local defaults
        env.getConfig().setAutoWatermarkInterval(200L);
        env.setRestartStrategy(RestartStrategies.fixedDelayRestart(1, Time.seconds(1)));

        // A tiny in-memory stream
        DataStream<String> input = env
                .fromCollection(Arrays.asList("hello", "flink", "hello", "junie"))
                .assignTimestampsAndWatermarks(WatermarkStrategy
                        .<String>forBoundedOutOfOrderness(Duration.ZERO));

        // Do a simple transform
        DataStream<String> output = input
                .map(s -> s.toUpperCase())
                .name("to-upper");

        // Print to stdout (for local verification)
        output.print();

        // Execute the program
        env.execute("Basic Flink Job");
    }
}
