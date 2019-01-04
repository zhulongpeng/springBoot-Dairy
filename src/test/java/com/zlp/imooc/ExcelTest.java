package com.zlp.imooc;

import com.zlp.DairyApplicationTests;
import com.zlp.dairy.business.entity.User;
import com.zlp.dairy.business.model.UserMO;
import com.zlp.dairy.business.service.UserService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ExcelTest extends DairyApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void importExcel(){
        String fileName = "C:/Users/Administrator/Desktop/user-import.xlsx";
        File file = new File(fileName);
        List<User> list = new ArrayList<>();
        try {
            FileInputStream fileInputStream = FileUtils.openInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(0);
            int last = xssfSheet.getLastRowNum();
            int first = xssfSheet.getFirstRowNum();
            //遍历获取单元格里的数据
            for(int i = first+1;i < last;i++){
                XSSFRow row = xssfSheet.getRow(i);
                //获取所在行的第一个行号
                short firstCellNum = row.getFirstCellNum();
                //获取所在行的最后一个行号
                String nickName = row.getCell(firstCellNum).getStringCellValue();
                String password = row.getCell(firstCellNum + 1).getStringCellValue();
                String email = row.getCell(firstCellNum + 2).getStringCellValue();
                String mobile = row.getCell(firstCellNum + 3).getCellFormula();
                userService.createUser(copyForEntity(nickName, password, email, mobile, null));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private UserMO copyForEntity(String nickName, String password, String email, String mobile, UserMO result) {
        result = result == null ? new UserMO() : result;
        if(StringUtils.isNotBlank(nickName))
            result.setNickName(nickName);
        if(StringUtils.isNotBlank(password))
            result.setPassword(password);
        if(StringUtils.isNotBlank(email))
            result.setEmail(email);
        if(StringUtils.isNotBlank(mobile))
            result.setMobile(mobile);
        return  result;
    }
}
