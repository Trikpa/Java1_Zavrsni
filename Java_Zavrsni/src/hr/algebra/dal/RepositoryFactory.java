package hr.algebra.dal;

import hr.algebra.dal.Repository;
import hr.algebra.dal.sql.SqlRepository;

public class RepositoryFactory {
    
    private RepositoryFactory() {}
    
    public static Repository getRepository() {
        return new SqlRepository();
    }
}