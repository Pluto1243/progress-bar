package cn.raccoon.team.boot.service;

import cn.raccoon.team.boot.entity.Check;

import java.util.List;

public interface ICheckService {

    List<Check> listCheck(Integer userId);

    Boolean insertCheck(Check check);

}
