import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class passwordBlackListDBImpl implements PasswordBlackList {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select password from password_black_list";

    private final JdbcTemplate jdbcTemplate;

    public passwordBlackListDBImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    private final ResultSetExtractor<List<String>> passwordsResultSetExtractor = rows -> {
        List<String> passwords = new ArrayList<>();

        while (rows.next()) {
            passwords.add(rows.getString("password"));
        }
        return passwords;
    };

    public List<String> findAll() {
        return jdbcTemplate.query(SQL_SELECT_ALL, passwordsResultSetExtractor);
    }

    @Override
    public boolean contains(String password) {
        return this.findAll().contains(password);
    }
}
