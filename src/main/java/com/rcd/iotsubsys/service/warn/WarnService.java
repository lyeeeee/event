package com.rcd.iotsubsys.service.warn;

import com.rcd.iotsubsys.domain.warn.Warn;
import com.rcd.iotsubsys.repository.warn.WarnRepository;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class WarnService {

    private final WarnRepository warnRepository;

    public WarnService(WarnRepository warnRepository) {
        this.warnRepository = warnRepository;
    }

    public Warn getType() {
        final Warn warnEntity = warnRepository.getOne(10000L);
        Hibernate.initialize(warnEntity);
        return warnEntity;
    }

    //增改复杂事件
    public Warn save(String type) {
        Warn warn = new Warn();
        warn.setId(10000L);
        warn.setType(type);

        return warnRepository.save(warn);
    }

}
