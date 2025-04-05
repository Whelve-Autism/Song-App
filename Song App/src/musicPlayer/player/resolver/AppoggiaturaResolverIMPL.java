package musicPlayer.player.resolver;

import musicPlayer.player.note.MyNoteIMPL;
import musicPlayer.player.note.NoteInfo;

/**
 * 此类用于解析倚音。
 * This class is used to resolve appoggiatura.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public class AppoggiaturaResolverIMPL implements AppoggiaturaResolver {

    /**
     * 钢琴技法，倚音的实现，此处定义倚音为一前一后。
     * Piano technique, the realization of Yiyin, here define Yiyin as one before and one after.
     *
     * @param noteFrontString 倚音前边音符。
     *                        The notes in front of the leaning sound.
     * @param noteAfterString 倚音后边音符。
     *                        The notes in front of the leaning sound.
     * @param noteStart 音符开始的时间,相对于整首曲子。
     *                  The notes in front of the leaning sound.
     * @param myNoteIMPL 音符映射类。
     *                   The notes in front of the leaning sound.
     * @param singleNoteResolver 单个音符解析器。
     *                           The notes in front of the leaning sound.
     * @return 音符信息的数组。
     *         The notes in front of the leaning sound.
     * @throws Exception 解析异常。
     *                   Analysis abnormality.
     * @author qguangyao
     * @since version 6.0
     */
    @Override
    public NoteInfo[] appoggiatura(String noteFrontString, String noteAfterString, int noteStart, MyNoteIMPL myNoteIMPL, SingleNoteResolver singleNoteResolver) {
        NoteInfo noteInfo0 = singleNoteResolver.singleNoteResolve(myNoteIMPL, noteFrontString, noteStart);
        NoteInfo noteInfo1 = singleNoteResolver.singleNoteResolve(myNoteIMPL, noteAfterString, noteStart);
        return appoggiatura(noteInfo0, noteInfo1, myNoteIMPL);
    }

    /**
     * 倚音解析。
     * Appoggiatura analysis.
     *
     * @param info1 前边音符。
     *              The notes in front of the leaning sound.
     * @param info2 后边音符。
     *              The notes in front of the leaning sound.
     * @param myNoteIMPL 音符映射类。
     *                   The notes in front of the leaning sound.
     * @return 音符信息的数组。
     *         The notes in front of the leaningsound.
     * @author qguangyao
     * @since version 6.0
     */
    private NoteInfo[] appoggiatura(NoteInfo info1, NoteInfo info2, MyNoteIMPL myNoteIMPL) {
        info1.noteTick = myNoteIMPL.getBaseTick() / 16;
        info2.noteTick = info2.noteTick - info1.noteTick;
        info2.originTick += info1.noteTick;
        return new NoteInfo[]{info1, info2};
    }
}
/*
 * End of musicPlayer.player.resolver.AppoggiaturaResolverIMPL Class.
 * Checked by Fan Xinkang on 2025/04/06.
 */