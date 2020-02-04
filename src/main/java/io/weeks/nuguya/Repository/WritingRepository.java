package io.weeks.nuguya.Repository;

import io.weeks.nuguya.Entity.Writing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WritingRepository extends JpaRepository<Writing, Long> {

    Writing findByTitle(String title);

}
