package com.it.common.api.model.system;

import com.it.common.core.model.BaseModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@Data
@EqualsAndHashCode(callSuper = true)
public class SysOrg extends BaseModel<SysOrg> {

	/**
	 * 机构ID
	 */
	private String orgId;
	/**
	 * 机构编码
	 */
	@NotBlank(message = "机构编号不能为空")
	private String code;

	/**
	 * 企业承担角色
	 */
	@NotBlank(message = "角色名称不能为空")
	private String type;
	/**
	 * 机构名称
	 */
	@NotBlank(message = "机构名称不能为空")
	private String name;
	/**
	 * 所属省份
	 */
	private String provinceId;
	/**
	 * 所属城市
	 */
	private String cityId;
	/**
	 * 所属区县
	 */
	private String districtId;
	/**
	 * 联系人
	 */
	@NotBlank(message = "机构联系人不能为空")
	private String contacter;
	/**
	 *负责人证件照片
	 */
	private String contacterImg;
	/**
	 * 负责人证件类型
	 */
	private String idcardType;

	/**
	 * 负责人证件号
	 */
	private String idcardNum;
	/**
	 * 联系电话
	 */
	@NotBlank(message = "机构联系方式不能为空")
	private String phone;

	/**
	 * 施工许可证编号
	 */
	private String builderlicenceNum;
	/**
	 * 备注
	 */
	private String description;

}
