package io.weeks.nuguya.Repository;

import io.weeks.nuguya.Entity.Writing;
import io.weeks.nuguya.Entity.WritingType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WritingTypeRepository extends JpaRepository<WritingType, String> {

}
