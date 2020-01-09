package com.example.frimo.beans;

public class Data {
    //BmobUser默认已经有username、password、email、emailVerified、mobilePhoneNumber、mobilePhoneNumberVerified
    private String HeadPicAddress;//头像地址
    private String NickName;//昵称
    private String Birthday;//生日
    private String WeChatNumber;//微信号
    private String QQNumber;//QQ号
    private String City;//城市
    private String HomeTown;//家庭住址
    private Integer Age;//年龄
    private Integer Gender;//性别
    private String UserName;//用户名
    private String PhoneNumber;//手机号
    private String PassWord;//密码
    private String  MaritalStatus;//婚姻状况

    public String getHeadPicAddress() {
        return HeadPicAddress;
    }

    public void setHeadPicAddress(String headPicAddress) {
        HeadPicAddress = headPicAddress;
    }

    public void setHomeTown(String homeTown) {
        HomeTown = homeTown;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }



    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getWeChatNumber() {
        return WeChatNumber;
    }

    public void setWeChatNumber(String weChatNumber) {
        WeChatNumber = weChatNumber;
    }

    public String getQQNumber() {
        return QQNumber;
    }

    public void setQQNumber(String QQNumber) {
        this.QQNumber = QQNumber;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getHomeTown() {
        return HomeTown;
    }

    public void setHometown(String homeTown) {
        HomeTown = homeTown;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer age) {
        Age = age;
    }

    public Integer getGender() {
        return Gender;
    }

    public void setGender(Integer gender) {
        Gender = gender;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public String getMaritalStatus() {
        return MaritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        MaritalStatus = maritalStatus;
    }
}
