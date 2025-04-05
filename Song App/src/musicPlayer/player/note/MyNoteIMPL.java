package musicPlayer.player.note;

/**
 * 此类用于实现音符的解析。
 * This class is used to resolve the note.
 *
 * @author qguangyao, Fan Xinkang
 * @version 6.0
 * @since version 6.0
 */
public class MyNoteIMPL implements MyNote {

    /*
      相对于简谱的主调，0代表C，1代表C#，2代表D，3代表D#，4代表E，5代表F，6代表F#，7代表G，8代表G#，9代表A，10代表A#，11代表B。
      Compared with the main tone of the simplified spectrum, 0 represents C, 1 represents C#, 2 represents D, 3 represents D#, 4 represents E, 5 represents F, 6 represents F#, 7 represents G, 8 represents G#, 9 represents A, 10 represents A#, and 11 represents B.
     */
    private Major major;

    /*
      一个节拍里面有几个 tick，默认为32。
      The number of ticks in one beat is 32 by default.
     */
    private String ppq;

    /*
      曲谱速度，默认为120。
      The speed of the score is 120 by default.
     */
    private int bpm;

    /*
      播放频道，默认为0。
      The play channel is 0 by default.
     */
    private int channel = 0;

    /*
      乐器，2电钢琴，40小提琴。
      Instrument, 2 electric piano, 40 violin.
     */
    private int instrument = InstrumentEnum.ACOUSTIC_GRAND_PIANO.value;

    /*
      音符之间的分隔符，默认为"|"。
      The separator between notes is "|" by default.
     */
    private String explan;

    /*
      右手tick标记，默认为false。
      The right hand tick mark is false by default.
     */
    private boolean rightStart;

    /*
      左手tick标记，默认为false。
      The left hand tick mark is false by default.
     */
    private boolean leftStart;

    private final int baseTick = 32;

    /*
      每个小节的长度，默认为32。
      The length of each bar is 32 by default.
     */
    private int barTick = baseTick;
    private OnMajorChangedListener majorChangedListener;
    private OnPPQChangedListener ppqChangedListener;
    private OnBPMChangedListener bpmChangedListener;

    public int getInstrument() {
        return instrument;
    }

    public void setInstrument(int instrument) {
        this.instrument = instrument;
    }

    public Major getMajor() {
        return major;
    }

    public MyNoteIMPL() {
        this.major = Major.C;
    }

    public String getExplan() {
        return explan;
    }

    public void setExplan(String explan) {
        this.explan = explan;
    }

    public void setMajorChangedListener(OnMajorChangedListener majorChangedListener) {
        this.majorChangedListener = majorChangedListener;
    }

    public void setPpqChangedListener(OnPPQChangedListener ppqChangedListener) {
        this.ppqChangedListener = ppqChangedListener;
    }

    public void setBpmChangedListener(OnBPMChangedListener bpmChangedListener) {
        this.bpmChangedListener = bpmChangedListener;
    }

    public String getPpq() {
        return ppq;
    }

    public int getBpm() {
        return bpm;
    }

    public boolean isRightStart() {
        return rightStart;
    }

    public boolean isLeftStart() {
        return leftStart;
    }

    /**
     * 设置主调，默认为C。
     * Set the main scale, default is C.
     *
     * @param major 主调。
     *              Major.
     * @return MyNoteIMPL.
     * @author qguangyao
     * @since version 6.0
     */
    @Override
    public MyNote setMajor(Major major) {
        if (majorChangedListener != null) {
            majorChangedListener.onMajorChange(this.major, major);
        }
        this.major = major;
        return this;
    }

    /**
     * 设置拍子，默认为4/4。
     * Set the beat, default is 4/4.
     *
     * @param ppq 拍子。
     *            PPQ.
     * @return MyNoteIMPL.
     * @author qguangyao
     * @since version 6.0
     */
    @Override
    public MyNote setPPQ(String ppq) {
        if (ppqChangedListener != null) {
            ppqChangedListener.onPPQChanged(this.ppq, ppq);
        }
        this.ppq = ppq;
        return this;
    }

    /**
     * 设置BPM，默认为120。
     * Set the BPM, default is 120.
     *
     * @param bpm BPM.
     * @return MyNoteIMPL.
     * @author qguangyao
     * @since version 6.0
     */
    @Override
    public MyNote setBPM(int bpm) {
        if (bpmChangedListener != null) {
            bpmChangedListener.OnBPMChanged(this.bpm, bpm);
        }
        this.bpm = bpm;
        return this;
    }

    /**
     * 设置右边开始标记。
     * Start marking on the right.
     *
     * @param rightStart 右边开始标记。
     *                   Start marking on the right.
     * @return MyNoteIMPL.
     * @author qguangyao
     * @since version 6.0
     */
    @Override
    public MyNote setRightStart(boolean rightStart) {
        this.rightStart = rightStart;
        return this;
    }

    /**
     * 设置左边开始标记。
     * Start marking on the left.
     *
     * @param leftStart 左边开始标记。
     *                  Start marking on the left.
     * @return MyNoteIMPL.
     * @author qguangyao
     * @since version 6.0
     */
    @Override
    public MyNote setLeftStart(boolean leftStart) {
        this.leftStart = leftStart;
        return this;
    }

    /**
     * 获取中央区升音。
     * Get the central area up.
     *
     * @param area 基于中央区升降几个区域。
     *             Area based on the central area.
     * @return int.
     * @author qguangyao
     * @since version 6.0
     */
    public int getDo(int area) {
        return major.value + 12 * area;
    }

    public int getDoUp(int area) {
        return major.value + 1 + 12 * area;
    }

    public int getRe(int area) {
        return major.value + 2 + 12 * area;
    }

    public int getReUp(int area) {
        return major.value + 3 + 12 * area;
    }

    public int getMi(int area) {
        return major.value + 4 + 12 * area;
    }

    public int getFa(int area) {
        return major.value + 5 + 12 * area;
    }

    public int getFaUp(int area) {
        return major.value + 6 + 12 * area;
    }

    public int getSol(int area) {
        return major.value + 7 + 12 * area;
    }

    public int getSolUp(int area) {
        return major.value + 8 + 12 * area;
    }

    public int getLa(int area) {
        return major.value + 9 + 12 * area;
    }

    public int getLaUp(int area) {
        return major.value + 10 + 12 * area;
    }

    public int getSi(int area) {
        return major.value + 11 + 12 * area;
    }

    /**
     * 设置主调。
     * Set the main scale.
     *
     * @param major 主调。
     *              Major.
     * @return MyNoteIMPL.
     * @author qguangyao
     * @since version 6.0
     */
    public MyNoteIMPL setMajor(String major) {
        if (major == null) return this;
        major = major.toLowerCase();
        switch (major) {
            case "a":
                setMajor(Major.A);
                break;
            case "a#":
                setMajor(Major.AUp);
                break;
            case "b":
                setMajor(Major.B);
                break;
            case "c":
                setMajor(Major.C);
                break;
            case "c#":
                setMajor(Major.CUp);
                break;
            case "d":
                setMajor(Major.D);
                break;
            case "d#":
                setMajor(Major.DUp);
                break;
            case "e":
                setMajor(Major.E);
                break;
            case "f":
                setMajor(Major.F);
                break;
            case "f#":
                setMajor(Major.FUp);
                break;
            case "g":
                setMajor(Major.G);
                break;
            case "g#":
                setMajor(Major.GUp);
                break;
            default:
                throw new RuntimeException("Invalid pitch.");
        }
        return this;
    }

    /**
     * 计算音符。
     * Calculate the note.
     *
     * @param code 音符代码。
     *             Code of the note.
     * @return 字符串。
     *         String.
     * @author qguangyao
     * @since version 6.0
     */
    public String calNoteNum(int code) {
        if (code == 0) {
            return "0";
        }
        int res = code - major.value;
        int note = res % 12;
        int area;
        StringBuilder prin = new StringBuilder();
        if (res < 0) {
            area = (res - 12) / 12;
            prin.append("l".repeat(Math.abs(area)));
        } else {
            area = res / 12;
            prin.append("h".repeat(area));
        }
        if (note < 0)
            note += 12;
        switch (Math.abs(note)) {
            case 0:
                prin.append("1");
                break;
            case 1:
                prin.append("1#");
                break;
            case 2:
                prin.append("2");
                break;
            case 3:
                prin.append("2#");
                break;
            case 4:
                prin.append("3");
                break;
            case 5:
                prin.append("4");
                break;
            case 6:
                prin.append("4#");
                break;
            case 7:
                prin.append("5");
                break;
            case 8:
                prin.append("5#");
                break;
            case 9:
                prin.append("6");
                break;
            case 10:
                prin.append("6#");
                break;
            case 11:
                prin.append("7");
                break;
        }
        return prin.toString();
    }

    public int getBaseTick() {
        return baseTick;
    }

    public int getBarTick() {
        return barTick;
    }

    public void setBarTick(int barTick) {
        this.barTick = barTick;
    }

    public int getChannel() {
        return channel;
    }

    public void setChannel(int channel) {
        this.channel = channel;
    }
}
/*
 * End of musicPlayer.java.player.note.MyNoteIMPL Class.
 * Checked by Fan Xinkang on 2025/04/05.
 */