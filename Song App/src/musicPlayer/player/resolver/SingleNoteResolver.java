package musicPlayer.player.resolver;


import musicPlayer.player.note.MyNoteIMPL;
import musicPlayer.player.note.NoteInfo;

/**
 * 此接口用于解析单个音符，并返回一个 NoteInfo 对象。
 * This interface is used to resolve a single note and return a NoteInfo object.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public interface SingleNoteResolver {

    /**
     * 解析单个音符，并返回一个 NoteInfo 对象。
     * This method resolves a single note and returns a NoteInfo object.
     *
     * @param myNoteIMPL  MyNoteIMPL 对象，用于获取音符信息。
     *                    MyNoteIMPL object used to get note information.
     * @param noteString  音符字符串，用于解析音符信息。
     *                    The note string used to parse note information.
     * @param originTick  音符起始时间，用于计算音符的播放时间。
     *                    The origin tick of the note, used to calculate the play time of the note.
     * @return 解析后的 NoteInfo 对象。
     *         The resolved NoteInfo object.
     * @author qguangyao
     * @since version 6.0
     */
    NoteInfo singleNoteResolve(MyNoteIMPL myNoteIMPL, String noteString, int originTick);
}
/*
 * End of musicPlayer.player.resolver.SingleNoteResolver Interface.
 * Checked by Fan Xinkang on 2025/04/06.
 */