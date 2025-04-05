package musicPlayer.player.resolver;


import musicPlayer.player.note.MyNoteIMPL;
import musicPlayer.player.note.NoteInfo;

/**
 * 此接口用于处理 appoggiatura 音符，包括 appoggiatura 上升音符和 appoggiatura 下降音符。
 * This interface is used to handle appoggiatura notes, including appoggiatura ascending and appoggiatura descending notes.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public interface AppoggiaturaResolver {

    /**
     * appoggiatura 处理。
     * appoggiatura processing.
     *
     * @param noteFrontString appoggiatura 上升音符。
     *                        appoggiatura ascending note.
     * @param noteAfterString appoggiatura 下降音符。
     *                        appoggiatura descending note.
     * @param noteStart       appoggiatura 开始位置。
     *                        appoggiatura start position.
     * @param myNoteIMPL      myNoteIMPL
     * @param singleNoteResolver singleNoteResolver
     * @return appoggiatura 处理后的音符数组。
     *         The array of notes processed by appoggiatura.
     * @author qguangyao
     * @since version 6.0
     */
    NoteInfo[] appoggiatura(String noteFrontString, String noteAfterString, int noteStart, MyNoteIMPL myNoteIMPL, SingleNoteResolver singleNoteResolver);
}
/*
 * End of musicPlayer.player.resolver.AppoggiaturaResolver Interface.
 * Checked by Fan Xinkang on 2025/04/06.
 */