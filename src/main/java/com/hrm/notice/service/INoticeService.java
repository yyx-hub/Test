package com.hrm.notice.service;

import com.hrm.commons.beans.Notice;
import com.hrm.utils.PageModel;

import java.util.List;

public interface INoticeService {
    List<Notice> findNotice(Notice notice, PageModel pageModel);

    int findNoticeCount(Notice notice);

    Notice findNoticeById(Integer id);

    int modifyNotice(Notice notice);

    int removeNotice(Integer[] ids);

    int addNotice(Notice notice);
}
