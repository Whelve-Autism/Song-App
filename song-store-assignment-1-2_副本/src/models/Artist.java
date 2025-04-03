package models;

/**
 * 此类用于创建并存储歌手对象。
 * This class is used to create and store artist objects.
 *
 * @author Fan Xinkang, Xu Shiyi, Lu Siyu
 * @version 5.0
 * @since version 3.0
 */
public class Artist {

    private String artistName = "";

    /*
      新增判断：验证 artistName 是否被设置过，使得 SongTest 成功编译。
      New judgment: Verify whether artistName has been set, so that SongTest can be successfully compiled.
     */
    private boolean artistNameSet = false;

    private boolean verified = false;

    /**
     * 构造函数，将 artistName 和 verified 作为参数传入 Artist。
     * Constructor, passing artistName and verified as parameters to Artist.
     *
     * @param artistName 歌手名字。
     *                   The name of the artist.
     * @param verified 歌手验证状态。
     *                 The verification status of the artist.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.0
     */
    public Artist(String artistName, boolean verified) {
        if (artistName == null) {
            artistName = "";
        }

        setArtistName(artistName);
        this.verified = verified;
    }

    /*
      封装。
      Encapsulation.
     */
    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        if (!artistNameSet) {
            if (artistName.length() <= 15) {
                this.artistName = artistName;
            } else {
                this.artistName = artistName.substring(0, 15);
            }
            artistNameSet = true;
        } else {
            if (artistName.length() <= 15) {
                this.artistName = artistName;
            }
        }
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    /**
     * 重写 equals() 方法，判断 artistName 和 verified 是否相等。
     * Override the equals() method to determine whether artistName and verified are equal.
     *
     * @param object 传入的对象。
     *               The incoming object.
     * @return 两个对象是否相等。
     *         Whether the two objects are equal.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.0
     */
    @Override
    public boolean equals(Object object) {

        /*
          如果是同一个对象引用，直接返回 true。
          If it is the same object, return true directly.
         */
        if (this == object) {
            return true;
        }

        /*
          如果传入的对象为 null 或者类型不匹配，直接返回 false。
          If the passed in object is null or the type does not match, return false directly.
         */
        if (object == null || getClass() != object.getClass()) {
            return false;
        }

        /*
          进行类型转换。
          Convert the passed in object to Song type.
         */
        Artist artist = (Artist) object;

        /*
          判断字段是否相等。
          Determine whether the field is equal.
         */
        return verified == artist.verified && artistName.equals(artist.artistName);
    }

    /**
     * 重写 toString() 方法，返回 artistName 和 verified 的信息。
     * Override the toString() method to return the information of artistName and verified.
     *
     * @return artistName 和 verified 的信息。
     *         Information of artistName and verified.
     * @author Fan Xinkang, Xu Shiyi, Lu Siyu
     * @since version 3.0
     */
    @Override
    public String toString() {
        if (isVerified()) {
            return artistName + " is a verified artist.";
        } else {
            return artistName + " is not a verified artist.";
        }
    }
}
/*
 * End of models.Artist Class.
 * Checked by Fan Xinkang on 2025/04/03.
 */