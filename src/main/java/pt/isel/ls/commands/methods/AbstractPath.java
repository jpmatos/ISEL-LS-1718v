package pt.isel.ls.commands.methods;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.commands.Command;
import pt.isel.ls.utils.interfaces.IPath;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class AbstractPath implements IPath {
    protected abstract Command pathExecute(Command c);

    @Override
    public Command execute(Command c) {
        return pathExecute(c);
    }

    protected static int getHighestID(String s, PGSimpleDataSource dataSource) {
        try {
            Connection conn = dataSource.getConnection();
            String SQLcommand = "SELECT max(_id) FROM " + s;
            Statement directStat = conn.createStatement();
            ResultSet rs = directStat.executeQuery(SQLcommand);
            rs.next();
            int res = rs.getInt(1);
            return rs.getInt(1) + 1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}
