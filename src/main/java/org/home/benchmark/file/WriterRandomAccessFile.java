package org.home.benchmark.file;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

public class WriterRandomAccessFile 
{

  private WriterRandomAccessFile(String filePath) { }
  
  public static void writeDirectInts(String path,  int numberOfWrites)
  {
    
    StopWatch sw = new StopWatch();
    try (RandomAccessFile raf = new RandomAccessFile(new File(path), "rw")) 
    {
      sw.start();
      for (int i = 0; i < numberOfWrites; i++)
      {
        raf.writeInt(6969);
      }
      sw.stop();
      
      TimeUnit unit = TimeUnit.MILLISECONDS;
      System.out.println("Direct Write Took " + sw.getTime(unit) + " " + unit);
      
    } 
    catch (Exception e) 
    {
      throw new RuntimeException(e);
    }
  }
  
  public static void writeConvertedInts(String path,  int numberOfWrites)
  {
    
    StopWatch sw = new StopWatch();
    try (RandomAccessFile raf = new RandomAccessFile(new File(path), "rw")) 
    {
      sw.start();
      for (int i = 0; i < numberOfWrites; i++)
      {
        raf.write(intToBytes(6969));
      }
      sw.stop();
      
      TimeUnit unit = TimeUnit.MILLISECONDS;
      System.out.println("Converted Write Took " + sw.getTime(unit) + " " + unit);
      
    } 
    catch (Exception e) 
    {
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
