build:
	mvnd package -DskipTests -Dos.detected.classifier=osx-x86_64 -X
protobuf:
	mvnd protobuf:compile -f pom.xml -Dos.detected.classifier=osx-x86_64 -X
clean:
	mvnd clean -Dos.detected.classifier=osx-x86_64 -X
