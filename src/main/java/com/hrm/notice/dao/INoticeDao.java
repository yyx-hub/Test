package com.hrm.notice.dao;

import com.hrm.commons.beans.Notice;
import com.hrm.utils.PageModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface INoticeDao {
    List<Notice> selectNotice(@Param("notice")Notice notice, @Param("pageModel")PageModel pageModel);

    int selectNoticeCount(Notice notice);

    Notice selectNoticeById(Integer id);

    int updateNotice(Notice notice);

    int deleteNotice(Integer[] ids);

    int insertNotice(Notice notice);
}
