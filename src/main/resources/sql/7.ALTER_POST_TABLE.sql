ALTER TABLE POST ADD LOCATION NVARCHAR2(200);
ALTER TABLE POST ADD USER_PAGE_POSTED_ID NUMBER NOT NULL;
ALTER TABLE POST ADD CONSTRAINT USER_PAGE_POSTED_FK FOREIGN KEY(USER_PAGE_POSTED_ID) REFERENCES USER_FM(ID);