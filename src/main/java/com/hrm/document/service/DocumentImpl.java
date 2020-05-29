package com.hrm.document.service;

import com.hrm.commons.beans.Document;
import com.hrm.document.dao.IDocumentDao;
import com.hrm.utils.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service

public class DocumentImpl implements IDocumentService {

    @Resource
    IDocumentDao documentDao;
    @Override
    public List<Document> findDocument(String title, PageModel pageModel) {
        //为多个参数，使用注解或者封装到map里都可以
        Map map = new HashMap();
        map.put("title",title);
        //把当前页的起始索引方法封装到start里
        map.put("start",pageModel.getFirstLimitParam());
        map.put("pageSize",pageModel.getPageSize());
        return documentDao.selectDocument(map);
    }

    @Override
    public int findDocumentCount(String title) {
        return documentDao.selectDocumentCount(title);
    }

    @Override
    public int addDocument(Document document) {
        return documentDao.insertDocument(document);
    }

    @Override
    public Document findDocumentById(Integer id) {
        return documentDao.selectDocumentById(id);
    }

    @Override
    public int modifyDocument(Document document) {
        return documentDao.updateDocument(document);
    }

    @Override
    public int removeDocument(Integer id) {
        return documentDao.deleteDocument(id);
    }
}
