package cn.raccoon.team.boot.service;

import cn.raccoon.team.boot.entity.Progress;

import java.util.List;

public interface IProgressService {

    List<Progress> listProgress(Integer userId);

    Boolean updateProgress(Progress progress);

    Boolean insertProgress(Progress progress);

    Progress getProgressById(Integer progressId);

    Boolean deleteProgress(Progress progress);
}
