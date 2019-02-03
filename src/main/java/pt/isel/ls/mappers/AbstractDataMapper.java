package pt.isel.ls.mappers;

import org.postgresql.ds.PGSimpleDataSource;
import pt.isel.ls.utils.interfaces.IDataMapper;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public abstract class AbstractDataMapper implements IDataMapper {

    protected abstract String SqlGetAll();
    protected abstract PreparedStatement SqlInsert(Object target, PGSimpleDataSource dataSource) throws Exception;
    protected abstract String SqlDelete (Object target);
    protected abstract Object Load(ResultSet rs);
    protected abstract Object ObjectMap(LinkedHashMap<String, ArrayList<String>>  path, int iD);
    protected abstract Object[] ObjectMapArray(LinkedHashMap<String, ArrayList<String>>  path, int iD);
    protected abstract String SqlDeleteWithoutId(Object target);

    public Object[] MapArray(LinkedHashMap<String, ArrayList<String>> path, int iD){
        return ObjectMapArray(path, iD);
    }

    public Object Map(LinkedHashMap<String, ArrayList<String>> path, int iD){
        return ObjectMap(path, iD);
    }

    @Override
    public Iterable GetAll(PGSimpleDataSource dataSource) {
        return Get(SqlGetAll(), dataSource);
    }

    @Override
    public void Insert (Object target, PGSimpleDataSource dataSource){
        try {
            PreparedStatement statement = SqlInsert(target, dataSource);
            statement.executeUpdate();
            Connection con = statement.getConnection();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void InsertArray (Object[] targets, PGSimpleDataSource dataSource){
        for (Object target : targets) {
            Insert(target, dataSource);
        }
    }

    @Override
    public void Delete (Object target, PGSimpleDataSource dataSource){
        String sql = SqlDelete(target);
        System.out.println(sql);
        Execute(sql, dataSource);
    }

    @Override
    public void DeleteArray (Object[] targets, PGSimpleDataSource dataSource){
        for (Object target : targets) {
            Delete(target, dataSource);
        }
    }

    private void DeleteWithoutId(Object target, PGSimpleDataSource dataSource){
        String sql = SqlDeleteWithoutId(target);
        System.out.println(sql);
        Execute(sql, dataSource);
    }

    public void DeleteArrayWithoutId (Object[] targets, PGSimpleDataSource dataSource){
        for (Object target : targets) {
            DeleteWithoutId(target, dataSource);
        }
    }

    private void Execute(String sql, PGSimpleDataSource dataSource) {
        try{
            Connection con = dataSource.getConnection();
            Statement directStat = con.createStatement();
            directStat.executeUpdate(sql);
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public Iterable Get(String sql, PGSimpleDataSource dataSource) {
        List res = new ArrayList<>();
        try{
            Connection con = dataSource.getConnection();
            Statement directStat = con.createStatement();
            ResultSet rs = directStat.executeQuery(sql);
            while (rs.next()) res.add(Load(rs));
            con.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }

    Timestamp convertStringToSqlDate(String date) throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date javaDate = df.parse(date);
        java.sql.Timestamp sqlDate = new java.sql.Timestamp(javaDate.getTime());
        return sqlDate;
    }

}
