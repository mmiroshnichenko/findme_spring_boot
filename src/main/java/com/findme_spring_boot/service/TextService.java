package com.findme_spring_boot.service;

import com.findme_spring_boot.h2.dao.TextDao;
import com.findme_spring_boot.h2.models.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextService {
    private TextDao textDao;

    @Autowired
    public TextService(TextDao textDao) {
        this.textDao = textDao;
    }

    public Text save(Text text) {
        return textDao.save(text);
    }
}
