package com.zyc.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 用户
 * </p>
 *
 * @author admin
 * @since 2022-09-02
 */
@Data
  @EqualsAndHashCode(callSuper = false)
    public class SysUser implements Serializable {

    private static final long serialVersionUID=1L;

      /**
     * 主键
     */
        @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      /**
     * 账号
     */
      private String userName;

      /**
     * 密码
     */
      private String password;

      /**
     * 昵称
     */
      private String nickName;

      /**
     * 手机号
     */
      private String mobile;

      /**
     * 是否删除 0正常 1删除
       * @TableLogic加了这个注解启动逻辑删除
     */
      @TableLogic
      private Integer isDeleted = 0;

      /**
     * 账号类型  2 系统账号 1 员工 3 候选人 ...
     */
      private Integer accountType;

      /**
     * 账号状态 0 可用  1冻结
     */
      private Integer accountState;

      /**
     * 创建时间
     */
        @TableField(fill = FieldFill.INSERT)
      private LocalDateTime createTime;

      /**
     * 更新时间
     */
        @TableField(fill = FieldFill.INSERT_UPDATE)
      private LocalDateTime updateTime;

      /**
     * 用户权限
     */
      private String userRole;

      /**
     * 用户权限
     */
      private String updateUser;

      /**
     * 创建人
     */
      private String createUser;


  @Override
  public String toString() {
    return "SysUser{" +
            "id=" + id +
            ", userName='" + userName + '\'' +
            ", password='" + password + '\'' +
            ", nickName='" + nickName + '\'' +
            ", mobile='" + mobile + '\'' +
            ", isDeleted=" + isDeleted +
            ", accountType=" + accountType +
            ", accountState=" + accountState +
            ", createTime=" + createTime +
            ", updateTime=" + updateTime +
            ", userRole='" + userRole + '\'' +
            ", updateUser='" + updateUser + '\'' +
            ", createUser='" + createUser + '\'' +
            '}';
  }
}
