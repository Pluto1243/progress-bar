package cn.raccoon.team.boot.entity;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
public class Progress {
    private Integer id;

    /**
    * 名称
    */
    @NotNull(message = "请输入名称")
    private String name;

    /**
    * 当前进度
    */
    private Integer current = 0;

    /**
    * 目标进度
    */
    @NotNull(message = "请输入目标进度")
    private Integer total;

    /**
    * 创建时间
    */
    private Date createAt;

    /**
    * 打卡时间
    */
    private Date updateAt;

    /**
     * 单位
     */
    @NotNull(message = "请输入单位")
    private String unit;

    /**
    * 用户ID
    */
    @NotNull(message = "你是谁啊？")
    private Integer userId;

    /**
     * 文件ID
     */
    private Integer fileId;

    /**
     * 文件路径
     */
    @TableField(exist = false)
    private String path;

    /**
     * 打卡浮动
     */
    @TableField(exist = false)
    private Integer change;

    /**
     * 删除时间
     */
    private Date deleteAt;
}