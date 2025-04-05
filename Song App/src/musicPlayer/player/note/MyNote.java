package musicPlayer.player.note;

/**
 * 此接口用于封装音符的信息。
 * This interface is used to encapsulate the information of a note.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public interface MyNote {

    /**
     * 此枚举用于封装音符的音高。
     * This enum is used to encapsulate the pitch of a note.
     *
     * @author qguangyao
     * @version 6.0
     * @since version 6.0
     */
    enum Major {
        A(57), AUp(58), B(59), C(60), CUp(61), D(62),
        DUp(63), E(64), F(65), FUp(66), G(67), GUp(68);
        public final int value;

        Major(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /*
      设置音符的音高，返回当前音符对象。
      Set the pitch of the note, and return the current note object.
     */
    MyNote setMajor(Major major);
    MyNote setPPQ(String ppq);
    MyNote setBPM(int bpm);
    MyNote setRightStart(boolean rightStart);
    MyNote setLeftStart(boolean leftStart);
}
/*
 * End of musicPlayer.player.note.MyNote Interface.
 * Checked by Fan Xinkang on 2025/04/06.
 */