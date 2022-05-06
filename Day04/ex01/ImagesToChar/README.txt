To make a package do:
(from the ImageToChar directory)
1. Make a folder for target
mkdir target

2. Run compiler with target attribute
javac -d target src/java/edu/school21/printer/app/Application.java src/java/edu/school21/printer/logic/DrawImage.java

3. Copy resource folder with image
cp -R ./src/resources ./target/resources

4. Make jar archive
jar cvfm target/Application.jar src/manifest.txt -C target edu -C target resources

5. Run application with arguments
java -jar target/Application.jar . 0