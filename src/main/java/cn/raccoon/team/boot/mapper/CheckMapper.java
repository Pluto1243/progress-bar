package cn.raccoon.team.boot.mapper;

import cn.raccoon.team.boot.entity.Check;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CheckMapper extends BaseMapper<Check> {
    List<Check> listCheck(@Param("userId") Integer userId);

    Check getLastCheck(@Param("progressId") Integer progressId);
}