package cn.raccoon.team.boot.service.impl;

import cn.raccoon.team.boot.entity.Check;
import cn.raccoon.team.boot.entity.Progress;
import cn.raccoon.team.boot.mapper.CheckMapper;
import cn.raccoon.team.boot.mapper.FileMapper;
import cn.raccoon.team.boot.mapper.ProgressMapper;
import cn.raccoon.team.boot.service.IProgressService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ProgressServiceImpl implements IProgressService {

    @Autowired
    private ProgressMapper progressMapper;

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private CheckMapper checkMapper;


    @Override
    public List<Progress> listProgress(Integer userId) {
        List<Progress> progresses = progressMapper.selectList(new QueryWrapper<Progress>().eq("userId", userId).isNull("deleteAt"));
        progresses.stream()
                .forEach(progress -> {
                    // 和最新进度的打卡记录计算得出浮动
                    Check lastCheck = checkMapper.getLastCheck(progress.getId());
                    if (lastCheck != null && lastCheck.getCurrent() != null) {
                        progress.setChange(progress.getCurrent() - lastCheck.getCurrent());
                    }
                    if (progress.getFileId() != null) {
                        progress.setPath(fileMapper.selectById(progress.getFileId()).getPath());
                    }
                });
        return progresses;
    }

    @Override
    public Boolean updateProgress(Progress progress) {
        if (progressMapper.updateById(progress) > 0) {
            // 还需要打个卡
            Check check = new Check();
            check.setUserId(progress.getUserId());
            check.setProgressId(progress.getId());
            check.setCurrent(progress.getCurrent());
            checkMapper.insert(check);
        }
        return true;
    }

    @Override
    public Boolean insertProgress(Progress progress) {
        if (progressMapper.insert(progress) > 0) {
            // 还需要打个卡
            Check check = new Check();
            check.setUserId(progress.getUserId());
            check.setProgressId(progress.getId());
            check.setCurrent(progress.getCurrent());
            checkMapper.insert(check);
        }
        return true;
    }

    @Override
    public Progress getProgressById(Integer progressId) {
        Progress progress = progressMapper.selectById(progressId);
        if (progress != null && progress.getFileId() != null) {
            progress.setPath(fileMapper.selectById(progress.getFileId()).getPath());
        }
        return progress;
    }

    @Override
    public Boolean deleteProgress(Progress progress) {
        progress.setDeleteAt(new Date());
        return progressMapper.updateById(progress) > 0;
    }
}