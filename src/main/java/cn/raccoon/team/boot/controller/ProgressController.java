package cn.raccoon.team.boot.controller;

import cn.raccoon.team.boot.entity.Progress;
import cn.raccoon.team.boot.exception.response.R;
import cn.raccoon.team.boot.service.IProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/progress")
@Validated
public class ProgressController {

    @Autowired
    private IProgressService progressService;

    @GetMapping("/listProgress")
    public R listProgress(@RequestParam("userId") Integer userId) {
        return R.ok(progressService.listProgress(userId));
    }

    @PostMapping("/updateProgress")
    public R updateProgress(@RequestBody Progress progress) {
        return R.ok(progressService.updateProgress(progress));
    }

    @PostMapping("/insertProgress")
    public R insertProgress(@Valid @RequestBody Progress progress) {
        return R.ok(progressService.insertProgress(progress));
    }

    @GetMapping("/getProgressById")
    public R getProgressById(@RequestParam("progressId") Integer progressId) {
        return R.ok(progressService.getProgressById(progressId));
    }

    @PostMapping("/deleteProgress")
    public R deleteProgress(@RequestBody Progress progress) {
        return R.ok(progressService.deleteProgress(progress));
    }
}
