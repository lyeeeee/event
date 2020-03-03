package com.rcd.iotsubsys.repository.warn;

import com.rcd.iotsubsys.domain.warn.Warn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarnRepository extends JpaRepository<Warn, Long> {

}
