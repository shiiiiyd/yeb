package com.xxxx.yeb.pojo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_employee")
@ApiModel(value = "Employee对象", description = "")
public class EmployeeQuery extends Employee {

    private static final long serialVersionUID = 1L;
    @Excel(name = "员工编号")
    @ApiModelProperty(value = "员工编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Excel(name = "员工姓名")
    @ApiModelProperty(value = "员工姓名")
    private String name;

    @Excel(name = "性别")
    @ApiModelProperty(value = "性别")
    private String gender;

    @Excel(name = "出生日期",importFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "出生日期")
    private LocalDate birthday;

    @Excel(name = "身份证号")
    @ApiModelProperty(value = "身份证号")
    private String idCard;

    @Excel(name = "婚姻状况")
    @ApiModelProperty(value = "婚姻状况")
    private String wedlock;

    @Excel(name = "民族")
    @ApiModelProperty(value = "民族")
    private Integer nationId;

    @Excel(name = "籍贯")
    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @Excel(name = "政治面貌")
    @ApiModelProperty(value = "政治面貌")
    private Integer politicId;

    @Excel(name = "邮箱")
    @ApiModelProperty(value = "邮箱")
    private String email;

    @Excel(name = "电话号码")
    @ApiModelProperty(value = "电话号码")
    private String phone;

    @Excel(name = "联系地址")
    @ApiModelProperty(value = "联系地址")
    private String address;

    @Excel(name = "所属部门")
    @ApiModelProperty(value = "所属部门")
    private Integer departmentId;

    @Excel(name = "职称ID")
    @ApiModelProperty(value = "职称ID")
    private Integer jobLevelId;

    @Excel(name = "职位ID")
    @ApiModelProperty(value = "职位ID")
    private Integer posId;

    @Excel(name = "聘用形式")
    @ApiModelProperty(value = "聘用形式")
    private String engageForm;

    @Excel(name = "最高学历")
    @ApiModelProperty(value = "最高学历")
    private String tiptopDegree;

    @Excel(name = "所属专业")
    @ApiModelProperty(value = "所属专业")
    private String specialty;

    @Excel(name = "毕业院校")
    @ApiModelProperty(value = "毕业院校")
    private String school;

    @Excel(name = "入职日期",importFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "入职日期")
    private LocalDate beginDate;

    @Excel(name = "在职状态")
    @ApiModelProperty(value = "在职状态")
    private String workState;

    @Excel(name = "工号")
    @ApiModelProperty(value = "工号")
    private String workID;

    @Excel(name = "合同期限")
    @ApiModelProperty(value = "合同期限")
    private Double contractTerm;

    @Excel(name = "转正日期",importFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "转正日期")
    private LocalDate conversionTime;

    @Excel(name = "离职日期",importFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "离职日期")
    private LocalDate notWorkDate;

    @Excel(name = "合同起始日期",importFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同起始日期")
    private LocalDate beginContract;

    @Excel(name = "合同终止日期",importFormat = "yyyy-MM-dd")
    @ApiModelProperty(value = "合同终止日期")
    private LocalDate endContract;

    @Excel(name = "工龄")
    @ApiModelProperty(value = "工龄")
    private Integer workAge;

    @Excel(name = "工资账套ID")
    @ApiModelProperty(value = "工资账套ID")
    private Integer salaryId;


    @Excel(name = "政治面貌")
    @ApiModelProperty(value = "政治面貌")
    private String politicsStatusName;

    @Excel(name = "部门")
    @ApiModelProperty(value = "部门")
    private String departmentName;

    @Excel(name = "职称")
    @ApiModelProperty(value = "职称")
    private String positionName;

    @Excel(name = "职位")
    @ApiModelProperty(value = "职位")
    private String joblevelName;
    @Excel(name = "民族")
    @ApiModelProperty(value = "民族")
    private String nationName;





}
