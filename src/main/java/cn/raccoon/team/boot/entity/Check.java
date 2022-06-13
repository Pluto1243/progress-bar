package cn.raccoon.team.boot.entity;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("check_in")
public class Check implements Serializable {

    private Integer id;

    /**
    * 打卡时间
    */
    private String date;

    /**
     * 打卡次数
     */
    private Integer count;

    /**
    * 用户Id
    */
    private Integer userId;

    /**
    * 进度Id
    */
    private Integer progressId;

    /**
     * 打卡当前进度
     */
    private Integer current;
}