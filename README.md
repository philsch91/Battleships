# Battleships Refactoring

for Advanced Software Development

### Setup

This project is a Maven project. It should therefore not be necessary to download dependencies or libraries other than a JDK.

#### Java VM options

- Main: `-Dlog4j.configurationFile=file:./src/main/resources/log4j2.properties`
- Testing: `-Dlog4j.configurationFile=file:./src/test/resources/log4j2.properties`

#### IntelliJ

1. Open project in IntelliJ
2. If the project is not detected automatically as a Maven project, right click on the pom.xml and select "Set as Maven project"
3. Wait until all dependencies are installed and downloaded
4. Run the static `main()` method with a right-click on the `Main` class

### Validated Toolstack

- IntelliJ 2019.2.4
- OpenJDK 13
- OpenJFX 13
- module-info.java on the java package level in `/src/main/java`

### Notes

`logger.rolling.name` in the log4j2.properties must be equal to the package name of the application.
`logger.rolling.name = sample`
