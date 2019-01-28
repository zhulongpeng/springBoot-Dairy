package com.zlp.dairy.business.controller;

import com.zlp.dairy.base.controller.BaseController;
import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.base.util.XaUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Api(value = "File", description = "文件操作接口")
@RestController
public class FileController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${web.upload-path}")
    private String uploadPath;

    //上传文件类型
    private static final List<String> fileExt = new ArrayList<>();

    static{
        fileExt.add(".JPG");
        fileExt.add(".PNG");
        fileExt.add(".JPEG");
        fileExt.add(".PDF");
    }

    @ApiOperation("上传文件信息")
    @PostMapping(value = "/v1/cms/files", consumes = "multipart/form-data")
    @ResponseBody
    public ResResult<String> uploadImage(
            @RequestParam(value = "file", required = true)MultipartFile file,
            @RequestParam(required = false) Integer width,
            @RequestParam(required = false) Integer height
    ){
        ResResult<String> result = new ResResult<>();
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        String newName = UUID.randomUUID().toString() + ext;
        File targetFile = new File(uploadPath + "/" + newName);
        //检查文件类型
        if(!fileExt.contains(ext.toUpperCase())){
            result.error("Upload image failed,file type :jpg, png, jpeg, pdf");
        }
        //如果图片有宽高要求，检查图片的宽高
        if(XaUtil.isNotEmpty(width) && XaUtil.isNotEmpty(height)){
            try {
                BufferedImage image = ImageIO.read(file.getInputStream());
                int _width = image.getWidth();
                int _height = image.getHeight();
                System.out.println(width + "  " + height);
                if(width != _width || height != _height){
                    result.error("The rules of uploaded pictures do not match，width：" + width + " height：" + height + " .Please upload again!");
                }
            } catch (IOException e) {
                result.error(e.getMessage());
            }
        }
        try {
            file.transferTo(targetFile);
            result.success(newName);
        } catch (IOException e) {
            result.error(e.getMessage());
        }
        return result;
    }

}
