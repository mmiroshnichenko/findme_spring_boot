package com.findme_spring_boot.test;

import com.findme_spring_boot.dao.oracle.MessageDAO;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MessageDAOIntegrationTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MessageDAO messageDAO;
}
