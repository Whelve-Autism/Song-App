package musicPlayer.player.note;

/**
 * 此类用于存储音符信息。
 * This class is used to store note information.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public class NoteInfo implements Comparable<NoteInfo> {

    /*
      音符开始。
      The start of the note.
     */
    public long originTick;

    /*
      音符音高。
      The pitch of the note.
     */
    public int note = -1;

    /*
      音符长度。
      The length of the note.
     */
    public int noteTick = 0;

    /*
      音符从 @originTick 开始后经过 @muteTick 个节拍静音。
      The note is muted after @muteTick ticks after @originTick.
     */
    public int muteTick = 0;

    /*
      音符音量。
      The volume of the note.
     */
    public int volume = 100;

    /*
      播放的时间点。
      The play time of the note.
     */
    public long playTime = 0;

    /*
      播放的 bpm。
      The bpm of the note.
     */
    public int bpm = -1;

    /*
      在哪个频道。
      The channel of the note.
     */
    public int channel = 0;

    /*
      乐器。
      The instrument of the note.
     */
    public int instrument = 0;

    /**
     * 获取音符长度。
     * Get the length of the note.
     *
     * @return 音符长度。
     *         The length of the note.
     * @author qguangyao
     * @since version 6.0
     */
    public int getNoteLength() {
        if (muteTick == 0) {
            return noteTick;
        } else {
            return Math.min(noteTick, muteTick);
        }

    }

    /**
     * 比较两个音符。
     * Compare two notes.
     *
     * @param o 另一个音符。
     *          Another note.
     * @return 音符开始时间。
     *         The start time of the note.
     * @author qguangyao
     * @since version 6.0
     */
    @Override
    public int compareTo(NoteInfo o) {
        return (int) (originTick - o.originTick);
    }


    public long getOriginTick() {
        return originTick;
    }

    public void setOriginTick(long originTick) {
        this.originTick = originTick;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public int getNoteTick() {
        return noteTick;
    }

    public void setNoteTick(int noteTick) {
        this.noteTick = noteTick;
    }

    public int getMuteTick() {
        return muteTick;
    }

    public void setMuteTick(int muteTick) {
        this.muteTick = muteTick;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
    }

    public void setBpm(int bpm) {
        this.bpm = bpm;
    }
}
/*
 * End of musicPlayer.player.note.NoteInfo Interface.
 * Checked by Fan Xinkang on 2025/04/06.
 */