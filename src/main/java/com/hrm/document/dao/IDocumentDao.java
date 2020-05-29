package com.hrm.document.dao;

import com.hrm.commons.beans.Document;

import java.util.List;
import java.util.Map;

public interface IDocumentDao {
    List<Document> selectDocument(Map map);

    int selectDocumentCount(String title);

    int insertDocument(Document document);

    Document selectDocumentById(Integer id);

    int updateDocument(Document document);

    int deleteDocument(Integer id);
}
