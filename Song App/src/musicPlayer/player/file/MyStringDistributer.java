package musicPlayer.player.file;

import java.io.RandomAccessFile;

/**
 * 此接口用于行解析，分发给相应的解析行为。
 * This interface is used for line analysis and distributed to the corresponding analysis behavior.
 *
 * @author qgaungyao
 * @version 6.0
 * @since version 6.0
 */
public interface MyStringDistributer {

    /**
     * 行解析类型枚举。
     * Line analysis type enumeration.
     *
     * @author qgaungyao
     * @version 6.0
     */
    enum Type {
        MAJOR("major"),
        PPQ("ppq"),
        BPM("bpm"),
        RIGHT_START("rightStart"),
        RIGHT_END("rightEnd"),
        LEFT_START("leftStart"),
        LEFT_END("leftEnd"),
        EXPLANATORY_NOTE("//"),
        INSTRUMENT("instrument"),
        CHANNEL("channel");

        public final String value;

        /**
         * 构造函数，初始化value。
         * Constructor, initialize value.
         *
         * @param value 值。
         *              Value.
         * @author qgaungyao
         * @since version 6.0
         */
        Type(String value) {
            this.value = value.toLowerCase();
        }
    }

    /**
     * 分隔符枚举。
     * Separator enumeration.
     *
     * @author qgaungyao
     * @version 6.0
     */
    enum SEPARATOR {
        SPACE(" "),
        COMMA(",")
        ;
        public final String value;
        SEPARATOR(String value){
            this.value = value;
        }
    }

    /**
     * 行解析分发行为。
     * Line analysis distribution behavior.
     *
     * @param randomAccessFile 随机访问文件。
     *                         Random access file.
     * @author qgaungyao
     * @since version 6.0
     */
    void distribute(RandomAccessFile randomAccessFile);
    int lineNoteDistributeNote(String line, int lineNum, int lineStart);
}
/*
 * End of musicPlayer.player.file.MyStringDistributer Interface.
 * Checked by Fan Xinkang on 2025/04/06.
 */