package com.hrm.document.service;

import com.hrm.commons.beans.Document;
import com.hrm.utils.PageModel;


import java.util.List;


public interface IDocumentService {
    List<Document> findDocument(String title, PageModel pageModel);

    int findDocumentCount(String title);

    int addDocument(Document document);

    Document findDocumentById(Integer id);

    int modifyDocument(Document document);

    int removeDocument(Integer id);
}
