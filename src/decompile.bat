set class_dir=D:\company\xxxx-oa\debug2
cd %class_dir%
jar cvf d.jar .
set tool_jar=C:\ideaIU-2018.3.win\plugins\java-decompiler\lib\java-decompiler.jar
mkdir _out
java  -cp %tool_jar% org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler d.jar _out
cd _out
jar xvf d.jar
