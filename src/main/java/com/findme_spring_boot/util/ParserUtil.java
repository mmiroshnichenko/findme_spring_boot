package com.findme_spring_boot.util;

import com.findme_spring_boot.exception.BadRequestException;
import org.springframework.stereotype.Service;

@Service
public class ParserUtil {
    public Long parseId(String id) throws BadRequestException {
        try {
            long paramId = Long.parseLong(id);
            if (paramId <= 0) {
                throw new BadRequestException("Error: incorrect id: " + id);
            }

            return paramId;
        } catch (NumberFormatException e) {
            throw new BadRequestException("Error: incorrect id format");
        }
    }
}
