package musicPlayer.player.note;

/**
 * 此接口用于监听 BPM 的变化。
 * This interface is used to listen to BPM changes.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public interface OnBPMChangedListener {

    /**
     * BPM 变化时调用此方法。
     * Called when BPM changes.
     * @param old 旧 BPM。
     *            The old BPM.
     * @param come 新 BPM。
     *             The new BPM.
     * @author qguangyao
     * @since version 6.0
     */
    void OnBPMChanged(int old,int come);
}
/*
 * End of musicPlayer.player.note.OnBPMChangedListener Interface.
 * Checked by Fan Xinkang on 2025/04/06.
 */