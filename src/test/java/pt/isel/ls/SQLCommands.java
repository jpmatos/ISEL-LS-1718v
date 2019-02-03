//package pt.isel.ls;
//
//import pt.isel.ls.utils.ConnectionManager;
//
//import java.sql.Connection;
//import java.sql.Statement;
//
//public class SQLCommands {
//    final static private String CREATE_MOVIES_TABLE = "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Movies' and xtype='U') " +
//            "CREATE TABLE Movies( " +
//            "_id INT not null, " +
//            "_title VARCHAR(255) not null, " +
//            "_releaseYear INT not null, " +
//            "_duration INT not null, " +
//            "PRIMARY KEY (_iD)" +
//            ")";
//    final static private String CREATE_CINEMAS_TABLE = "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Cinemas' and xtype='U') " +
//            "CREATE TABLE Cinemas( " +
//            "_id INT not null, " +
//            "_name VARCHAR(255) not null, " +
//            "_city VARCHAR(255) not null, " +
//            "PRIMARY KEY (_id) " +
//            ")";
//    final static private String CREATE_THEATERS_TABLE = "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Theaters' and xtype='U') " +
//            "CREATE TABLE Theaters( " +
//            "_id INT not null, " +
//            "_name VARCHAR(255) not null, " +
//            "_rows INT not null, " +
//            "_seats INT not null, " +
//            "_availableSeats INT null, " +
//            "_cid INT not null, " +
//            "PRIMARY KEY (_id), " +
//            "FOREIGN KEY (_cid) REFERENCES Cinemas(_id) " +
//            ")";
//    final static private String CREATE_SESSIONS_TABLE = "IF NOT EXISTS (SELECT * FROM sysobjects WHERE name='Sessions' and xtype='U') " +
//            "CREATE TABLE Sessions ( " +
//            "_id INT not null, " +
//            "_date DATETIME not null, " +
//            "_mid INT not null, " +
//            "_tid INT not null, " +
//            "_cid INT not null, " +
//            "PRIMARY KEY (_id), " +
//            "FOREIGN KEY (_mid) REFERENCES Movies(_id), " +
//            "FOREIGN KEY (_tid) REFERENCES Theaters(_id), " +
//            "FOREIGN KEY (_cid) REFERENCES Cinemas(_id) " +
//            ")";
//
//    public static void CreateDBsIfNotExists(){
//        Execute(CREATE_MOVIES_TABLE);
//        Execute(CREATE_CINEMAS_TABLE);
//        Execute(CREATE_THEATERS_TABLE);
//        Execute(CREATE_SESSIONS_TABLE);
//    }
//
//    private static void Execute(String sql) {
//        try {
//            Connection con = ConnectionManager.autoSetup();
//            Statement directStat = con.createStatement();
//            directStat.executeUpdate(sql);
//            con.close();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}
