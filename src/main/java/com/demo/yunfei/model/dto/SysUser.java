package com.demo.yunfei.model.dto;

import com.demo.yunfei.model.Admin;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @author : yunfei
 * @date : 2018/10/30 20:01
 */
@Data
@Builder
@ToString
public class SysUser {

   private Admin admin;
}
