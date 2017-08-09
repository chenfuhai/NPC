package com.liufeng.npc.controller;

import com.liufeng.npc.bean.EditorMsg;
import com.liufeng.npc.utils.Log;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 对文件进行管理
 */
@Controller
@RequestMapping("/admin")
public class FileController {

    //文件上传
    @RequestMapping("/uploadFile")
    @ResponseBody
    public EditorMsg uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,
                                HttpServletRequest request) {
        String path = request.getServletContext().getRealPath("/");
        Log.logI("文件开始保存  "+path);
        //服务器项目所在的实际地址
        try {
            request.getServletContext().getRealPath("/");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(path);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String ymd = sdf.format(new Date());
        String saveFolder = "/upload/" + ymd + "/";
        String fileName = file.getOriginalFilename();//文件重名问题 自动覆盖
        // path应该是 upload/YYMMDD/
        File targetFile = new File(path + saveFolder, fileName);
        //判断文件是否存在  不存在就创建 可以这样用
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        //保存语句
        try {
            file.transferTo(targetFile);
            System.out.println(path + saveFolder + fileName);
            String fileUrl = request.getContextPath() + saveFolder + fileName;
            System.out.println(fileUrl);
            return EditorMsg.getSuccessWithUrl(fileUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return EditorMsg.getError("服务器IO错误");
        } catch (Exception e) {
            e.printStackTrace();
            return EditorMsg.getError("服务器错误");
        }

    }


    @RequestMapping("/fileManager")
    public void fileManager(HttpServletRequest request, HttpServletResponse  response) throws IOException {


//根目录路径，可以指定绝对路径，比如 /var/www/attached/
        String rootPath = request.getServletContext().getRealPath("/") + "upload/";
//根目录URL，可以指定绝对路径，比如 http://www.yoursite.com/attached/
        String rootUrl = request.getContextPath() + "/upload/";
//图片扩展名
        String[] fileTypes = new String[]{"gif", "jpg", "jpeg", "png", "bmp"};
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        String dirName = sdf.format(new Date());
        //rootPath += dirName + "/";
        //rootUrl += dirName + "/";
        File saveDirFile = new File(rootPath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }

//根据path参数，设置各路径和URL
        String path = request.getParameter("path") != null ? request.getParameter("path") : "";
        String currentPath = rootPath + path;
        String currentUrl = rootUrl + path;
        String currentDirPath = path;
        String moveupDirPath = "";
        if (!"".equals(path)) {
            String str = currentDirPath.substring(0, currentDirPath.length() - 1);
            moveupDirPath = str.lastIndexOf("/") >= 0 ? str.substring(0, str.lastIndexOf("/") + 1) : "";
        }

//排序形式，name or size or type
        String order = request.getParameter("order") != null ? request.getParameter("order").toLowerCase() : "name";

//不允许使用..移动到上一级目录
        if (path.indexOf("..") >= 0) {
            System.out.println("Access is not allowed.");
            response.getWriter().print( "Access is not allowed.");
            return ;
        }
//最后一个字符不是/
        if (!"".equals(path) && !path.endsWith("/")) {
            System.out.println("Parameter is not valid.");
            response.getWriter().print( "Parameter is not valid.");
            return ;
        }
//目录不存在或不是目录
        File currentPathFile = new File(currentPath);
        if (!currentPathFile.isDirectory()) {
            System.out.println("Directory does not exist.");
            response.getWriter().print( "Directory does not exist.");
            return ;
        }

//遍历目录取的文件信息
        List<Hashtable> fileList = new ArrayList<Hashtable>();
        if (currentPathFile.listFiles() != null) {
            for (File file : currentPathFile.listFiles()) {
                Hashtable<String, Object> hash = new Hashtable<String, Object>();
                String fileName = file.getName();
                if (file.isDirectory()) {
                    hash.put("is_dir", true);
                    hash.put("has_file", (file.listFiles() != null));
                    hash.put("filesize", 0L);
                    hash.put("is_photo", false);
                    hash.put("filetype", "");
                } else if (file.isFile()) {
                    String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
                    hash.put("is_dir", false);
                    hash.put("has_file", false);
                    hash.put("filesize", file.length());
                    hash.put("is_photo", Arrays.<String>asList(fileTypes).contains(fileExt));
                    hash.put("filetype", fileExt);
                }
                hash.put("filename", fileName);
                hash.put("datetime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(file.lastModified()));
                fileList.add(hash);
            }
        }

        if ("size".equals(order)) {
            Collections.sort(fileList, new SizeComparator());
        } else if ("type".equals(order)) {
            Collections.sort(fileList, new TypeComparator());
        } else {
            Collections.sort(fileList, new NameComparator());
        }



        JSONObject result = new JSONObject();
        result.put("moveup_dir_path", moveupDirPath);
        result.put("current_dir_path", currentDirPath);
        result.put("current_url", currentUrl);
        result.put("total_count", fileList.size());
        result.put("file_list", fileList);

        System.out.println(result.toString() + result.toString());
        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(result.toString());
    }


    @ResponseBody
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public EditorMsg handleException(MaxUploadSizeExceededException ex){
            long max = ex.getMaxUploadSize();
            long kb = 1024;
            long mb = 1024*1024;

            return EditorMsg.getError("上传的文件大小应不大于"+max/kb+"KB"+"("+max/mb+"MB"+")");
    }


}


class NameComparator implements Comparator {
    public int compare(Object a, Object b) {
        Hashtable hashA = (Hashtable) a;
        Hashtable hashB = (Hashtable) b;
        if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
            return -1;
        } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
            return 1;
        } else {
            return ((String) hashA.get("filename")).compareTo((String) hashB.get("filename"));
        }
    }
}

class SizeComparator implements Comparator {
    public int compare(Object a, Object b) {
        Hashtable hashA = (Hashtable) a;
        Hashtable hashB = (Hashtable) b;
        if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
            return -1;
        } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
            return 1;
        } else {
            if (((Long) hashA.get("filesize")) > ((Long) hashB.get("filesize"))) {
                return 1;
            } else if (((Long) hashA.get("filesize")) < ((Long) hashB.get("filesize"))) {
                return -1;
            } else {
                return 0;
            }
        }
    }
}

class TypeComparator implements Comparator {
    public int compare(Object a, Object b) {
        Hashtable hashA = (Hashtable) a;
        Hashtable hashB = (Hashtable) b;
        if (((Boolean) hashA.get("is_dir")) && !((Boolean) hashB.get("is_dir"))) {
            return -1;
        } else if (!((Boolean) hashA.get("is_dir")) && ((Boolean) hashB.get("is_dir"))) {
            return 1;
        } else {
            return ((String) hashA.get("filetype")).compareTo((String) hashB.get("filetype"));
        }
    }
}




