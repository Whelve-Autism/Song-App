package musicPlayer.player.file;

import java.io.RandomAccessFile;

/**
 * 此接口用于读取文件并返回一个 RandomAccessFile 对象，用于后续解析。该对象可以进行文件读取、写入等操作。
 * This interface is used to read the file and return a RandomAccessFile object for subsequent parsing. This object can perform file reading, writing and other operations.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public interface MyFIleReader {
    RandomAccessFile Read(String path);
}
/*
 * End of musicPlayer.player.file.MyFIleReader Interface.
 * Checked by Fan Xinkang on 2025/04/06.
 */