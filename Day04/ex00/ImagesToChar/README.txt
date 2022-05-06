To make a package do:
(from the ImageToChar directory)
1. Make a folder for target
mkdir target

2. Run compiler with target attribute
javac -d target src/java/edu/school21/printer/app/Application.java src/java/edu/school21/printer/logic/DrawImage.java

3. Run application with source path
java -cp target edu.school21.printer.app.Application /Users/rmerrie/Downloads/it.bmp . 0