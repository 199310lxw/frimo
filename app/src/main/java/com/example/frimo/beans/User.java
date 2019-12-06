package com.example.frimo.beans;

import cn.bmob.v3.BmobUser;

public class User extends BmobUser {
    //BmobUser默认已经有username、password、email、emailVerified、mobilePhoneNumber、mobilePhoneNumberVerified
    private String headpicaddress;//头像地址
    private String nickName;//昵称
    private String birthday;//生日
    private String weChatNumber;//微信号
    private String qqNumber;//QQ号
    private String address;//住址
    private String home_address;//家庭住址
    private Integer age;//年龄
    private Integer gender;//性别

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHeadpicaddress() {
        return headpicaddress;
    }

    public void setHeadpicaddress(String headpicaddress) {
        this.headpicaddress = headpicaddress;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getWeChatNumber() {
        return weChatNumber;
    }

    public void setWeChatNumber(String weChatNumber) {
        this.weChatNumber = weChatNumber;
    }

    public String getQqNumber() {
        return qqNumber;
    }

    public void setQqNumber(String qqNumber) {
        this.qqNumber = qqNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHome_address() {
        return home_address;
    }

    public void setHome_address(String home_address) {
        this.home_address = home_address;
    }
}
