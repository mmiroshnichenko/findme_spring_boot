package com.findme_spring_boot.service;

import com.findme_spring_boot.dao.h2.TextDAO;
import com.findme_spring_boot.model.h2.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TextService {
    private TextDAO textDao;

    @Autowired
    public TextService(TextDAO textDao) {
        this.textDao = textDao;
    }

    public Text save(Text text) throws Exception {
        return textDao.save(text);
    }
}
