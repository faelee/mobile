package com.liyeefay.thedenimalz;

public class Member {
    private String Name, Denimal, Nickname, Position, Birthday, MBTI;

    public Member()
    {
        Name = "";
        Position = "";
        Nickname = "";
        Denimal = "";
        Birthday = "";
        MBTI = "";
    }
    public Member(String n, String d, String nk, String p, String b, String m)
    {
        Name = n;
        Position = p;
        Nickname = nk;
        Denimal = d;
        Birthday = b;
        MBTI = m;
    }

    public String toString(){
        return Name + "\nDenimal: " + Denimal + "\nNickname: " + Nickname + "\nPosition: " + Position + "\nBirthday: " + Birthday + "\nMBTI: " + MBTI;
    }
}
