package org.home.benchmark.jmh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@BenchmarkMode(value = { Mode.AverageTime })
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@Warmup(iterations = 4, time = 4, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 4, time = 4, timeUnit = TimeUnit.SECONDS)
public class RandomAccessWriteFileTest {

  
  public static void main(String[] args) throws Exception {
    Options opt = new OptionsBuilder().include(RandomAccessWriteFileTest.class.getSimpleName())
            .jvmArgs("-ea")
            .shouldFailOnError(true)
            .build();
    new Runner(opt).run();
}

@Benchmark()
@Fork(1)
public long benchamrkWriteDirectInt(BenchmarkPlainIntSetup setupTest) {
    try {
        setupTest.raf.writeInt(6969);
        return setupTest.raf.length();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

@Benchmark()
@Fork(1)
public long benchamrkWriteConvertedInt(BenchmarkConvertedIntSetup setupTest) {
    try {
        setupTest.raf.write(intToBytes(6969));
        return setupTest.raf.length();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
}

private static byte[] intToBytes(int i) {
    byte[] result = new byte[4];

    result[0] = (byte) (i >> 24);
    result[1] = (byte) (i >> 16);
    result[2] = (byte) (i >> 8);
    result[3] = (byte) i;

    return result;
}

@State(Scope.Thread)
static public class BenchmarkConvertedIntSetup {

    public RandomAccessFile raf;

    public File f;

    @Setup(Level.Iteration)
    public void setUp() {
        try {
            f = new File("jmhDirectIntBenchamrk.ser" + ThreadLocalRandom.current().nextInt());
            raf = new RandomAccessFile(f, "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @TearDown(Level.Iteration)
    public void tearDown() {
        f.delete();
    }
}

@State(Scope.Thread)
static public class BenchmarkPlainIntSetup {

    public RandomAccessFile raf;

    public File f;

    @Setup(Level.Iteration)
    public void setUp() {
        try {
            f = new File("jmhDirectIntBenchamrk.ser" + ThreadLocalRandom.current().nextInt());
            raf = new RandomAccessFile(f, "rw");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @TearDown(Level.Iteration)
    public void tearDown() {
        f.delete();
    }
}
  
}
