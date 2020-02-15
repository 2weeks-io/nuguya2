package io.weeks.nuguya.Repository;

import io.weeks.nuguya.Entity.Writing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WritingRepository extends JpaRepository<Writing, Long> {

    Writing findByWritingNo(Long writingNo);

    List<Writing> findAllBy(Pageable pageable);

    List<Writing> findByWritingDivCd(String writingDivCd);

}
