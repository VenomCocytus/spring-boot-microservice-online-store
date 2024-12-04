package com.sehkmet.microservices.productservice.component;

import com.sehkmet.microservices.productservice.model.BaseModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class MongoListener extends AbstractMongoEventListener<BaseModel> {
    @Override
    public void onBeforeConvert(BeforeConvertEvent<BaseModel> beforeConvertEvent) {
        super.onBeforeConvert(beforeConvertEvent);

        BaseModel baseModel = beforeConvertEvent.getSource();

        if(StringUtils.isBlank(baseModel.getId())){
            baseModel.setCreatedAt(LocalDateTime.now());
            baseModel.setUpdatedAt(LocalDateTime.now());
        } else baseModel.setUpdatedAt(LocalDateTime.now());
    }
}
