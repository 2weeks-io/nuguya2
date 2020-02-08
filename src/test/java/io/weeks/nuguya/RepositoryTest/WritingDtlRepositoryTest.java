package io.weeks.nuguya.RepositoryTest;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import io.weeks.nuguya.Repository.WritingDtlRepository;
import io.weeks.nuguya.Repository.WritingRepository;
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

    @Autowired
    WritingRepository writingRepository;

    @Test
    public void writingTest() throws SQLException {

        Writing newWriting = new Writing();
        writingRepository.save(newWriting);

        Long writingNo = 1L;
        Writing writing = writingRepository.findByWritingNo(writingNo);

        WritingDtl writingDtl = new WritingDtl();
        writingDtl.setAnswer("hoon");
        writingDtl.setWriting(newWriting);

        WritingDtl writingDtl2 = new WritingDtl();
        writingDtl.setAnswer("hoon2");
        writingDtl.setWriting(newWriting);

        WritingDtl newWritingDtl = writingDtlRepository.save(writingDtl);
        System.out.println(newWritingDtl.toString());

        WritingDtl newWritingDtl2 = writingDtlRepository.save(writingDtl2);
        System.out.println(newWritingDtl2.toString());

        assertThat(newWritingDtl.getWriting().getWritingNo()).isEqualTo(newWritingDtl.getWriting().getWritingNo());

    }


}
