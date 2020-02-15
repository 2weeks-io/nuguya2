package io.weeks.nuguya.Repository;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingDtl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface WritingDtlRepository extends JpaRepository<WritingDtl, Long> {

    @Query("select w from WritingDtl w where w.writingNo = :writingNo order by function('RAND')")
    Page<WritingDtl> findByRandomWritingNo(@Param("writingNo") Long writingNo, Pageable pageable);

}
