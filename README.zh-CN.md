<div align="center">

# 歌曲与播放列表管理

**Java 命令行 · 播放列表管理 · XML 存储**

一个团队课程项目，在命令行中管理播放列表里的歌曲和艺人，提供搜索、统计和本地 XML 保存。

[English](./README.md) · **简体中文**

</div>

[快速开始](#quick-start) · [源码与版本](#versions)

<a id="versions"></a>
## 源码与版本

`main` 上的[命令行项目](./song-store-assignment-1-2/)支持歌曲的添加、修改、删除和查找，也可以按艺人或时长筛选，统计总时长和平均时长，并通过 XStream 将播放列表保存到 `Store.xml`。

- [播放列表与模型](./song-store-assignment-1-2/src/)包含 Java 源码。
- [测试](./song-store-assignment-1-2/test/)覆盖播放列表、歌曲和艺人类。
- [Update-history](https://github.com/Whelve-Autism/Song-App/tree/Update-history)保存课程迭代。
- [Expand-history](https://github.com/Whelve-Autism/Song-App/tree/Expand-history/Song%20App)收录包含专辑和音乐播放代码的扩展版。

<a id="quick-start"></a>
## 快速开始

使用 JDK 17。将 `song-store-assignment-1-2/` 导入 Java IDE，把 `src/` 标为源码目录，并将 [XStream 1.4.20](./song-store-assignment-1-2/lib/xstream-1.4.20.jar) 加入类路径。将模块目录设为工作目录，运行 [Driver](./song-store-assignment-1-2/src/Driver.java)。
