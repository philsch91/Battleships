module battleship {
    requires transitive javafx.controls;
    requires javafx.media;
    requires java.desktop;
    requires org.apache.logging.log4j;
    requires java.sql;
    requires hibernate.core;
    requires hibernate.jpa;
    exports sample;
}