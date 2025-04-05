package musicPlayer.player.note;

/**
 * 此接口用于监听主调的变化。
 * This interface is used to listen to major changes.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public interface OnMajorChangedListener {

    /**
     * 主调变化时调用此方法。
     * This method is called when the major changes.
     *
     * @param old 变化前的主调
     *            The major before the change.
     * @param come 变化后的主调
     *             The major after the change.
     * @author qguangyao
     * @since version 6.0
     */
    void onMajorChange(MyNote.Major old, MyNote.Major come);
}
/*
 * End of musicPlayer.player.note.OnMajorChangedListener Interface.
 * Checked by Fan Xinkang on 2025/04/06.
 */