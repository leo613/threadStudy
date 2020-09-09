package com.qd.pattern;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.concurrent.FutureTask;

@Fork(1) //fork 进行的数目
@BenchmarkMode(Mode.AverageTime) //结果使用的世间单位
@Warmup(iterations =4) //先预热4轮
@Measurement(iterations = 5) // 进行5轮测试
public class MyBenchmark {
    static int[] ARRAY= new int[10000];
     static {
         Arrays.fill(ARRAY,1);
     }

     @Benchmark
    public int c() throws Exception{
         int [] array=ARRAY;
         FutureTask<Integer> t1=new FutureTask<Integer>(()->{
             int sum=0;
             for (int i = 0; i < 10000; i++) {
                 sum += array[0+i];
             }
             return sum;
         });

         FutureTask<Integer> t2=new FutureTask<Integer>(()->{
             int sum=0;
             for (int i = 0; i < 10000; i++) {
                 sum += array[0+i];

             }
             return sum;
         });
         FutureTask<Integer> t3=new FutureTask<Integer>(()->{
             int sum=0;
             for (int i = 0; i < 10000; i++) {
                 sum += array[0+i];

             }
             return sum;
         });

         FutureTask<Integer> t4=new FutureTask<Integer>(()->{
             int sum=0;
             for (int i = 0; i < 10000; i++) {
                 sum += array[0+i];

             }
             return sum;
         });

         new Thread(t1).start();
         new Thread(t2).start();
         new Thread(t3).start();
         new Thread(t4).start();

         return t1.get()+t2.get()+t3.get()+t4.get();
     }

    @Benchmark
    public int d() throws Exception{
        int [] array=ARRAY;
        FutureTask<Integer> t1=new FutureTask<Integer>(()->{
            int sum=0;
            for (int i = 0; i < 10000; i++) {
                 sum += array[0+i];
            }
          return sum;
        });
        new Thread(t1).start();
        return t1.get();
    }

    public static void main(String[] args) {
        Options opt = new OptionsBuilder()
                .include(MyBenchmark.class.getSimpleName())
                .build();
        try {
            new Runner(opt).run();
        } catch (RunnerException e) {
            e.printStackTrace();
        }
        MyBenchmark my=new MyBenchmark();
        try {
            int c = my.c();
            System.out.println(c);
            int d = my.d();
            System.out.println(d);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
