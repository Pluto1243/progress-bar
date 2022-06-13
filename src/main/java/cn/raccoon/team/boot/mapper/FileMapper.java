package cn.raccoon.team.boot.mapper;

import cn.raccoon.team.boot.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileMapper extends BaseMapper<File> {

    /**
     * 上传文件
     *
     * @param path
     * @return
     */
    Boolean insertFile(File file);
}
