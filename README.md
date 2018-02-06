#  RAF writeInt(int i) vs write(byte[] b) performance

The RandomAccessFile's write(byte[] b) with converting int to byte[] array seems significant faster than writeInt(int i). Why?

JMH Results:
|Benchmark                                             |Mode  | Cnt | Score   | Error | Units |
|------------------------------------------------------|------|:---:|---------|-------|-------|
|RandomAccessWriteFileTest.benchamrkWriteConvertedInt  |avgt  |  5  | 0,004 ± | 0,001 | ms/op |
|RandomAccessWriteFileTest.benchamrkWriteDirectInt     |avgt  |  5  | 0,011 ± | 0,001 | ms/op |