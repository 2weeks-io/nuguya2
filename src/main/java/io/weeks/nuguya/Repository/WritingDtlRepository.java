package io.weeks.nuguya.Repository;

import io.weeks.nuguya.Entity.WritingDtl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WritingDtlRepository extends JpaRepository<WritingDtl, Long> {

    /*
    Page<WritingDtl> findByWritingNo(Pageable pageable, WritingDtl writingDtl);
    */

}
