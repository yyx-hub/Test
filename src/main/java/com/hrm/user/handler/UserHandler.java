package com.hrm.user.handler;


import com.hrm.commons.beans.User;

import com.hrm.user.service.IUserService;
import com.hrm.utils.PageModel;
import org.aspectj.asm.IModelFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserHandler {

    @Autowired
    private IUserService userService;
//登陆
    @RequestMapping("/login.do")
    public String login(User user, HttpSession session, Model model){

      User login_user =   userService.findUserByLoginUser(user);
       // System.out.println(login_user);
        System.out.println(login_user.getCreatedate());
      if (login_user!= null){
          session.setAttribute("login_user",login_user);
          return "/jsp/main.jsp";
      }
      else {
          model.addAttribute("login_error","用户名或密码错误，请重新登陆！");
          return "/index.jsp";
      }
    }
    //退出
  @RequestMapping("/logout.do")
    public String logout(HttpSession session, Model model){
        session.removeAttribute("login_user");
        model.addAttribute("login_error","退出成功，请重新登陆！");
        return "/index.jsp";
    }
//用户分页查询
    @RequestMapping("/findUser.do")
    public String findUser(@RequestParam(defaultValue = "1") int pageIndex, User user,Model model){
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);


        int recordCount = userService.findUserCount(user);
        pageModel.setRocordCount(recordCount);
        List<User> users = userService.findUser(user,pageModel);


        model.addAttribute("pageModel",pageModel);
        model.addAttribute("users",users);
        model.addAttribute("user",user);
       for (User u:users){
           System.out.println(u);
       }
        return "/jsp/user/user.jsp";

    }
    //用户修改
    @RequestMapping("/modifyUser.do")
    public String modifyUser(int pageIndex,User user,String flag,Model model){
        if(flag ==null){

        user = userService.findUserById(user.getId());
        model.addAttribute("user",user);
        model.addAttribute("pageIndex",pageIndex);
        return "/jsp/user/showUpdateUser.jsp";
    }else {
           int rows =  userService.modifyUser(user);
           if(rows > 0){
               return "redirect:/user/findUser.do?pageIndex"+pageIndex;
           }else{
               model.addAttribute("fail","用户修改信息失败");
                return "/jsp/fail.jsp";
           }
        }
    }
    //用户添加
    @RequestMapping("/addUser.do")
    public String addUser(User user,Model model){
        int rows = userService.addUser(user);
        if(rows>0){
            PageModel pageModel = new PageModel();
            int recordCount = userService.findUserCount(null);
            pageModel.setRocordCount(recordCount);
            pageModel.getTotalSize();
            return "redirect:/user/findUser.do?pageIndex="+pageModel.getTotalSize();
        }else{
            model.addAttribute("fail","用户修改信息失败！");
            return "/jsp/fail.jsp";
        }
    }

//用户删除
    @RequestMapping("/removeUser.do")
    public String removeUser(Integer[] ids,Model model,HttpSession session){
        User login_user = (User) session.getAttribute("login_user");
        for(Integer id:ids){
            if (login_user.getId()==id){
                model.addAttribute("fail","不能删除当前登陆用户自身");
                return "/jsp/fail.jsp";
            }
        }
        try{
            int rows = userService.removeUser(ids);
            if(rows == ids.length){
                return "/user/findUser.do";
            }else{
                model.addAttribute("fail","用户信息删除失败！");
                return "/jsp/fail.jsp";
            }
        }catch (DataIntegrityViolationException e){
            model.addAttribute("fail","当前用户有发布公告，请删除后在删除该用户！！");
            return "/jsp/fail.jsp";
        }
        }
        //检查重复登录名称
    @RequestMapping("/checkLoginname.do")
    @ResponseBody
    public String checkLoginname(String loginname){
        //System.out.println(loginname);
        User user = userService.findLoginname(loginname);
        if (user!=null){
            return "EXIST";
        }else{
            return "okkkk";
        }

    }


}
