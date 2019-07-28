package net.zzqd.mmall.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author zqnh
 * @date 2019/7/28 on 8:34.
 */
public interface IFileService
{
    String upload(MultipartFile file, String path);
}
