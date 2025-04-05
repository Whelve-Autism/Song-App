package musicPlayer.player.file;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 此类用于读取txt文件。
 * This class is used to read txt file.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public class MyFileReaderTxt implements MyFIleReader {

    /**
     * 读取txt文件，如果文件不存在则创建文件，并写入默认值。
     * Read txt file, if file not exist then create file and write default value.
     *
     * @param path 文件路径。
     *             Path of file.
     * @return 随机访问文件。
     *         Random access file.
     * @author qguangyao
     * @since version 6.0
     */
    @Override
    public RandomAccessFile Read(String path) {
        File file = null;
        RandomAccessFile rf = null;
        try {
            file = new File(path);
            if (!file.exists()){
                file.createNewFile();
                rf = new RandomAccessFile(file,"rw");
                String bootstrap = """
                        major:F#
                        PPQ:4/4
                        BPM:80
                        rightStart
                        
                        rightEnd
                        leftStart
                        
                        leftEnd""";
                rf.writeBytes(bootstrap);
            }else {
                rf = new RandomAccessFile(file,"rw");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return rf;
    }
}
/*
 * End of musicPlayer.player.file.MyFIleReaderTxt Class.
 * Checked by Fan Xinkang on 2025/04/06.
 */