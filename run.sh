find -name "*.java" > sources.txt
javac -d ./bin @sources.txt
java -cp ./bin Obama