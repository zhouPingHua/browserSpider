注意
如果桌面，你需要一个键盘，其接口是PS / 2。

如果您的Windows是64位，则此命令应在cmd中以管理员身份运行：

bcdedit/set testsigning on

然后重新启动计算机。

首次以管理员身份运行应用程序。

要么

执行WinIoInstall.exe（您可以先运行您的应用程序，然后在桌面上找到“winIo”文件夹）。

它将在您的系统上安装WinIo驱动程序。

要确保已在系统上安装WinIo驱动程序：

sc查询winio(sc query winio)

在cmd中执行命令，如果可以看到关键字“RUNNING”，那没关系！

不支持并发访问！