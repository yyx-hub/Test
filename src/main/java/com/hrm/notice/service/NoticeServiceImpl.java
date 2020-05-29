package com.hrm.notice.service;

import com.hrm.commons.beans.Notice;
import com.hrm.notice.dao.INoticeDao;
import com.hrm.utils.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class NoticeServiceImpl implements INoticeService {

    @Resource
    INoticeDao noticeDao;

    @Override
    public List<Notice> findNotice( Notice notice,PageModel pageModel) {
        return noticeDao.selectNotice(notice,pageModel);
    }

    @Override
    public int findNoticeCount(Notice notice) {
        return noticeDao.selectNoticeCount(notice);
    }

    @Override
    public Notice findNoticeById(Integer id) {
        return noticeDao.selectNoticeById(id);
    }

    @Override
    public int modifyNotice(Notice notice) {
        return noticeDao.updateNotice(notice);
    }

    @Override
    public int removeNotice(Integer[] ids) {
        return noticeDao.deleteNotice(ids);
    }

    @Override
    public int addNotice(Notice notice) {
        return noticeDao.insertNotice(notice);
    }
}
