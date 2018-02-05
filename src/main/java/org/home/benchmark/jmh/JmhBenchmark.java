package org.home.benchmark.jmh;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Warmup;

public class JmhBenchmark {

  
  public static void main(String[] args) throws Exception    {
    org.openjdk.jmh.Main.main(args);
  }
  
  
  @Benchmark()
  @BenchmarkMode(Mode.SampleTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Warmup(iterations = 10)
  @Fork(value = 2)
  public void benchamrkWriteDirectInt(BenchmarkDirectIntSetupTest setupTest)
  {
    try {
      setupTest.raf.writeInt(6969);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  @Benchmark()
  @BenchmarkMode(Mode.SampleTime)
  @OutputTimeUnit(TimeUnit.NANOSECONDS)
  @Warmup(iterations = 10)
  @Fork(value = 2)
  public void benchamrkWriteConvertedInt(BenchmarkConvertedIntSetupTest setupTest)
  {
    try {
      setupTest.raf.write(intToBytes(6969));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
  
  
  private static byte[] intToBytes(int i)
  {
    byte[] result = new byte[4];

    result[0] = (byte) (i >> 24);
    result[1] = (byte) (i >> 16);
    result[2] = (byte) (i >> 8);
    result[3] = (byte) (i);

    return result;
  }
  
}
