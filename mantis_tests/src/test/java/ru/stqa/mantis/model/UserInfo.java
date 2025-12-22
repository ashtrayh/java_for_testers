package ru.stqa.mantis.model;

public record UserInfo (String username, String realname, String email) {
    public UserInfo() {
        this("", "", "");
    }

    public UserInfo withUsername(String username) {
        return new UserInfo(username, this.realname, this.email);
    }

    public UserInfo withRealname(String realname) {
        return new UserInfo(this.username, realname, this.email);
    }

    public UserInfo withEmail(String email) {
        return new UserInfo(this.username, this.realname, email);
    }
}
