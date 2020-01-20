module battleship {
    requires transitive javafx.controls;
    requires javafx.media;
    requires java.desktop;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires org.apache.logging.log4j.core;
    requires h2;
    exports sample;
}