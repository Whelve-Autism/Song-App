package musicPlayer.player.note;

/**
 * 此接口用于监听 PPQ 的变化。
 * This interface is used to listen for changes in PPQ.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public interface OnPPQChangedListener {

    /**
     * 当 PPQ 变化时调用此方法。
     * This method is called when PPQ changes.
     *
     * @param old 旧 PPQ。
     *            The old PPQ.
     * @param come 新 PPQ。
     *             The new PPQ.
     * @author qguangyao
     * @since version 6.0
     */
    void onPPQChanged(String old,String come);
}
/*
 * End of musicPlayer.player.note.OnPPQChangedListener Interface.
 * Checked by Fan Xinkang on 2025/04/06.
 */