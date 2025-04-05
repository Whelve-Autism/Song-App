package musicPlayer.player.file;

import musicPlayer.player.note.InstrumentEnum;
import musicPlayer.player.note.MyNoteIMPL;
import musicPlayer.player.note.NoteInfo;
import musicPlayer.player.resolver.AppoggiaturaResolverIMPL;
import musicPlayer.player.resolver.SingleNoteResolver;
import musicPlayer.player.resolver.SingleNoteResolverIMPL;

import javax.sound.midi.*;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 此类用于解析 .txt 文件，分发给相应的解析行为，将解析后的结果存储在 CopyOnWriteArrayList 中。
 * This class is used to parse .txt files, distribute the lines to the appropriate parsing behaviors, and store the parsed results in a CopyOnWriteArrayList.
 *
 * @author qguangyao, Fan Xinkang
 * @version 6.0
 * @since version 6.0
 */
public class MyStringDistributerIMPL implements MyStringDistributer {

    private MyNoteIMPL myNoteIMPL = new MyNoteIMPL();
    private Sequence sequence;
    private Track track;
    private final SingleNoteResolver singleNoteResolver = new SingleNoteResolverIMPL();
    private final AppoggiaturaResolverIMPL appoggiaturaResolverIMPL = new AppoggiaturaResolverIMPL();
    private static final String COLON = ":";
    private static final SEPARATOR separator = SEPARATOR.SPACE;

    CopyOnWriteArrayList<NoteInfo> noteInfoList = new CopyOnWriteArrayList<>();

    public int rightTick = 0;
    public int leftTick = 0;
    public int totalTick = 0;

    /**
     * 行解析分发方法，以行为单位，分发给相应的解析行为。
     * The line analysis distribution method is distributed to the corresponding analysis behavior in the unit of behavior.
     *
     * @param randomAccessFile 读取的文件。
     *                         The file to be read.
     * @author qguangyao, Fan Xinkang
     * @since version 6.0
     */
    @Override
    public void distribute(RandomAccessFile randomAccessFile) {

        /*
          清空列表，防止重复添加。
          Clear the list to prevent repeated additions.
         */
        noteInfoList.clear();
        if (randomAccessFile == null){
            return;
        }
        String line;
        int lineNumber = 0;
        rightTick = 0;
        leftTick = 0;

        /*
          读取文件，逐行解析。
          Read the file and parse the lines.
         */
        try {
            while ((line = randomAccessFile.readLine()) != null) {
                lineNumber++;
                if (line.replace(" ", "").isEmpty()) {
                    continue;
                }
                line = line.toLowerCase();

                if (line.startsWith(Type.MAJOR.value)) {
                    System.out.println(line);
                    myNoteIMPL.setMajor(line.replace(Type.MAJOR.value + COLON, ""));
                } else if (line.startsWith(Type.PPQ.value)) {
                    System.out.println(line);
                    myNoteIMPL.setPPQ(line.replace(Type.PPQ.value + COLON, ""));
                    String[] ppq = myNoteIMPL.getPpq().split("/");
                    int ppq0 = Integer.parseInt(ppq[0]);
                    int ppq1 = Integer.parseInt(ppq[1]);

                    /*
                      设置每小节时长。
                      Set the duration of each bar.
                     */
                    myNoteIMPL.setBarTick(myNoteIMPL.getBaseTick() * 4 * ppq0 / ppq1);
                    if (sequence == null) {

                        /*
                          创建 Sequence 和 Track，并设置乐器。
                          Create Sequence and Track, and set the instrument.
                         */
                        try {
                            sequence = new Sequence(Sequence.PPQ, myNoteIMPL.getBaseTick() * 4 / ppq1);
                            track = sequence.createTrack();
                            setInstrument(track, myNoteIMPL.getInstrument(), myNoteIMPL.getInstrument());
                        } catch (InvalidMidiDataException e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else if (line.startsWith(Type.BPM.value)) {
                    System.out.println(line);

                    /*
                      设置 BPM，并添加到列表中。
                      Set the BPM and add it to the list.
                     */
                    myNoteIMPL.setBPM(Integer.parseInt(line.replace(Type.BPM.value + COLON, "")));
                    NoteInfo noteInfo = new NoteInfo();

                    if (myNoteIMPL.isRightStart()) {
                        noteInfo.originTick = rightTick;
                    } else if (myNoteIMPL.isLeftStart()) {
                        noteInfo.originTick = leftTick;
                    }
                    noteInfo.setBpm(myNoteIMPL.getBpm());
                    noteInfoList.add(noteInfo);
                } else if (line.startsWith(Type.EXPLANATORY_NOTE.value)) {
                    myNoteIMPL.setExplan(line.replace(Type.EXPLANATORY_NOTE.value, ""));
                } else if (line.startsWith(Type.RIGHT_START.value)) {
                    rightTick = 0;
                    myNoteIMPL.setRightStart(true);
                } else if (line.startsWith(Type.LEFT_START.value)) {
                    leftTick = 0;
                    myNoteIMPL.setLeftStart(true);
                } else if (line.startsWith(Type.RIGHT_END.value)) {
                    myNoteIMPL.setRightStart(false);
                } else if (line.startsWith(Type.LEFT_END.value)) {
                    myNoteIMPL.setLeftStart(false);
                } else if (line.startsWith(Type.INSTRUMENT.value)){
                    line = line.replace(Type.INSTRUMENT.value+COLON,"").replace(Type.CHANNEL.value+COLON,"");
                    String[] arr = line.split(COLON);
                    myNoteIMPL.setInstrument(Integer.parseInt(arr[0]));
                    myNoteIMPL.setChannel(Integer.parseInt(arr[1]));
                    setInstrument(track, myNoteIMPL.getInstrument(), myNoteIMPL.getChannel());
                }
                else {
                    if (myNoteIMPL.isRightStart() && myNoteIMPL.isLeftStart()) {
                        throw new RuntimeException("The left and right vocal parts cannot start at the same time.");
                    } else if (myNoteIMPL.isRightStart()) {
                        int tick = lineNoteDistributeNote(line, lineNumber, rightTick);
                        rightTick += tick;
                        if (totalTick < rightTick) {
                            totalTick = rightTick;
                        }
                    } else if (myNoteIMPL.isLeftStart()) {
                        int tick = lineNoteDistributeNote(line, lineNumber, leftTick);
                        leftTick += tick;
                        if (totalTick < leftTick) {
                            totalTick = leftTick;
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
          对音符进行排序。
          Sort the notes.
         */
        noteInfoList.sort((o1, o2) -> (int) (o1.originTick - o2.originTick));
    }

    public CopyOnWriteArrayList<NoteInfo> getNoteInfoList() {
        return noteInfoList;
    }

    public void setNoteInfoList(CopyOnWriteArrayList<NoteInfo> noteInfoList) {
        this.noteInfoList = noteInfoList;
    }

    /**
     * 行分发器判定行为音符时调用此方法，对改行的音符进行解析。
     * The line distributor calls this method when determining the behavioral notes to parse the notes of the line change.
     *
     * @param line      从文件里读出的一行信息。
     *                  The information read from a line.
     * @param lineNum   行号。
     *                  The line number.
     * @param lineStart 本行开始的时间tick,相对于整首曲子。
     *                  The time tick of this line, relative to the whole song.
     * @return 此行音符的总长度。
     *         The total length of the notes in this line.
     * @author qguangyao, Fan Xinkang
     * @since version 6.0
     */
    @Override
    public int lineNoteDistributeNote(String line, int lineNum, int lineStart) {
        int lineTick = 0;
        String[] notesPre = line.split(separator.value);
        int noteStart = lineStart;
        for (String s : notesPre) {
            String[] singleNotes = s.split(COLON);
            int noteTick = 0;

            /*
              判断是否有 appoggiatura，如果有，则解析 appoggiatura。
              Judge whether there is appoggiatura. If there is, parse appoggiatura.
             */
            for (String singleNote : singleNotes) {
                if (singleNote.contains("<")) {
                    String[] split = singleNote.split("<");
                    NoteInfo[] noteInfos = appoggiaturaResolverIMPL.appoggiatura(split[0], split[1], noteStart, myNoteIMPL, singleNoteResolver);
                    if (noteTick < noteInfos[0].noteTick + noteInfos[1].noteTick) {
                        noteTick = noteInfos[0].noteTick + noteInfos[1].noteTick;
                    }
                    noteInfoList.add(noteInfos[0]);
                    noteInfoList.add(noteInfos[1]);
                    try {
                        addNote(track, noteInfos[0]);
                        addNote(track, noteInfos[1]);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    NoteInfo noteInfo = singleNoteResolver.singleNoteResolve(myNoteIMPL, singleNote, noteStart);
                    if (noteInfo == null) {
                        continue;
                    }
                    if (noteTick < noteInfo.noteTick) {
                        noteTick = noteInfo.noteTick;
                    }
                    noteInfoList.add(noteInfo);
                    try {
                        addNote(track, noteInfo);
                    } catch (Exception e) {
                        System.out.println("Exception:" + lineNum);
                        throw new RuntimeException(e);
                    }
                }
            }
            lineTick += noteTick;
            noteStart += noteTick;
        }
        if (lineTick != myNoteIMPL.getBarTick()) {
            System.out.println("The length of the sable is wrong. The length of the " + lineNum + " line is " + lineTick + " , which should be " + myNoteIMPL.getBarTick() + ".");
        }
        return lineTick;
    }

    /**
     * 添加解析的音符到音轨中。
     * Add the parsed notes to the track.
     *
     * @param track 音轨。
     *              The track.
     * @param noteInfo 音符信息。
     *                 The note information.
     * @throws Exception 创建音轨发生异常会抛出异常。
     *                   Create track exception will throw exception.
     * @author qguangyao, Fan Xinkang
     * @since version 6.0
     */
    public void addNote(Track track, NoteInfo noteInfo) throws Exception {
        ShortMessage messageOn = new ShortMessage();
        messageOn.setMessage(ShortMessage.NOTE_ON, noteInfo.channel, noteInfo.note, noteInfo.volume);
        MidiEvent eventOn = new MidiEvent(messageOn, noteInfo.originTick);
        track.add(eventOn);
        MetaMessage metaMessage = new MetaMessage();
        byte[] data = (messageOn.getData1() + " ").getBytes();
        metaMessage.setMessage(127, data, data.length);
        MidiEvent midiEvent = new MidiEvent(metaMessage, noteInfo.originTick);
        track.add(midiEvent);

        /*
          如果音符的静音时间小于音符的长度，则添加音符的静音事件。
          If the mute time of the note is less than the length of the note, add the mute event of the note.
         */
        if (noteInfo.muteTick < noteInfo.noteTick) {
            ShortMessage messageOff = new ShortMessage();
            messageOff.setMessage(ShortMessage.NOTE_OFF, noteInfo.channel, noteInfo.note, noteInfo.volume);
            MidiEvent eventOff = new MidiEvent(messageOff, noteInfo.getMuteTick());
            track.add(eventOff);
        } else {
            if (noteInfo.instrument == InstrumentEnum.ACOUSTIC_GRAND_PIANO.value || noteInfo.instrument == InstrumentEnum.ELECTRIC_GRAND_PIANO.value) {

            /*
              这边有个bug，如果音符是钢琴，并且静音时间小于音符长度，则添加音符的静音事件。
              There is a bug here. If the note is a piano and the mute time is less than the length of the note, add the mute event of the note.
             */
            } else if (noteInfo.instrument == InstrumentEnum.VIOLIN.value) {
                ShortMessage messageOff = new ShortMessage();
                messageOff.setMessage(ShortMessage.NOTE_OFF, noteInfo.channel, noteInfo.note, noteInfo.volume);
                MidiEvent eventOff = new MidiEvent(messageOff, noteInfo.getOriginTick() + noteInfo.getMuteTick());
                track.add(eventOff);
            }
        }
    }

    /**
     * 设置音轨的乐器。
     * Set the instrument of the track.
     *
     * @param track 音轨。
     *              The track.
     * @param instrument 乐器。
     *                   The instrument.
     * @param channel 频道。
     *                The channel.
     * @author qguangyao
     * @since version 6.0
     */
    private void setInstrument(Track track, int instrument, int channel) {
        try {
            ShortMessage messageChangeInstrument = new ShortMessage();
            messageChangeInstrument.setMessage(ShortMessage.PROGRAM_CHANGE, channel, instrument, 0);
            MidiEvent eventChangeInstrument = new MidiEvent(messageChangeInstrument, 0);
            track.add(eventChangeInstrument);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public MyNoteIMPL getMyNoteIMPL() {
        return myNoteIMPL;
    }

    public void setMyNoteIMPL(MyNoteIMPL myNoteIMPL) {
        this.myNoteIMPL = myNoteIMPL;
    }

    public Sequence getSequence() {
        return sequence;
    }

    public void setSequence(Sequence sequence) {
        this.sequence = sequence;
    }
}
/*
 * End of musicPlayer.java.player.file.MyStringDistributerIMPL Class.
 * Checked by Fan Xinkang on 2025/04/06.
 */