package cn.raccoon.team.boot.service.impl;

import cn.raccoon.team.boot.entity.Check;
import cn.raccoon.team.boot.mapper.CheckMapper;
import cn.raccoon.team.boot.service.ICheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckServiceImpl implements ICheckService {

    @Autowired
    private CheckMapper checkMapper;

    @Override
    public List<Check> listCheck(Integer userId) {
        return checkMapper.listCheck(userId);
    }

    @Override
    public Boolean insertCheck(Check check) {
        return checkMapper.insert(check) > 0;
    }
}
