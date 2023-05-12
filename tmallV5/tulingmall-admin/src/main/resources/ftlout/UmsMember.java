package com.tuling.tulingmall.model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

/**
* @desc：the module of table ums_member
found table comment  会员表
* @date 2022/10/14
*/
@TableName("ums_member")
public class UmsMember implements Serializable{
    private static final long serialVersionUID = 1L;
        
        @TableId
        @TableField("id")
        private Long id;

        
        @TableField("member_level_id")
        private Long memberLevelId;

        //用户名
        @TableField("username")
        private String username;

        //密码
        @TableField("password")
        private String password;

        //昵称
        @TableField("nickname")
        private String nickname;

        //手机号码
        @TableField("phone")
        private String phone;

        //帐号启用状态:0->禁用；1->启用
        @TableField("status")
        private Integer status;

        //注册时间
        @TableField("create_time")
        private Date createTime;

        //头像
        @TableField("icon")
        private String icon;

        //性别：0->未知；1->男；2->女
        @TableField("gender")
        private Integer gender;

        //生日
        @TableField("birthday")
        private Date birthday;

        //所做城市
        @TableField("city")
        private String city;

        //职业
        @TableField("job")
        private String job;

        //个性签名
        @TableField("personalized_signature")
        private String personalizedSignature;

        //用户来源
        @TableField("source_type")
        private Integer sourceType;

        //积分
        @TableField("integration")
        private Integer integration;

        //成长值
        @TableField("growth")
        private Integer growth;

        //剩余抽奖次数
        @TableField("luckey_count")
        private Integer luckeyCount;

        //历史积分数量
        @TableField("history_integration")
        private Integer historyIntegration;

        
        @TableField("gmt_create")
        private Date gmtCreate;

        
        @TableField("gmt_modified")
        private Date gmtModified;


        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
        public Long getMemberLevelId() {
            return memberLevelId;
        }

        public void setMemberLevelId(Long memberLevelId) {
            this.memberLevelId = memberLevelId;
        }
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username == null ? null : username.trim();
        }
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password == null ? null : password.trim();
        }
        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname == null ? null : nickname.trim();
        }
        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone == null ? null : phone.trim();
        }
        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }
        public Date getCreateTime() {
            return createTime;
        }

        public void setCreateTime(Date createTime) {
            this.createTime = createTime;
        }
        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon == null ? null : icon.trim();
        }
        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }
        public Date getBirthday() {
            return birthday;
        }

        public void setBirthday(Date birthday) {
            this.birthday = birthday;
        }
        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city == null ? null : city.trim();
        }
        public String getJob() {
            return job;
        }

        public void setJob(String job) {
            this.job = job == null ? null : job.trim();
        }
        public String getPersonalizedSignature() {
            return personalizedSignature;
        }

        public void setPersonalizedSignature(String personalizedSignature) {
            this.personalizedSignature = personalizedSignature == null ? null : personalizedSignature.trim();
        }
        public Integer getSourceType() {
            return sourceType;
        }

        public void setSourceType(Integer sourceType) {
            this.sourceType = sourceType;
        }
        public Integer getIntegration() {
            return integration;
        }

        public void setIntegration(Integer integration) {
            this.integration = integration;
        }
        public Integer getGrowth() {
            return growth;
        }

        public void setGrowth(Integer growth) {
            this.growth = growth;
        }
        public Integer getLuckeyCount() {
            return luckeyCount;
        }

        public void setLuckeyCount(Integer luckeyCount) {
            this.luckeyCount = luckeyCount;
        }
        public Integer getHistoryIntegration() {
            return historyIntegration;
        }

        public void setHistoryIntegration(Integer historyIntegration) {
            this.historyIntegration = historyIntegration;
        }
        public Date getGmtCreate() {
            return gmtCreate;
        }

        public void setGmtCreate(Date gmtCreate) {
            this.gmtCreate = gmtCreate;
        }
        public Date getGmtModified() {
            return gmtModified;
        }

        public void setGmtModified(Date gmtModified) {
            this.gmtModified = gmtModified;
        }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("pojo.UmsMember ").append("[");
                sb.append(", id = ").append(id);
                sb.append(", memberLevelId = ").append(memberLevelId);
                sb.append(", username = ").append(username);
                sb.append(", password = ").append(password);
                sb.append(", nickname = ").append(nickname);
                sb.append(", phone = ").append(phone);
                sb.append(", status = ").append(status);
                sb.append(", createTime = ").append(createTime);
                sb.append(", icon = ").append(icon);
                sb.append(", gender = ").append(gender);
                sb.append(", birthday = ").append(birthday);
                sb.append(", city = ").append(city);
                sb.append(", job = ").append(job);
                sb.append(", personalizedSignature = ").append(personalizedSignature);
                sb.append(", sourceType = ").append(sourceType);
                sb.append(", integration = ").append(integration);
                sb.append(", growth = ").append(growth);
                sb.append(", luckeyCount = ").append(luckeyCount);
                sb.append(", historyIntegration = ").append(historyIntegration);
                sb.append(", gmtCreate = ").append(gmtCreate);
                sb.append(", gmtModified = ").append(gmtModified);
        sb.append("]");
        return sb.toString();
    }
}