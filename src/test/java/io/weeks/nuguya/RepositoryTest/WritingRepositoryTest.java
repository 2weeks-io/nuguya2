package io.weeks.nuguya.RepositoryTest;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Repository.WritingRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WritingRepositoryTest {

    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    WritingRepository writingRepository;

    /*
    @Test
    public void di() throws SQLException {
        try (Connection connection = dataSource.getConnection()) {
            DatabaseMetaData metaData = connection.getMetaData();
            System.out.println(metaData.getURL());
            System.out.println(metaData.getDriverName());
            System.out.println(metaData.getUserName());
        }
    }
     */

    /*
    @Test
    public void writingTest() throws SQLException {


        Writing writing = new Writing();
        writing.setTitle("jpa test");

        Writing newWriting = writingRepository.save(writing);
        System.out.println(newWriting.toString());
        assertThat(newWriting).isNotNull();


        Writing existingWriting = writingRepository.findByTitle(newWriting.getTitle());
        assertThat(existingWriting).isNotNull();

        System.out.println("=========================");
        System.out.println(existingWriting);
        System.out.println("=========================");

        Writing nonExistingWriting = writingRepository.findByTitle("superman");
        assertThat(nonExistingWriting).isNull();

    }
    */

}
