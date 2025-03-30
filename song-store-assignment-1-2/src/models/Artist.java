package models;

public class Artist {

    //TODO The artist name (String artistName) in the system is entered by the user.
    //     Default value is "".
    //     When creating the Artist, truncate the name to 15 characters.
    //     When updating an existing Artist, only update the name if it is 15 characters or less.
    private String artistName = "";

    /*
      新增判断：验证 artistName 是否被设置过，使得 SongTest 成功编译。
      New judgment: Verify whether artistName has been set, so that SongTest can be successfully compiled.
     */
    private boolean artistNameSet = false;

    //TODO The verified status (boolean verified).
    //     Default is false.
    private boolean verified = false;

    //TODO Add the constructor, Artist(String, boolean), that adheres to the above validation rules.
    public Artist(String artistName, boolean verified) {
        setArtistName(artistName);
        this.verified = verified;
    }

    //TODO Add a getter and setter for each field, that adheres to the above validation rules.
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

    //TODO Add a generated equals method.
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

    //TODO The toString should return the string in this format: Taylor Swift is a verified artist OR Shane Hennessy is not a verified artist
    @Override
    public String toString() {
        if (isVerified()) {
            return artistName + " is a verified artist.";
        } else {
            return artistName + " is not a verified artist.";
        }
    }
}