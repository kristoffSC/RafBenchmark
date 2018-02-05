package org.home.benchmark.jmh;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;

import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

@State(Scope.Benchmark)
public class BenchmarkConvertedIntSetupTest {
 
  public RandomAccessFile raf;
  
  public BenchmarkConvertedIntSetupTest() {
    try {
      raf = new RandomAccessFile("jmhConvertedIntBenchamrk.ser", "rw");
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
}
