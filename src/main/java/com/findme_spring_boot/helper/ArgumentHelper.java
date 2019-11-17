package com.findme_spring_boot.helper;

import com.findme_spring_boot.exception.BadRequestException;
import com.findme_spring_boot.oracle.models.RelationshipStatus;
import org.springframework.stereotype.Service;

@Service
public class ArgumentHelper {
    //TODO такую простую логику не стоит выносить в отдельный класс, это простая валидация которая на уровне сервиса должны лежать
    public Long parseLongArgument(String id) throws BadRequestException {
        try {
            long paramId = Long.parseLong(id);
            if (paramId <= 0) {
                throw new BadRequestException("Error: incorrect id: " + id);
            }

            return paramId;
        } catch (NumberFormatException e) {
            throw new BadRequestException("Error: incorrect argument format");
        }
    }

    public RelationshipStatus parseRelationshipStatus(String status) throws BadRequestException {
        try {
            return RelationshipStatus.valueOf(status);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("Error: incorrect relationship status");
        }
    }
}
