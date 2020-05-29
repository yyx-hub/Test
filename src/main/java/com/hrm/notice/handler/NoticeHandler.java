package com.hrm.notice.handler;


import com.hrm.commons.beans.Notice;
import com.hrm.commons.beans.User;
import com.hrm.notice.service.INoticeService;
import com.hrm.utils.PageModel;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/notice")
public class NoticeHandler {

    @Autowired
    INoticeService noticeService;

    @RequestMapping("/findNotice.do")
    public String findNotice(@RequestParam(defaultValue = "1") int pageIndex, Notice notice, Model model){
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        List<Notice> notices = noticeService.findNotice(notice,pageModel);
        /*for(Notice n:notices){
            System.out.println(n);
        }*/
        //查询记录数
        int recordCount = noticeService.findNoticeCount(notice);
        pageModel.setRocordCount(recordCount);
        model.addAttribute("notices",notices);
        model.addAttribute("pageModel",pageModel);
        model.addAttribute("notice",notice);


        //System.out.println("公告记录："+recordCount);
        return "/jsp/notice/notice.jsp";


    }

    //修改
    @RequestMapping("modifyNotice.do")
    public String modifyNotice(int pageIndex,String flag,Notice notice,Model model){
        if (flag == null){
        notice = noticeService.findNoticeById(notice.getId());
        model.addAttribute("notice",notice);
        model.addAttribute("pageIndex",pageIndex);
        return "/jsp/notice/showUpdateNotice.jsp";
    }else{
        int rows = noticeService.modifyNotice(notice);
        if (rows > 0){
            return "redirect:/notice/findNotice.do?pageIndex="+pageIndex;
        }else{
            //在fail里填写失败警告信息
            model.addAttribute("fail","公告信息修改失败！！");
        }
            return "/jsp/fail.jsp";
        }
    }

    //预览公告
    @RequestMapping("/previewNotice.do")
    public String previewNotice(int id,Model model){
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice",notice);
        return "/jsp/notice/previewNotice.jsp";
    }

    //删除公告信息
    @RequestMapping("/removeNotice.do")
    public String removeNotice(Integer[] ids,Model model){
        int rows = noticeService.removeNotice(ids);
        if (rows == ids.length){
            return "redirect:/notice/findNotice.do";
        }else{
            model.addAttribute("fail","公告信息删除失败");
            return "/jsp/fail.jsp";
        }
    }

    //添加公告
    @RequestMapping("addNotice.do")
    public String addNotice(Notice notice, Model model, HttpSession session){
        //因为不同用户有不同的公告，所以要获取当前登陆用户
        User login_user = (User) session.getAttribute("login_user");
        notice.setUser(login_user);
        int rows = noticeService.addNotice(notice);
        if(rows > 0){
            //查询的总记录速就是要跳转到页数
            PageModel pageModel = new PageModel();
            //null表示查询全部的记录数但不包含条件
            int recordCount = noticeService.findNoticeCount(null);
            pageModel.setRocordCount(recordCount);
            return "redirect:/notice/findNotice.do?pageIndex="+pageModel.getTotalSize();
        }else{
            model.addAttribute("fail","公告信息添加失败！！！");
            return "/jsp/fail.jsp";
        }
    }
}
