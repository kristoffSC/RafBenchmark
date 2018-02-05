package org.home.benchmark;

import org.home.benchmark.file.WriterRandomAccessFile;

public class App {

  public static void main(String[] args) {
    
  
    WriterRandomAccessFile.writeConvertedInts("writeConvertedInts.txt", 1000000);
    WriterRandomAccessFile.writeDirectInts("writeDirectInts.txt", 1000000);
    
   
  }

}
