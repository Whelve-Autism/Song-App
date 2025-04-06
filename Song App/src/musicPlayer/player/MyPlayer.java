package musicPlayer.player;

import musicPlayer.player.file.MyFileReaderTxt;
import musicPlayer.player.file.MyStringDistributerIMPL;
import musicPlayer.player.note.MyNoteIMPL;
import musicPlayer.player.note.NoteInfo;

import javax.sound.midi.*;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.util.concurrent.CopyOnWriteArrayList;

import static userInterface.Display.printlnRandomColor;

/**
 * 此类用于播放 MIDI 文件（对外接口）。
 * This type is used to play MIDI files (external interface).
 *
 * @author qguangyao, Fan Xinkang
 * @version 6.0
 * @since version 6.0
 */
public class MyPlayer implements MetaEventListener {

    MyStringDistributerIMPL md = new MyStringDistributerIMPL();
    MyFileReaderTxt mf = new MyFileReaderTxt();
    Sequencer player;
    String file;
    private boolean reserve = false;

    public void setReserve(boolean reserve) {
        this.reserve = reserve;
    }

    public MyPlayer() throws Exception {
        player = MidiSystem.getSequencer();
    }

    public CopyOnWriteArrayList<NoteInfo> getNoteInfoList() {
        return md.getNoteInfoList();
    }

    public long getCurrentTick() {
        return player.getTickPosition();
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    long tick;
    boolean first = true;

    /**
     * 播放时，会调用此方法输出当前节数和当前节数下的音符。
     * When playing, this method will output the current section and the notes under the current section.
     *
     * @param meta MetaMessage
     * @author qguangyao, Fan Xinkang
     * @since version 6.0
     */
    @Override
    public void meta(MetaMessage meta) {
        if (meta.getType() == 127) {
            String data = new String(meta.getData());
            String[] arr = data.split(" ");
            String note = arr[0];
            long currentTick = player.getTickPosition();
            if (tick < currentTick / md.getMyNoteIMPL().getBarTick()) {
                tick = currentTick / md.getMyNoteIMPL().getBarTick();
                System.out.println();
                System.out.printf("%5d: Current Section%3d/%3d:  ", currentTick, (tick + 1), player.getTickLength() / md.getMyNoteIMPL().getBarTick());
                System.out.printf("%-5s", md.getMyNoteIMPL().calNoteNum(Integer.parseInt(note)));
            } else {
                if (first) {
                    System.out.printf("%5d: Current Section%3d/%3d:  ", currentTick, (tick + 1), player.getTickLength() / md.getMyNoteIMPL().getBarTick());
                    first = false;
                }
                String s = md.getMyNoteIMPL().calNoteNum(Integer.parseInt(note));
                System.out.printf("%-5s", s);
            }
        } else if (meta.getType() == 47) {
            System.out.println();
            printlnRandomColor("Finished playing");
            player.close();
        } else {
            System.out.println(meta.getType());
        }
    }

    public MyNoteIMPL getMyNoteIMPL() {
        return md.getMyNoteIMPL();
    }

    public float getBPM() {
        return player.getTempoInBPM();
    }

    public String getPPQ() {
        return getMyNoteIMPL().getPpq();
    }

    public void play() throws Exception {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(file);
        if (inputStream == null) {
            throw new Exception("File not found: " + file);
        }
        RandomAccessFile read = new RandomAccessFile(Files.createTempFile("temp", ".txt").toFile(), "rw");
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            read.write(buffer, 0, bytesRead);
        }
        read.seek(0);
        md.distribute(read);
        player.setTempoInBPM(md.getMyNoteIMPL().getBpm());

        player.open();
        Thread.sleep(200);
        if (reserve) {
            reversePlay();
        }
        player.setSequence(md.getSequence());
        player.addMetaEventListener(this);
        if (player.getSequence() == null) {
            player.close();
            return;
        }
        player.start();
    }


    /**
     * 倒放。
     * Reverse play.
     *
     * @author qguangyao, Fan Xinkang
     * @since version 6.0
     */
    private void reversePlay() {
        Track track = md.getSequence().getTracks()[0];
        Track track1 = md.getSequence().createTrack();
        CopyOnWriteArrayList<NoteInfo> noteInfoList = md.getNoteInfoList();
        CopyOnWriteArrayList<NoteInfo> noteInfoList2 = new CopyOnWriteArrayList<>();

        for (int i = 0; i < track.size(); i++) {
            MidiEvent midiEvent = track.get(i);
            long tick = track.ticks() - midiEvent.getTick();
            midiEvent.setTick(tick);
            track1.add(midiEvent);
        }
        for (int i = noteInfoList.size() - 1; i >= 0; i--) {
            long tick = track1.ticks();
            NoteInfo noteInfo = noteInfoList.get(i);
            noteInfo.setOriginTick(tick - noteInfo.getOriginTick());
            noteInfoList2.add(noteInfo);
        }
        md.setNoteInfoList(noteInfoList2);
        md.getSequence().deleteTrack(track);
    }
}
/*
 * End of musicPlayer.java.player.resolver.MyPlayer Class.
 * Checked by Fan Xinkang on 2025/04/05.
 */