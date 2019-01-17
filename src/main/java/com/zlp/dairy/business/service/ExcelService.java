package com.zlp.dairy.business.service;


import com.zlp.dairy.base.util.ResResult;
import com.zlp.dairy.business.entity.UploadResultError;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ExcelService {

    ResResult<List<UploadResultError>> fileUploadForIssuer(MultipartFile file);

}
