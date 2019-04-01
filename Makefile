.DEFAULT_GOAL := compile-run

run:
    java -cp .\target\classes games.Slot

compile: clean
    mkdir -p ./target/classes
    javac -d ./target/classes ./src/main/java/games/Slot.java

clean:
    rm -rf ./target

compile-run: compile run