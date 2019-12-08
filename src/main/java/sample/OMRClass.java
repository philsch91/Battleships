package sample;

import java.sql.SQLException;
import java.util.ArrayList;

public abstract class OMRClass {
     public abstract String getClassName();
     public abstract ArrayList<String> getVariableNames();
     public abstract ArrayList<String> getValues();

    public boolean save() throws SQLException, ClassNotFoundException {
        DBController dbc = DBController.getInstance();
        return dbc.executeStatement("INSERT INTO " + this.getClassName()
                + " (" + "".join(", ", this.getVariableNames()) + ")"
                + " VALUES (" + "".join(", ", this.getValues()) + ")");
    }

    public boolean migrate() throws SQLException, ClassNotFoundException {
        DBController dbc = DBController.getInstance();
        return dbc.executeStatement("CREATE TABLE " + this.getClassName()
                + " (" + "".join(" varchar(255), ", this.getVariableNames()) + " varchar(255))");
    }
}
