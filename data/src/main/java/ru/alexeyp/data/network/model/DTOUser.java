package ru.alexeyp.data.network.model;

import com.google.gson.annotations.SerializedName;

public class DTOUser {

    public String login;
    public long id;

    @SerializedName("avatar_url")
    public String avatar;
}
