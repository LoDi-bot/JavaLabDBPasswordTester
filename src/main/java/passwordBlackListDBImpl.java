import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class passwordBlackListDBImpl implements PasswordBlackList {

    //language=SQL
    private static final String SQL_SELECT_ALL = "select password from password_black_list";

    private final JdbcTemplate jdbcTemplate;

    public passwordBlackListDBImpl() {
        Properties properties = new Properties();

        try {
            properties.load(new FileReader("src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(properties.getProperty("db.url"));
        config.setUsername(properties.getProperty("db.user"));
        config.setPassword(properties.getProperty("db.password"));
        config.setDriverClassName(properties.getProperty("db.driver"));
        config.setMaximumPoolSize(Integer.parseInt(properties.getProperty("db.hikari.pool-size")));

        DataSource dataSource = new HikariDataSource(config);

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
