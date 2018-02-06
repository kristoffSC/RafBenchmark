#  RAF writeInt(int i) vs write(byte[] b) performance

The RandomAccessFile's write(byte[] b) with converting int to byte[] array seems significant faster than writeInt(int i). Why?

Results are in: https://github.com/kristoffSC/RafBenchmark/blob/master/JMH_Results.txt 