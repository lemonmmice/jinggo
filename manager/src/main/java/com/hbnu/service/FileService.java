package com.hbnu.service;

import com.hbnu.vo.ImageVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author LCY
 * @date Created in 2022/5/12 11:08
 */
public interface FileService {

    ImageVO upload(MultipartFile uploadFile);
}
