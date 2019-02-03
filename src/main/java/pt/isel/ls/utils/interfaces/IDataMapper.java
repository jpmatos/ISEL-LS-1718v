package pt.isel.ls.utils.interfaces;

import org.postgresql.ds.PGSimpleDataSource;

public interface IDataMapper {

    // Returns all rows and domain objects
    Iterable GetAll(PGSimpleDataSource dataSource);
    //Inserts the target domain object
    void Insert (Object target, PGSimpleDataSource dataSource);
    //Inserts the target array of domain object
    void InsertArray (Object[] targets, PGSimpleDataSource dataSource);
    //Removes the row of the lastTable corresponding to the target domain object
    void Delete (Object target, PGSimpleDataSource dataSource);
    //Removes the rows of the lastTable corresponding to the array of target domain object
    void DeleteArray (Object[] targets, PGSimpleDataSource dataSource);
}
