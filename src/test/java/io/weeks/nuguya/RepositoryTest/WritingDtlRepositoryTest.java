package io.weeks.nuguya.RepositoryTest;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Repository.WritingDtlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class WritingDtlRepositoryTest {

    @Autowired
    WritingDtlRepository writingDtlRepository;

    @Test
    public void writingTest() throws SQLException {
        WritingDtl writingDtl = new WritingDtl();
        writingDtl.setAnswer("hoon");
        System.out.println(writingDtl.toString());

        WritingDtl newWritingDtl = writingDtlRepository.save(writingDtl);
        System.out.println(newWritingDtl.toString());
        assertThat(newWritingDtl).isNotNull();

    }



}
