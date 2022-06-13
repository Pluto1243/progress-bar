package cn.raccoon.team.boot.controller;

import cn.raccoon.team.boot.entity.Check;
import cn.raccoon.team.boot.exception.response.R;
import cn.raccoon.team.boot.service.ICheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 打卡数据
 *
 * @author wangjie
 * @date 15:02 2022年05月25日
 **/
@RestController
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private ICheckService checkService;

    /**
     * @param userId
     * @return cn.raccoon.team.boot.exception.response.R
     * @description 查看打卡数据
     * @author wangjie
     * @date 16:26 2022年05月25日
     */
    @GetMapping("/listCheck")
    public R listCheck(@RequestParam("userId") Integer userId) {
        return R.ok(checkService.listCheck(userId));
    }

    /**
     * @description 新增打卡
     *
     * @author wangjie
     * @date 16:47 2022年05月25日
     * @Param check: 
     * @return cn.raccoon.team.boot.exception.response.R 
     */
    @PostMapping("/insertCheck")
    public R insertCheck(@RequestBody Check check) {
        return R.ok(checkService.insertCheck(check));
    }
}
