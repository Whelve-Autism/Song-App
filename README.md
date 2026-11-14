<div align="center">

# Song App

**Java console · Playlist management · XML storage**

A team coursework project that manages songs and artists in a playlist, with text-based search, reports, and local XML storage.

**English** · [简体中文](./README.zh-CN.md)

</div>

[Quick start](#quick-start) · [Source and versions](#versions)

<a id="versions"></a>
## Source and versions

The [console project](./song-store-assignment-1-2/) on `main` supports adding, updating, deleting, and finding songs. It also filters by artist or duration, reports total and average duration, and saves the playlist to `Store.xml` with XStream.

- [Playlist and models](./song-store-assignment-1-2/src/) contains the Java source.
- [Tests](./song-store-assignment-1-2/test/) covers the playlist, song, and artist classes.
- [Update-history](https://github.com/Whelve-Autism/Song-App/tree/Update-history) preserves coursework revisions.
- [Expand-history](https://github.com/Whelve-Autism/Song-App/tree/Expand-history/Song%20App) contains an expanded application with album and music playback code.

<a id="quick-start"></a>
## Quick start

Use JDK 17. Import `song-store-assignment-1-2/` into a Java IDE, mark `src/` as a source directory, and add [XStream 1.4.20](./song-store-assignment-1-2/lib/xstream-1.4.20.jar) to the classpath. Run [Driver](./song-store-assignment-1-2/src/Driver.java) with the module directory as the working directory.
