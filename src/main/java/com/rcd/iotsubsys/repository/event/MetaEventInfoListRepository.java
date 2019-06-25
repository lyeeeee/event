package com.rcd.iotsubsys.repository.event;

import com.rcd.iotsubsys.domain.event.MetaEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MetaEventInfoListRepository extends JpaRepository<MetaEvent, Long> {

}
