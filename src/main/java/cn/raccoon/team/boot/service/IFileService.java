package cn.raccoon.team.boot.service;

import cn.raccoon.team.boot.entity.File;

import java.util.List;

/**
 * @Date 2021/12/20 16:32
 */
public interface IFileService {
    /**
     * 上传文件
     *
     * @param file
     * @return
     */
    Boolean uploadFile(File file);
}
