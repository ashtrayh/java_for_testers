package ru.stqa.mantis.model;

public record UserInfoForApi (String username, String email) {
    public UserInfoForApi() {
        this("","");
    }

    public UserInfoForApi withUsername(String username) {
        return new UserInfoForApi(username, this.email);
    }

    public UserInfoForApi withEmail(String email) {
        return new UserInfoForApi(this.username, email);
    }
}

