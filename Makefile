.DEFAULT_GOAL := build-run

run:
	java -jar ./target/project-lvl1-s498-1.0-SNAPSHOT-jar-with-dependencies.jar

update:
	./mvnw versions:update-properties versions:display-plugin-updates

build-run: build run

build: 
	./mvnw clean package