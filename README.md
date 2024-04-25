Requires installation of the Java Runtim Environment (JRE) version 21, downloadable: https://adoptium.net/en-GB/temurin/archive/?version=21

Alternatively, you can use the JDK from this same download page. If you do this you don't need the JRE due to the JDK contatining a version of the JRE.

## Compiling and Running

To compile navigate to root project folder and run: 
javac -cp src/view/main/*.java src/model/facade/*  src/model/util/* src/model/exception/* src/model/*.java src/view/*.java src/view/util/* src/view/visitor/* src/controller/* -Xlint:deprecation --module-path javafx-sdk-22.0.1/lib --add-modules javafx.web,javafx.media,javafx.swing -d .\bin

Then use your editor/compiler to export a runnable JAR file from the project

To run navigate to root project folder and run:
java -jar --module-path javafx-sdk-22.0.1/lib --add-modules javafx.web,javafx.media,javafx.swing --enable-preview LibraryManagementSystem.jar