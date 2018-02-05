package org.home.benchmark.jmh;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class BenchmarkDirectIntSetupTest
{
  public RandomAccessFile raf;
  
  public BenchmarkDirectIntSetupTest() {
    try {
      raf = new RandomAccessFile("jmhDirectIntBenchamrk.ser", "rw");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
