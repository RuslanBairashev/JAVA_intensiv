To make a package do:
(from the ImageToChar directory)
1. Make a folder for target and lib
mkdir target
mkdir lib

2. Download libs
curl -o ./lib/JColor-5.0.0.jar https://repo1.maven.org/maven2/com/diogonunes/JColor/5.0.0/JColor-5.0.0.jar
curl -o ./lib/jcommander-1.78.jar https://repo1.maven.org/maven2/com/beust/jcommander/1.78/jcommander-1.78.jar

3. Run compiler with target attribute
javac -cp ".:lib/jcommander-1.78.jar:lib/JColor-5.0.0.jar" -d target src/java/edu/school21/printer/app/Application.java src/java/edu/school21/printer/logic/DrawImage.java

4. Copy resource folder with image
cp -R ./src/resources ./target/resources

5. Add classes from jar
cd target
jar xf ../lib/jcommander-1.78.jar
jar xf ../lib/JColor-5.0.0.jar
cd ..

6. Make jar archive
jar cvfm target/Application.jar src/manifest.txt -C target .

7. Run application with arguments
java -jar target/Application.jar --white=GREEN --black=BLUE