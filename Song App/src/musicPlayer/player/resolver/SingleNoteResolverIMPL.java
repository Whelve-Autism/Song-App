package musicPlayer.player.resolver;


import musicPlayer.player.note.MyNoteIMPL;
import musicPlayer.player.note.NoteInfo;

/**
 * 此类用于解析单个音符。
 * This class is used to resolve a single note.
 *
 * @return appoggiatura 处理后的音符数组。
 *         The array of notes processed by appoggiatura.
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public class SingleNoteResolverIMPL implements SingleNoteResolver {

    /**
     * 打哪个音符解析，解析后返回一个音符数组。
     * Parse which note, and return an array of notes after parsing.
     *
     * @param myNoteIMPL 音符映射类。
     *                   The note mapping class.
     * @param noteString 从file文件里读来的音符。
     *                   The note read from the file.
     * @param originTick 音符的开始时刻,相对于整首曲子。
     *                   The start time of the note, relative to the whole song.
     * @return noteInfo 解析后的音符。
     *         The note processed by parsing.
     * @author qguangyao
     * @since version 6.0
     */
    @Override
    public NoteInfo singleNoteResolve(MyNoteIMPL myNoteIMPL , String noteString, int originTick) {
        NoteInfo noteInfo = new NoteInfo();
        noteInfo.originTick = originTick;
        noteInfo.channel = myNoteIMPL.getChannel();
        noteInfo.instrument = myNoteIMPL.getInstrument();
        if (noteString == null || noteString.isEmpty()) {
            return null;
        }
        int tick = myNoteIMPL.getBaseTick();
        int deta = 0;
        int note = -1;
        boolean hasPoint = false;
        int volume = 100;
        int noteMuteTick = 0;
        char[] separatedNote = noteString.toCharArray();
        for (char c : separatedNote) {
            if (c == '+') {
                tick = tick + myNoteIMPL.getBaseTick();
            } else if (c == '-') {
                tick = tick >> 1;
            } else if (c == 'l') {
                deta -= 12;
            } else if (c == 'h') {
                deta += 12;
            } else if (c == 'u') {
                deta += 1;
            } else if (c == 'b') {
                deta -= 1;
            } else if (c == '0') {
                note = 0;
                volume = 0;
            } else if (c == '1') {
                note = myNoteIMPL.getDo(0);
            } else if (c == '2') {
                note = myNoteIMPL.getRe(0);
            } else if (c == '3') {
                note = myNoteIMPL.getMi(0);
            } else if (c == '4') {
                note = myNoteIMPL.getFa(0);
            } else if (c == '5') {
                note = myNoteIMPL.getSol(0);
            } else if (c == '6') {
                note = myNoteIMPL.getLa(0);
            } else if (c == '7') {
                note = myNoteIMPL.getSi(0);
            } else if (c == '.') {
                hasPoint = true;
            } else if (c == '^') {
                volume = 0;
            } else if (c == '>') {
                noteMuteTick++;
            }
        }
        if (hasPoint) {
            tick = tick * 3 / 2;
        }
        noteInfo.noteTick = tick;
        if (noteMuteTick > 0) {
            noteMuteTick = tick * noteMuteTick / 4; // noteEndTick = baseTick * 4;
        } else {
            noteMuteTick = noteInfo.noteTick;
        }
        noteInfo.muteTick = noteMuteTick;
        noteInfo.volume = volume;
        noteInfo.note = note + deta;
        return noteInfo;
    }
}
/*
 * End of musicPlayer.player.resolver.SingleNoteResolverIMPL Class.
 * Checked by Fan Xinkang on 2025/04/06.
 */