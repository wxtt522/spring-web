package study.entity;

import java.util.Date;

public class Person {

    private String PersonId;
    private String LoginName;
    private String NiChen;
    private String PassWord;
    private Date AddTime;
    private int PersonType;
    private int IsDelete;

    public Person() {
    }

    public Person(String loginName, String passWord, Date addTime) {
        this.LoginName = loginName;
        this.PassWord = passWord;
        this.AddTime = addTime;
    }

    public String getPersonId() {
        return PersonId;
    }

    public void setPersonId(String personId) {
        PersonId = personId;
    }

    public String getLoginName() {
        return LoginName;
    }

    public void setLoginName(String loginName) {
        LoginName = loginName;
    }

    public String getNiChen() {
        return NiChen;
    }

    public void setNiChen(String niChen) {
        NiChen = niChen;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public Date getAddTime() {
        return AddTime;
    }

    public void setAddTime(Date addTime) {
        AddTime = addTime;
    }

    public int getPersonType() {
        return PersonType;
    }

    public void setPersonType(int personType) {
        PersonType = personType;
    }

    public int getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(int isDelete) {
        IsDelete = isDelete;
    }

    @Override
    public String toString() {
        return "Person{" +
                "PersonId='" + PersonId + '\'' +
                ", LoginName='" + LoginName + '\'' +
                ", NiChen='" + NiChen + '\'' +
                ", PassWord='" + PassWord + '\'' +
                ", AddTime=" + AddTime +
                ", PersonType=" + PersonType +
                ", IsDelete=" + IsDelete +
                '}';
    }
}
