.DEFAULT_GOAL := build-run

run:
	java -jar ./target/Slot.jar

compile: clean
	mkdir -p ./target/classes
	javac -d ./target/classes ./src/main/java/games/Slot.java	

clean:
	rm -rf ./target

build-run: build run

build: compile
	jar cfe ./target/Slot.jar games.Slot -C ./target/classes .