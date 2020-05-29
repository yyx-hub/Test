package com.hrm.document.handler;

import com.hrm.commons.beans.Document;
import com.hrm.commons.beans.User;
import com.hrm.document.service.IDocumentService;
import com.hrm.utils.PageModel;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;


@Controller
@RequestMapping("/document")
public class DocumentHandler {

    @Autowired
    IDocumentService documentService;

    @RequestMapping("/findDocument.do")
    public String findDocument(@RequestParam(defaultValue = "1") int pageIndex, String title, Model moddel){
        PageModel pageModel = new PageModel();
        pageModel.setPageIndex(pageIndex);
        List<Document> documents = documentService.findDocument(title,pageModel);

        //查询记录数
        int recordCount = documentService.findDocumentCount(title);
        pageModel.setRocordCount(recordCount);
        moddel.addAttribute("documents",documents);
        moddel.addAttribute("pageModel",pageModel);
        moddel.addAttribute("title",title);
        for (Document d:documents){
            System.out.println(d);
        }
        return "/jsp/document/document.jsp";
    }

//文件上传
    @RequestMapping("/addDocument.do")
    //MultipartFile也可以直接在beans里定义，设置get set方法 ，也可以在这直接传
    public String addDocument(Document document, HttpSession session,Model model) throws IOException {
      //  System.out.println("上传的文档为:"+document);
        //1.保存上传的文件到服务器
        String path = "C:/upload";
        //检查路径下是否存在文件
        File file  = new File(path);
        if (!file.exists()){  //如果不存在就创建一个
            file.mkdirs();  //加s是子父目录都可以创建 不加s只能创建父目录
        }
        //获取原始文件名称:保证重名文件不会被覆盖掉/或者使用uuid
        String filename = System.currentTimeMillis() + "-" + document.getFile().getOriginalFilename();
        //定义保存的文件
        //获取当前的文件，但文件为MultipartyFile，所以要转换为File，然后保存到服务器对应的地址
        document.getFile().transferTo(new File(path,filename));

        //2.数据库的信息插入对应数据
        //给document的filename赋值
        document.setFilename(filename);
        //从session中获取当前登录用户，就能得到user信息
        User login_user = (User)session.getAttribute("login_user");
        document.setUser(login_user);
        //调用service方法插入文档对应信息
        int rows = documentService.addDocument(document);
        if (rows > 0){
            PageModel pageModel = new PageModel();
            int recordCount = documentService.findDocumentCount(null);
            pageModel.setRocordCount(recordCount);
            //重定向查的是全部数据
            return "redirect:/document/findDocument.do?pageIndex="+pageModel.getTotalSize();
        }else{
            model.addAttribute("fail","上传失败！！！！");
            return "/jsp/fail.jsp";
        }

    }

    //文档修改
    @RequestMapping("/modifyDocument.do")
    public String modifyDocument (String flag,int pageIndex,Document document,Model model) throws IOException {
        //按id查询原始文档信息
        if (flag == null){
            document = documentService.findDocumentById(document.getId());
            model.addAttribute("document",document);
            model.addAttribute("pageIndex",pageIndex);
            return "/jsp/document/showUpdateDocument.jsp";
        }else{
            System.out.println("修改文档："+document);
            //判断修改文档时是否上传新文件，为空时不需要删除之前文件等操作，不为空时才进行以下操作
            if (!document.getFile().isEmpty()){
                String path = "C:/upload";
                //获取原始目标文件信息
                Document target = documentService.findDocumentById(document.getId());
                //判断原始文件是否存在
                File targetFile = new File(path,target.getFilename());
                if (targetFile.exists()){
                    //删除要被修改的之前的文件
                    targetFile.delete();
                }
                //把修改后接受到的新文件保存在服务器
                String filename = System.currentTimeMillis() + "-" + document.getFile().getOriginalFilename();
                document.getFile().transferTo(new File(path,filename));
                //新的文件名称保存在document
                document.setFilename(filename);
            }
            //修改数据库判断修改文档时是否上传新文件，为空时不需要删除之前文件等操作，不为空时才进行以下操作
            int rows = documentService.modifyDocument(document);
            if (rows > 0){
                return "redirect:/document/findDocument.do?pageIndex="+pageIndex;
            }else {
                model.addAttribute("fail","文档信息修改失败");
                return "/jsp/fail.jsp";
            }

        }

    }

//文件下载
    @RequestMapping("/downLoad.do")
    public ResponseEntity<Object> downLoad(int id) throws IOException {
        String path = "C:/upload";
        Document targetDocument = documentService.findDocumentById(id);
        String filename = targetDocument.getFilename();
        //获取下载的目标文件
        File file = new File(path ,filename);
        //如果下载的文件存在
        if (file.exists()){
            //先获取才能响应   不包括IE浏览器
            filename = new String(filename.getBytes("UTF-8"),"iso8859-1");

            //设置响应头的响应内容类型
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
            headers.setContentDispositionFormData("attachment",filename);
            //返回为字节数组
            return new ResponseEntity<Object>(FileUtils.readFileToByteArray(file),headers, HttpStatus.OK);

        }else{
            String error = "下载的文档不存在！！";
            //设置响应头的响应内容为html
            HttpHeaders headers = new HttpHeaders();
            MediaType mediaType = new MediaType("text","html", Charset.forName("utf-8"));
            headers.setContentType(mediaType);
            //返回的为字符串
            return new ResponseEntity<Object>(error,headers,HttpStatus.OK);

        }

    }

    //删除（包括数据库信息和文件）
    @RequestMapping("/removeDocument.do")
    public String removeDocument(Integer[] ids,Model model){
        String path = "C:/upload";
        int rows = 0;
        //查找要删除的目标文件
        for (Integer id:ids){
            Document target = documentService.findDocumentById(id);
            File targetFile = new File(path,target.getFilename());
            //如果目标文件存在，就从服务器删除文件
            if (targetFile.exists()){
                targetFile.delete();
            }
            //删除文档对应的数据库信息
            int i = documentService.removeDocument(id);
            if (i > 0){
                rows++;
            }
        }
            if (rows == ids.length){
                return "redirect:/document/findDocument.do";
            }else{
                model.addAttribute("fail","文档删除失败！！！");
                return "/jsp/fail.jsp";
            }
    }

}
