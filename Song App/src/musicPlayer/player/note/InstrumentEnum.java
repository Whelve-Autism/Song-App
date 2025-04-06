package musicPlayer.player.note;

/**
 * 此枚举用于表示乐器的枚举类型，其中每个枚举常量表示一种乐器，并包含一个值和描述。
 * This enum is used to represent an enumeration of instruments, where each enum constant represents a type of instrument, and contains a value and a description.
 *
 * @author qguangyao
 * @version 6.0
 * @since version 6.0
 */
public enum InstrumentEnum {

    ACOUSTIC_GRAND_PIANO(0,"Grand piano"),
    ELECTRIC_GRAND_PIANO(2,"Electric piano"),
    VIOLIN(40,"Violin");

    public final int value;
    public final String znName;

    /*
      构造函数, 初始化 value 和 znName。
      Constructor, initializes value and znName.
     */
    InstrumentEnum(int value,String znName){
        this.value = value;
        this.znName = znName;
    }
}
/*
 * End of musicPlayer.java.player.note.InstrumentEnum Enum.
 * Checked by Fan Xinkang on 2025/04/05.
 */