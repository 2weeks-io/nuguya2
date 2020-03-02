package io.weeks.nuguya.Repository;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WritingRepository extends JpaRepository<Writing, Long> {

    Writing findByWritingNo(Long writingNo);

    List<Writing> findAllBy(Pageable pageable);

    List<Writing> findByWritingDivCd(String writingDivCd);

    List<Writing> findByWritingDivCdOrderByRegDtsDesc(String writingDivCd);

    Page<Writing> findByWritingDivCd(Pageable pageable, String writingDivCd);

    //Page<Writing> findByWritingDivCdAndUseYn(Pageable pageable, String writingDivCd, String useYn);

    @Query("select w from Writing w where w.writingDivCd = :writingDivCd and w.useYn = :useYn order by function('RAND')")
    Page<Writing> findByWritingDivCdAndUseYn(Pageable pageable, @Param("writingDivCd") String writingDivCd, @Param("useYn") String useYn);

    Writing findByWritingNoOrderByRegDtsDesc(Long writingNo);
}
